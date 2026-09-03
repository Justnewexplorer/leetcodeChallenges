class Solution {
    public boolean uniformArray(int[] nums1) {
        int minOdd = Integer.MAX_VALUE;
        int odd = 0;
        for (int x : nums1) {
            if (x % 2 != 0) {
                odd++;
                minOdd = Math.min(minOdd, x);
            }
        }
        if (odd == 0)
            return true;
        for (int x : nums1) {
            if (x % 2 == 0 && x <= minOdd)
                return false;
        }
        return true;
    }
}