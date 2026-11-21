class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        // Step 1: Build the original tree.
        List<Integer>[] children = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            children[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            children[parent[i]].add(i);
        }
        // Step 2: Find the new parent of every node.
        int[] newParent = new int[n];
        newParent[0] = -1;
        int[] last = new int[26];
        Arrays.fill(last, -1);
        // Iterative DFS with backtracking.
        int[] nodeStack = new int[n];
        int[] childIndex = new int[n];
        int[] previous = new int[n];
        int top = 0;
        nodeStack[0] = 0;
        int rootChar = s.charAt(0) - 'a';
        previous[0] = last[rootChar];
        last[rootChar] = 0;
        while (top >= 0) {
            int u = nodeStack[top];
            if (childIndex[top] < children[u].size()) {
                int v = children[u].get(childIndex[top]++);
                int c = s.charAt(v) - 'a';
                // Find closest matching ancestor.
                int ancestor = last[c];
                if (ancestor != -1) {
                    newParent[v] = ancestor;
                } else {
                    newParent[v] = parent[v];
                }
                // Save previous value and update current path.
                previous[top + 1] = last[c];
                last[c] = v;
                top++;
                nodeStack[top] = v;
                childIndex[top] = 0;
            } else {
                // Backtrack: restore the previous ancestor.
                int c = s.charAt(u) - 'a';
                last[c] = previous[top];
                top--;
            }
        }
        // Step 3: Build the modified tree.
        List<Integer>[] newChildren = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            newChildren[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            newChildren[newParent[i]].add(i);
        }
        // Step 4: Get traversal order of the modified tree.
        int[] order = new int[n];
        int[] stack = new int[n];
        int size = 0;
        int top2 = 0;
        stack[top2++] = 0;
        while (top2 > 0) {
            int u = stack[--top2];
            order[size++] = u;
            for (int v : newChildren[u]) {
                stack[top2++] = v;
            }
        }
        // Step 5: Calculate subtree sizes in reverse order.
        int[] answer = new int[n];
        Arrays.fill(answer, 1);
        for (int i = n - 1; i >= 0; i--) {
            int u = order[i];
            if (newParent[u] != -1) {
                answer[newParent[u]] += answer[u];
            }
        }
        return answer;
    }
}
