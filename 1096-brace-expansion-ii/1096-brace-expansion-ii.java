class Solution {
    private String expr;
    private int pos;

    public List<String> braceExpansionII(String expression) {
        expr = expression;
        pos = 0;
        Set<String> result = parseUnion();
        List<String> ans = new ArrayList<>(result);
        Collections.sort(ans);
        return ans;
    }

    private Set<String> parseUnion() {
        Set<String> result = new HashSet<>(parseConcat());
        while (pos < expr.length() && expr.charAt(pos) == ',') {
            pos++; 
            result.addAll(parseConcat());
        }
        return result;
    }

    private Set<String> parseConcat() {
        List<Set<String>> pieces = new ArrayList<>();
        while (pos < expr.length() && expr.charAt(pos) != ',' && expr.charAt(pos) != '}') {
            pieces.add(parsePiece());
        }
        Set<String> result = new HashSet<>();
        result.add("");
        for (Set<String> piece : pieces) {
            Set<String> next = new HashSet<>();
            for (String prefix : result) {
                for (String w : piece) {
                    next.add(prefix + w);
                }
            }
            result = next;
        }
        return result;
    }

    private Set<String> parsePiece() {
        if (expr.charAt(pos) == '{') {
            pos++; 
            Set<String> result = parseUnion();
            pos++; 
            return result;
        } else {
            int start = pos;
            while (pos < expr.length() && Character.isLowerCase(expr.charAt(pos))) {
                pos++;
            }
            return new HashSet<>(Set.of(expr.substring(start, pos)));
        }
    }
}