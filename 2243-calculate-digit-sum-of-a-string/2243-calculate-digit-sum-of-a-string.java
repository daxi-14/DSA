
class Solution {
    public String digitSum(String s, int k) {
        String res = s;

        while (res.length() > k) {
            res = parseSum(res, k);
        }

        return res;
    }

    private String parseSum(String res, int k) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < res.length(); i += k) {
            int sum = 0;

            int end = Math.min(i + k, res.length());

            for (int j = i; j < end; j++) {
                sum += res.charAt(j) - '0';
            }

            sb.append(sum);
        }

        return sb.toString();
    }
}


// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna