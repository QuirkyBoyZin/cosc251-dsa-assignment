package utils;

import datastructure.Stack;

public class DelimiterChecker {
    public static boolean check(String input) {
        int length = input.length();
        if (input.isBlank()) return true; // Nothing to check

        Stack<String> stack = new Stack<>(length);

        // Check for [ { ( and ] { )
        for (int i = 0; i < length; i++) {
            // Character at each index
            char c = input.charAt(i);

            // Check for opening delimiter  [ { (
            // Push to stack when matches
            if (c == '[' || c == '{' || c == '(') {
                stack.push(String.valueOf(c));
            }

            // Check for closing delimiter ] { )
            else if (c == ']' || c == '}' || c == ')') {
                // null means there was never an opening delimiter to close with in the beginning
                if (stack.peek() == null) {
                    return false;
                }

                char openingDelimiter = stack.pop().charAt(0);

                // Only returning false if the opening and closing delimiter doesn't match !
                if (!isMatchingDelimiter(openingDelimiter, c)) return false;

            }

        }
        // if the top of the stack isn't equal to null, it means there is still one or multiple opening delimiters still left in the stack
        // returning true if and only the stack is empty
        return stack.peek() == null;
    }

    public static boolean isMatchingDelimiter(char openingDelimiter, char closingDelimiter) {
        return (openingDelimiter == '[' && closingDelimiter == ']') || (openingDelimiter == '{' && closingDelimiter == '}') || (openingDelimiter == '(' && closingDelimiter == ')');
    }
}
