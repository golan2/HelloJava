package strings;

import java.util.Stack;

/**
 * <pre>
 * <B>Copyright:</B>   HP Software IL
 * <B>Owner:</B>       <a href="mailto:izik.golan@hp.com">Izik Golan</a>
 * <B>Creation:</B>    15/07/14 23:22
 * <B>Since:</B>       BSM 9.21
 * <B>Description:</B>
 *
 * </pre>
 */
public class ParenthesisMatchUp {

    private static final Pair[] pairs = {
        new Pair('(',')'),
        new Pair('{','}'),
        new Pair('[',']'),
        new Pair('<','>')
    };

    public static void main(String[] args) {

        System.out.println(isMatch(""));
        System.out.println(isMatch("()"));
        System.out.println(isMatch("{}"));
        System.out.println(isMatch("{()}"));
        System.out.println(isMatch("{123}"));
        System.out.println(isMatch("{)")); // failure
        System.out.println(isMatch("{ () () < <> } >  ()()()()()()")); // failure
    }

    public static boolean isMatch(String s) {
        Stack<Elem> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (isOpeningChar(c)) {
                stack.push(new Elem(c, i));
            }
            else if (isClosingChar(c)) {
                if (stack.isEmpty()) {
                    System.out.println("The '}' was encountered but no matching '{' in stack. Stack is empty.");
                    return false;
                }

                char expectedCharacter = findOpeningByClosing(c);
                Elem lastChar = stack.pop();
                if (expectedCharacter!=lastChar.getCharacter()) {
                    System.out.println("The '"+c+"' was encountered but no matching '"+expectedCharacter+"' in stack. Stack contains "+lastChar);
                    return false;
                }
                //if (c=='}' && !lastChar.equals('{')) {
                //    System.out.println("The '}' was encountered but no matching '{' in stack. Stack contains '"+lastChar+"'");
                //    return false;
                //}
                //if (c==')' && !lastChar.equals('(')) {
                //    System.out.println("The ')' was encountered but no matching '(' in stack. Stack contains '"+lastChar+"'");
                //    return false;
                //}
                //if (c==']' && !lastChar.equals('[')) {
                //    System.out.println("The ']' was encountered but no matching '[' in stack. Stack contains '"+lastChar+"'");
                //    return false;
                //}
            }
        }

        if (!stack.isEmpty()) {
            Elem lastChar = stack.pop();
            System.out.println("Stack not empty in the end. Stack contains "+lastChar);
            return false;
        }

        return true;
    }

    private static char findOpeningByClosing(char closing) {
        for (Pair pair : pairs) {
            if (closing==pair.getClosing()) return pair.getOpening();
        }
        throw new IllegalArgumentException("No matching opening for given closing: '"+closing+"'");
    }

    private static boolean isClosingChar(char c) {
        for (Pair pair : pairs) {
            if (c==pair.getClosing()) return true;
        }
        return false;
    }

    private static boolean isOpeningChar(char c) {
        for (Pair pair : pairs) {
            if (c==pair.getOpening()) return true;
        }
        return false;
    }

    private static class Pair {
        private final char opening;
        private final char closing;

        private Pair(char opening, char closing) {
            this.opening = opening;
            this.closing = closing;
        }

        private char getOpening() {
            return opening;
        }

        private char getClosing() {
            return closing;
        }
    }

    private static class Elem {
        private final Character character;
        private final int       position;

        private Elem(Character character, int position) {
            this.character = character;
            this.position = position;
        }

        private Character getCharacter() {
            return character;
        }

        private int getPosition() {
            return position;
        }

        public int compareTo(char c) {
            return getCharacter().compareTo(c);
        }
        public boolean equals(char c) {
            return c==getCharacter();
        }

        @Override
        public String toString() {
            return "#" + character + "~" + position + '#';
        }
    }
}
