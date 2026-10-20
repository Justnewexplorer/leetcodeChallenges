
import java.util.*;

class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;

        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        int ans = 0;

        for (int k = 1; k <= n; k++) {
            if (n % k != 0) {
                continue;
            }
            boolean valid = true;

            for (int start = 0; start < n; start += k) {

                HashMap<Integer, Integer> freq = new HashMap<>();

                for (int i = start; i < start + k; i++) {
                    freq.merge(nums[i], 1, Integer::sum);
                    freq.merge(sorted[i], -1, Integer::sum);
                }
                for (int count : freq.values()) {
                    if (count != 0) {
                        valid = false;
                        break;
                    }
                }
                if (!valid) {
                    break;
                }
                int descents = 0;
                for (int i = start; i < start + k; i++) {
                    int next = start + (i - start + 1) % k;

                    if (nums[i] > nums[next]) {
                        descents++;
                    }
                }
                if (descents > 1) {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                ans += k;
            }
        }
        return ans;
    }
}
