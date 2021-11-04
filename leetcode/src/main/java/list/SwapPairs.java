package list;

/**
 * @DESCIRPTION 两两交换链表中的节点 - recursion
 * @AUTHOR SCORPIO.HU
 * @DATE 2021/11/2 3:35 PM
 */
public class SwapPairs {

    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode next = head.next;
        head.next = swapPairs(next.next);
        next.next = head;
        return next;
    }

    public static void main(String[] args) {
        int[] arrs = {1, 2, 3, 4};
        ListNode head = NodeUtil.createNode(new ListNode(0), arrs, 0);
        NodeUtil.printLinedNode(head);
        System.out.println("===");
        NodeUtil.printLinedNode(swapPairs(head));
    }

}
