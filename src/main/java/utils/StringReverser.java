package utils;
import datastructure.Stack;

public class StringReverser {
    public static String reverse(String input){
        int length = input.length();
        if (length < 1) { return input; }

        Stack<Character> stack = new Stack<>(length);

        return reverseV2(input, stack, length);

    }

    private static String reverseV2(String input, Stack<Character> stack, int stringLength) {
        // get the sequence of letters from input
        char[] charArr = input.toCharArray();

        for (char c : charArr) { stack.push(c); }


        long startTime = System.nanoTime();

        for (int i = 0; i < stringLength; i++) {
            // Pop the top of the stack then replacing the ith element
            charArr[i] = stack.pop();
        }
        long endTime =  System.nanoTime();

        System.out.println("It took: " + (endTime - startTime)/1000000000 + "s to execute" );

        return String.valueOf(charArr);
    }

    private static String reverseV1(String input, Stack<String> stack, int stringLength) {
        // Build the reversed String later by popping the stack
        String reversedInput = "";


        // get the sequence of letters from input
        char[] charArr = input.toCharArray();

        for (char c : charArr) {
            // Convert to a string then push to stack
            stack.push(String.valueOf(c));
        }

        long startTime = System.nanoTime();
        for (int i = 0; i < stringLength; i++) {
            // Pop the top of the stack then concatenating with the last string
            reversedInput += stack.pop();
        }
        long endTime =  System.nanoTime();

        System.out.println("It took: " + (endTime - startTime)/1000000000 + "s to execute" );

        return reversedInput;
    }
}
