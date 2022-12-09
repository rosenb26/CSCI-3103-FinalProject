
import java.util.Stack;

public class StackCopyTester {

    public static void main(String[] args) {

        StackCopy tester = new StackCopy();

        /* Test with Characters. */
        Stack<Character> original1 = new Stack<>();

        char[] letters = {'Q', 'W', 'E', 'R', 'T', 'Y'};
        for (char c : letters) {
            original1.push(c);
        }

        Stack<Character> copy1 = tester.copy(original1);
        Stack<Character> reverse1 = tester.reverseCopy(original1);

        System.out.println("Original Stack");
        tester.printStack(original1);

        System.out.println("Copy Stack");
        tester.printStack(copy1);

        System.out.println("Original Stack");
        tester.printStack(original1);

        System.out.println("Reverse copy Stack");
        tester.printStack(reverse1);

        /* Test with Strings. */
        Stack<String> original2 = new Stack<>();
        String[] words = {"Don't", "we", "all", "love", "Java?!"};
        for (int i = 0; i < words.length; i++) {
            original2.push(words[i]);
        }

        Stack<String> copy2 = tester.copy(original2);
        Stack<String> reverse2 = tester.reverseCopy(original2);

        System.out.println("Original Stack");
        tester.printStack(original2);

        System.out.println("Copy Stack");
        tester.printStack(copy2);

        System.out.println("Original Stack");
        tester.printStack(original2);

        System.out.println("Reverse copy Stack");
        tester.printStack(reverse2);
    }
}
