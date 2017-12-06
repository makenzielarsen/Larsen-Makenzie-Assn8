package cs2410.view;

import cs2410.model.Cell;
import cs2410.model.MineField;
import cs2410.model.Scoreboard;

import java.util.ArrayList;
import java.util.Collections;

public class View {
    Scoreboard scoreboard = new Scoreboard();
    MineField mineField = new MineField(20,20,0.25);
}
