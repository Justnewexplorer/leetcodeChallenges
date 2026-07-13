class Solution {

    private boolean ispossible(int[] bloomDay,int day ,int m ,int k){
        int count = 0;
        int boques = 0;
        for(int i : bloomDay){
            if(i <= day){
                count++;
                if(count == k){
                    boques++;
                    count = 0;
                }
            }
            else
                count = 0;
        }
        return boques >= m;
    }

    public int minDays(int[] bloomDay, int m, int k) {
        if( (long)m * k > bloomDay.length)
            return -1;
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            for(int i : bloomDay){
                min = Math.min(min,i);
                max = Math.max(max,i);
            }
            int low = min;
            int high = max;
            while(low <= high){
                int mid = low + (high - low)/2;
                if(ispossible(bloomDay,mid,m,k))
                    high = mid - 1;
                else
                    low = mid + 1;    
            }
        return low;     
    }
}