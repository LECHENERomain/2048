package fr.univartois.butinfo.ihm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.Random;

public class DeuxMilleQuaranteHuitController {

    @FXML
    private Button restartButton;

    @FXML
    private Button ShowScore;

    @FXML
    private Button downButton;

    @FXML
    private Button leftButton;

    @FXML
    private Button rightButton;

    @FXML
    private Label score;

    @FXML
    private Button upButton;

    @FXML
    private GridPane gridNumbers;

    private Label[][] tileLabels=new Label[4][4];

    @FXML
    void initialize(){
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                Label label=new Label();
                gridNumbers.add(label,j,i);
                tileLabels[i][j]=label;
            }
        }
        generateRandomTile();
        generateRandomTile();
        generateRandomTile();
        updateGrid();
    }
    @FXML
    void onDownButtonClick(ActionEvent event) {
        grid.moveDown();
        generateRandomTile();
        updateGrid();
    }

    @FXML
    void onLeftButtonClick(ActionEvent event) {
        grid.moveLeft();
        generateRandomTile();
        updateGrid();
    }

    @FXML
    void onRestartButtonClick(ActionEvent event) {
        grid.clear();
        generateRandomTile();
        generateRandomTile();
        generateRandomTile();
        downButton.setDisable(false);
        leftButton.setDisable(false);
        rightButton.setDisable(false);
        upButton.setDisable(false);
        restartButton.setDisable(false);
        updateGrid();
    }

    @FXML
    void onRightButtonClick(ActionEvent event) {
        grid.moveRight();
        generateRandomTile();
        updateGrid();
    }

    @FXML
    void onShowScoreButtonClick(ActionEvent event) {
        if(score.isVisible()){
            score.setVisible(false);
        }else{
            score.setVisible(true);
        }
    }

    @FXML
    void onUpButtonClick(ActionEvent event) {
        grid.moveUp();
        generateRandomTile();
        updateGrid();
    }

    void generateRandomTile(){
        if(rand.nextDouble(1)<90){
            grid.availableTiles().get(rand.nextInt(5)).setValue(2);
        }else{
            grid.availableTiles().get(rand.nextInt(5)).setValue(4);
        }
    }

    void updateGrid(){
        int max=0;
        for(int i=0;i<tileLabels.length;i++){
            for(int j=0;j<tileLabels[i].length;j++){
                tileLabels[i][j].setText(grid.get(i,j).getValue()+ "");
                tileLabels[i][j].setBackground(computeBackground(grid.get(i,j).getValue()));
                if(grid.get(i,j).getValue()>max) max=grid.get(i,j).getValue();
            }
        }

        score.setText("Score : "+max);

        if(grid.isBlocked()){
            downButton.setDisable(true);
            leftButton.setDisable(true);
            rightButton.setDisable(true);
            upButton.setDisable(true);
            restartButton.setDisable(false);
        }
    }
    Random rand=new Random();
    Grid grid=new Grid();

    private static Background computeBackground(int value) {
        // On calcule d'abord à quelle puissance de 2 la valeur correspond
        int powerOf2=0;
        while((value>>powerOf2)>0){
            powerOf2++;
        }

        // On détermine ensuite à quel pourcentage de la valeur maximale elle correspond
        double maxPower=1. + Grid.SIZE*Grid.SIZE;
        double pct = powerOf2/maxPower;

        // On créé enfin l'arrière plan à partir de ce pourcentage

        return new Background(
                new BackgroundFill(
                        new Color(1-pct, 1-pct, 1-pct, 1),
                        CornerRadii.EMPTY,
                        Insets.EMPTY));
    }


}
