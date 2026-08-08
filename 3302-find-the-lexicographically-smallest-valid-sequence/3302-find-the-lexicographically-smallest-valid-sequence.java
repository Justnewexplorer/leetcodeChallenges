class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        int[] suffix = new int[m];
        int i = n - 1;
        int j = m - 1;

        while (i >= 0 && j >= 0) {
            if (word1.charAt(i) == word2.charAt(j)){
                suffix[j] = i;
                j--;
            }
            i--;
        }

        if (n < m)
            return new int[0];

        int[] ans = new int[m];
        i = 0;
        j = 0;
        boolean mismatchUsed = false;

        while (i < n && j < m) {
            if (word1.charAt(i) == word2.charAt(j)){
                ans[j] = i;
                i++;
                j++;
            }
            else if (!mismatchUsed && (j == m - 1 || i < suffix[j + 1])){
                ans[j] = i;
                mismatchUsed = true;
                i++;
                j++;
            }
            else {
                i++;
            }
        }
        if (j < m)
            return new int[0];
        return ans;
    }
}