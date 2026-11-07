class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        PriorityQueue<int[]> available = new PriorityQueue<>(
            (a, b) -> a[0] != b[0]
                ? Integer.compare(a[0], b[0])
                : Integer.compare(a[1], b[1])
        );

        PriorityQueue<long[]> busy = new PriorityQueue<>(
            (a, b) -> {
                int cmp = Long.compare(a[0], b[0]);
                if (cmp != 0) return cmp;

                cmp = Long.compare(a[1], b[1]);
                if (cmp != 0) return cmp;

                return Long.compare(a[2], b[2]);
            }
        );

        for (int i = 0; i < servers.length; i++) {
            available.offer(new int[]{servers[i], i});
        }

        int[] result = new int[tasks.length];
        long time = 0;

        for (int i = 0; i < tasks.length; i++) {
            time = Math.max(time, i);

            // Release all servers that have completed their tasks.
            while (!busy.isEmpty() && busy.peek()[0] <= time) {
                long[] server = busy.poll();

                available.offer(new int[]{
                    (int) server[1],
                    (int) server[2]
                });
            }

            // If no server is available, advance time.
            if (available.isEmpty()) {
                time = busy.peek()[0];

                while (!busy.isEmpty() && busy.peek()[0] <= time) {
                    long[] server = busy.poll();

                    available.offer(new int[]{
                        (int) server[1],
                        (int) server[2]
                    });
                }
            }

            int[] server = available.poll();

            result[i] = server[1];

            busy.offer(new long[]{
                time + tasks[i],
                server[0],
                server[1]
            });
        }

        return result;
    }
}

// Synced seamlessly with LeetHub Pro
// Pro features: https://bit.ly/leethubpro | Free version: https://bit.ly/leethubv4
// Get it here: https://chromewebstore.google.com/detail/bcilpkkbokcopmabingnndookdogmbna