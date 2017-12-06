package cs2410.model;

public class Cell {
    private boolean isBomb = false;
    private boolean isFlagged;
    private boolean isClicked;
    private boolean isQuestionable;

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

    Cell(boolean bomb) {
        isBomb = bomb;
        isFlagged = false;
        isClicked = false;
        isQuestionable = false;
    }
}
