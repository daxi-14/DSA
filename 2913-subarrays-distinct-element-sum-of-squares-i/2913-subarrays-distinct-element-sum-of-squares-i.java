class Solution {
    public int sumCounts(List<Integer> nums) {
        int n = nums.size();
        int ans = n;
        for (int i=0;i<n-1;i++) {
            Set<Integer> set = new HashSet<Integer>();
            set.add(nums.get(i));
            for (int j = i+1;j<n;j++) {
                set.add(nums.get(j));
                int size= set.size();
                ans += size * size;
            }
        }

        return ans;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna