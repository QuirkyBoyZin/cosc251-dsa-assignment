import datastructure.recursion.IterativeSolution;
import datastructure.recursion.RecursiveSolution;

public class Main {
    public static void main(String[] args) {

//        testTri(5, 15);
//        testTri(6, 21);
//        System.out.println("\n==================================================================\n");
//
//        testFact(0, 1);
//        testFact(1, 1);
//        testFact(3, 6);
//        testFact(4, 24);
//        System.out.println("\n==================================================================\n");

//        testFib(0,0);
//        testFib(1,1);
//        testFib(2,1);
//        testFib(3, 2);
//        testFib(4, 3);
//        testFib(10,55);
//        System.out.println("\n==================================================================\n");

//        testReverseStr("", "");
//        testReverseStr("abcd","dcba");
//        testReverseStr("abcde","edcba");


//        testReverseStr("ABC", "CBA");
//        testReverseStr("  ABC", "CBA  ");
//        testReverseStr("abcdefg", "gfedcba");
//        System.out.println("\n==================================================================\n");


//        testHasChar("ABC", 'A', true);
//        testHasChar("ABC", 'B', true);
//        testHasChar("ABC", 'C', true);
//        testHasChar("ABC", 'a', false);
//        System.out.println("\n==================================================================\n");

//        testGcd(6999,1,1);
//        testGcd(10,12, 2);
//        testGcd(18,12, 6);
//        testGcd(96,2, 2);
//        testGcd(12,18, 6);
//        testGcd(2,2,2);
//        testGcd(50,25, 25);
//        testGcd(100,50, 50);
//        testGcd(6,3,3);
//
//        testGcd(18,0, 18);
//        testGcd(0,12, 12);
//
//        testGcd(5,7,1);
//        testGcd(67,7,1);


//        testCount(1,1,1);
//        testCount(1,2,1);
//        testCount(2,2,2);
//        testCount(3,2,3);
//        testCount(2,3,3);
//        testCount(3,3,6);
//        testCount(3,7,28);





















    }

    static void testTri(int n, int expected) {
        var iter = IterativeSolution.tri(n);
        var recur = RecursiveSolution.tri(n);
        String name = "Triangular Number";

        System.out.println("==================== " + name + " ================================");
        System.out.println("Test input: " + n);
        System.out.println("Iterative: " + (iter == expected) + ", Actual: " + iter + " Expected: " + expected);
        System.out.println("Recursive: " + (recur == expected) + ", Actual: " + recur + " Expected: " + expected);

    }

    static void testFact(int n, int expected) {
        var iter = IterativeSolution.fact(n);
        var recur = RecursiveSolution.fact(n);
        String name = "Factorial";

        System.out.println("==================== " + name + " ================================");
        System.out.println("Test input: " + n);
        System.out.println("Iterative: " + (iter == expected) + ", Actual: " + iter + " Expected: " + expected);
        System.out.println("Recursive: " + (recur == expected) + ", Actual: " + recur + " Expected: " + expected);

    }

    static void testFib(int n, int expected) {
        var iter = IterativeSolution.fib(n);
        var recur = RecursiveSolution.fib(n);
        String name = "Fibonacci";

        System.out.println("==================== " + name + " ================================");
        System.out.println("Test input: " + n);
        System.out.println("Iterative: " + (iter == expected) + ", Actual: " + iter + " Expected: " + expected);
        System.out.println("Recursive: " + (recur == expected) + ", Actual: " + recur + " Expected: " + expected);

    }

    static void testReverseStr(String s, String expected) {
        var iter = IterativeSolution.reverseStr(s);
        var recur = RecursiveSolution.reverseStr(s);
        String name = "Reverse String";

        System.out.println("==================== " + name + " ================================");
        System.out.println("Test input: " + s);
        System.out.println("Iterative: " + (iter.equals(expected)) + ", Actual: " + iter + " Expected: " + expected);
        System.out.println("Recursive: " + (recur.equals(expected)) + ", Actual: " + recur + " Expected: " + expected);

    }

    static void testHasChar(String s, char c, boolean expected) {
        var iter = IterativeSolution.hasChar(s,c);
        var recur = RecursiveSolution.hasChar(s,c);
        String name = "hasChar";

        System.out.println("==================== " + name + " ================================");
        System.out.println("Test input: " + s + ", Char: " + c);
        System.out.println("Iterative: " + (iter == expected) + ", Actual: " + iter + " Expected: " + expected);
        System.out.println("Recursive: " + (recur == expected) + ", Actual: " + recur + " Expected: " + expected);

    }

    static void testGcd(int x1, int x2, int expected) {
        var iter = IterativeSolution.gcd(x1,x2);
        var recur = RecursiveSolution.gcd(x1,x2);
        String name = "gcd";

        System.out.println("==================== " + name + " ================================");
        System.out.println("Test input: " + x1 + " " + x2);
        System.out.println("Iterative: " + (iter == expected) + ", Actual: " + iter + " Expected: " + expected);
        System.out.println("Recursive: " + (recur == expected) + ", Actual: " + recur + " Expected: " + expected);
    }

    static void testCount(int x1, int x2, int expected) {
        var iter = IterativeSolution.countUniquePaths(x1,x2);
        var recur = RecursiveSolution.countUniquePaths(x1,x2);
        String name = "Unique Path";

        System.out.println("==================== " + name + " ================================");
        System.out.println("Test input: " + x1 + " " + x2);
        System.out.println("Iterative: " + (iter == expected) + ", Actual: " + iter + " Expected: " + expected);
        System.out.println("Recursive: " + (recur == expected) + ", Actual: " + recur + " Expected: " + expected);
    }


}
