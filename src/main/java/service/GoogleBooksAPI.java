package service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Book;

import javax.swing.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GoogleBooksAPI {

    //Requisita à API e retorna a resposta em formato JSON
    public static String searchBooks(String query) throws Exception {
        String urlString = "https://www.googleapis.com/books/v1/volumes?q=" + query.trim().replace(" ", "+");
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        Scanner scanner = new Scanner(connection.getInputStream());
        StringBuilder response = new StringBuilder();

        while (scanner.hasNext()) {
            response.append(scanner.nextLine());
        }

        scanner.close();
        return response.toString();
    }

    //Metodo para exibir os resultados dos livros
    public static List<Book> displayBooks(String jsonResponse) {
        List<Book> bookList = new ArrayList<>();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonResponse);
            JsonNode itemsNode = rootNode.path("items");

            if (itemsNode.isArray()) {
                for (JsonNode bookNode : itemsNode) {
                    JsonNode volumeInfo = bookNode.path("volumeInfo");
                    String title = volumeInfo.path("title").asText();
                    String authors = volumeInfo.path("authors").isArray() ?
                            volumeInfo.path("authors").toString().replaceAll("[\\[\\]\"]", "") : "Desconhecido";
                    String year = volumeInfo.path("publishedDate").asText().isEmpty() ?
                            "Desconhecido" : volumeInfo.path("publishedDate").asText().substring(0, 4);

                    bookList.add(new Book(title, authors, year));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //for (Book book : bookList) {
        //  System.out.println(String.format("Resultado: %s - %s - %s", book.getAuthors(), book.getYear(), book.getTitle()));
        //}
        if (bookList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Sem resultados para a pesquisa!");
        }
        return bookList;
    }
}