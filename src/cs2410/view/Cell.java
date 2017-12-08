package cs2410.view;

import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;

import java.io.File;

public class Cell extends ToggleButton {
    private ImageView emptyImageView = new ImageView(new Image(new File("images/empty.png").toURI().toString()));
    private ImageView flagImageView = new ImageView(new Image(new File("images/flag.png").toURI().toString()));
    private ImageView questionImageView = new ImageView(new Image(new File("images/question.png").toURI().toString()));
    private ImageView bombImageView = new ImageView(new Image(new File("images/bomb.png").toURI().toString()));
    private ImageView oneImageView = new ImageView(new Image(new File("images/one.png").toURI().toString()));
    private ImageView twoImageView = new ImageView(new Image(new File("images/two.png").toURI().toString()));
    private ImageView threeImageView = new ImageView(new Image(new File("images/three.png").toURI().toString()));
    private ImageView fourImageView = new ImageView(new Image(new File("images/four.png").toURI().toString()));
    private ImageView fiveImageView = new ImageView(new Image(new File("images/five.png").toURI().toString()));
    private ImageView sixImageView = new ImageView(new Image(new File("images/six.png").toURI().toString()));
    private ImageView sevenImageView = new ImageView(new Image(new File("images/seven.png").toURI().toString()));
    private ImageView eightImageView = new ImageView(new Image(new File("images/eight.png").toURI().toString()));
    private boolean isBomb;
    private boolean isFlagged;
    private boolean isClicked;
    private boolean isQuestionable;
    private boolean isMuted;
    private int x;
    private int y;
    private int neighboringBombs;

    public Cell(boolean bomb) {
        super();
        isBomb = bomb;
        isFlagged = false;
        isClicked = false;
        isQuestionable = false;
        isMuted = false;

        flagImageView.setFitHeight(10);
        flagImageView.setFitWidth(10);
        flagImageView.setPreserveRatio(true);
        questionImageView.setFitHeight(10);
        questionImageView.setFitWidth(10);
        questionImageView.setPreserveRatio(true);
        bombImageView.setFitHeight(10);
        bombImageView.setFitWidth(10);
        bombImageView.setPreserveRatio(true);
        oneImageView.setFitHeight(10);
        oneImageView.setFitWidth(10);
        oneImageView.setPreserveRatio(true);
        twoImageView.setFitHeight(10);
        twoImageView.setFitWidth(10);
        twoImageView.setPreserveRatio(true);
        threeImageView.setFitHeight(10);
        threeImageView.setFitWidth(10);
        threeImageView.setPreserveRatio(true);
        fourImageView.setFitHeight(10);
        fourImageView.setFitWidth(10);
        fourImageView.setPreserveRatio(true);
        fiveImageView.setFitHeight(10);
        fiveImageView.setFitWidth(10);
        fiveImageView.setPreserveRatio(true);
        sixImageView.setFitHeight(10);
        sixImageView.setFitWidth(10);
        sixImageView.setPreserveRatio(true);
        sevenImageView.setFitHeight(10);
        sevenImageView.setFitWidth(10);
        sevenImageView.setPreserveRatio(true);
        eightImageView.setFitHeight(10);
        eightImageView.setFitWidth(10);
        eightImageView.setPreserveRatio(true);
        emptyImageView.setFitHeight(20);
        emptyImageView.setFitWidth(10);
        emptyImageView.setPreserveRatio(true);

        setGraphic(emptyImageView);
        setPrefSize(20, 20);
    }

    public void setX(int X) {
        x = X;
    }

    public void setY(int Y) {
        y = Y;
    }

    public void setNeighboringBombs(int bombs) {
        neighboringBombs = bombs;
    }

    public int getNeighboringBombs() {
        return neighboringBombs;
    }

    public boolean isBomb() {
        return isBomb;
    }

    public void setFlagged(boolean flagged) {
        isFlagged = flagged;
    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public void setClicked(boolean clicked) {
        isClicked = clicked;
    }

    public boolean isClicked() {
        return isClicked;
    }

    public void setQuestionable(boolean questionable) {
        isQuestionable = questionable;
    }

    public boolean isQuestionable() {
        return isQuestionable;
    }

    public void setMuted(boolean mute) {
        isMuted = mute;
    }

    public boolean getMuted() {
        return isMuted;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void updateCell(Boolean gameIsOver) {
        if(isBomb() && isClicked()) {
            setGraphic(bombImageView);
        } else if(isFlagged()) {
            setGraphic(flagImageView);
        } else if(isQuestionable()) {
            setGraphic(questionImageView);
        } else if(isClicked() && !isFlagged && !isQuestionable) {
            if(neighboringBombs == 1) {
                setGraphic(oneImageView);
            } else if(neighboringBombs == 2) {
                setGraphic(twoImageView);
            } else if(neighboringBombs == 3) {
                setGraphic(threeImageView);
            } else if(neighboringBombs == 4) {
                setGraphic(fourImageView);
            } else if(neighboringBombs == 5) {
                setGraphic(fiveImageView);
            } else if(neighboringBombs == 6) {
                setGraphic(sixImageView);
            } else if(neighboringBombs == 7) {
                setGraphic(sevenImageView);
            } else if(neighboringBombs == 8) {
                setGraphic(eightImageView);
            }
        }

        if(gameIsOver) {
            if(isBomb() && !isFlagged()) {
                setGraphic(bombImageView);
                setStyle("-fx-background-color: red");
            } else if(isBomb() && isFlagged()) {
                setGraphic(bombImageView);
                setStyle("-fx-background-color: green");
            } else if(!isBomb() && isFlagged()) {
                setStyle("-fx-background-color: yellow");
            } else {
                setGraphic(emptyImageView);
            }
            setDisable(true);
        }

        if(isClicked()) {
            setDisable(true);
        }

        if(isFlagged || isQuestionable) {
            setSelected(false);
        }
    }

    public void clearGraphic() {
        setGraphic(emptyImageView);
    }
}
