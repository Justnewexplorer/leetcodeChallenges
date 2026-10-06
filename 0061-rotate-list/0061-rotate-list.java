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
    private ListNode firstNode(ListNode temp, int k){
        int cnt = 1;
        while(temp != null){
            if(cnt == k)
                return temp;
            cnt++;
            temp = temp.next;    
        }
        return temp;
    }

    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || k == 0)
            return head;
        ListNode tail = head;
        int len = 1;
        while(tail.next != null){
            tail = tail.next;
            len++;
        }
        if(k % len == 0)
            return head;
        k = k % len;
        tail.next = head;
        ListNode newlastnode = firstNode(head, len - k);
        head = newlastnode.next;
        newlastnode.next = null;
        return head;      
    }
}