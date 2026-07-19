class Solution {
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length())
            return false;

        HashMap<Character, Character> map = new HashMap<>();
        HashSet<Character> used = new HashSet<>();

        for (int i = 0; i < s.length(); i++) {
            char ori = s.charAt(i);
            char repl = t.charAt(i);
            if (!map.containsKey(ori)) {
                if (used.contains(repl))
                    return false;
                map.put(ori, repl);
                used.add(repl);
            } else {
                if (map.get(ori) != repl)
                    return false;
            }
        }
        return true;
    }
}