package datastructure.recursion;

import java.util.Objects;

public class IterativeSolution {

    /**
     * Calculates the sum of all non-negative integers up to n.
     *
     * @param n the upper bound integer
     * @return the sum of all non-negative integers up to n
     */
    public static int tri(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Input must be a non-negative integer.");
        }

        int result = 0;

        for (int i = 0; i <= n; i++) {
            result += i;
        }
        return result;
    }

    /**
     * Calculates the multiplication of all non-negative integers up to n.
     *
     * @param n the upper bound integer
     * @return the multiplication of all non-negative integers up to n
     */
    public static int fact(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Input must be a non-negative integer.");
        }

        int result = 1;

        for (int i = 1; i <= n; i++) {
            result *= i;
        }

        return result;
    }

    /**
     * Returns the nth Fibonacci number.
     * The Fibonacci sequence is a series of numbers where each number is the sum of the two
     * preceding ones, starting with 0 and 1.
     *
     * @param n the index in the Fibonacci sequence
     * @return the nth Fibonacci number
     */
    public static int fib(int n) {
        if (n < 0) throw new IllegalArgumentException("Input must be a non-negative integer.");

        int x0 = 0;
        int x1 = 1;
        int result = 0;

        for (int i = 1; i < n; i++) {
            result = x1 + x0;
            x0 = x1;
            x1 = result;
        }

        return result;


    }

    /**
     * Takes a string as input and returns its reverse.
     *
     * @param str the original string to be reversed
     * @return the reversed string
     */
    public static String reverseStr(String str) {
        Objects.requireNonNull(str, "Input string cannot be null.");

        StringBuilder sb = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--) {
            sb.append(str.charAt(i));
        }

        return sb.toString();
    }

    /**
     * Checks if a specific character exists within a given string.
     *
     * @param str the string to search through
     * @param c   the character to find
     * @return true if the character exists in the string, false otherwise
     */
    public static boolean hasChar(String str, char c) {
        Objects.requireNonNull(str, "Input string cannot be null.");

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == c) {
                return true;
            }
        }
        return false;
    }

    /**
     * Finds the greatest common divisor of two non-negative integers.
     *
     * @param x1 the first non-negative integer
     * @param x2 the second non-negative integer
     * @return the greatest common divisor of x1 and x2
     */
    public static int gcd(int x1, int x2) {
        if (x1 < 0 || x2 < 0) {
            throw new IllegalArgumentException("Inputs must be non-negative integers.");
        }

        int remainder = x1 % x2;
        while (remainder != 0) {
            x1 = x2;
            x2 = remainder;
            remainder = x1 % x2;
        }

        return x1;
    }

    /**
     * Calculates the number of unique paths from the top left corner to
     * the bottom right corner of an n * m grid.
     * Constraints: the path can only move down or right by one unit at a time.
     *
     * @param n the number of rows in the grid
     * @param m the number of columns in the grid
     * @return the number of unique paths
     */
    public static int countUniquePaths(int n, int m) {
        return 0;
    }


}
