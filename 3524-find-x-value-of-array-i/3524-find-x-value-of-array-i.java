class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] dp = new long[k];
        long[] ans = new long[k];

        for(int num : nums){
            long[] curDp = new long[k];
            int mod = num % k;
            curDp[mod]++;

            for(int i = 0; i < k; i++){
                int newRem = (int)((long)i * mod % k);
                curDp[newRem] += dp[i];
            }

            for(int i=0;i<k;i++)
                ans[i] += curDp[i];
            dp = curDp;    
        }
        return ans;
    }
}