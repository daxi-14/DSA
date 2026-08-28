class Solution {

    public String lexPalindromicPermutation(String s, String target) {

        int n = s.length();

        // -----------------------------------------
        // 1. Count characters
        // -----------------------------------------
        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        // -----------------------------------------
        // 2. Check whether palindrome is possible
        // -----------------------------------------
        int oddCount = 0;
        int middleChar = -1;

        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 == 1) {
                oddCount++;
                middleChar = i;
            }
        }

        if (oddCount > 1) {
            return "";
        }

        // -----------------------------------------
        // 3. Build frequency of characters
        //    available in the first half
        // -----------------------------------------
        int halfLength = n / 2;

        int[] halfFreq = new int[26];

        for (int i = 0; i < 26; i++) {
            halfFreq[i] = freq[i] / 2;
        }

        String targetHalf = target.substring(0, halfLength);

        // -----------------------------------------
        // 4. Check if targetHalf itself can be formed
        // -----------------------------------------
        boolean canMakeTargetHalf = canMake(targetHalf, halfFreq);

        // -----------------------------------------
        // 5. If targetHalf can be formed,
        //    construct the corresponding palindrome.
        //
        //    It might already be > target.
        // -----------------------------------------
        if (canMakeTargetHalf) {

            String palindrome = buildPalindrome(
                    targetHalf,
                    middleChar,
                    n
            );

            if (palindrome.compareTo(target) > 0) {
                return palindrome;
            }
        }

        // -----------------------------------------
        // 6. targetHalf itself doesn't work.
        //    Find the smallest half strictly
        //    greater than targetHalf.
        // -----------------------------------------
        String greaterHalf = findGreaterHalf(
                halfFreq,
                targetHalf
        );

        if (greaterHalf == null) {
            return "";
        }

        // -----------------------------------------
        // 7. Mirror the half to form palindrome
        // -----------------------------------------
        return buildPalindrome(
                greaterHalf,
                middleChar,
                n
        );
    }


    // =====================================================
    // Can we construct target using the available frequency?
    // =====================================================
    private boolean canMake(String target, int[] freq) {

        int[] remaining = freq.clone();

        for (char c : target.toCharArray()) {

            int index = c - 'a';

            if (remaining[index] == 0) {
                return false;
            }

            remaining[index]--;
        }

        return true;
    }


    // =====================================================
    // Find smallest permutation strictly greater than target
    // =====================================================
    private String findGreaterHalf(int[] freq, String target) {

        int n = target.length();

        /*
         * We want the RIGHTMOST position where we can
         * increase the character.
         *
         * Example:
         *
         * target = abb
         *
         * try:
         *
         * position 2 -> change b to c
         *                 |
         *                 abc
         *
         * That's better than changing position 1:
         *
         * acb
         *
         * because abc < acb.
         */

        for (int i = n - 1; i >= 0; i--) {

            // Characters needed for target[0 ... i-1]
            int[] remaining = freq.clone();

            boolean prefixPossible = true;

            for (int j = 0; j < i; j++) {

                int index = target.charAt(j) - 'a';

                if (remaining[index] == 0) {
                    prefixPossible = false;
                    break;
                }

                remaining[index]--;
            }

            if (!prefixPossible) {
                continue;
            }

            // -----------------------------------------
            // Try the smallest character greater than
            // target[i]
            // -----------------------------------------
            int current = target.charAt(i) - 'a';

            for (int c = current + 1; c < 26; c++) {

                if (remaining[c] > 0) {

                    // Use this larger character
                    remaining[c]--;

                    StringBuilder result = new StringBuilder();

                    // Keep target prefix unchanged
                    result.append(target, 0, i);

                    // Increase at position i
                    result.append((char) ('a' + c));

                    // Put remaining characters in sorted order
                    for (int k = 0; k < 26; k++) {
                        while (remaining[k] > 0) {
                            result.append((char) ('a' + k));
                            remaining[k]--;
                        }
                    }

                    return result.toString();
                }
            }
        }

        return null;
    }


    // =====================================================
    // Build palindrome from first half + middle
    // =====================================================
    private String buildPalindrome(
            String half,
            int middleChar,
            int n) {

        StringBuilder result = new StringBuilder();

        // Left half
        result.append(half);

        // Middle character for odd length
        if (n % 2 == 1) {
            result.append((char) ('a' + middleChar));
        }

        // Right half = reverse(left half)
        for (int i = half.length() - 1; i >= 0; i--) {
            result.append(half.charAt(i));
        }

        return result.toString();
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna