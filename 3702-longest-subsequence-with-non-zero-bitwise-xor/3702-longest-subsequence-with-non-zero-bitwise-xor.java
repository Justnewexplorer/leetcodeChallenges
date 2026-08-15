class Solution {
    public int longestSubsequence(int[] nums) {
        int xor = 0;
        boolean nonZero = false;
        for(int i : nums){
            xor ^= i;
            if(i != 0)
                nonZero = true;
        }
        if(xor != 0)
            return nums.length;
        return nonZero ? nums.length - 1 : 0;
    }
}