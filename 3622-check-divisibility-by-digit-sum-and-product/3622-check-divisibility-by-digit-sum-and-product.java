class Solution {
    public boolean checkDivisibility(int n) {
        int sum1 = 0;
        int sum2 = 1;
        int ori = n;
        while(n > 0){
            int rev = n % 10;
            sum1 += rev;
            sum2 *= rev;
            n /= 10;
        }
        return ori % (sum1 + sum2) == 0;    
    }
}