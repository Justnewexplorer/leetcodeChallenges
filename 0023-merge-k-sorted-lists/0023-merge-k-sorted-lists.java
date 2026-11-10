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
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> heap = new PriorityQueue<>((a, b) -> Integer.compare(a.val,b.val));
        // Add the head of every linked list
        for (ListNode node : lists) {
            if (node != null) {
                heap.offer(node);
            }
        }
        // Dummy node
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (!heap.isEmpty()) {
            // Extract the smallest node
            ListNode smallest = heap.poll();
            // Add the next node from the same list
            if (smallest.next != null) {
                heap.offer(smallest.next);
            }
            // Attach the smallest node
            tail.next = smallest;
            tail = tail.next;
        }
        return dummy.next;
    }
}
