class Solution {
    public int minOperations(int[] nums, int x) {
        int n = nums.length;
        int sum = 0;
        HashMap<Integer ,Integer> map = new HashMap<>();
        map.put(0,-1);
        for(int i = 0; i < n; i++){
            sum += nums[i];
            map.put(sum,i);
        }

        if(sum < x)
            return -1;

        int remsum = sum - x;
        int largest = Integer.MIN_VALUE;
        sum = 0;
        for(int i = 0 ; i < n ; i++){
            sum += nums[i];
            if(map.containsKey(sum - remsum))
                largest = Math.max(largest, i - map.get(sum - remsum));
        }
        return largest == Integer.MIN_VALUE ? -1 : n - largest;   
    }
}