class Solution {
    public String shortestBeautifulSubstring(String s, int k) {

        // Store positions of all 1s
        List<Integer> ones = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                ones.add(i);
            }
        }

        // Not enough 1s
        if (ones.size() < k) {
            return "";
        }

        String answer = "";

        for (int i = 0; i + k - 1 < ones.size(); i++) {

            int left = ones.get(i);
            int right = ones.get(i + k - 1);

            String candidate = s.substring(left, right + 1);

            // First candidate
            if (answer.equals("")) {
                answer = candidate;
            }

            // Shorter candidate
            else if (candidate.length() < answer.length()) {
                answer = candidate;
            }

            // Same length -> lexicographically smaller
            else if (candidate.length() == answer.length()
                    && candidate.compareTo(answer) < 0) {
                answer = candidate;
            }
        }

        return answer;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna