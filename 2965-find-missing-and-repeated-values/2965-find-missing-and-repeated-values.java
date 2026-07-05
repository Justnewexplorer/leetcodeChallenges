class Solution {
    public int[] findMissingAndRepeatedValues(int[][] grid) {
        int n = grid.length;
        long N = (long)n * (long)n;
        long sn = (N * (N + 1))/2;
        long s2n = (N * (N + 1) * (2 * N + 1))/6;
        long s = 0;
        long s2 = 0;
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < n ; j++){
                s += grid[i][j];
                s2 += (long)grid[i][j] * (long)grid[i][j];
            }
        }
            long val1 = s - sn;
            long val2 = s2 - s2n;
            val2 = val2 / val1;
            long x = (val1 + val2)/2;
            long y = x - val1;

            return new int[]{(int)x,(int)y};
    }
}