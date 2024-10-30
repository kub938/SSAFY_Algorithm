package programmers.level4.move;

import java.util.PriorityQueue;

class Solution {

    /**
     * start 09:04
     * end 09: 31
     */

    static class Edge implements Comparable<Edge> {
        int from;
        int to;
        int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        public int compareTo(Edge o) {
            return weight - o.weight;
        }
    }


    private int[][] delta = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private int n;
    private int[][] land;
    private int height;
    private int[] parent;


    PriorityQueue<Edge> pq = new PriorityQueue<>();

    public int solution(int[][] land, int height) {
        n = land.length;
        parent = new int[n * n];
        this.land = land;
        this.height = height;

        init();


        return kruskal();
    }

    private void init() {

        boolean[] check = new boolean[n * n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                parent[i * n + j] = i * n + j;

                int from = i * n + j;
                check[from] = true;
                for (int[] d : delta) {
                    int nextX = i + d[0];
                    int nextY = j + d[1];

                    if (!isRange(nextX, nextY)) {
                        continue;
                    }

                    int to = nextX * n + nextY;

                    if (!check[to]) {

                        int dif = Math.abs(land[i][j] - land[nextX][nextY]);

                        if (dif <= height) {
                            pq.add(new Edge(from, to, 0));
                            continue;
                        }
                        pq.add(new Edge(from, to, dif));
                    }
                }
            }
        }
    }

    private int kruskal() {

        int sum = 0;

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();

            int findFrom = find(edge.from);
            int findTo = find(edge.to);

            if (findFrom != findTo) {
                union(edge.from, edge.to);
                sum += edge.weight;
            }

        }
        return sum;
    }

    private int find(int x) {
        if (x == parent[x]) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    private void union(int x, int y) {
        int findX = find(x);
        int findY = find(y);

        if (findX != findY) {
            if (findX < findY) {
                parent[findY] = findX;
            } else {
                parent[findX] = findY;
            }
        }
    }


    private boolean isRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

}


public class Main {


    public static void main(String[] args) {
        int[][] land = {{1, 4, 8, 10}, {5, 5, 5, 5}, {10, 10, 10, 10}, {10, 10, 10, 20}};
        int height = 3;

        System.out.println(new Solution().solution(land, height));
    }


}



