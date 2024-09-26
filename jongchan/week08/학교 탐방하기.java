import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * pq를 두개 만듬 첫 번째는 오름차순, 두번째는 내림차순
 * 필요 변수
 * pq maxPq;
 * pq minPq;
 * int parents[]
 * 필요 메서드
 * union
 * parent
 * kruskal
 */

public class Main {


    static int[] parents;
    static int n;

    static class Edge {
        int from;
        int to;
        int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        parents = new int[n + 1];
        parentInit();

        PriorityQueue<Edge> maxHeap = new PriorityQueue<>((o1, o2) -> o2.weight - o1.weight);
        PriorityQueue<Edge> minHeap = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));

        for (int i = 0; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            Edge edge = new Edge(from, to, weight);
            maxHeap.add(edge);
            minHeap.add(edge);
        }

        int min = kruskal(maxHeap);
        parentInit();
        int max = kruskal(minHeap);

        System.out.println(max - min);
    }

    private static int kruskal(PriorityQueue<Edge> pq) {

        int sum = 0;
        int cnt = 0;

        while(cnt < n && !pq.isEmpty()) {
            Edge edge = pq.poll();
            int from = edge.from;
            int to = edge.to;

            int findFrom = find(from);
            int findTo = find(to);

            if(findFrom != findTo) {
                union(findFrom, findTo);

                if (edge.weight == 0) {
                    sum++;
                }

                cnt++;
            }
        }
        return sum * sum;
    }

    private static int find(int vertex) {
        if (vertex == parents[vertex]) {
            return vertex;
        }
        return parents[vertex] = find(parents[vertex]);
    }
    private static void union(int vertex1, int vertex2) {
        vertex1 = find(vertex1);
        vertex2 = find(vertex2);
        if (vertex1 < vertex2) {
            parents[vertex2] = vertex1;
        } else {
            parents[vertex1] = vertex2;
        }
    }

    private static void parentInit() {
        for (int i = 0; i <= n; i++) {
            parents[i] = i;
        }
    }
}