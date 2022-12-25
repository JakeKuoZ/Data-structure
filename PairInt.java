package Maze;

/**
 * @author Kuo Zhang
 * @date 2022/10/28 16:17
 * @description
 */
public class PairInt implements Cloneable {
    //field
    private int x;
    private int y;
    //constructor
    public PairInt() {
    }
    //constructor
    public PairInt(int x, int y) {
        this.x = x;
        this.y = y;
    }
    //getters and setters
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean equals(Object p) {

        if(!(p instanceof PairInt)) {
            return false;
        }

        else {
            PairInt pairint= (PairInt)p;
            return this.x==pairint.x && this.y==pairint.y;

        }

    }
    public String toString() {

        return "[" + x + "," + y +"]";
    }

    public PairInt copy() {
        return new PairInt(x,y);

    }
}
