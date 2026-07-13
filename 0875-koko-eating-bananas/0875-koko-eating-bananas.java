class Solution {

    private long totalhours(int[] piles,int speed){
        long total = 0;
        for(int i : piles){
            total += (i + speed - 1) / speed;
        }
        return total;
    }

    public int minEatingSpeed(int[] piles, int h) {
        int low = 1;
        int high = Arrays.stream(piles).max().getAsInt();
        while(low <= high){
            int mid = low + (high - low)/2;
            long total = totalhours(piles,mid);
            if(total <= h)
                high = mid - 1;
            else
                low = mid + 1;    
        }
        return low;
    }
}