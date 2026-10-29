import java.util.*;

class Solution {

    int n;
    int LOG;

    List<Integer>[] adj;

    int[] tin;
    int[] tout;
    int[] depth;

    int[][] up;

    int[] prefixMask;
    char[] chars;

    Fenwick fenwick;

    public List<Boolean> palindromePath(int n, int[][] edges,
                                       String s, String[] queries) {

        this.n = n;
        this.chars = s.toCharArray();

        LOG = 1;
        while ((1 << LOG) <= n) {
            LOG++;
        }

        adj = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            adj[u].add(v);
            adj[v].add(u);
        }

        tin = new int[n];
        tout = new int[n];
        depth = new int[n];

        up = new int[n][LOG];
        prefixMask = new int[n];

        buildTree();

        fenwick = new Fenwick(n);

        List<Boolean> result = new ArrayList<>();

        for (String query : queries) {

            String[] parts = query.split(" ");

            if (parts[0].equals("update")) {

                int u = Integer.parseInt(parts[1]);
                char newChar = parts[2].charAt(0);

                int oldBit = 1 << (chars[u] - 'a');
                int newBit = 1 << (newChar - 'a');

                int delta = oldBit ^ newBit;

                chars[u] = newChar;

                // Update the subtree of u
                fenwick.rangeXor(tin[u], tout[u], delta);

            } else {

                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);

                int lca = getLCA(u, v);

                int maskU = getCurrentPrefix(u);
                int maskV = getCurrentPrefix(v);

                int lcaBit = 1 << (chars[lca] - 'a');

                int pathMask = maskU ^ maskV ^ lcaBit;

                result.add(Integer.bitCount(pathMask) <= 1);
            }
        }

        return result;
    }

    // Build tree information
    void buildTree() {

        int[] parent = new int[n];
        Arrays.fill(parent, -1);

        int[] order = new int[n];

        int timer = 0;

        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        parent[0] = 0;
        up[0][0] = 0;

        prefixMask[0] = 1 << (chars[0] - 'a');

        while (!stack.isEmpty()) {

            int u = stack.pop();

            tin[u] = timer;
            order[timer++] = u;

            for (int v : adj[u]) {

                if (v == parent[u]) {
                    continue;
                }

                parent[v] = u;

                depth[v] = depth[u] + 1;

                prefixMask[v] =
                    prefixMask[u] ^ (1 << (chars[v] - 'a'));

                up[v][0] = u;

                stack.push(v);
            }
        }

        // Binary lifting table
        for (int j = 1; j < LOG; j++) {
            for (int i = 0; i < n; i++) {
                up[i][j] = up[up[i][j - 1]][j - 1];
            }
        }

        // Calculate subtree sizes
        int[] size = new int[n];
        Arrays.fill(size, 1);

        for (int i = n - 1; i > 0; i--) {
            int u = order[i];
            size[parent[u]] += size[u];
        }

        // Calculate subtree boundaries
        for (int u = 0; u < n; u++) {
            tout[u] = tin[u] + size[u] - 1;
        }
    }

    // Find Lowest Common Ancestor
    int getLCA(int u, int v) {

        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        // Lift u to the same depth as v
        int diff = depth[u] - depth[v];

        for (int j = 0; j < LOG; j++) {
            if ((diff & (1 << j)) != 0) {
                u = up[u][j];
            }
        }

        if (u == v) {
            return u;
        }

        // Lift both nodes together
        for (int j = LOG - 1; j >= 0; j--) {

            if (up[u][j] != up[v][j]) {
                u = up[u][j];
                v = up[v][j];
            }
        }

        return up[u][0];
    }

    // Get updated prefix mask
    int getCurrentPrefix(int node) {
        return prefixMask[node] ^ fenwick.pointQuery(tin[node]);
    }

    // Fenwick Tree supporting range XOR updates and point queries
    class Fenwick {

        int n;
        int[] tree;

        Fenwick(int n) {
            this.n = n;
            tree = new int[n + 2];
        }

        void update(int index, int value) {

            index++;

            while (index <= n + 1) {
                tree[index] ^= value;
                index += index & -index;
            }
        }

        void rangeXor(int left, int right, int value) {

            update(left, value);
            update(right + 1, value);
        }

        int pointQuery(int index) {

            index++;

            int result = 0;

            while (index > 0) {
                result ^= tree[index];
                index -= index & -index;
            }
            return result;
        }
    }
}