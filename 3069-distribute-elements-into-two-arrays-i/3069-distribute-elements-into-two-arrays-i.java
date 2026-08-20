class Solution {
    public int[] resultArray(int[] nums) {
        int n = nums.length;
        List<Integer> arr1 = new ArrayList<>();
        List<Integer> arr2 = new ArrayList<>();
        arr1.add(nums[0]);
        arr2.add(nums[1]);
        for (int i=2;i<n;i++) {
            int n1 = arr1.size();
            int n2 = arr2.size();
            if (arr1.get(n1-1) > arr2.get(n2-1)) {
                arr1.add(nums[i]);
            } else arr2.add(nums[i]);
        }

        int[] result = new int[n];
        int c = 0;
        for (int i=0;i<arr1.size();i++) {
            result[c++] = arr1.get(i);
        }
        for (int i=0;i<arr2.size();i++) {
            result[c++] = arr2.get(i);
        }
        return result;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna