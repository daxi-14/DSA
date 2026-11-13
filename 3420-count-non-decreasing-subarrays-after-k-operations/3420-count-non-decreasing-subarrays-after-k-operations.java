
class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;

        Deque<Integer> dq = new ArrayDeque<>();

        long cost = 0;
        long ans = 0;

        int j = n - 1;

        for (int i = n - 1; i >= 0; i--) {

            // Merge smaller segments into the current segment
            while (!dq.isEmpty() && nums[dq.peekLast()] < nums[i]) {

                int left = dq.pollLast();

                int right = dq.isEmpty() ? j + 1 : dq.peekLast();

                cost += (long) (right - left) * (nums[i] - nums[left]);
            }

            dq.offerLast(i);

            // Shrink the window while the operation cost exceeds k
            while (cost > k) {

                cost -= nums[dq.peekFirst()] - nums[j];

                if (dq.peekFirst() == j) {
                    dq.pollFirst();
                }

                j--;
            }

            // All subarrays [i...j], [i...j-1], etc. are valid
            ans += j - i + 1;
        }

        return ans;
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna