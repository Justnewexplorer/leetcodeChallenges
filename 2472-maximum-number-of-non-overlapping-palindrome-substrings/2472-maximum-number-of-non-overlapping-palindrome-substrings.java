class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        boolean[][] pal = new boolean[n][n];

        for (int len = 1; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                if (s.charAt(i) == s.charAt(j) &&
                    (len <= 2 || pal[i + 1][j - 1])) {
                    pal[i][j] = true;
                }
            }
        }

        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            if (i > 0) dp[i] = dp[i - 1];

            for (int j = 0; j <= i; j++) {
                int len = i - j + 1;

                if (len >= k && pal[j][i]) {
                    int candidate = j == 0 ? 1 : dp[j - 1] + 1;
                    dp[i] = Math.max(dp[i], candidate);
                }
            }
        }

        return dp[n - 1];
    }
}