class Solution {
    private int[][] minSlots;
    private final int[] E2 = new int[10];
    private final int[] E3 = new int[10];
    private final int[] E5 = new int[10];
    private final int[] E7 = new int[10];

    public String smallestNumber(String num, long t) {
        int a = 0, b = 0, c = 0, d = 0;
        while (t % 2 == 0) { t /= 2; a++; }
        while (t % 3 == 0) { t /= 3; b++; }
        while (t % 5 == 0) { t /= 5; c++; }
        while (t % 7 == 0) { t /= 7; d++; }
        if (t != 1) return "-1";

        E2[2]=1; E2[4]=2; E2[6]=1; E2[8]=3;
        E3[3]=1; E3[6]=1; E3[9]=2;
        E5[5]=1;
        E7[7]=1;

        minSlots = new int[a+1][b+1];
        int[][] opts = {{1,0},{0,1},{2,0},{1,1},{3,0},{0,2}}; // digits 2,3,4,6,8,9
        for (int x = 0; x <= a; x++) {
            for (int y = 0; y <= b; y++) {
                if (x == 0 && y == 0) { minSlots[x][y] = 0; continue; }
                int best = Integer.MAX_VALUE;
                for (int[] op : opts) {
                    int nx = Math.max(0, x - op[0]);
                    int ny = Math.max(0, y - op[1]);
                    if (nx == x && ny == y) continue;
                    best = Math.min(best, 1 + minSlots[nx][ny]);
                }
                minSlots[x][y] = best;
            }
        }

        int n = num.length();
        int[] digits = new int[n];
        for (int i = 0; i < n; i++) digits[i] = num.charAt(i) - '0';

        int[] pA = new int[n+1], pB = new int[n+1], pC = new int[n+1], pD = new int[n+1];
        for (int i = 0; i < n; i++) {
            int dg = digits[i];
            pA[i+1] = pA[i] + E2[dg];
            pB[i+1] = pB[i] + E3[dg];
            pC[i+1] = pC[i] + E5[dg];
            pD[i+1] = pD[i] + E7[dg];
        }

        int firstZero = n;
        for (int i = 0; i < n; i++) if (digits[i] == 0) { firstZero = i; break; }

        if (firstZero == n) {
            if (pA[n] >= a && pB[n] >= b && pC[n] >= c && pD[n] >= d) return num;
        }

        int upper = Math.min(n - 1, firstZero);
        for (int i = upper; i >= 0; i--) {
            int a1 = Math.max(0, a - pA[i]);
            int b1 = Math.max(0, b - pB[i]);
            int c1 = Math.max(0, c - pC[i]);
            int d1 = Math.max(0, d - pD[i]);
            int R = n - 1 - i;
            for (int v = digits[i] + 1; v <= 9; v++) {
                int a2 = Math.max(0, a1 - E2[v]);
                int b2 = Math.max(0, b1 - E3[v]);
                int c2 = Math.max(0, c1 - E5[v]);
                int d2 = Math.max(0, d1 - E7[v]);
                int need = c2 + d2 + minSlots[a2][b2];
                if (need <= R) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(num, 0, i);
                    sb.append((char) ('0' + v));
                    fill(sb, R, a2, b2, c2, d2);
                    return sb.toString();
                }
            }
        }

        int minTotal = c + d + minSlots[a][b];
        int L = Math.max(n + 1, minTotal);
        StringBuilder sb = new StringBuilder();
        fill(sb, L, a, b, c, d);
        return sb.toString();
    }

    private void fill(StringBuilder sb, int R, int a1, int b1, int c1, int d1) {
        for (int pos = 0; pos < R; pos++) {
            int rem = R - pos - 1;
            for (int v = 1; v <= 9; v++) {
                int a2 = Math.max(0, a1 - E2[v]);
                int b2 = Math.max(0, b1 - E3[v]);
                int c2 = Math.max(0, c1 - E5[v]);
                int d2 = Math.max(0, d1 - E7[v]);
                int need = c2 + d2 + minSlots[a2][b2];
                if (need <= rem) {
                    sb.append((char) ('0' + v));
                    a1 = a2; b1 = b2; c1 = c2; d1 = d2;
                    break;
                }
            }
        }
    }
}