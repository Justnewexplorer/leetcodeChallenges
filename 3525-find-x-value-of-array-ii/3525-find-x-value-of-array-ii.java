class Solution {
    int k, n;
    int[][][] mat;   
    int[] prod;      

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.k = k;
        this.n = nums.length;
        mat = new int[4 * n][][];
        prod = new int[4 * n];
        build(1, 0, n - 1, nums);

        int q = queries.length;
        int[] result = new int[q];
        int startRemainder = 1 % k;

        for (int i = 0; i < q; i++) {
            int idx = queries[i][0];
            int val = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            update(1, 0, n - 1, idx, val);

            QR res = query(1, 0, n - 1, start, n - 1);
            result[i] = res.C[startRemainder][x];
        }
        return result;
    }

    private void setLeaf(int node, int val) {
        int v = val % k;
        int[][] m = new int[k][k];
        for (int in = 0; in < k; in++) {
            m[in][(in * v) % k] = 1;
        }
        mat[node] = m;
        prod[node] = v;
    }

    private void build(int node, int l, int r, int[] nums) {
        if (l == r) { setLeaf(node, nums[l]); return; }
        int mid = (l + r) >>> 1;
        build(2 * node, l, mid, nums);
        build(2 * node + 1, mid + 1, r, nums);
        pull(node);
    }

    private void pull(int node) {
        int lc = 2 * node, rc = 2 * node + 1;
        mat[node] = mergeMat(mat[lc], prod[lc], mat[rc]);
        prod[node] = (prod[lc] * prod[rc]) % k;
    }

    private void update(int node, int l, int r, int idx, int val) {
        if (l == r) { setLeaf(node, val); return; }
        int mid = (l + r) >>> 1;
        if (idx <= mid) update(2 * node, l, mid, idx, val);
        else update(2 * node + 1, mid + 1, r, idx, val);
        pull(node);
    }

    private int[][] mergeMat(int[][] L, int pl, int[][] R) {
        int[][] C = new int[k][k];
        for (int in = 0; in < k; in++) {
            int mid = (in * pl) % k;
            int[] Lin = L[in], Rmid = R[mid], Cin = C[in];
            for (int x = 0; x < k; x++) Cin[x] = Lin[x] + Rmid[x];
        }
        return C;
    }

    private static class QR {
        int[][] C; int prod;
        QR(int[][] C, int prod) { this.C = C; this.prod = prod; }
    }

    private QR query(int node, int l, int r, int ql, int qr) {
        if (qr < l || r < ql) return null;
        if (ql <= l && r <= qr) return new QR(mat[node], prod[node]);
        int mid = (l + r) >>> 1;
        QR left = query(2 * node, l, mid, ql, qr);
        QR right = query(2 * node + 1, mid + 1, r, ql, qr);
        if (left == null) return right;
        if (right == null) return left;
        int[][] C = mergeMat(left.C, left.prod, right.C);
        return new QR(C, (left.prod * right.prod) % k);
    }
}