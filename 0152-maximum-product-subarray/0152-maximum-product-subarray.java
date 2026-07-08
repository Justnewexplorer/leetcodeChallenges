class Solution {
    public int maxProduct(int[] nums) {
        int prifix = 1;
        int suffix = 1;
        int ans = Integer.MIN_VALUE;
        for(int i = 0; i < nums.length; i++){
            if(prifix == 0)
                prifix = 1;
            if(suffix == 0)
                suffix = 1;
            prifix = prifix * nums[i];
            suffix = suffix * nums[nums.length - i - 1];
            ans = Math.max(ans,Math.max(prifix,suffix));        
        }
        return ans;
    }
}