
import java.util.ArrayList;
import java.util.Comparator;

public abstract class Heap<E> implements Comparator<E> {

    private ArrayList<E> heap;

    public Heap() {
        this.heap = new ArrayList<>();
    }

    /**
     * Adds an item to the heap in the appropriate position.
     */
    public void add(E item) {
        this.heap.add(item);

        /* Fix the heap if the heap contains more than 1 item. */
        if (this.heap.size() > 1) {
            int index = this.heap.size() - 1;
            /* Compares the current element with its parent, and swaps them
            if the child is bigger (for a max heap) or smaller (for a min heap),
            as defined in the subclasses. */
            while (index > 0 && this.compare(this.heap.get(index / 2), this.heap.get(index)) > 0) {
                E temp = this.heap.get(index);
                this.heap.set(index, this.heap.get(index / 2));
                this.heap.set(index / 2, temp);
                index /= 2;
            }

        }
    }

    /**
     * Removes an item from the top of the heap.
     */
    public E remove() {
        if (this.heap.size() == 0) {
            return null;
        }

        if (this.heap.size() == 1) {
            return this.heap.remove(0);
        }
        /* Save the item at the top of the heap. */
        E top = this.heap.get(0);

        /* Replace the top of the heap with the last item in the heap. */
        this.heap.set(0, this.heap.remove(this.heap.size() - 1));

        /* Fix the heap. */
        int index = 0;
        while (true) {

            /*If the current node has no left child, it has no right child either
             so it can't be pushed any further down the heap. */
            if (2 * index + 1 >= this.heap.size()) {
                break;
            }

            int smallerChildIndex = 2 * index + 1;

            /* Check for a right child and compare to the left child and find the smaller. */
            if (smallerChildIndex + 1 < this.heap.size()) {
                if (this.compare(this.heap.get(smallerChildIndex),
                        this.heap.get(smallerChildIndex + 1)) > 0) {
                    smallerChildIndex++;
                }
            }

            /* Swap smaller child with parent node if out of order. */
            if (this.compare(this.heap.get(index), this.heap.get(smallerChildIndex)) > 0) {
                E temp = this.heap.get(index);
                this.heap.set(index, this.heap.get(smallerChildIndex));
                this.heap.set(smallerChildIndex, temp);
                index = smallerChildIndex;
            }

            /* Exit the loop if the node has found its correct position. */
            else {
                break;
            }
        }

        return top;
    }

    public ArrayList<E> getHeap() {
        return this.heap;
    }

    public int getSize() {
        return this.heap.size();
    }

    public abstract int compare(E first, E second);
}
