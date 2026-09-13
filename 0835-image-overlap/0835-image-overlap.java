class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        int ans = 0;

        for (int dr = -(n - 1); dr <= n - 1; dr++) {
            for (int dc = -(n - 1); dc <= n - 1; dc++) {
                int overlap = 0;
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        int newRow = i + dr;
                        int newCol = j + dc;
                        if (newRow >= 0 && newRow < n &&
                            newCol >= 0 && newCol < n) {
                            if (img1[i][j] == 1 &&
                                img2[newRow][newCol] == 1) {
                                overlap++;
                            }
                        }
                    }
                }
                ans = Math.max(ans, overlap);
            }
        }
        return ans;
    }
}