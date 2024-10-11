package fr.istic.vv;

import java.util.Stack;

public class StringUtils {

    private StringUtils() {}

    public static boolean isBalanced(String str) {
        // Stack of char
        Stack<Character> stack = new Stack<>();

        for (char ch : str.toCharArray()) { 
            
            // Fill the stack
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            }

            // Check for closing symbols
            else if (ch == ')' || ch == '}' || ch == ']') {
                // If the stack is empty return false
                if (stack.isEmpty()) {
                    return false;
                }
                char top = stack.pop();
                // If the character doesn't match the opening symbol return false
                if (!isMatchingPair(top, ch)) {
                    return false;
                }            
            }
        }

        // If the stack is empty, the string is balanced
        return stack.isEmpty();
    }

    // Check if a pair of symbols match
    private static boolean isMatchingPair(char open, char close) {
        return (open == '(' && close == ')') ||
               (open == '{' && close == '}') ||
               (open == '[' && close == ']');
    }

}
