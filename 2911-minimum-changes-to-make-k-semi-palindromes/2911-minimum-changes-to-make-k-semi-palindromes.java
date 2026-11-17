class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        int INF = 1_000_000_000;
        // Step 1: Precompute the cost of every substring.
        int[][] cost = new int[n][n];
        for (int start = 0; start < n; start++) {
            for (int end = start + 1; end < n; end++) {
                int len = end - start + 1;
                int best = INF;
                // Try every valid divisor.
                for (int d = 1; d < len; d++) {
                    if (len % d != 0) {
                        continue;
                    }
                    int changes = 0;
                    // Check each group.
                    for (int offset = 0; offset < d; offset++) {
                        int left = start + offset;
                        int right = start + offset + ((len - 1 - offset) / d) * d;
                        // Check whether this group is a palindrome.
                        while (left < right) {
                            if (s.charAt(left) != s.charAt(right)) {
                                changes++;
                            }
                            left += d;
                            right -= d;
                        }
                    }
                    best = Math.min(best, changes);
                }
                cost[start][end] = best;
            }
        }
        // Step 2: DP for exactly k substrings.
        int[][] dp = new int[k + 1][n + 1];
        for (int[] row : dp) {
            Arrays.fill(row, INF);
        }
        dp[0][0] = 0;
        for (int p = 1; p <= k; p++) {
            for (int i = 2 * p; i <= n; i++) {
                // j is the starting index of the last substring.
                for (int j = 2 * (p - 1); j <= i - 2; j++) {
                    if (dp[p - 1][j] == INF) {
                        
                    }
                    dp[p][i] = Math.min(dp[p][i],dp[p - 1][j] + cost[j][i - 1]);
                }
            }
        }
        return dp[k][n];
    }
}
