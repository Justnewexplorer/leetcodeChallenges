class Solution {
    public List<String> removeInvalidParentheses(String s) {
        int leftRemove = 0, rightRemove = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                leftRemove++;
            } else if (c == ')') {
                if (leftRemove > 0) leftRemove--;
                else rightRemove++;
            }
        }

        Set<String> result = new HashSet<>();
        dfs(s, 0, leftRemove, rightRemove, 0, new StringBuilder(), result);
        return new ArrayList<>(result);
    }

    private void dfs(String s, int idx, int leftRemove, int rightRemove, int open,
                      StringBuilder sb, Set<String> result) {
        // Prune: can't possibly reach a valid state
        if (leftRemove < 0 || rightRemove < 0 || open < 0) return;

        if (idx == s.length()) {
            if (leftRemove == 0 && rightRemove == 0 && open == 0) {
                result.add(sb.toString());
            }
            return;
        }

        char c = s.charAt(idx);
        int sbLenBefore = sb.length();

        if (c == '(') {
            // try removing it
            dfs(s, idx + 1, leftRemove - 1, rightRemove, open, sb, result);
            // try keeping it
            sb.append(c);
            dfs(s, idx + 1, leftRemove, rightRemove, open + 1, sb, result);
            sb.setLength(sbLenBefore);
        } else if (c == ')') {
            // try removing it
            dfs(s, idx + 1, leftRemove, rightRemove - 1, open, sb, result);
            // try keeping it (only valid if there's an unmatched '(' to close)
            if (open > 0) {
                sb.append(c);
                dfs(s, idx + 1, leftRemove, rightRemove, open - 1, sb, result);
                sb.setLength(sbLenBefore);
            }
        } else {
            // regular letter, must keep
            sb.append(c);
            dfs(s, idx + 1, leftRemove, rightRemove, open, sb, result);
            sb.setLength(sbLenBefore);
        }
    }
}