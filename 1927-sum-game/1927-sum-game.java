class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int mid = n / 2;

        int lsum = 0;
        int rsum = 0;

        int lq = 0;
        int rq = 0;

        for (int i = 0; i < mid; i++) {
            if (num.charAt(i) == '?')
                lq++;
            else
                lsum += num.charAt(i) - '0';
        }

        for (int i = mid; i < n; i++) {
            if (num.charAt(i) == '?')
                rq++;
            else
                rsum += num.charAt(i) - '0';
        }
        int dif = lsum - rsum;
        return 2 * dif != 9 * (rq - lq);
    }
}