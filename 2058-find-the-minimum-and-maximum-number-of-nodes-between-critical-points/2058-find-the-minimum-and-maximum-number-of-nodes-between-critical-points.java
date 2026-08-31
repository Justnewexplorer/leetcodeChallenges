/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        ListNode prev = head;
        ListNode curr = head.next;

        int index = 1;
        int first = -1;
        int last = -1;
        int min = Integer.MAX_VALUE;

        while (curr.next != null) {
            ListNode next = curr.next;
            if ((prev.val < curr.val && curr.val > next.val) || (prev.val > curr.val && curr.val < next.val)) {
                if (first == -1) {
                    first = index;
                } else {
                    min = Math.min(min, index - last);
                }
                last = index;
            }

            prev = curr;
            curr = next;
            index++;
        }
        if (first == -1 || first == last)
            return new int[]{-1, -1};
        return new int[]{min, last - first};
    }
}