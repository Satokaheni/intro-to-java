package pai;

public class Rectangle extends Polygon{
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
