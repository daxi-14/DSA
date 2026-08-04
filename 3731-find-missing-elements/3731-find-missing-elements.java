class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        int min=101, max = 0;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);

            set.add(num);
        }

        List<Integer> ans = new ArrayList();
        for (int i=min;i<=max;i++) {
            if (!set.contains(i)) ans.add(i);
        }

        return ans;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna