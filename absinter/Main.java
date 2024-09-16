package absinter;

public class Main {
    public static void main(String[] args) {

    }
}

abstract class Polygon {
    private int numSides = 0;
    private String name = "";

    Polygon() {

    }

    Polygon(int numSides, String name) {
        this.numSides = numSides;
        this.name = name;
    }

    public abstract double getArea();

    public abstract double getPerimeter();
}

class Rectangle extends Polygon implements Comparable<Rectangle>{
    private double width;
    private double height;

    Rectangle() {
        super(4, "Rectangle");
        this.width = 1;
        this.height = 1;
    }

    Rectangle(double width, double height) {
        super(4, "Rectangle");
        this.width = width;
        this.height = height;
    }

    public double getArea() {
        return this.width * this.height;
    }

    public double getPerimeter() {
        return 2 * this.width + 2 * this.height;
    }

    // Compare based on Area
    public int compareTo(Rectangle rect) {
        double areaOne = this.getArea();
        double areaTwo = rect.getArea();

        // Current Object Area is Less
        if (areaOne < areaTwo) {
            return -1;
        }
        // Other Rectangle Object Area is Less
        else if (areaOne > areaTwo) {
            return 1;
        }
        // Areas are the same
        else{
            return 0;
        }
    }
}

abstract class Vehicle {
    int numWheels;
    int gear;

    Vehicle() {

    }

    public abstract void shiftGear();
    public abstract void accelerate();
    public abstract void brake();
}

interface howToDrive {
    final int GEAR = 1;
    public void shiftGear();
    public void accelerate();
}

class Car implements howToDrive {

}