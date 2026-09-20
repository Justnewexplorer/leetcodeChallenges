class Solution {
    public int reverseDegree(String s) {
        int ans = 0;

        for (int i = 0; i < s.length(); i++) {
            int revch = 'z' - s.charAt(i) + 1;
            ans += (i + 1) * revch;
        }

        return ans;
    }
}