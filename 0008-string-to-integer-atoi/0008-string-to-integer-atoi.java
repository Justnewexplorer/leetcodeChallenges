class Solution {
    public int myAtoi(String s) {
        int i = 0;
        while (i < s.length() && s.charAt(i) == ' ') 
            i++;
        if(i == s.length())
            return 0;
        long num = 0;
        int sign = 1;
        char ch = s.charAt(i);
        if(ch == '-' || ch == '+'){
            sign = (ch == '-') ? -1 : 1;
            i++;
        }
        while(i < s.length() && Character.isDigit(s.charAt(i))){
            num = num * 10 + (s.charAt(i) - '0');
            if(num > Integer.MAX_VALUE && sign == 1)
                return Integer.MAX_VALUE;
            else if(-num < Integer.MIN_VALUE && sign == -1)
                return Integer.MIN_VALUE;
            i++;        
        }
        return (int) num * sign; 
    }
}