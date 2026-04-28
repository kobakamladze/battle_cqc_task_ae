package utils;

public class CellCoordinate {

    public int y;
    public int x;

    public CellCoordinate(int x, int y) {
        this.y = y;
        this.x = x;
    }

    @Override
    public String toString() {
        return "Y = " + y + " ,X = " + x;
    }
}
