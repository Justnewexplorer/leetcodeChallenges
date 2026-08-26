class Solution {
    public String shortestBeautifulSubstring(String s, int k) {

        int n = s.length();

        int[] ones = new int[n];
        int count = 0;

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                ones[count++] = i;
            }
        }

        if (count < k)
            return "";

        String ans = "";

        for (int i = 0; i + k - 1 < count; i++) {
            int left = ones[i];
            int right = ones[i + k - 1];

            String curr = s.substring(left, right + 1);

            if (ans.isEmpty()
                    || curr.length() < ans.length()
                    || (curr.length() == ans.length()
                        && curr.compareTo(ans) < 0)) {

                ans = curr;
            }
        }
        return ans;
    }
}