class Solution {

    public int sumPosible(int[] nums,int div){
        int sum = 0;
        for(int i : nums)
            sum += (i + div - 1)/div;
        return sum;    
    }

    public int smallestDivisor(int[] nums, int threshold) {
        int low = 1;
        int high = Arrays.stream(nums).max().getAsInt();
        while(low <= high){
            int mid = low + (high - low)/2;
            if(sumPosible(nums,mid) <= threshold)
                high = mid - 1;
            else
                low = mid + 1;    
        }
        return low;
    }
}