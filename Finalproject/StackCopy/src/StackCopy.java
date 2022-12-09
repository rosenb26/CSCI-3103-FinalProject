
import java.util.Stack;

public class StackCopy {

    public <E> Stack<E> copy(Stack<E> s) {
        Stack<E> copy = new Stack<>();
        Stack<E> temp = new Stack<>();

        while (!s.isEmpty()) {
            temp.push(s.pop());
        }

        while (!temp.isEmpty()) {
            E element = temp.pop();
            copy.push(element);
            s.push(element);
        }
        return copy;
    }

    public <E> Stack<E> reverseCopy(Stack<E> s) {
        Stack<E> temp = new Stack<>();
        Stack<E> reverse = new Stack<>();

        while (!s.isEmpty()) {
            E element = s.pop();
            temp.push(element);
            reverse.push(element);
        }

        while (!temp.isEmpty()) {
            s.push(temp.pop());
        }
        return reverse;
    }

    public <E> void printStack(Stack<E> s) {
        for (E element : s) {
            System.out.println(element + " ");
        }
        System.out.println();
    }

}
