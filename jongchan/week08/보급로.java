package swea.d.d1249;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Solution {
    static final int[][] delta = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int[][] map = new int[100][100];
    static int n;
    static int result;

    static class Point implements Comparable<Point> {
        int x;
        int y;
        int weight;
        Point(int x, int y, int weight) {
            this.x = x;
            this.y = y;
            this.weight = weight;
        }


        @Override
        public int compareTo(Point o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            n = Integer.parseInt(br.readLine());
            for (int i = 0; i < n; i++) {
                String str = br.readLine();
                for (int j = 0; j < n; j++) {
                    map[i][j] = str.charAt(j) - '0';
                }
            }
            bfs();
            sb.append("#").append(t).append(" ").append(result).append("\n");
        }
        System.out.println(sb);
    }

    public static void bfs() {

        PriorityQueue<Point> pq = new PriorityQueue<>();
        boolean[][] visited = new boolean[n][n];

        pq.add(new Point(0, 0, 0));
        visited[0][0] = true;

        while (!pq.isEmpty()) {
            Point current = pq.remove();

            for (int[] d : delta) {
                int nextX = current.x + d[0];
                int nextY = current.y + d[1];

                if (current.x == n - 1 && current.y == n - 1) {
                    result = current.weight;
                    return;
                }

                if (isRange(nextX, nextY) && !visited[nextX][nextY]) {
                    pq.add(new Point(nextX, nextY, current.weight + map[nextX][nextY]));
                    visited[nextX][nextY] = true;
                }
            }
        }
    }

    public static boolean isRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }
}
