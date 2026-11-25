class Solution {
    public int smallestUniqueSubarray(int[] nums){
        int n = nums.length;

        // Step 1: Build suffix array
        int[] sa = buildSuffixArray(nums);

        // Step 2: Build rank array
        int[] rank = new int[n];

        for (int i = 0; i < n; i++) {
            rank[sa[i]] = i;
        }

        // Step 3: Build LCP array using Kasai's algorithm
        int[] lcp = new int[n];
        int h = 0;

        for (int i = 0; i < n; i++) {

            int r = rank[i];

            if (r == 0) {
                continue;
            }

            int j = sa[r - 1];

            while (i + h < n && j + h < n &&
                   nums[i + h] == nums[j + h]) {
                h++;
            }

            lcp[r] = h;

            if (h > 0) {
                h--;
            }
        }

        // Step 4: Find minimum unique subarray length
        int ans = n;

        for (int i = 0; i < n; i++) {

            int r = rank[i];

            int prevLCP = (r > 0) ? lcp[r] : 0;
            int nextLCP = (r < n - 1) ? lcp[r + 1] : 0;

            int maxLCP = Math.max(prevLCP, nextLCP);

            int suffixLength = n - i;

            if (maxLCP < suffixLength) {
                ans = Math.min(ans, maxLCP + 1);
            }
        }

        return ans;
    }

    // Build suffix array using doubling algorithm
    private int[] buildSuffixArray(int[] nums) {

        int n = nums.length;

        int[] sa = new int[n];
        int[] rank = new int[n];
        int[] temp = new int[n];

        int maxVal = 0;

        for (int x : nums) {
            maxVal = Math.max(maxVal, x);
        }

        for (int i = 0; i < n; i++) {
            sa[i] = i;
            rank[i] = nums[i] - 1;
        }

        int classes = maxVal;

        for (int len = 1; len < n; len *= 2) {

            // Sort by second rank, then first rank
            countingSort(sa, temp, rank, len, classes);
            countingSort(temp, sa, rank, 0, classes);

            int[] newRank = new int[n];

            int newClasses = 1;
            newRank[sa[0]] = 0;

            for (int i = 1; i < n; i++) {

                int a = sa[i - 1];
                int b = sa[i];

                int aSecond = (a + len < n)
                        ? rank[a + len] : -1;

                int bSecond = (b + len < n)
                        ? rank[b + len] : -1;

                if (rank[a] != rank[b] ||
                    aSecond != bSecond) {
                    newClasses++;
                }

                newRank[b] = newClasses - 1;
            }

            rank = newRank;
            classes = newClasses;

            if (classes == n) {
                break;
            }
        }

        return sa;
    }

    // Stable counting sort by rank[index + offset]
    private void countingSort(
            int[] input,
            int[] output,
            int[] rank,
            int offset,
            int classes) {

        int n = input.length;

        int[] count = new int[classes + 1];

        for (int i : input) {

            int key = (i + offset < n)
                    ? rank[i + offset] + 1 : 0;

            count[key]++;
        }

        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {

            int index = input[i];

            int key = (index + offset < n)
                    ? rank[index + offset] + 1 : 0;

            output[--count[key]] = index;
        }
    }
}
