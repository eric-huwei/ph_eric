package queue;

import org.junit.Test;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

/**
 * @DESCIRPTION
 * @AUTHOR SCORPIO.HU
 * @DATE 2021/11/3 3:33 PM
 */
public class PriorityQueueExec {

    @Test
    public void testIntPriorityQueue() {
        Queue<Integer> intPriority = new PriorityQueue<>(8);
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            intPriority.add(random.nextInt(100));
        }

        for (int i = 0; i < 8; i++) {
            System.out.println(intPriority.poll());
        }
    }

    private Comparator<Consumer> idComparator = Comparator.comparing(Consumer::getId);

    @Test
    public void testObjPriorityQueue() {
        Queue<Consumer> objPriority = new PriorityQueue<>(8, idComparator);
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            int id = random.nextInt(100);
            objPriority.add(new Consumer(id, "com.eric " + id));
        }
        System.out.println(objPriority);

        for (int i = 0; i < 8; i++) {
            System.out.println(objPriority.poll());
        }
    }
}
