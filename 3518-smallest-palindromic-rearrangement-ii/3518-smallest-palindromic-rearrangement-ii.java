class Solution {

    public long comb(int n,int r,long k){
        long com = 1;
        r = Math.min(r, n - r);
        for(int i = 1; i <= r; i++){
            com = com * (n - r + i)/i;
            if(com >= k)
                return k;
        }
        com = Math.min(com, k);
        return com;
    }

    public long cntper(int[] freq,long k){
        int total = 0;
        for(int f : freq)
            total += f;
        long ans = 1;
        for(int f : freq){
            if(f > 0){
                ans *= comb(total,f,k);
                if (ans >= k)
                    return k;
                total -= f;
            }
        }
        
        return ans;    
    }

    public String smallestPalindrome(String s, int k) {
        int[] freq = new int[26];
        for(int i = 0; i < s.length() ; i++){
            freq[s.charAt(i) - 'a']++;
        }
        String mid = "";
        for (int i = 0; i < 26; i++) {
            if ((freq[i] & 1) == 1) {
                mid = String.valueOf((char) ('a' + i));
            }
            freq[i] /= 2;
        }
        StringBuilder left = new StringBuilder();

        while (left.length() < s.length() / 2) {
        boolean found = false;
        for (int i = 0; i < 26; i++) {
            if (freq[i] == 0)
                continue;
            freq[i]--;
            long ways = cntper(freq, k);
            if (ways >= k) {
                left.append((char) ('a' + i));
                found = true;
                break;
            }
            freq[i]++;
            k -= ways;
        }
        if (!found)
            return "";
    }
    String right = new StringBuilder(left).reverse().toString();
    return left.toString() + mid + right;
    }
}