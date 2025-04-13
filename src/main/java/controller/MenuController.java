package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import librarymanagement.demo.ApiSearch;


public class MenuController {
    @FXML
    private Button menuQuitButton;

    @FXML
    protected void onMenuQuitButtonClick() {
        Stage stage = (Stage) menuQuitButton.getScene().getWindow(); //pego a janela onde o menuQuitButton esta, e transformo-a em stage,
                                                                    //podendo usar funcoes de stage nela, como o .close()
        stage.close();
    }

    @FXML
    private Button menuSearchApiButton;

    @FXML
    protected void onMenuSearchApiButtonClick(){
        ApiSearch.showApiSearchModal();
    }
}