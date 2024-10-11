import java.util.*;
import java.io.*;

public class Main {

    static StringTokenizer st;

    static int T;

    // 정점, 간선, 목적지 후보의 각각의 개수
    static int n, m, t;

    // s : 출발 정점 g-h : 특정 간선.
    static int s, g, h;

    // a - b사이에 d 가중치
    static int a, b, d;

    // 목적지 후보
    static int[] destinationList;

    static List<Edge>[] graph;

    static int[] distance;

    static PriorityQueue<Edge> pq;

    static int INF = Integer.MAX_VALUE;

    public static class Edge implements Comparable<Edge> {
        int vertex;
        int weight;

        public Edge(int v, int w) {
            vertex = v;
            weight = w;
        }

        @Override
        public int compareTo(Edge o) {
            return weight - o.weight;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());

        for (int tc = 1; tc <= T; tc++) {

            List<Integer> answerList = new ArrayList<>();

            st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken());

            graph = new ArrayList[n + 1];

            for (int i = 0; i <= n; i++) {
                graph[i] = new ArrayList<>();
            }

            distance = new int[n + 1];

            destinationList = new int[t];

            st = new StringTokenizer(br.readLine());

            s = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());

                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                d = Integer.parseInt(st.nextToken());

                // 간선 가중치 2배, g-h 간선은 2배 후 1을 뺌
                if ((a == g && b == h) || (a == h && b == g)) {
                    graph[a].add(new Edge(b, 2 * d - 1));
                    graph[b].add(new Edge(a, 2 * d - 1));
                } else {
                    graph[a].add(new Edge(b, 2 * d));
                    graph[b].add(new Edge(a, 2 * d));
                }
            }

            for (int i = 0; i < t; i++) {
                st = new StringTokenizer(br.readLine());
                destinationList[i] = Integer.parseInt(st.nextToken());
            }

            dijkstra(s);

            Arrays.sort(destinationList);

            for (int destination : destinationList) {
                if (distance[destination] % 2 == 1) { // 홀수 가중치
                    answerList.add(destination);
                }
            }

            for (int ans : answerList) {
                System.out.print(ans + " ");
            }
            System.out.println();
        }
    }

    public static void dijkstra(int start) {
        Arrays.fill(distance, 50000*1000);
        pq = new PriorityQueue<>();
        pq.add(new Edge(start, 0));
        distance[start] = 0;

        while (!pq.isEmpty()) {
            Edge currentEdge = pq.poll();
            int currentVertex = currentEdge.vertex;
            int currentWeight = currentEdge.weight;

            if (distance[currentVertex] < currentWeight) continue;

            // 현재 정점에 이어진 애들 찾음
            for (Edge edge : graph[currentVertex]) {
                int nextVertex = edge.vertex;
                int nextWeight = currentWeight + edge.weight;

                if (distance[nextVertex] > nextWeight) {
                    distance[nextVertex] = nextWeight;
                    pq.add(new Edge(nextVertex, nextWeight));
                }
            }
        }
    }
}
