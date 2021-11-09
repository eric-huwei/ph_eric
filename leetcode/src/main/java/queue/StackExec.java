package queue;

import org.junit.Test;

import java.util.Stack;

/**
 * @DESCIRPTION
 * @AUTHOR SCORPIO.HU
 * @DATE 2021/11/4 9:11 AM
 */
public class StackExec {

    @Test
    public void testStack() {
        Stack<Integer> sta = new Stack<>();
        for (int i = 0; i < 8; i++) {
            sta.add(i);
        }
        System.out.println(sta);
        for (int i = 0; i < 8; i++) {
            System.out.print(sta.pop() + " ");
        }
    }
}
