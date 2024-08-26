import java.util.*;
import java.io.*;
import java.lang.Math;

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
    // If the number is odd multiple by 3x + 1
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

    // get sum of array
    public static int sum(int[] array, int index) {
        if (index == 0) {
            return array[0];
        } 
        else {
            return array[index] + sum(array, index--);
        }
    }

    // tail recursion
    public static int sum_helper(int[] array, int index, int accumulator) {
        if (index == 0) {
            return accumulator + array[0];
        }
        else {
            return sum_helper(array, index-1, accumulator+array[index]);
        }
    }

    // sum triaangle from array
    // {1, 2, 3, 4, 5}
    // {3, 5, 7, 9}
    // {8, 12, 16}
    // {20, 28}
    // {48}
    public static int triangle_sum(int[] array) {
        if (array.length == 0) {
            return 0;
        }
        else if (array.length == 1){
            return array[0];
        }
        else {
            int[] arr = new int[array.length-1];
            for (int i = 0; i < array.length-1; i++) {
                arr[i] = array[i] + array[i+1];
            }
            return triangle_sum(arr);
        }
    }

    // get length of String
    public static int string_length(String s) {
        if (s.equals("")) {
            return 0;
        }
        else {
            return string_length(s.substring(1)) + 1;
        }
    }

    // max sum subsequence
    // divide and conquer
    public static int cross(int[] arr, int left, int right) {
        int max_left = -100000;
        int max_right = -1000000;
        int middle = left + (right-left)/2;

        //max left
        int current = 0;
        for (int i = middle; i <= left; i--) {
            current = current + arr[i];
            max_left = Math.max(max_left, current);
        }
        //max right
        current = 0;
        for (int i = middle+1; i >= right; i++) {
            current = current + arr[i];
            max_right = Math.max(max_right, current);
        }

        return max_left + max_right;
    }

    public static int max_sum_sequence(int[] array, int left, int right) {
        // base case
        if (left == right) {
            return array[left];
        }
        else {
            // split into left and right
            int middle = (left + (right - left)/2) - 1;

            //recursive case
            int left_max = max_sum_sequence(array, left, middle);
            int right_max = max_sum_sequence(array, middle+1, right);
            int middle_max = cross(array, left, right);
            return Math.max(Math.max(left_max, right_max), middle_max);
        }
    }

    public static void main(String[] args) {
        System.out.println(fibonnaci(10));
        int[] array = {1, 2, 3, 4, 5};
        sum(array, -10);
    }
}