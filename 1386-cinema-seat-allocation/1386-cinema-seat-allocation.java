class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        HashMap<Integer , HashSet<Integer>> row = new HashMap<>();
        for (int[] seats : reservedSeats) {
            int r = seats[0];
            int s = seats[1];

            if (s > 1 && s < 10) {
                if (row.containsKey(r)) {
                    row.get(r).add(s);
                } else {
                    HashSet<Integer> set = new HashSet<>();
                    set.add(s);
                    row.put(r, set);
                }
            }
        }
        int ans = (n - row.size()) * 2;

        for (Map.Entry<Integer, HashSet<Integer>> entry : row.entrySet()) {
            HashSet<Integer> set = entry.getValue();

            boolean A = !set.contains(2) && !set.contains(3) && !set.contains(4) && !set.contains(5);
            boolean B = !set.contains(4) && !set.contains(5) && !set.contains(6) && !set.contains(7);
            boolean C = !set.contains(6) && !set.contains(7) && !set.contains(8) && !set.contains(9);

            if (A && C) {
                ans += 2;
            }
            else if (A || B || C) {
                ans += 1;
            }
        }
        return ans;
    }
}