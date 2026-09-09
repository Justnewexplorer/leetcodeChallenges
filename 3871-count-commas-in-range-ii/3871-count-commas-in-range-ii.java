class Solution {
    public long countCommas(long n) {
        long srt = 1000;
        long com = 1;
        long ans = 0;
        while(srt <= n){
            long end = srt * 1000 - 1;
            long count = Math.min(n, end) - srt + 1;
            ans += count * com;
            srt *= 1000;
            com++;        
        }
        return ans;
    }
}