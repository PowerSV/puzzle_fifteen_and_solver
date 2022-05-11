package sample;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Controller {

    private Stage rules;

    @FXML
    private GridPane gridPane;

    @FXML
    private Label score;

    @FXML
    private Button controlButton;

    @FXML
    private Button okRules;

    @FXML
    void click() throws IOException {
        rules.showAndWait();
    }

    @FXML
    private void initialize() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("rules.fxml"));
        rules = new Stage();
        rules.setScene(new Scene(root));
    }

}