package cs2410.controller;

import cs2410.view.Cell;

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
        initializeState();
    }

    private void randomizeBombs() {
        for(int i = 0; i < totalBombs; i++) {
            grid.add(new Cell(true));
        }
        for(int i = 0; i < totalCells - totalBombs; i++) {
            grid.add(new Cell(false));
        }

        Collections.shuffle(grid);
    }

    private void initializeState() {
        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                Cell cell = getCell(i, j);
                cell.setX(i);
                cell.setY(j);
                cell.setNeighboringBombs(numberOfNeighboringMines(i, j));
            }
        }
    }

    private int getIndex(int x, int y) {
        return x + (y * width);
    }

    public Cell getCell(int x, int y) {
        int index = getIndex(x,y);
        if(index < 0 || index >= grid.size()) {
            return null;
        }
        if(x < 0 || y < 0 || x > width - 1 || y >  height - 1) {
            return null;
        }
        return grid.get(index);
    }

    private int numberOfNeighboringMines(int x, int y) {
        int sum = 0;
        Cell cell = getCell(x - 1, y - 1);
        if(cell != null && cell.isBomb()) {
            sum += 1;
        }

        cell = getCell(x, y - 1);
        if(cell != null && cell.isBomb()) {
            sum += 1;
        }

        cell = getCell(x + 1, y - 1);
        if(cell != null && cell.isBomb()) {
            sum += 1;
        }

        cell = getCell(x - 1 , y);
        if(cell != null && cell.isBomb()) {
            sum += 1;
        }

        cell = getCell(x + 1, y);
        if(cell != null && cell.isBomb()) {
            sum += 1;
        }

        cell = getCell(x - 1, y + 1);
        if(cell != null && cell.isBomb()) {
            sum += 1;
        }

        cell = getCell(x, y + 1);
        if(cell != null && cell.isBomb()) {
            sum += 1;
        }

        cell = getCell(x + 1, y + 1);
        if(cell != null && cell.isBomb()) {
            sum += 1;
        }

        return sum;
    }

    public boolean hasLost() {
        for(Cell cell: grid) {
            if(cell.isBomb() && cell.isClicked()) {
                return true;
            }
        }
        return false;
    }

    public boolean hasWon() {
        for(Cell cell: grid) {
            if(!cell.isBomb() && !cell.isClicked()) {
                return false;
            }
        }
        for(Cell cell: grid) {
            if(cell.isBomb()) {
                cell.setFlagged(true);
            }
        }
        return true;
    }

    public int numberBombsLeft() {
        int totalFlags = 0;
        for(Cell cell: grid) {
            if(cell.isFlagged()) {
                totalFlags += 1;
            }
        }
        return totalBombs - totalFlags;
    }

    public void click(int x, int y) {
        Cell cell = getCell(x, y);
        if(cell.isFlagged() || cell.isQuestionable() || cell.isClicked()) {
            return;
        }
        cell.setClicked(true);

        if(cell.getNeighboringBombs() == 0) {
            if (x > 0 && y > 0) {
                click(x - 1, y - 1);
            }
            if (y > 0) {
                click(x, y - 1);
            }
            if (x < width - 1 && y > 0) {
                click(x + 1, y - 1);
            }
            if (x > 0) {
                click(x - 1, y);
            }
            if (x < width - 1) {
                click(x + 1, y);
            }
            if (x > 0 && y < height - 1) {
                click(x - 1, y + 1);
            }
            if (y < height - 1) {
                click(x, y + 1);
            }
            if (x < width - 1 && y < height - 1) {
                click(x + 1, y + 1);
            }
        }
    }

    public void endGame() {
        for(Cell cell : grid) {
            cell.updateCell(true);
        }
    }

    public void refreshCells() {
        boolean gameOver = false;
        if(hasWon()) {
            gameOver = true;
        } else if(hasLost()) {
            gameOver = true;
        }

        for(Cell cell : grid) {
            cell.updateCell(gameOver);
        }
    }
}
