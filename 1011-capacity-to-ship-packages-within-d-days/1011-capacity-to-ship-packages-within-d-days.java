class Solution {
    public int requiredDays(int[] weights,int limit){
        int sum = 0;
        int rdays = 1;
        for(int i : weights){
            if(sum + i <= limit){
                sum += i;
            }
            else{
                rdays++;
                sum = i;
            }
        }
        return rdays;    
    }
    public int shipWithinDays(int[] weights, int days) {
        int sum = 0;
        for(int i : weights)
            sum += i;
        int low = Arrays.stream(weights).max().getAsInt();
        int high = sum;
        while(low <= high){
            int mid = low + (high - low)/2;
            if(requiredDays(weights,mid) <= days)
                high = mid - 1;
            else
                low = mid + 1;    
        }
        return low;
    }
}