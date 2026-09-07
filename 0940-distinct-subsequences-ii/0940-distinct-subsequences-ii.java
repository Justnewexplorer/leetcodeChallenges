class Solution {
    public int distinctSubseqII(String s) {
        long[] last = new long[26];
        long dp = 1;
        long mod = 1_000_000_007;

        for (char c : s.toCharArray()) {
            int idx = c - 'a';
            long newDp = (2 * dp - last[idx] + mod) % mod;
            last[idx] = dp;
            dp = newDp;
        }
        return (int)((dp - 1 + mod) % mod);
    }
}