package list;

/**
 * @DESCIRPTION Definition for singly-linked list.
 * @AUTHOR SCORPIO.HU
 * @DATE 2021/7/28 下午3:19
 */
public class ListNode {

    int val;
    ListNode next;

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
