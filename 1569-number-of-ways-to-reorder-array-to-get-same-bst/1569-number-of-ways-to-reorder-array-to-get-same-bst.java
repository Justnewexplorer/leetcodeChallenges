class Solution {

    private static final long MOD = 1_000_000_007L;
    private long[][] comb;

    public int numOfWays(int[] nums) {
        int n = nums.length;
        comb = new long[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            comb[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                comb[i][j] = (comb[i - 1][j - 1] + comb[i - 1][j]) % MOD;
            }
        }
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        long total = ways(list);
        return (int) ((total - 1 + MOD) % MOD);
    }

    private long ways(List<Integer> list) {
        int size = list.size();
        if (size <= 2) {
            return 1;
        }
        int root = list.get(0);
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        for (int i = 1; i < size; i++) {
            if (list.get(i) < root) {
                left.add(list.get(i));
            } else {
                right.add(list.get(i));
            }
        }
        long result = comb[size - 1][left.size()];
        result = result * ways(left) % MOD;
        result = result * ways(right) % MOD;

        return result;
    }
}