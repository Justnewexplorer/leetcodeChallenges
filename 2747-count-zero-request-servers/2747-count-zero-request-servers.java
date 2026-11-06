class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {

        Arrays.sort(logs, (a, b) -> a[1] - b[1]);

        int m = queries.length;

        // Store query and its original index
        int[][] sortedQueries = new int[m][2];

        for (int i = 0; i < m; i++) {
            sortedQueries[i][0] = queries[i];
            sortedQueries[i][1] = i;
        }

        Arrays.sort(sortedQueries, (a, b) -> a[0] - b[0]);

        int[] count = new int[n + 1];
        int[] ans = new int[m];

        int left = 0;
        int right = 0;
        int active = 0;

        for (int[] query : sortedQueries) {

            int q = query[0];
            int originalIndex = query[1];

            // Add logs with time <= q
            while (right < logs.length && logs[right][1] <= q) {

                int server = logs[right][0];

                if (count[server] == 0) {
                    active++;
                }

                count[server]++;
                right++;
            }

            // Remove logs with time < q - x
            while (left < right && logs[left][1] < q - x) {

                int server = logs[left][0];

                count[server]--;

                if (count[server] == 0) {
                    active--;
                }

                left++;
            }

            ans[originalIndex] = n - active;
        }
        return ans;
    }
}
