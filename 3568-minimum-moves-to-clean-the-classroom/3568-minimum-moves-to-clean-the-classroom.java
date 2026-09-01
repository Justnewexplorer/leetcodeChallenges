class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();

        int sr = 0, sc = 0;
        int litterCount = 0;

        int[][] litter = new int[10][2];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = classroom[i].charAt(j);

                if (c == 'S') {
                    sr = i;
                    sc = j;
                } else if (c == 'L') {
                    litter[litterCount][0] = i;
                    litter[litterCount][1] = j;
                    litterCount++;
                }
            }
        }

        if (litterCount == 0)
            return 0;

        int fullMask = (1 << litterCount) - 1;

        boolean[][][][] visited =
            new boolean[m][n][energy + 1][1 << litterCount];

        Queue<int[]> q = new LinkedList<>();

        q.offer(new int[]{sr, sc, energy, 0});
        visited[sr][sc][energy][0] = true;

        int moves = 0;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                int[] state = q.poll();

                int r = state[0];
                int c = state[1];
                int e = state[2];
                int mask = state[3];

                if (mask == fullMask)
                    return moves;

                if (e == 0)
                    continue;

                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if (nr < 0 || nr >= m || nc < 0 || nc >= n)
                        continue;

                    if (classroom[nr].charAt(nc) == 'X')
                        continue;

                    int ne = e - 1;
                    int nmask = mask;

                    for (int i = 0; i < litterCount; i++) {
                        if (litter[i][0] == nr && litter[i][1] == nc) {
                            nmask |= (1 << i);
                            break;
                        }
                    }

                    if (classroom[nr].charAt(nc) == 'R')
                        ne = energy;

                    if (!visited[nr][nc][ne][nmask]) {
                        visited[nr][nc][ne][nmask] = true;
                        q.offer(new int[]{nr, nc, ne, nmask});
                    }
                }
            }

            moves++;
        }
        return -1;
    }
}