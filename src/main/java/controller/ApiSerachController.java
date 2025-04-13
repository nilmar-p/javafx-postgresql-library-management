package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import model.Book;
import service.GoogleBooksAPI;

import javax.swing.*;
import java.util.List;

public class ApiSerachController {
    @FXML
    private Button searchButton;

    @FXML
    private TextField searchField;

    @FXML
    private TableView<Book> bookTableView;

    @FXML
    private TableColumn<Book, String> titleColumn;

    @FXML
    private TableColumn<Book, String> authorsColumn;

    @FXML
    private TableColumn<Book, String> yearColumn;

    @FXML
    private VBox vBox;

    private ContextMenu currentContextMenu; // Adiciona uma variável para armazenar o ContextMenu atual.


    @FXML
    protected void onSearchButtonClick() {
        String search = searchField.getText();

        String response = null;

        try {
            response = GoogleBooksAPI.searchBooks(search);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Pesquisa inválida!", "Erro!", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }

        List<Book> bookList = GoogleBooksAPI.displayBooks(response);

        for (Book book : bookList) {
            System.out.println("Título: " + book.getTitle() + ", Autores: " + book.getAuthors() + ", Ano: " + book.getYear());
        }

        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorsColumn.setCellValueFactory(new PropertyValueFactory<>("authors"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));

        ObservableList<Book> observableBookList = FXCollections.observableArrayList(bookList);

        bookTableView.setItems(observableBookList);

        vBox.setVgrow(bookTableView, Priority.ALWAYS);
    }

    @FXML
    protected void onRightClickInTable(MouseEvent event) {
        if (event.getButton() == MouseButton.SECONDARY) {
            Book selectedBook = bookTableView.getSelectionModel().getSelectedItem();

            if (selectedBook != null) {
                // Fecha o ContextMenu atual, se existir.
                if (currentContextMenu != null) {
                    currentContextMenu.hide();
                }

                ContextMenu contextMenu = new ContextMenu();
                MenuItem yesItem = new MenuItem("Sim");
                MenuItem noItem = new MenuItem("Não");

                yesItem.setOnAction(actionEvent -> {
                    // Lógica para o botão "Sim"
                    System.out.println("Sim clicado para o livro: " + selectedBook.getTitle());
                    contextMenu.hide();
                });

                noItem.setOnAction(actionEvent -> {
                    // Lógica para o botão "Não"
                    System.out.println("Não clicado para o livro: " + selectedBook.getTitle());
                    contextMenu.hide();
                });

                contextMenu.getItems().addAll(yesItem, noItem);
                contextMenu.show(bookTableView, event.getScreenX(), event.getScreenY());

                currentContextMenu = contextMenu; // Atualiza a referência do ContextMenu atual.
            }
        }
    }

    @FXML
    public void initialize() {
        bookTableView.addEventHandler(MouseEvent.MOUSE_CLICKED, this::onRightClickInTable);
    }

}
