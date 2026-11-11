class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {
        long minN = Math.min(need1,need2);
        long maxN = Math.max(need1,need2);

        long price1 = (long) need1 * cost1 + (long) need2 * cost2;

        long price2 = minN * costBoth + (need1 - minN) * cost1 + (need2 - minN) * cost2;

        long price3 = maxN * costBoth;

        return Math.min(price1,Math.min(price2,price3));
    }
}