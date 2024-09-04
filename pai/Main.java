
public class Main {
    public static void main(String[] args) {
        Polygon polygon = new Polygon();
        Rectangle square = new Rectangle();
    }    
}

class Polygon {

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

class Rectangle extends Polygon{
    public double length = 1;
    public double height = 1;

    Rectangle() {
        super("Rectangle", 4);
    }

    Rectangle(String name, double length, double height) {
        super(name, 4);
        this.length = length;
        this.height = height; 
    }

    public double getPerimeter() {
        return 2 * length + 2 * height;
    }

    public double getArea() {
        return length * height;
    }
}

class Triangle extends Polygon {

    private double side1 = 1;
    private double side2 = 1;
    private double side3 = 1;
    private double base = 1;
    private double height = 1;

    Triangle() {
        super("", 3);
    }

    Triangle(String name, double side1, double side2, double side3, double base, double height) {
        super(name, 3);
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
        this.base = base;
        this.height = height;
    }

    public double getPerimeter() {
        return side1 + side2 + side3;
    }

    public double getArea() {
        return height * (base/2);
    }
}