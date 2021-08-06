package list;

/**
 * @DESCIRPTION Reverse List
 * @AUTHOR SCORPIO.HU
 * @DATE 2021/7/28 下午3:19
 */
public class ReverseList {

    public static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        ListNode curr = head;
        for (int i = 1; i < 5; i++) {
            curr.next = new ListNode(i);
            curr = curr.next;
        }

        System.out.println(reverseList(head).val == 4);
    }
}
