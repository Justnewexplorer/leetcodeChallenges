class Solution {

    private int solve(int[] nums,int l , int r, Integer[][] dp){
        if(l == r)
            return nums[l];
        if(dp[l][r] != null)
            return dp[l][r];
        int left = nums[l] - solve(nums,l + 1,r,dp);
        int right = nums[r] - solve(nums,l,r - 1,dp);
        dp[l][r] = Math.max(left,right);
        return dp[l][r];        
    }

    public boolean predictTheWinner(int[] nums) {
        Integer[][] dp = new Integer[nums.length][nums.length];
        return solve(nums,0,nums.length - 1,dp) >= 0;
    }
}