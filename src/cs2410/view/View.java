package cs2410.view;

import java.util.ArrayList;
import java.util.Collections;

public class View {
    Scoreboard scoreboard = new Scoreboard();
    private ArrayList<Cell> grid = new ArrayList<>();
    private int total_cells = 400;
    private int total_bombs = total_cells / 4;

    void initialize() {
        for(int i = 0; i < total_bombs; i++) {
            Cell temp = new Cell(true);
            grid.add(temp);
        }
        for(int i = 0; i < total_cells - total_bombs; i++) {
            Cell temp = new Cell(false);
            grid.add(temp);
        }

        Collections.shuffle(grid);
    }
}
