class Solution {
    public boolean canAliceWin(int n) {
        int step = 0;
        int take = 10;
        while(n >= take){
            n -= take;
            take -= 1;
            step++;
        }
        if(step % 2 != 0)
            return true;
        return false;        
    }
}