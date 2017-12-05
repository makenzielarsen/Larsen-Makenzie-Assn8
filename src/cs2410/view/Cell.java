package cs2410.view;

import javafx.scene.control.Button;

public class Cell extends Button {
    private boolean isBomb = false;
    private int x = 0;
    private int y = 0;

    public boolean getBombStatus() {
        return isBomb;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int X) {
        x = X;
    }

    public void setY(int Y) {
        y = Y;
    }

    public void setBomb(boolean bomb) {
        isBomb = bomb;
    }

    Cell(boolean bomb) {
        isBomb = bomb;
    }
}
