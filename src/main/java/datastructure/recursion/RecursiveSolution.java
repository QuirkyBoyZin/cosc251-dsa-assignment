package datastructure.recursion;

import java.util.Objects;

public class RecursiveSolution {
    /**
     * Calculates the sum of all non-negative integers up to n.
     *
     * @param n the upper bound integer
     * @return the sum of all non-negative integers up to n
     */
    public static int tri(int n) {
        if (n < 0) throw new IllegalArgumentException("Input must be a non-negative integer.");

        if (n == 1) {
            return 1;
        }

        return n + tri(n - 1);
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

        if (n <= 1) {
            return 1;
        }

        return n * fact(n - 1);
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
        if (n == 0) return 0;
        if (n == 1) return 1;

        return fib(n - 1) + fib(n - 2);
    }

    /**
     * Takes a string as input and returns its reverse.
     *
     * @param str the original string to be reversed
     * @return the reversed string
     */
    public static String reverseStr(String str) {
        Objects.requireNonNull(str, "Input string cannot be null.");

        if (str.isEmpty()) return str;

        return str.charAt(str.length() - 1) + reverseStr(str.substring(0, str.length() - 1));
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

        if (str.isEmpty()) return false;

        if (str.charAt(0) == c) return true;

        return hasChar(str.substring(1), c);
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

        if (remainder == 0) {
            return x1;
        }

        return gcd(x2, remainder);
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
