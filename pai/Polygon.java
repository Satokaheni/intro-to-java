package pai;

public class Polygon {

    private String name;
    private int numSides;

    Polygon() {
        name = "";
        numSides = 0;
    }

    Polygon(String name, int numSides) {
        this.name = name;
        this.numSides = numSides;
    }

    // accessor methods
    public String getName() {
        return name;
    }

    public int getNumSides() {
        return numSides;
    }
}