//가중치 2배,
//gh 간선은 가중치 2배 -1

//왜?
//1. 만약 gh 간선과 다른 간선의 가중치가 같았을 경우를 -1로 하여금 무조건 gh 간선을 선택하게 만든다.
//2.. 도착지에서의 가중치가 홀수일 경우 gh 간선ㅇ르 지난뒤 도착했다고 보장 할 수 있다.


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    static int n, m, T, t, s, g, h;
    static PriorityQueue<Node> pq;
    static ArrayList<ArrayList<Node>> graph;
    static int[] dist;

    static class Node {
        int idx, c;

        public Node(int idx, int c) {
            this.idx = idx;
            this.c = c;
        }
    }

    public static void dijstra() {
         for (int i = 0; i < n + 1; i++) {
                dist[i] = 50000 * 1000;
        }

        pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.c, o2.c));
        pq.add(new Node(s, 0));
        dist[s] = 0;

        while (!pq.isEmpty()) {
            Node nowNode = pq.poll();
            if (dist[nowNode.idx] < nowNode.c) {
                continue;
            }

            for (Node nextNode : graph.get(nowNode.idx)) {
                int sumDist = nowNode.c + nextNode.c;
                if (dist[nextNode.idx] > sumDist) {
                    dist[nextNode.idx] = sumDist;
                    pq.offer(new Node(nextNode.idx, dist[nextNode.idx]));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());

        for (int x = 0; x < T; x++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());   //노드
            m = Integer.parseInt(st.nextToken());   //간선
            t = Integer.parseInt(st.nextToken());   // 목적지 후보 갯수
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());   //출발지
            g = Integer.parseInt(st.nextToken());   //거쳐간 노드1
            h = Integer.parseInt(st.nextToken());   //거쳐간 노드2
            int[] target = new int[t];
            graph = new ArrayList<>();

            for (int i = 0; i < n + 1; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());

                if (a == g && b == h || a == h && b == g) {
                    graph.get(a).add(new Node(b, d * 2 - 1));
                    graph.get(b).add(new Node(a, d * 2 - 1));
                } else {
                    graph.get(a).add(new Node(b, d * 2));
                    graph.get(b).add(new Node(a, d * 2));
                }
            }

            for (int i = 0; i < t; i++) {
                target[i] = Integer.parseInt(br.readLine());
            }

            dist = new int[n + 1];


            dijstra();
            ArrayList<Integer> result = new ArrayList<>();
            for (int i : target) {
                if (dist[i] % 2 != 0) {
                    result.add(i);
                }
            }

            Collections.sort(result);
            for (int i = 0; i < result.size(); i++) {
                System.out.print(result.get(i) + " ");
            }
        }
    }
}
