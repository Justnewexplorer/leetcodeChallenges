class Solution {

    private void dfs(int node, List<Integer>[] graph, boolean[] visited) {
        visited[node] = true;
        for (int nei : graph[node]) {
            if (!visited[nei]) {
                dfs(nei, graph, visited);
            }
        }
    }

    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        List<Integer>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : invocations) {
            graph[edge[0]].add(edge[1]);
        }

        boolean[] visited = new boolean[n];
        dfs(k, graph, visited);

        for (int[] edge : invocations) {
            int u = edge[0];
            int v = edge[1];
            if (!visited[u] && visited[v]) {
                List<Integer> ans = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    ans.add(i);
                }
                return ans;
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                ans.add(i);
            }
        }
        return ans;
    }
}