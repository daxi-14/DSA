class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        int n = nums.length;
        Arrays.sort(nums);

        List<Integer> ans = new ArrayList<>();
        boolean isTargetFound = false;
        for (int i=0;i<n;i++) {
            if (nums[i] == target) {
                ans.add(i);
                isTargetFound = true;
            } else if (isTargetFound) break;
        }

        //Collections.reverse(ans);
        return ans;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna