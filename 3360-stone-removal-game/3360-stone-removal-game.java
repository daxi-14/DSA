class Solution {
    public boolean canAliceWin(int n) {
       int counter = 10;
       boolean isA = false;
        while (n > 0) {
            n -= counter;
            if (n >= 0) isA = !isA;
            counter--;
        }
       return isA; 
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna