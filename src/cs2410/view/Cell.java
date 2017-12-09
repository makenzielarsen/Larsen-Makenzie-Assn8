package cs2410.view;

import cs2410.ImageManager;
import javafx.scene.control.ToggleButton;

public class Cell extends ToggleButton {
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

        setGraphic(ImageManager.emptyImageView());
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
            setGraphic(ImageManager.bombImageView());
        } else if(isFlagged()) {
            setGraphic(ImageManager.flagImageView());
        } else if(isQuestionable()) {
            setGraphic(ImageManager.questionImageView());
        } else if(isClicked() && !isFlagged && !isQuestionable) {
            if(neighboringBombs == 1) {
                setGraphic(ImageManager.oneImageView());
            } else if(neighboringBombs == 2) {
                setGraphic(ImageManager.twoImageView());
            } else if(neighboringBombs == 3) {
                setGraphic(ImageManager.threeImageView());
            } else if(neighboringBombs == 4) {
                setGraphic(ImageManager.fourImageView());
            } else if(neighboringBombs == 5) {
                setGraphic(ImageManager.fiveImageView());
            } else if(neighboringBombs == 6) {
                setGraphic(ImageManager.sixImageView());
            } else if(neighboringBombs == 7) {
                setGraphic(ImageManager.sevenImageView());
            } else if(neighboringBombs == 8) {
                setGraphic(ImageManager.eightImageView());
            }
        }

        if(gameIsOver) {
            if(isBomb() && !isFlagged()) {
                setGraphic(ImageManager.bombImageView());
                setStyle("-fx-background-color: red");
            } else if(isBomb() && isFlagged()) {
                setGraphic(ImageManager.bombImageView());
                setStyle("-fx-background-color: green");
            } else if(!isBomb() && isFlagged()) {
                setStyle("-fx-background-color: yellow");
            } else {
                setGraphic(ImageManager.emptyImageView());
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
        setGraphic(ImageManager.emptyImageView());
    }
}
