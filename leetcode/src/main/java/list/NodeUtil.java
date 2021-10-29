package list;

/**
 * @DESCIRPTION NODE UTIL
 * @AUTHOR SCORPIO.HU
 * @DATE 2021/10/29 下午2:46
 */
public class NodeUtil {

    public static ListNode createNode(ListNode node, int[] arrs, int i) {
        if (i >= arrs.length) {
            return new ListNode(arrs[i - 1]);
        }
        node.next = createNode(new ListNode(arrs[i++]), arrs, i++);
        return node;
    }

    public static void printLinedNode(ListNode head) {
        while (null != head.next) {
            System.out.print(head.val + " ");
            head = head.next;

        }
        System.out.print(head.val + " ");
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(0, null);
        int[] arrs = {1, 2, 3};
        createNode(head, arrs, 0);
        printLinedNode(head);
    }
}
