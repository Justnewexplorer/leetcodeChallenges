class Solution {
    private String buildPalindrome(StringBuilder half, char mid, int n) {
        StringBuilder result = new StringBuilder(half);
        if (n % 2 != 0)
            result.append(mid);
        for (int i = half.length() - 1; i >= 0; i--)
            result.append(half.charAt(i));
        return result.toString();
    }

    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] freq = new int[26];
        int[] hfreq = new int[26];
        char mid = 0;

        for (char c : s.toCharArray())
            freq[c - 'a']++;

        int odd = 0;
        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 != 0) {
                odd++;
                mid = (char)('a' + i);
            }
            hfreq[i] = freq[i] / 2;
        }

        if (odd > 1 || (n % 2 == 0 && odd == 1))
            return "";

        int halfLen = n / 2;
        StringBuilder half = new StringBuilder();

        for (int i = 0; i < halfLen; i++) {
            int idx = target.charAt(i) - 'a';
            if (hfreq[idx] > 0) {
                half.append(target.charAt(i));
                hfreq[idx]--;
            } 
            else {
                for (int j = idx + 1; j < 26; j++) {
                    if (hfreq[j] > 0) {
                        half.append((char)('a' + j));
                        hfreq[j]--;
                        for (int k = 0; k < 26; k++)
                            while (hfreq[k] > 0) {
                                half.append((char)('a' + k));
                                hfreq[k]--;
                            }
                        return buildPalindrome(half, mid, n);
                    }
                }
                break;
            }
        }

        if (half.length() == halfLen) {
            String result = buildPalindrome(half, mid, n);
            if (result.compareTo(target) > 0)
                return result;
        }

        for (int i = half.length() - 1; i >= 0; i--) {
            hfreq[half.charAt(i) - 'a']++;

            int idx = half.charAt(i) - 'a';

            for (int j = idx + 1; j < 26; j++) {
                if (hfreq[j] > 0) {
                    StringBuilder newHalf = new StringBuilder();
                    for (int k = 0; k < i; k++)
                        newHalf.append(half.charAt(k));
                    newHalf.append((char)('a' + j));
                    hfreq[j]--;
                    for (int k = 0; k < 26; k++)
                        while (hfreq[k] > 0) {
                            newHalf.append((char)('a' + k));
                            hfreq[k]--;
                        }
                    return buildPalindrome(newHalf, mid, n);
                }
            }
        }
        return "";
    }
}