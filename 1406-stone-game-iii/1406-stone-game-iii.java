class Solution {

    private int solve(int[] stoneValue, int i,Integer[] dp){
        if(i >= stoneValue.length)
                return 0;
        if(dp[i] != null)
            return dp[i];        
        int sum = 0;
        int ans = Integer.MIN_VALUE;
        for(int j = 0 ; j < 3 && i + j < stoneValue.length; j++){
            sum += stoneValue[i + j];
            ans = Math.max(ans,sum - solve(stoneValue,i + j + 1,dp));      
        }
        return dp[i] = ans;
    }

    public String stoneGameIII(int[] stoneValue) {
        Integer[] dp = new Integer[stoneValue.length];
        int diff = solve(stoneValue,0,dp);
        if(diff > 0)
            return "Alice";
        else if(diff < 0)
            return "Bob";
        return "Tie";        
    }
}