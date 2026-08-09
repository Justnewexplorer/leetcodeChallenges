class Solution {

    private int solve(int[] piles,int i,int M,Integer[][] dp){
        if(i >= piles.length)
            return 0;
        if(dp[i][M] != null)
            return dp[i][M];
        int sum = 0;
        int ans = Integer.MIN_VALUE;
        for(int x = 1; x <= 2 * M && i + x <= piles.length; x++){
            sum += piles[i + x - 1];
            int currsum = sum - solve(piles,i + x,Math.max(x,M),dp);
            ans = Math.max(currsum,ans);
        }
        return dp[i][M] = ans;        
    }

    public int stoneGameII(int[] piles) {
        Integer[][] dp = new Integer[piles.length][piles.length + 1];
        int total = 0;
        for(int t : piles)
            total += t;
        return (total + solve(piles,0,1,dp))/2;    
    }
}