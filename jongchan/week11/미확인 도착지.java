import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    /**
     * start 12:50 45분
     * n: 교차로의 개수
     * m: 도로의 개수
     * t: 목적지 후보의 개수
     * s: 예술가들의 출발지
     * g, h: g와 h를 연결하는 간선을 반드시 지나가야 함
     * 1. s에서 최단 거리를 다익스트라로 구함
     * 2. h에서 최단 거리를 구함
     * 3. g에서 최단 거리를 구함
     * 4. 2와 3에서 구한 값의 합이랑 1이랑 일치하는지 확인
     */

    static int n;
    static int m;
    static int t;
    static List<List<int[]>> graph;
    static int s;
    static int g;
    static int h;
    static List<Integer> dest;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int testCase = Integer.parseInt(bufferedReader.readLine());

        for (int tc = 1; tc <= testCase; tc++) {
            st = new StringTokenizer(bufferedReader.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken());
            dest = new ArrayList<>();
            graph = new ArrayList<>();

            for (int i = 0; i <= n; i++) {
                graph.add(new ArrayList<>());
            }
            st = new StringTokenizer(bufferedReader.readLine());
            s = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(bufferedReader.readLine());
                int vertex1 = Integer.parseInt(st.nextToken());
                int vertex2 = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());
                graph.get(vertex1).add(new int[]{vertex2, weight});
                graph.get(vertex2).add(new int[]{vertex1, weight});
            }

            for (int i = 0; i < t; i++) {
                int d = Integer.parseInt(bufferedReader.readLine());
                dest.add(d);
            }

            int[] distanceStart = dijkstra(s);
            int[] distanceG = dijkstra(g);
            int[] distanceH = dijkstra(h);

            Collections.sort(dest);

            for (Integer d : dest) {

                if (distanceStart[d] == (distanceStart[g] + distanceG[h] + distanceH[d]) ||
                    distanceStart[d] == (distanceStart[h] + distanceH[g] + distanceG[d])) {
                    sb.append(d).append(" ");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static int[] dijkstra(int start) {

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        pq.offer(new int[]{start, 0});

        boolean[] visited = new boolean[n + 1];

        while(!pq.isEmpty()) {

            int[] current = pq.poll();

            if (visited[current[0]]) {
                continue;
            }
            visited[current[0]] = true;

            for (int[] node : graph.get(current[0])) {
                if (dist[current[0]] == Integer.MAX_VALUE) {
                    continue;
                }
                if (dist[node[0]] > dist[current[0]] + node[1]) {
                    dist[node[0]] = dist[current[0]] + node[1];
                    pq.offer(new int[]{node[0], dist[node[0]]});
                }
            }
        }
        return dist;
    }
}