class Solution {

    class MedianWindow {
        TreeMap<Integer, Integer> left = new TreeMap<>();
        TreeMap<Integer, Integer> right = new TreeMap<>();

        int leftSize = 0, rightSize = 0;
        long leftSum = 0, rightSum = 0;

        void addToLeft(int val) {
            left.put(val, left.getOrDefault(val, 0) + 1);
            leftSize++;
            leftSum += val;
        }

        void addToRight(int val) {
            right.put(val, right.getOrDefault(val, 0) + 1);
            rightSize++;
            rightSum += val;
        }

        void removeFromLeft(int val) {
            int count = left.get(val);
            if (count == 1) left.remove(val);
            else left.put(val, count - 1);

            leftSize--;
            leftSum -= val;
        }

        void removeFromRight(int val) {
            int count = right.get(val);
            if (count == 1) right.remove(val);
            else right.put(val, count - 1);

            rightSize--;
            rightSum -= val;
        }

        void rebalance() {
            while (leftSize < rightSize) {
                int val = right.firstKey();
                removeFromRight(val);
                addToLeft(val);
            }

            while (leftSize > rightSize + 1) {
                int val = left.lastKey();
                removeFromLeft(val);
                addToRight(val);
            }
        }

        void add(int val) {
            if (left.isEmpty() || val <= left.lastKey()) {
                addToLeft(val);
            } else {
                addToRight(val);
            }
            rebalance();
        }

        void remove(int val) {
            if (left.containsKey(val)) {
                removeFromLeft(val);
            } else {
                removeFromRight(val);
            }
            rebalance();
        }

        long getCost() {
            long median = left.lastKey();

            long leftCost = median * leftSize - leftSum;
            long rightCost = rightSum - median * rightSize;

            return leftCost + rightCost;
        }
    }

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;

        // Step 1: Calculate cost of every window of size x
        long[] cost = new long[n - x + 1];

        MedianWindow window = new MedianWindow();

        for (int i = 0; i < x; i++) {
            window.add(nums[i]);
        }

        for (int i = 0; i <= n - x; i++) {
            cost[i] = window.getCost();

            if (i < n - x) {
                window.remove(nums[i]);
                window.add(nums[i + x]);
            }
        }

        // Step 2: DP to select k non-overlapping windows
        long INF = Long.MAX_VALUE / 4;

        long[] prev = new long[n + 1];
        long[] curr = new long[n + 1];

        // prev[i] = minimum cost using 0 windows
        // This is 0 for every prefix.
        for (int t = 1; t <= k; t++) {
            Arrays.fill(curr, INF);

            for (int i = 1; i <= n; i++) {

                // Option 1: Skip current element
                curr[i] = curr[i - 1];

                // Option 2: Select window ending at i - 1
                if (i >= x && prev[i - x] != INF) {
                    long candidate =
                        prev[i - x] + cost[i - x];

                    curr[i] = Math.min(curr[i], candidate);
                }
            }

            long[] temp = prev;
            prev = curr;
            curr = temp;
        }
        return prev[n];
    }
}
