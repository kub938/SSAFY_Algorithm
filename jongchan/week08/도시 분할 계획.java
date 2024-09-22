import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * start 22:13 end 22:34 1284ms
 * 양방향 그래프
 * MST 2개 만드는 문제?
 * Set로 parents를 유지하는데, size가 2이면 그만두게
 * 필요 변수
 * int[] parents, Set<Integer> parentSet, Pq<Edge> pq, int result
 */

public class Main {

    static int[] parents;
    static Set<Integer> parentSet;
    static PriorityQueue<Edge> pq;
    static int result;
    static int n;

    static class Edge implements Comparable<Edge> {

        int from;
        int to;
        int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        result = 0;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        parents = new int[n + 1];
        parentSet = new HashSet<>();
        pq = new PriorityQueue<>();

        for (int i = 1; i <= n; i++) {
            parents[i] = i;
            parentSet.add(i);
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            pq.add(new Edge(from, to, weight));
        }

        solve();

        System.out.println(result);

    }

    private static void solve() {

        while(parentSet.size() != 2) {

            if (pq.isEmpty()) {
                return;
            }
            Edge edge = pq.poll();

            int x = edge.from;
            int y = edge.to;

            int findX = find(x);
            int findY = find(y);

            if (findX == findY) {
                continue;
            }
            union(x, y);
            result += edge.weight;
        }

    }

    private static int find(int x) {
        if (parents[x] == x) {
            return x;
        }
        parents[x] = find(parents[x]);
        return parents[x];
    }

    private static void union(int x, int y) {
        int findX = find(x);
        int findY = find(y);

        if (findX < findY) {
            parents[findY] = findX;
            parentSet.remove(findY);
        } else {
            parents[findX] = findY;
            parentSet.remove(findX);
        }

    }
}
