import java.util.*;
import java.io.*;

public class Main {

    static StringTokenizer st;

    static int N, M;

    static int[] parent;

    public static class Edge implements Comparable<Edge> {
        int start, destination, weight;

        Edge(int start, int destination, int weight) {
            this.start = start;
            this.destination = destination;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        Edge[] edges = new Edge[M];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int destination = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edges[i] = new Edge(start, destination, weight);
        }

        int answer = kruskal(N, edges);

        System.out.println(answer);
    }

    public static int kruskal(int n, Edge[] edges) {
        Arrays.sort(edges);

        parent = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }

        int fatigue = 0;
        int bigcost = 0;
        int count = 0;

        for (Edge edge : edges) {
            if (find(edge.start) != find(edge.destination)) {

                fatigue += edge.weight;
                union(edge.start, edge.destination);
                bigcost = edge.weight;
                count++;

            }

            if (count == n - 1) {
                break;
            }
        }

        return fatigue - bigcost;
    }

    public static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    public static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x != y) {
            parent[x] = y;
        }
    }
}
