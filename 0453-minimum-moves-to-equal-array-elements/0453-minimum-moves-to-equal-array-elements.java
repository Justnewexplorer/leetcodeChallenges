class Solution {
    public int minMoves(int[] nums) {
        int sum = 0;
        int min_ele = Integer.MAX_VALUE;
        for(int num : nums){
            min_ele = Math.min(num,min_ele);
            sum += num;
        }
        return sum - nums.length * min_ele;    
    }
}