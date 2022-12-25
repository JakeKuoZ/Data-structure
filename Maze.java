package Maze;

import java.util.ArrayList;

/**
 * Class that solves maze problems with backtracking.
 *
 * @author Koffman and Wolfgang
 **/
public class Maze implements GridColors {

    /**
     * The maze
     */
    private final TwoDimGrid maze;

    public Maze(TwoDimGrid m) {
        maze = m;
    }

    /**
     * Wrapper method.
     */
    public boolean findMazePath() {
        return findMazePath(0, 0); // (0, 0) is the start point.
    }

    /**
     * Attempts to find a path through point (x, y).
     *
     * @param x The x-coordinate of current point
     * @param y The y-coordinate of current point
     * @return If a path through (x, y) is found, true;
     * otherwise, false
     * @pre Possible path cells are in BACKGROUND color;
     * barrier cells are in ABNORMAL color.
     * @post If a path is found, all cells on it are set to the
     * PATH color; all cells that were visited but are
     * not on the path are in the TEMPORARY color.
     */
    public boolean findMazePath(int x, int y) {
        // COMPLETE HERE FOR PROBLEM 1
        if (x >= 0 && y >= 0
                && x < maze.getNCols()
                && y < maze.getNRows()
                && maze.getColor(x, y) == NON_BACKGROUND) {
            if (x == (maze.getNCols() - 1)
                    && y == (maze.getNRows() - 1)) {
                maze.recolor(x, y, PATH);
                return true;
            } else {
                maze.recolor(x, y, PATH);
                if (findMazePath(x + 1, y)
                        || findMazePath(x - 1, y)
                        || findMazePath(x, y + 1)
                        || findMazePath(x, y - 1)) {
                    return true;
                } else {
                    maze.recolor(x, y, TEMPORARY);
                    return false;
                }
            }

        }
        return false;
    }

    // ADD METHOD FOR PROBLEM 2 HERE
    public ArrayList<ArrayList<PairInt>> findAllMazePaths(int x, int y) {
        if (findMazePath()) {
            maze.recolor(PATH, NON_BACKGROUND);
            maze.recolor(TEMPORARY, NON_BACKGROUND);
            return findAllMazePathsHelp(0, 0, new ArrayList<PairInt>());
        } else {
            maze.recolor(PATH, NON_BACKGROUND);
            maze.recolor(TEMPORARY, NON_BACKGROUND);
            ArrayList<ArrayList<PairInt>> list = new ArrayList<>();
            ArrayList<PairInt> empty = new ArrayList<PairInt>();
            list.add(empty);
            return list;
        }
    }

    private ArrayList<ArrayList<PairInt>> findAllMazePathsHelp(int x, int y, ArrayList<PairInt> current) {
        if (x < maze.getNCols() && x >= 0 && y < maze.getNRows() && y >= 0 && maze.getColor(x, y).equals(NON_BACKGROUND)) {
            current.add(new PairInt(x, y));
            if (x == (maze.getNCols() - 1) && y == (maze.getNRows() - 1)) {
                ArrayList<ArrayList<PairInt>> answer = new ArrayList<ArrayList<PairInt>>();
                @SuppressWarnings("unchecked")
                ArrayList<PairInt> currentCopy = (ArrayList<PairInt>) current.clone();
                answer.add(currentCopy);
                current.remove(new PairInt(x, y));
                return answer;
            } else {
                maze.recolor(x, y, PATH);
                ArrayList<ArrayList<PairInt>> right = findAllMazePathsHelp(x + 1, y, current);
                ArrayList<ArrayList<PairInt>> left = findAllMazePathsHelp(x - 1, y, current);
                ArrayList<ArrayList<PairInt>> up = findAllMazePathsHelp(x, y + 1, current);
                ArrayList<ArrayList<PairInt>> down = findAllMazePathsHelp(x, y - 1, current);
                ArrayList<ArrayList<PairInt>> answer = new ArrayList<>();
                answer.addAll(right);
                answer.addAll(left);
                answer.addAll(up);
                answer.addAll(down);
                maze.recolor(x, y, NON_BACKGROUND);
                current.remove(new PairInt(x, y));
                return answer;
            }
        } else {
            return new ArrayList<>();
        }
    }

    // ADD METHOD FOR PROBLEM 3 HERE

    public ArrayList<PairInt> findMazePathMin(int x, int y) {
        maze.recolor(PATH, NON_BACKGROUND);
        ArrayList<ArrayList<PairInt>> answer = findAllMazePaths(x, y);
        if (answer.size() != 0) {
            ArrayList<PairInt> min = answer.get(0);
            int minlength = min.size();
            for (int i = 1; i < answer.get(i).size(); i++) {
                if (minlength >= answer.get(i).size()) {
                    min = answer.get(i);
                    minlength = min.size();
                }
            }
            return min;
        } else {
            return new ArrayList<PairInt>();
        }
    }


    /*<exercise chapter="5" section="6" type="programming" number="2">*/
    public void resetTemp() {
        maze.recolor(TEMPORARY, BACKGROUND);
    }
    /*</exercise>*/

    /*<exercise chapter="5" section="6" type="programming" number="3">*/
    public void restore() {
        resetTemp();
        maze.recolor(PATH, BACKGROUND);
        maze.recolor(NON_BACKGROUND, BACKGROUND);
    }
    /*</exercise>*/
}
/*</listing>*/
