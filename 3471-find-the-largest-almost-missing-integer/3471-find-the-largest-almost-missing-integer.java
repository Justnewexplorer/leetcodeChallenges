class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;

        // Only one subarray exists: the entire array.
        if (k == n) {
            int ans = 0;

            for (int num : nums) {
                ans = Math.max(ans, num);
            }

            return ans;
        }

        int[] freq = new int[51];

        for (int num : nums) {
            freq[num]++;
        }

        // k == 1:
        // Each element itself is a subarray.
        if (k == 1) {
            int ans = -1;

            for (int num = 0; num <= 50; num++) {
                if (freq[num] == 1) {
                    ans = num;
                }
            }

            return ans;
        }

        // 1 < k < n:
        // Only the first and last positions can belong
        // to exactly one subarray of length k.
        int ans = -1;

        if (freq[nums[0]] == 1) {
            ans = Math.max(ans, nums[0]);
        }

        if (freq[nums[n - 1]] == 1) {
            ans = Math.max(ans, nums[n - 1]);
        }

        return ans;
    }
}