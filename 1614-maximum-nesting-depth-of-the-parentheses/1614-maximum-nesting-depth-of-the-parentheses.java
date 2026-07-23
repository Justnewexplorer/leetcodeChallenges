class Solution {
    public int maxDepth(String s) {
        int count = 0;
        int maxcnt = 0;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '(')
                count++;
            maxcnt = Math.max(count,maxcnt);    
            if(s.charAt(i) == ')')
                count--;         
        }
        return maxcnt;            
    }
}