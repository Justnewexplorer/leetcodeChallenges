class Solution {
    public int minimumPushes(String word) {
        int[] freq = new int[26];
        for(int i = 0 ; i < word.length() ; i++){
            freq[word.charAt(i) - 'a']++;
        }
        
        Arrays.sort(freq);

        int ans = 0;
        int cnt = 0;
        for(int i = 25; i >= 0; i--){
            if(freq[i] == 0)
                continue;
            ans += freq[i] * ((cnt/8) + 1); 
            cnt++;   
        }
        return ans;
    }
}