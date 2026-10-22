import java.util.*;

class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;

        int zeroIndex = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                zeroIndex = i;
                break;
            }
        }

        boolean ascending = true;
        boolean descending = true;

        for (int i = 0; i < n; i++) {
            int next = nums[(i + 1) % n];

            if (next != (nums[i] + 1) % n) {
                ascending = false;
            }

            if (next != (nums[i] - 1 + n) % n) {
                descending = false;
            }
        }

        if (!ascending && !descending) {
            return -1;
        }

        int[][] dist = new int[2][n];
        for (int[] row : dist) {
            Arrays.fill(row, -1);
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        dist[0][0] = 0;

        while (!queue.isEmpty()) {
            int[] state = queue.poll();

            int orientation = state[0];
            int shift = state[1];
            int d = dist[orientation][shift];

            // Operation 1: Rotate left by one
            int nextShift = (shift + 1) % n;

            if (dist[orientation][nextShift] == -1) {
                dist[orientation][nextShift] = d + 1;
                queue.offer(new int[]{orientation, nextShift});
            }

            // Operation 2: Reverse the entire array
            int nextOrientation = 1 - orientation;
            nextShift = (n - shift) % n;

            if (dist[nextOrientation][nextShift] == -1) {
                dist[nextOrientation][nextShift] = d + 1;
                queue.offer(new int[]{nextOrientation, nextShift});
            }
        }

        int answer = Integer.MAX_VALUE;

        if (ascending) {
            answer = Math.min(answer, dist[0][zeroIndex]);
        }

        if (descending) {
            int reverseShift = (n - 1 - zeroIndex + n) % n;
            answer = Math.min(answer, dist[1][reverseShift]);
        }
        return answer;
    }
}