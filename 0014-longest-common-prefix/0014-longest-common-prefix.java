class Solution {
    public String longestCommonPrefix(String[] strs) {
        if(strs.length == 0)
            return "";
        if(strs.length == 1)
            return strs[0];    
        String common = strs[0];
        for(int i = 1; i < strs.length; i++){
            int j = 0;
            int len = Math.min(common.length(),strs[i].length());
            while(j < len && common.charAt(j) == strs[i].charAt(j))
                j++;
            common = common.substring(0,j);
            if(common.isEmpty())
                return "";    
        }
        return common.toString();
    }
}