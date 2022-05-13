package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class RulesController {
    @FXML
    private Button okButton;

    @FXML
    void clickOk() {
        Stage stage = (Stage) okButton.getScene().getWindow();
        stage.close();
    }
}
