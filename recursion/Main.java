import java.util.*;
import java.io.*;

public class Main {

    // Fibonnaci Sequence
    public static int fibonnaci(int x) {
        if (x == 0) {
            return 0;
        }
        else if (x == 1) {
            return 1;
        }
        else {
            return fibonnaci(x-1) + fibonnaci(x-2);
        }
    }

    // Factorial
    public static int factorial(int x) {
        if (x == 0) {
            return 1;
        } else {
            return factorial(x-1) * x;
        }
    }

    // Factorial Tail Recursive
    public static int factorial_helper(int x, int accumulator) {
        if (x == 0) {
            return accumulator;
        } else {
            return factorial_helper(x-1, x*accumulator);
        }
    }

    public static int factorial_tr(int x) {
        return factorial_helper(x, 1);
    }

    // collatz conjecture
    // The collatz conjecture is that any sequence created from an initial positive integer x following an certain algorithm will always reach 1
    // If the number is even divide by 2
    // If the number is odd multiple by 3 and add 1
    public static boolean collatz(int x) {
        if (x == 1) {
            return true;
        } else {
            if (x % 2 == 0) {
                return collatz(x/2);
            } else {
                return collatz(3*x + 1);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(fibonnaci(10));
    }
}