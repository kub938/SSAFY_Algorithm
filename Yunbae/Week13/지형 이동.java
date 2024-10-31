import java.util.*;
import java.io.*;

class Solution {
    static class Edge {
        int v, w;

        public Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    static int[][] landNum;
    static int n, h, cnt, result;
    static int[][] visited;
    static ArrayList<ArrayList<Edge>> graph;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] land;

    public static int solution(int[][] l, int height) {
        land = l;
        n = land.length;
        h = height;
        landNum = new int[n][n];
        cnt = 0;
        visited = new int[n][n];
        graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j] == 0) {
                    setLandNum(i, j);
                }
            }
        }
        for (int i = 0; i < cnt + 1; i++) {
            graph.add(new ArrayList<Edge>());
        }
        
        setLadder();
        prim();
        return result;
    }


    public static boolean inRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }


    public static void setLandNum(int X, int Y) {
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[]{X, Y});
        visited[X][Y] = 1;
        cnt += 1;
        landNum[X][Y] = cnt;
        while (!que.isEmpty()) {
            int[] tmp = que.poll();
            int x = tmp[0];
            int y = tmp[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (inRange(nx, ny) && visited[nx][ny] == 0) {
                    int diff = Math.abs(land[nx][ny] - land[x][y]);
                    if (diff <= h) { //사다리 없이 지나갈수 있으면
                        landNum[nx][ny] = cnt;
                        que.add(new int[]{nx, ny});
                        visited[nx][ny] = 1;
                    }
                }
            }
        }
    }


    public static void setLadder() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (landNum[i][j] != landNum[i][j + 1]) {
                    graph.get(landNum[i][j]).add(new Edge(landNum[i][j + 1], Math.abs(land[i][j + 1] - land[i][j])));
                    graph.get(landNum[i][j + 1]).add(new Edge(landNum[i][j], Math.abs(land[i][j + 1] - land[i][j])));
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (landNum[j][i] != landNum[j + 1][i]) {
                    graph.get(landNum[j][i]).add(new Edge(landNum[j + 1][i], Math.abs(land[j + 1][i] - land[j][i])));
                    graph.get(landNum[j + 1][i]).add(new Edge(landNum[j][i], Math.abs(land[j + 1][i] - land[j][i])));
                }
            }
        }
    }

    public static void prim() {
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.w, o2.w));
        pq.add(new Edge(1, 0));
        int[] vis = new int[graph.size() +1];
        result = 0;

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();

            if (vis[edge.v] != 0) continue;

            result += edge.w;
            vis[edge.v] = 1;
            for (Edge nextEdge : graph.get(edge.v)) {
                pq.add(nextEdge);
            }
        }
    }
}
