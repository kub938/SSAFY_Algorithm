import java.util.*;

class Solution {
    private static final int[] dx = {0, 1, 0, -1};
    private static final int[] dy = {1, 0, -1, 0};

    public int solution(int[][] land, int height) {
        int n = land.length;
        int[][] regions = new int[n][n];
        int regionId = 1;
        
        // BFS로 각 영역을 나누어 표시
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (regions[i][j] == 0) {
                    bfs(land, regions, i, j, regionId++, height);
                }
            }
        }

        // 영역 간의 모든 간선 및 비용을 저장
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int d = 0; d < 4; d++) {
                    int nx = i + dx[d];
                    int ny = j + dy[d];
                    if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                        if (regions[i][j] != regions[nx][ny]) {
                            int cost = Math.abs(land[i][j] - land[nx][ny]);
                            edges.add(new Edge(regions[i][j], regions[nx][ny], cost));
                        }
                    }
                }
            }
        }

        // 간선을 비용 기준으로 정렬한 후 크루스칼 알고리즘으로 최소 스패닝 트리 계산
        Collections.sort(edges);
        UnionFind uf = new UnionFind(regionId);
        int totalCost = 0;
        for (Edge edge : edges) {
            if (uf.union(edge.u, edge.v)) {
                totalCost += edge.cost;
            }
        }
        return totalCost;
    }

    // BFS를 통한 영역 나누기
    private void bfs(int[][] land, int[][] regions, int x, int y, int regionId, int height) {
        int n = land.length;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        regions[x][y] = regionId;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int cx = curr[0], cy = curr[1];

            for (int d = 0; d < 4; d++) {
                int nx = cx + dx[d];
                int ny = cy + dy[d];

                if (nx >= 0 && nx < n && ny >= 0 && ny < n && regions[nx][ny] == 0) {
                    int diff = Math.abs(land[cx][cy] - land[nx][ny]);
                    if (diff <= height) {
                        regions[nx][ny] = regionId;
                        queue.offer(new int[]{nx, ny});
                    }
                }
            }
        }
    }

    // 간선 클래스
    static class Edge implements Comparable<Edge> {
        int u, v, cost;
        Edge(int u, int v, int cost) {
            this.u = u;
            this.v = v;
            this.cost = cost;
        }
        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.cost, other.cost);
        }
    }

    // 유니온 파인드 클래스
    static class UnionFind {
        int[] parent, rank;
        UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
        }
        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }
        boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                if (rank[rootX] > rank[rootY]) {
                    parent[rootY] = rootX;
                } else if (rank[rootX] < rank[rootY]) {
                    parent[rootX] = rootY;
                } else {
                    parent[rootY] = rootX;
                    rank[rootX]++;
                }
                return true;
            }
            return false;
        }
    }
}
