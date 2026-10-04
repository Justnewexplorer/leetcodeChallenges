class Solution {
    public boolean checkValidString(String s) {
        Stack<Integer> openbr = new Stack<>();
        Stack<Integer> astrikbr = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '(')
                openbr.push(i);
            else if (ch == '*')
                astrikbr.push(i);
            else {
                if (!openbr.isEmpty())
                    openbr.pop();
                else if (!astrikbr.isEmpty())
                    astrikbr.pop();
                else
                    return false;
            }
        }

        while (!openbr.isEmpty()) {
            if (astrikbr.isEmpty())
                return false;

            if (openbr.pop() > astrikbr.pop())
                return false;
        }
        return true;
    }
}