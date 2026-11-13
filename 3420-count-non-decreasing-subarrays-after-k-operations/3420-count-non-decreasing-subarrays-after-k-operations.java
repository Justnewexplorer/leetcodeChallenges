class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;
        long ans = 0;
        long cost = 0;
        int j = n - 1;
        Deque<int[]> dq = new ArrayDeque<>();
        for (int i = n - 1; i >= 0; i--) {
            int num = nums[i];
            int count = 1;
            // Merge smaller groups into the current group.
            while (!dq.isEmpty() && dq.peekLast()[0] < num) {
                int[] last = dq.pollLast();
                int value = last[0];
                int size = last[1];
                count += size;
                cost += (long) (num - value) * size;
            }
            dq.offerLast(new int[]{num, count});
            // Remove elements from the right until cost <= k.
            while (cost > k) {
                int[] first = dq.pollFirst();
                int value = first[0];
                int size = first[1];
                cost -= value - nums[j];
                j--;
                if (size > 1) {
                    dq.offerFirst(new int[]{value, size - 1});
                }
            }
            // Every subarray ending between i and j is valid.
            ans += j - i + 1;
        }
        return ans;
    }
}