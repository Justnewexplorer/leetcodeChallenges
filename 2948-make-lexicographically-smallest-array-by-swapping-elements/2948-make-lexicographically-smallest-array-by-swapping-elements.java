class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        Integer[] idx = new Integer[n];

        for (int i = 0; i < n; i++)
            idx[i] = i;

        Arrays.sort(idx, (a, b) -> Integer.compare(nums[a], nums[b]));

        int start = 0;
        while (start < n) {
            int end = start;
            while (end + 1 < n &&
                   nums[idx[end + 1]] - nums[idx[end]] <= limit)
                end++;

            ArrayList<Integer> positions = new ArrayList<>();
            ArrayList<Integer> values = new ArrayList<>();

            for (int i = start; i <= end; i++) {
                positions.add(idx[i]);
                values.add(nums[idx[i]]);
            }

            Collections.sort(positions);

            for (int i = 0; i < positions.size(); i++)
                nums[positions.get(i)] = values.get(i);
            start = end + 1;
        }
        return nums;
    }
}