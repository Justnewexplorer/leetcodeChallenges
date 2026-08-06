class Solution {

    private int product(int a){
        int pro = 1;
        while(a > 0){
            int dig = a % 10;
            pro *= dig;
            a /= 10;
        }
        return pro;
    }

    public int smallestNumber(int n, int t) {
        while (product(n) % t != 0) {
            n++;
        }
        return n;
    }
}