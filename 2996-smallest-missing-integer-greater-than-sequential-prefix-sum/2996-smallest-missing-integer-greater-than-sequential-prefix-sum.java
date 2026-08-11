import java.util.*;

class Solution {
    public int missingInteger(int[] nums) {

        int n = nums.length;
        int curr = 1;
        int sum = nums[0];

        // Find sequential prefix
        while (curr < n && nums[curr] == nums[curr - 1] + 1) {
            sum += nums[curr++];
        }

        // Put all numbers into a HashSet
        Set<Integer> set = new HashSet<>();

        for (int num : nums) {
            set.add(num);
        }

        // Find the smallest missing number >= sum
        while (set.contains(sum)) {
            sum++;
        }

        return sum;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna