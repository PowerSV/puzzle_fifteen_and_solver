package sample;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.solver.Solver;
import sample.solver.SolverState;

public class FieldController {

    private final Field field = new Field();
    private final View view = new View(field);
    private final Deque<SolverState> solverSteps = new ArrayDeque<>();

    @FXML
    private GridPane gridPane;

    @FXML
    private Label score;

    private boolean isShow = false;

    @FXML
    void click() throws IOException {
        if (!isShow) {
            isShow = true;
            Parent root = FXMLLoader.load(getClass().getResource("textures/rules.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.showAndWait();
            isShow = false;
        }
    }

    @FXML
    void onKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case W:
                if (field.moveUp()) {
                    updateScene();
                }
                break;
            case A:
                if (field.moveLeft()) {
                    updateScene();
                }
                break;
            case S:
                if (field.moveDown()) {
                    updateScene();
                }
                break;
            case D:
                if (field.moveRight()) {
                    updateScene();
                }
                break;
            case R:
                field.shuffle();
                updateScene();
                break;
            case E:
                System.out.println("run solver ");
                solverSteps.clear();
                SolverState startField = new SolverState(field.getField());
                Solver solver = new Solver(startField);
                solverSteps.addAll(solver.getResult());
                view.showSolverMessage(solverSteps);
                break;
            case BACK_SPACE:
                System.out.println("BACKSPACE");
                if (!solverSteps.isEmpty()) {
                    SolverState current = solverSteps.pollLast();
                    field.setField(current.getField());
                    updateScene();
                }
                break;
        }
    }

    private void updateScene() {
        score.setText(String.valueOf(field.getScore()));
        view.updateUI(field.getField());
    }

    @FXML
    private void initialize() {
        fillGridPane();
        score.setText("0");
    }

    private void fillGridPane() {
        ImageView[][] images = view.getImages();
        for (int i = 0; i < Field.SIZE; i++) {
            for (int j = 0; j < Field.SIZE; j++) {
                gridPane.add(images[i][j], i, j);
            }
        }
    }
}