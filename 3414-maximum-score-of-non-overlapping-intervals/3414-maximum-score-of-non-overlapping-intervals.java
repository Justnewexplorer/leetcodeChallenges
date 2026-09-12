import java.util.*;

class Solution {
    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        long[] l = new long[n], r = new long[n], w = new long[n];
        for (int i = 0; i < n; i++) {
            l[i] = intervals.get(i).get(0);
            r[i] = intervals.get(i).get(1);
            w[i] = intervals.get(i).get(2);
        }

        List<Integer> result = new ArrayList<>();
        List<Integer> forbidden = new ArrayList<>(); 
        int rem = 4, scanPos = 0;

        while (rem > 0) {
            List<Integer> F = new ArrayList<>();
            for (int i = scanPos; i < n; i++) {
                boolean ok = true;
                for (int f : forbidden) {
                    if (!(r[f] < l[i] || r[i] < l[f])) { ok = false; break; }
                }
                if (ok) F.add(i);
            }
            if (F.isEmpty()) break;

            int m = F.size();

            Integer[] byR = F.toArray(new Integer[0]);
            Arrays.sort(byR, (a, b) -> Long.compare(r[a], r[b]));
            long[] rVals = new long[m];
            for (int i = 0; i < m; i++) rVals[i] = r[byR[i]];

            long[][] preR = new long[rem + 1][m + 1];
            for (int i = 1; i <= m; i++) {
                int idx = byR[i - 1];
                int p = firstGE(rVals, m, l[idx]); 
                for (int k = 1; k <= rem; k++)
                    preR[k][i] = Math.max(preR[k][i - 1], w[idx] + preR[k - 1][p]);
            }

            Integer[] byL = F.toArray(new Integer[0]);
            Arrays.sort(byL, (a, b) -> Long.compare(l[a], l[b]));
            long[] lVals = new long[m];
            for (int i = 0; i < m; i++) lVals[i] = l[byL[i]];

            long[][] sufL = new long[rem + 1][m + 1];
            for (int i = m - 1; i >= 0; i--) {
                int idx = byL[i];
                int succ = firstGT(lVals, m, r[idx]); 
                for (int k = 1; k <= rem; k++)
                    sufL[k][i] = Math.max(sufL[k][i + 1], w[idx] + sufL[k - 1][succ]);
            }

            long target = preR[rem][m];
            if (target == 0) break;

            int committed = -1;
            for (int t : F) {
                int idx1 = firstGE(rVals, m, l[t]);   
                int idx2 = firstGT(lVals, m, r[t]);   

                long best = 0;
                for (int a = 0; a <= rem - 1; a++) {
                    int b = rem - 1 - a;
                    best = Math.max(best, preR[a][idx1] + sufL[b][idx2]);
                }
                if (w[t] + best == target) { committed = t; break; }
            }
            if (committed == -1) break;

            result.add(committed);
            forbidden.add(committed);
            rem--;
            scanPos = committed + 1;
        }

        int[] ans = new int[result.size()];
        for (int i = 0; i < ans.length; i++) ans[i] = result.get(i);
        return ans;
    }

    private int firstGE(long[] arr, int limit, long x) {
        int lo = 0, hi = limit;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (arr[mid] < x) lo = mid + 1; else hi = mid;
        }
        return lo;
    }

    private int firstGT(long[] arr, int limit, long x) {
        int lo = 0, hi = limit;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (arr[mid] <= x) lo = mid + 1; else hi = mid;
        }
        return lo;
    }
}