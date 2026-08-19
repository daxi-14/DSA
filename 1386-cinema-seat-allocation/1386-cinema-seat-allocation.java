class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {

        // row -> bitmask of reserved seats
        Map<Integer, Integer> map = new HashMap<>();

        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];

            map.put(row, map.getOrDefault(row, 0) | (1 << col));
        }

        // Rows with no reservations can always fit 2 families
        long answer = (long) (n - map.size()) * 2;

        // Masks for the three possible family blocks
        int left = (1 << 2) | (1 << 3) | (1 << 4) | (1 << 5);
        int middle = (1 << 4) | (1 << 5) | (1 << 6) | (1 << 7);
        int right = (1 << 6) | (1 << 7) | (1 << 8) | (1 << 9);

        for (int reserved : map.values()) {

            boolean canLeft = (reserved & left) == 0;
            boolean canMiddle = (reserved & middle) == 0;
            boolean canRight = (reserved & right) == 0;

            if (canLeft && canRight) {
                answer += 2;
            } else if (canLeft || canMiddle || canRight) {
                answer += 1;
            }
        }

        return (int) answer;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna