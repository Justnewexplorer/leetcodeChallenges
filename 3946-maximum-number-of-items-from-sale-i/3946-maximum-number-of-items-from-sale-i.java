class Solution {
    public int maximumSaleItems(int[][] items, int budget){
        int n = items.length;
        // Calculate free copies for each item
        int[] free = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j &&
                    items[j][0] % items[i][0] == 0) {
                    free[i]++;
                }
            }
        }
        // dp[b] = maximum items using exactly b money
        int[] dp = new int[budget + 1];
        Arrays.fill(dp, -1_000_000);
        dp[0] = 0;
        // Process each item type
        for (int i = 0; i < n; i++) {
            int price = items[i][1];
            int bonus = free[i];
            int[] next = dp.clone();
            // Process each remainder separately
            for (int r = 0; r < price && r <= budget; r++) {
                int best = -1_000_000;
                int t = 0;
                for (int b = r; b <= budget; b += price, t++) {
                    // Buy at least one copy of this item
                    if (best != -1_000_000) {
                        next[b] = Math.max(
                            next[b],
                            t + bonus + best
                        );
                    }
                    // Save this state for future copies
                    if (dp[b] != -1_000_000) {
                        best = Math.max(best, dp[b] - t);
                    }
                }
            }
            dp = next;
        }
        // Maximum answer within budget
        int ans = 0;
        for (int b = 0; b <= budget; b++) {
            ans = Math.max(ans, dp[b]);
        }
        return ans;
    }
}
