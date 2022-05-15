package sample;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class View {

    private final Field field;
    private final Map<Byte, Image> picturesMap = new HashMap<>();
    private final ImageView[][] images = new ImageView[Field.SIZE][Field.SIZE];

    public View(Field field) {
        this.field = field;
        initializePicturesMap();
        initializeImages();
    }

    private void initializePicturesMap(){
        for (byte i = 1; i < Field.SIZE * Field.SIZE; i++) {
            try {
                picturesMap.put(i, new Image(new FileInputStream("src/sample/sample.textures/" + i + ".png")));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void initializeImages(){
        for (int i = 0; i < Field.SIZE; i++) {
            for (int j = 0; j < Field.SIZE; j++) {
                ImageView newImage = new ImageView();
                newImage.setImage(picturesMap.get(field.getValue(i, j)));
                newImage.setFitWidth(70);
                newImage.setFitHeight(70);
                images[i][j] = newImage;
            }
        }
    }

    public ImageView[][] getImages() {
        return images;
    }

    public void updateUI(byte[][] temp) {
        for (int i = 0; i < Field.SIZE; i++) {
            for (int j = 0; j < Field.SIZE; j++) {
                images[i][j].setImage(picturesMap.get(temp[i][j]));
            }
        }

        if (Arrays.deepEquals(temp, Field.controlArray)) {
            showWinMessage();
        }
    }

    private void showWinMessage(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("WIN");
        alert.setHeaderText("Congratulations, you have completed the puzzle!!!");
        alert.setContentText("Score: " + field.getScore());
        alert.showAndWait();
    }
}
