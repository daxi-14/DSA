class Solution {

    public String lexGreaterPermutation(String s, String target) {

        int[] freq = new int[26];

        // Build frequency array
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        /*
         * First, try to match target from left to right.
         *
         * At each position, consume the same character if possible.
         */
        for (int i = 0; i < target.length(); i++) {

            int index = target.charAt(i) - 'a';

            if (freq[index] > 0) {
                freq[index]--;
            } else {

                /*
                 * We cannot match target[i].
                 *
                 * Try to make the answer greater at this position.
                 */
                for (int j = index + 1; j < 26; j++) {

                    if (freq[j] > 0) {

                        StringBuilder result = new StringBuilder();

                        result.append(target, 0, i);
                        result.append((char) ('a' + j));

                        freq[j]--;

                        result.append(buildRemaining(freq));

                        return result.toString();
                    }
                }

                /*
                 * We couldn't make the answer greater at i.
                 *
                 * We have to backtrack through the characters
                 * that we successfully matched before i.
                 *
                 * But first restore the characters that were consumed
                 * while matching target[0 ... i-1].
                 */
                for (int k = i - 1; k >= 0; k--) {

                    int previous = target.charAt(k) - 'a';

                    freq[previous]++;

                    /*
                     * Try to replace target[k] with the smallest
                     * available character greater than it.
                     */
                    for (int j = previous + 1; j < 26; j++) {

                        if (freq[j] > 0) {

                            StringBuilder result = new StringBuilder();

                            result.append(target, 0, k);
                            result.append((char) ('a' + j));

                            freq[j]--;

                            result.append(buildRemaining(freq));

                            return result.toString();
                        }
                    }
                }

                return "";
            }
        }

        /*
         * We matched the entire target.
         * That means we are equal to target, which isn't allowed.
         *
         * Backtrack from the last position.
         */
        for (int i = target.length() - 1; i >= 0; i--) {

            int index = target.charAt(i) - 'a';

            // Restore the character used at this position.
            freq[index]++;

            // Try a larger character here.
            for (int j = index + 1; j < 26; j++) {

                if (freq[j] > 0) {

                    StringBuilder result = new StringBuilder();

                    result.append(target, 0, i);
                    result.append((char) ('a' + j));

                    freq[j]--;

                    result.append(buildRemaining(freq));

                    return result.toString();
                }
            }
        }

        return "";
    }


    private String buildRemaining(int[] freq) {

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < 26; i++) {

            while (freq[i] > 0) {
                result.append((char) ('a' + i));
                freq[i]--;
            }
        }

        return result.toString();
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna