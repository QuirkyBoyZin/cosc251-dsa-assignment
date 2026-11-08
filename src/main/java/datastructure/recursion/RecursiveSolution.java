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

        if (x2 == 0) {
            return x1;
        }

        return gcd(x2, x1 % x2);
    }

    private static int countUniquePathsHelper(int n, int m, int[][] mem) {
        if (n == 1 || m == 1) return 1;

        if (mem[n - 1][m - 1] != 0) return mem[n - 1][m - 1];

        mem[n - 1][m - 1] = countUniquePathsHelper(n - 1, m, mem) + countUniquePathsHelper(n, m - 1, mem);

        return mem[n - 1][m - 1];

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
        if (n <= 0 || m <= 0) return 0;
        int[][] mem = new int[n][m];
        return countUniquePathsHelper(n, m, mem);
    }
}
