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
    public ListNode reverseKGroup(ListNode head, int k) {
        List<ListNode> nodes = new ArrayList<>();

        ListNode temp = head;

        while (temp != null) {
            nodes.add(temp);
            temp = temp.next;
        }

        for (int i = 0; i + k <= nodes.size(); i += k) {
            Collections.reverse(nodes.subList(i, i + k));
        }

        for (int i = 0; i < nodes.size() - 1; i++) {
            nodes.get(i).next = nodes.get(i + 1);
        }

        nodes.get(nodes.size() - 1).next = null;

        return nodes.get(0);
    }
}