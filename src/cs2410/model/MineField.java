package cs2410.model;

import java.util.ArrayList;
import java.util.Collections;

public class MineField {
    private ArrayList<Cell> grid = new ArrayList<>();
    private int height;
    private int width;
    private int totalCells;
    private int totalBombs;

    public MineField(int width, int height, double bombPercentage) {
        this.height = height;
        this.width = width;
        totalCells = width * height;
        totalBombs = (int)((double)totalCells * bombPercentage);
        randomizeBombs();
    }

    void randomizeBombs() {
        for(int i = 0; i < totalBombs; i++) {
            grid.add(new Cell(true));
        }
        for(int i = 0; i < totalCells - totalBombs; i++) {
            grid.add(new Cell(false));
        }

        Collections.shuffle(grid);
    }

    private int getIndex(int x, int y) {
        return x + (y * width);
    }

    Cell getCell(int x, int y) {
        return grid.get(getIndex(x, y));
    }

    int numberOfNeighboringMines(int x, int y) {
        // check all eight neighbors to see if they are mines
        return 0;
    }

    boolean hasLost() {
        for(Cell cell: grid) {
            if(cell.isBomb() && cell.isClicked()) {
                return true;
            }
        }
        return false;
    }

    boolean hasWon() {
        for(Cell cell: grid) {
            if(!cell.isBomb() && !cell.isClicked()) {
                return false;
            }
        }
        return true;
    }

    int numberBombsLeft() {
        int totalFlags = 0;
        for(Cell cell: grid) {
            if(cell.isFlagged()) {
                totalFlags += 1;
            }
        }
        return totalBombs - totalFlags;
    }
}
