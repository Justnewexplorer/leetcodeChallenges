class Solution {
    public int totalNumbers(int[] digits) {
        int[] freq = new int[10];
        for(int i : digits)
            freq[i]++;

        int ans = 0;    

        for(int A = 0; A <= 9; A++){
            for(int B = 0; B <= 9; B++){
                for(int C = 0; C <= 9; C++){
                    if(A != 0 && C % 2 == 0){
                        freq[A]--;
                        freq[B]--;
                        freq[C]--;
                        if(freq[A] >= 0 && freq[B] >= 0 && freq[C] >= 0)
                            ans++;
                        freq[A]++;
                        freq[B]++;
                        freq[C]++;
                    }
                }
            }
        }
        return ans;    
    }
}