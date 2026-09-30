class Solution {
    public int[] maxDepthAfterSplit(String seq) {
        int[] ans = new int[seq.length()];
        int dept = 0;

        for(int i = 0; i < seq.length(); i++){
            if(seq.charAt(i) == '('){
                dept++;
                ans[i] = dept % 2 == 0 ? 0 : 1;
            }
            else {
                ans[i] = dept % 2 == 0 ? 0 : 1;
                dept--;
            }
        }
        return ans;
    }
}