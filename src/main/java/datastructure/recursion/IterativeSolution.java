package datastructure.recursion;

import java.util.Arrays;
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
            throw new IllegalArgumentException("Input must be a non-negative Integer!");
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
            throw new IllegalArgumentException("Input must be a non-negative Integer!");
        }

        if (n == 0) return 1;

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
        if (n < 0) {
            throw new IllegalArgumentException("Input must be a non-negative Integer or greater than zero! ");
        }

        if (n == 0) return 0;
        if (n == 1) return 1;

        int firstLast = 0;
        int secondLast = 1;

        int current = 0;
        for (int i = 1; i < n; i++) {
            current = firstLast + secondLast;
            firstLast = secondLast;
            secondLast = current;
        }


        return current;
    }

    /**
     * Takes a string as input and returns its reverse.
     *
     * @param str the original string to be reversed
     * @return the reversed string
     */
    public static String reverseStr(String str) {
        Objects.requireNonNull(str);
        if (str.isEmpty()) return "";

        char[] charArr = str.toCharArray();

        int length = charArr.length;
        for (int i = 0; i < length / 2; i++) {
            char temp = charArr[i];
            charArr[i] = charArr[length - 1 - i];
            charArr[length - 1 - i] = temp;
        }

        return new String(charArr);
    }

    /**
     * Checks if a specific character exists within a given string.
     *
     * @param str the string to search through
     * @param c   the character to find
     * @return true if the character exists in the string, false otherwise
     */
    public static boolean hasChar(String str, char c) {
        Objects.requireNonNull(str);

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
            throw new IllegalArgumentException("x1 and x2 must be non-negative Integers!");
        }

        if (x1 == 0) return x2; // gcd(0,a)
        if (x2 == 0) return x1; // gcd(a,0)

        while (true) {
            if (x1 % x2 == 0) return x2;

            int temp = x2;
            x2 = x1 % x2;
            x1 = temp;
        }

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
        if (n == 0 || m == 0) return 0;

        int[][] table = new int[n + 1][m + 1];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                if (table[i][j + 1] == 0) {
                    table[i][j] = 1;
                } else if (table[i + 1][j] == 0) {
                    table[i][j] = 1;
                } else {
                    table[i][j] = table[i + 1][j] + table[i][j + 1];
                }

            }

        }
        return table[0][0];


    }


}
