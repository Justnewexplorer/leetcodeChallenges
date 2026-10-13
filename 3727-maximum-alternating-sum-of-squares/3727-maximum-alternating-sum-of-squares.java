class Solution {
    public long maxAlternatingSum(int[] nums) {
        if(nums.length == 1)
            return nums[0] * nums[0];
        int[] sq = new int[nums.length];
        for(int i = 0; i < nums.length; i++)
            sq[i] = nums[i] * nums[i];
        Arrays.sort(sq);
        long sum = 0;
        int left = 0;
        int right = sq.length - 1;
        while(left < right){
            sum += sq[right--] - sq[left++];
            if(left == right)
                sum += sq[left];
        }
        return sum;    
    }
}