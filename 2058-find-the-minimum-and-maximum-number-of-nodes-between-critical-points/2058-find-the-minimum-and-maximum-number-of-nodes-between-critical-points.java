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
        int index = -1, curr = 1, first = -1, last = -1, minDist = Integer.MAX_VALUE;
        ListNode prevNode = head, currNode = head.next, next = head.next.next;

        while (next != null) {
            if ((currNode.val > prevNode.val && currNode.val > next.val) || (currNode.val < prevNode.val && currNode.val < next.val)) {
                //it's a critical point
                if (index == -1) {
                    first = curr;
                } else {
                    minDist = Math.min(minDist, curr - index);
                }
                
                index = curr;
            }

            prevNode = currNode;
            currNode = next;
            next = next.next;
            curr++;
        }

        int[] ans = new int[2];
        if (minDist == Integer.MAX_VALUE) {
            ans[0] = -1;
            ans[1] = -1;
        } else {
            ans[0] = minDist;
            ans[1] = index - first;
        }

        return ans;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna