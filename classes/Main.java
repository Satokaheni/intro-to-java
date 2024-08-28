import java.util.*;
import java.lang.Math;
import java.lang.module.FindException;

public class Main {
    public static void main(String[] args) {
        System.out.println(String.valueOf(Circle.numObjects)); // 0

        Circle circleOne = new Circle();

        System.out.println(String.valueOf(Circle.numObjects)); // 1

        Circle circleTwo = new Circle(5);

        System.out.println(String.valueOf(Circle.numObjects)); // 2
    }

    public static class Circle {
        private double radius;

        public static int numObjects = 0;

        // base constructor
        Circle() {
            this.radius = 6;
            numObjects++;
        }

        // overloaded constructor with base parameter
        Circle(double radius) {
            this.radius = radius;
            numObjects++;
        }

        // getters
        public double getRadius() {
            return this.radius;
        }

        //setters
        public void setRadius(double radius) {
            this.radius = radius;
        }

        public double getArea() {
            return Math.PI * Math.pow(this.radius, 2);
        }
    }

    public static class Fibonnaci {
        private static ArrayList<Integer> dynamic = new ArrayList<>();

        Fibonnaci() {
        }

        private int getNumberRecursive(int x) {
            if (x == 0) {
                if (dynamic.size() == 0) {
                    dynamic.add(0, 0);
                }
                return dynamic.get(0);
            } 
            else if (x == 1) {
                if (dynamic.size() < 2) {
                    dynamic.add(1, 1);
                }
                return 1;
            }
            else {
                int x1 = 0;
                if (dynamic.size() < (x-1)) {
                    x1 = this.getNumberRecursive(x-1);
                    dynamic.add(x-1, x1);
                }
                else {
                    x1 = dynamic.get(x-1);
                }
                int x2 = 0;
                if (dynamic.size() < (x-2)) {
                    x2 = this.getNumberRecursive(x-2);
                    dynamic.add(x-2, x2);
                }
                else {
                    x2 = dynamic.get(x-2);
                }
                return x1 + x2;
            }
        }

        public int getNumber(int x) {
            return this.getNumberRecursive(x);
        }
    }
}