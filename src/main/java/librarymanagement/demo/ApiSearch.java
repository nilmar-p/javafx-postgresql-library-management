package librarymanagement.demo;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ApiSearch {
    public static void showApiSearchModal() {
        try {
            FXMLLoader loader = new FXMLLoader(ApiSearch.class.getResource("apiSearchModal.fxml"));
            Parent root = loader.load();

            Stage modalStage = new Stage();
            modalStage.setTitle("Pesquisar livro na API");
            modalStage.setResizable(false);
            modalStage.setScene(new Scene(root));
            modalStage.initModality(Modality.APPLICATION_MODAL);
            modalStage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
