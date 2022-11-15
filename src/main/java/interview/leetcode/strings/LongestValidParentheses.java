package interview.leetcode.strings;

/**
 * https://leetcode.com/problems/longest-valid-parentheses/
 *
 * Given a string containing just the characters '(' and ')', return the length of the longest valid (well-formed) parentheses substring
 */
@SuppressWarnings("JavadocLinkAsPlainText")
public class LongestValidParentheses {
    public static void main(String[] args) {
        final LongestValidParentheses a = new LongestValidParentheses();
        System.out.println(a.longestValidParentheses("( (()) ())))))) ((()))((()))((()))  "));
        System.out.println(a.longestValidParentheses("()(()"));
        System.out.println(a.longestValidParentheses("(()"));
    }

    public int longestValidParentheses(String s) {
        int max = 0;
        int openCount = 0;
        int length = 0;
        for (int i = 0; i < s.length(); i++) {
            final char c = s.charAt(i);
            if (c == '(') {
                openCount++;
            }
            else if (c == ')') {
                if (openCount > 0) {
                    openCount--;
                    length++;
                }
                else {
                    //this character breaks the sequence; whatever length of valid sequence we've collected so far is the length
                    if (length > max) {
                        max = length;
                        length = 0;
                    }
                }
            }
        }

        return Math.max(length, max) * 2;
    }
}
