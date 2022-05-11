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
import javafx.stage.Stage;

public class Controller {


    @FXML
    private GridPane gridPane;

    @FXML
    private Label score;

    @FXML
    private Button controlButton;

    private boolean isPressed = false;

    @FXML
    private Button okButton;

    @FXML
    void clickOk() {
        Stage stage = (Stage) okButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void click() throws IOException {
        if (!isPressed) {
            isPressed = true;
            Parent root = FXMLLoader.load(getClass().getResource("rules.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
            isPressed = false;
        }
    }

    @FXML
    private void initialize() {
    }

}