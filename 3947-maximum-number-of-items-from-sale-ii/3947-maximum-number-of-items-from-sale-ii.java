class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int[] freq = new int[n + 1];
        int minPrice = Integer.MAX_VALUE;

        for (int[] item : items) {
            freq[item[0]]++;
            minPrice = Math.min(minPrice, item[1]);
        }

        // Calculate bonus copies available for each item
        int[] gain = new int[n + 1];

        for (int factor = 1; factor <= n; factor++) {
            for (int j = factor; j <= n; j += factor) {
                gain[factor] += freq[j];
            }

            gain[factor]--;
        }

        // Process cheaper bonus purchases first
        Arrays.sort(items, (a, b) ->
            Integer.compare(a[1], b[1])
        );

        long remaining = budget;
        long ans = 0;

        for (int[] item : items) {

            int factor = item[0];
            int price = item[1];

            if (price >= 2L * minPrice) {
                break;
            }

            long copies = Math.min(
                (long) gain[factor],
                remaining / price
            );

            ans += 2L * copies;
            remaining -= copies * price;
        }

        // Spend remaining budget on cheapest normal copies
        ans += remaining / minPrice;
        return (int) ans;
    }
}
