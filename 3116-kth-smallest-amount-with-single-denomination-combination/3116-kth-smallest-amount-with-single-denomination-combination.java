class Solution {

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    private long count(int[] coins, long x) {
        long sum = 0;
        int n = coins.length;

        for (int mask = 1; mask < (1 << n); mask++) {
            long lcm = 1;
            int bits = 0;

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    bits++;
                    long g = gcd(lcm, coins[i]);
                    lcm = lcm / g * coins[i];
                    if (lcm > x)
                        break;
                }
            }
            if (lcm > x)
                continue;
            if (bits % 2 == 1)
                sum += x / lcm;
            else
                sum -= x / lcm;
        }
        return sum;
    }

    public long findKthSmallest(int[] coins, int k) {
        int minCoin = coins[0];

        for (int coin : coins) {
            minCoin = Math.min(minCoin, coin);
        }
        long low = 1;
        long high = (long) minCoin * k;
        while (low < high) {
            long mid = low + (high - low) / 2;
            if (count(coins, mid) >= k)
                high = mid;
            else
                low = mid + 1;
        }
        return low;
    }
}