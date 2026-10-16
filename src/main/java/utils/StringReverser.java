package utils;

import datastructure.Stack;

import java.util.Objects;

public class StringReverser {
    public static String reverse(String input) {
        Objects.requireNonNull(input);
        int length = input.length();

        if (length < 1) {
            return input;
        }

        Stack<Character> stack = new Stack<>(length);

        for (int i = 0; i < length; i++) {
            char c = input.charAt(i); // get each character from the input
            stack.push(c);
        }

        // Pop the top of the stack then replacing the ith element
        char[] reversedString = new char[length];
        for (int i = 0; i < length; i++) {
           reversedString[i] = stack.pop();
        }

        // Convert the Character array into a string then returning
        return String.valueOf(reversedString);

    }

}
