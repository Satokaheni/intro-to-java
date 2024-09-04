package pai;

public class Triangle extends Polygon {

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