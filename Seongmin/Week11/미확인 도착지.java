import java.util.*;
import java.io.*;

/*
git commit -m "김성민 / 241014 / [BOJ_미확인 도착지_9370] / 524ms"
### 풀이 사항
풀이 일자: 2024.10.14
풀이 시간: 1시간 반
채점 결과: 정답
예상 문제 유형: 다익스트라
시간: 524 ms
메모리: 62672 kb

### 풀이방법
1. 데이터를 입력받는다.
2. 다익스트라를 돌려서 각 최단거리를 구한다.
3. 최단경로와 필수 경로를 들렸다가는 경로가 같으면 출력한다.
### 느낀점
연습용으로 플로이드 워샬을 사용해도 좋을 거 같다.
*/

public class Main {
    static int n;
    static int m;
    static int k;
    static int start;
    static int g;
    static int h;
    static ArrayList<Edge>[] graph;
    static boolean[] visited;
    static int[] distS;
    static int[] distG;
    static int[] distH;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < t; tc++) {
            init(br);
            solve(sb,br);

        }

        System.out.print(sb);
    }

    private static void solve( StringBuilder sb, BufferedReader br) throws IOException {
        dijkstra(start, distS);
        dijkstra(g, distG);
        dijkstra(h, distH);

        PriorityQueue<Integer> goQue = new PriorityQueue<>();
        for (int i = 0; i < k; i++) {
            int num = Integer.parseInt(br.readLine());

            if(check(num))
                goQue.add(num);
        }
        while (!goQue.isEmpty()) {
            int node = goQue.poll();
            sb.append(node + " ");
        }
        sb.append('\n');
    }

    private static void init(BufferedReader br) throws IOException {
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        g = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());


        graph = new ArrayList[n + 1];

        distS = new int[n+1];
        distG = new int[n+1];
        distH = new int[n+1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph[s].add(new Edge(e, w));
            graph[e].add(new Edge(s, w));
        }
    }

    private static boolean check(int end) {
        int root1 = distS[end];
        int root2 = distS[g]+distG[h]+distH[end];
        int root3 = distS[h]+distG[end]+distH[g];

        return root1 == root2 || root1 == root3;
    }

    private static void dijkstra(int node, int[] dist) {
        visited = new boolean[n+1];

        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[node] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(node,0));

        while(!pq.isEmpty()){
            Edge edge = pq.poll();

            if(visited[edge.e]) continue;
            visited[edge.e] = true;

            for(Edge next:graph[edge.e]){
                if(!visited[next.e] && dist[next.e] > dist[edge.e]+next.w){
                    dist[next.e] = dist[edge.e]+next.w;
                    pq.add(new Edge(next.e,dist[next.e]));
                }
            }

        }
    }

    static class Edge implements Comparable<Edge> {
        int e, w;

        public Edge(int e, int w) {
            this.e = e;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.w, o.w);
        }
    }
}