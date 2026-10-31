class Solution {
    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int MOD = 1_000_000_007;

        // go[i][j][0] = destination when moving right
        // go[i][j][1] = destination when moving down
        int[][][] go = new int[m][n][2];

        // -1 represents an invalid move
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                go[i][j][0] = -1;
                go[i][j][1] = -1;
            }
        }

        // Precompute destinations
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                // Try moving right
                go[i][j][0] = getDestination(grid, i, j + 1, 0);

                // Try moving down
                go[i][j][1] = getDestination(grid, i + 1, j, 1);
            }
        }

        // Dynamic Programming
        long[][] dp = new long[m][n];

        dp[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (dp[i][j] == 0) {
                    continue;
                }

                // Move right
                int right = go[i][j][0];

                if (right != -1) {
                    int x = right / n;
                    int y = right % n;

                    dp[x][y] = (dp[x][y] + dp[i][j]) % MOD;
                }

                // Move down
                int down = go[i][j][1];

                if (down != -1) {
                    int x = down / n;
                    int y = down % n;

                    dp[x][y] = (dp[x][y] + dp[i][j]) % MOD;
                }
            }
        }

        return (int) dp[m - 1][n - 1];
    }

    // direction = 0 means right
    // direction = 1 means down
    private int getDestination(int[][] grid, int r, int c, int direction) {
        int m = grid.length;
        int n = grid[0].length;

        // Process consecutive mirror reflections
        while (r < m && c < n && grid[r][c] == 1) {

            if (direction == 0) {
                // Moving right into mirror:
                // reflect down and move below the mirror
                r++;
                direction = 1;
            } else {
                // Moving down into mirror:
                // reflect right and move right of the mirror
                c++;
                direction = 0;
            }
        }

        // Out of bounds means invalid path
        if (r >= m || c >= n) {
            return -1;
        }

        // Return flattened destination
        return r * n + c;
    }
}