class Solution {
    public List<String> braceExpansionII(String expression) {
        Set<String> result = new TreeSet<>();
        dfs(expression, 0, "", result);
        return new ArrayList<>(result);
    }

    private void dfs(String s, int index, String current, Set<String> result) {
        if (index == s.length()) {
            result.add(current);
            return;
        }

        char ch = s.charAt(index);

        if (ch == '{') {
            int end = findClosingBrace(s, index);
            String content = s.substring(index + 1, end);

            List<String> options = parse(content);

            for (String option : options) {
                dfs(s, end + 1, current + option, result);
            }
        } else {
            dfs(s, index + 1, current + ch, result);
        }
    }

    private int findClosingBrace(String s, int start) {
        int balance = 0;

        for (int i = start; i < s.length(); i++) {
            if (s.charAt(i) == '{') {
                balance++;
            } else if (s.charAt(i) == '}') {
                balance--;
                if (balance == 0) {
                    return i;
                }
            }
        }

        return -1;
    }

    private List<String> parse(String s) {
        Set<String> result = new TreeSet<>();
        List<String> parts = new ArrayList<>();

        int balance = 0;
        int start = 0;

        for (int i = 0; i <= s.length(); i++) {
            if (i == s.length() || (s.charAt(i) == ',' && balance == 0)) {
                parts.add(s.substring(start, i));
                start = i + 1;
            } else if (s.charAt(i) == '{') {
                balance++;
            } else if (s.charAt(i) == '}') {
                balance--;
            }
        }

        for (String part : parts) {
            result.addAll(parseConcatenation(part));
        }

        return new ArrayList<>(result);
    }

    private List<String> parseConcatenation(String s) {
        Set<String> current = new TreeSet<>();
        current.add("");

        for (int i = 0; i < s.length();) {
            Set<String> next = new TreeSet<>();

            if (s.charAt(i) == '{') {
                int end = findClosingBrace(s, i);
                List<String> options = parse(s.substring(i + 1, end));

                for (String a : current) {
                    for (String b : options) {
                        next.add(a + b);
                    }
                }

                i = end + 1;
            } else {
                char ch = s.charAt(i);

                for (String a : current) {
                    next.add(a + ch);
                }

                i++;
            }

            current = next;
        }

        return new ArrayList<>(current);
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna