
import java.util.Random;

public class MaxHeap<E extends Comparable<E>> extends Heap<E> {

    public int compare(E first, E second) {
        /* Reverse the order of the comparison in the MinHeap class. */
        return -1 * first.compareTo(second);
    }

    public static void maxHeapTester() {
        Heap<Integer> heap = new MaxHeap<>();
        System.out.print("Adding: ");
        for (int i = 1; i <= 10; i++) {
            int random = new Random().nextInt(50);
            heap.add(random);
            System.out.print(random + " ");
        }
        System.out.print("\nmax heap after adding: " + heap.getHeap());

        System.out.print("\nremoving: ");
        while (heap.getSize() > 0) {
            System.out.print(heap.remove() + " ");
        }
        System.out.print("\nmax heap after removing: " + heap.getHeap() + "\n");
    }
}
