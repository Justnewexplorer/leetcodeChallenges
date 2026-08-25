class Solution {
    public int missingMultiple(int[] nums, int k) {
        int mul = k;
        while(true){
            boolean status = false;
            for(int i : nums){
                if(i == mul){
                    status = true;
                    break;
                }        
            }
            if(!status)
                return mul;
            mul += k;    
        }
    }
}