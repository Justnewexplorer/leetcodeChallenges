class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int[] freq = new int[26];

        for (int i = 0; i < s.length(); i++)
            freq[s.charAt(i) - 'a']++;

        StringBuilder prefix = new StringBuilder();

        for (int i = 0; i < target.length(); i++) {
            int idx = target.charAt(i) - 'a';

            if (freq[idx] > 0) {
                prefix.append(target.charAt(i));
                freq[idx]--;
                continue;
            }

            for (int j = idx + 1; j < 26; j++) {
                if (freq[j] > 0) {
                    StringBuilder result = new StringBuilder(prefix);
                    result.append((char)('a' + j));
                    freq[j]--;

                    for (int k = 0; k < 26; k++)
                        while (freq[k] > 0) {
                            result.append((char)('a' + k));
                            freq[k]--;
                        }
                    return result.toString();
                }
            }
            break;
        }

        for (int i = prefix.length() - 1; i >= 0; i--) {
            int idx = target.charAt(i) - 'a';
            freq[idx]++;

            for (int j = idx + 1; j < 26; j++) {
                if (freq[j] > 0) {
                    StringBuilder result = new StringBuilder();
                    result.append(target, 0, i);
                    result.append((char)('a' + j));
                    freq[j]--;

                    for (int k = 0; k < 26; k++)
                        while (freq[k] > 0) {
                            result.append((char)('a' + k));
                            freq[k]--;
                        }
                    return result.toString();
                }
            }
        }
        return "";
    }
}