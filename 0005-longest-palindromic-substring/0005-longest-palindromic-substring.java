class Solution {
    int start = 0;
    int end = 0;

    private void expand(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            if (right - left + 1 > end - start + 1) {
                start = left;
                end = right;
            }
            left--;
            right++;
        }
    }

    public String longestPalindrome(String s) {
        if (s == null || s.length() < 2)
            return s;
        for (int i = 0; i < s.length(); i++) {
            expand(s, i, i);
            expand(s, i, i + 1);
        }
        return s.substring(start, end + 1);
    }
}