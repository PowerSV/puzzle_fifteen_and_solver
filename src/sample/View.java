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
    private final Map<Integer, Image> picturesMap = new HashMap<>();
    private final ImageView[][] images = new ImageView[Field.SIZE][Field.SIZE];
    private final int[][] controlArray = new int[][]{
            {1, 5, 9, 13},
            {2, 6, 10, 14},
            {3, 7, 11, 15},
            {4, 8, 12, 0}};

    public View(Field field) {
        this.field = field;
        initializePicturesMap();
        initializeImages();
    }

    private void initializePicturesMap(){
        try {
            picturesMap.put(0, new Image(new FileInputStream("src/textures/0.png")));
            picturesMap.put(1, new Image(new FileInputStream("src/textures/1.png")));
            picturesMap.put(2, new Image(new FileInputStream("src/textures/2.png")));
            picturesMap.put(3, new Image(new FileInputStream("src/textures/3.png")));
            picturesMap.put(4, new Image(new FileInputStream("src/textures/4.png")));
            picturesMap.put(5, new Image(new FileInputStream("src/textures/5.png")));
            picturesMap.put(6, new Image(new FileInputStream("src/textures/6.png")));
            picturesMap.put(7, new Image(new FileInputStream("src/textures/7.png")));
            picturesMap.put(8, new Image(new FileInputStream("src/textures/8.png")));
            picturesMap.put(9, new Image(new FileInputStream("src/textures/9.png")));
            picturesMap.put(10, new Image(new FileInputStream("src/textures/10.png")));
            picturesMap.put(11, new Image(new FileInputStream("src/textures/11.png")));
            picturesMap.put(12, new Image(new FileInputStream("src/textures/12.png")));
            picturesMap.put(13, new Image(new FileInputStream("src/textures/13.png")));
            picturesMap.put(14, new Image(new FileInputStream("src/textures/14.png")));
            picturesMap.put(15, new Image(new FileInputStream("src/textures/15.png")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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

    public void updateUI(int[][] temp) {
        for (int i = 0; i < Field.SIZE; i++) {
            for (int j = 0; j < Field.SIZE; j++) {
                images[i][j].setImage(picturesMap.get(temp[i][j]));
            }
        }

        if (Arrays.deepEquals(temp, controlArray)) {
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
