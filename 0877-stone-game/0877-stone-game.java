class Solution {

    private int solve(int[] piles,int l,int r,Integer[][] dp){
        if(l == r)
            return piles[l];
        if(dp[l][r] != null)
            return dp[l][r];
        int left = piles[l] - solve(piles,l+1,r,dp);
        int right = piles[r] - solve(piles,l,r-1,dp);
        dp[l][r] = Math.max(left,right);
        return dp[l][r];        
    }

    public boolean stoneGame(int[] piles) {
        Integer[][] dp = new Integer[piles.length][piles.length];
        return solve(piles,0,piles.length - 1,dp) > 0;
    }
}