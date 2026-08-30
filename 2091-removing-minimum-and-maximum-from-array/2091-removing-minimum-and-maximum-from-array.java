class Solution {
    public int minimumDeletions(int[] nums) {
        int minIdx = 0;
        int maxIdx = 0;
        for(int i = 1; i < nums.length; i++){
            if(nums[i] > nums[maxIdx])
                maxIdx = i;
            if(nums[i] < nums[minIdx])
                minIdx = i;    
        }
        int a = Math.min(maxIdx,minIdx);
        int b = Math.max(maxIdx,minIdx);
        return Math.min(Math.min(b + 1,nums.length - a), a + 1 + nums.length - b);
    }
}