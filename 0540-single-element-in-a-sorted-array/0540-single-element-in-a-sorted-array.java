class Solution {
    public int singleNonDuplicate(int[] nums) {
        int low = 0;
        int high =  nums.length - 1;
        int ans = 0;
        while(low < high){
            int mid = low + (high - low)/2;
            if(mid % 2 == 1)
                mid--;
            if(nums[mid] == nums[mid + 1])
                low = mid + 2;
            else 
                high = mid;                  
        }
        return nums[low];
    }
}