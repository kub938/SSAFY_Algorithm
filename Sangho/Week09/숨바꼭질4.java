import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static int[] visited = new int[100001]; // 방문 배열
    static int[] parent = new int[100001]; // 부모 배열
    static Queue<Integer> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        if (N == M) {
            System.out.println(0);
            System.out.println(N);
            return;
        } else if (N > M) {
            // N이 M보다 클 경우
            System.out.println(N - M); // 최소 이동 횟수
            for (int i = N; i >= M; i--) {
                System.out.print(i + " ");
            }
            System.out.println();
            return;
        }

        BFS(N);
        System.out.println(visited[M]); // 최소 시간 출력
        printPath(M); // 경로 출력
    }

    public static void BFS(int start) {
        Arrays.fill(visited, -1); // 방문 배열 초기화
        q.add(start);
        visited[start] = 0; // 시작점의 거리 초기화

        while (!q.isEmpty()) {
            int temp = q.poll();

            for (int next : new int[]{temp - 1, temp + 1, temp * 2}) {
                if (next < 0 || next >= visited.length) continue; // 경계 체크

                if (visited[next] == -1) { // 방문하지 않은 경우
                    q.add(next);
                    visited[next] = visited[temp] + 1; // 거리 기록
                    parent[next] = temp; // 부모 기록

                    if (next == M) {
                        return; // 목표 지점에 도달
                    }
                }
            }
        }
    }

    public static void printPath(int target) {
        List<Integer> path = new ArrayList<>();
        for (int at = target; at != N; at = parent[at]) {
            path.add(at);
        }
        path.add(N); // 시작점 추가
        Collections.reverse(path); // 역순으로 추가했으므로 반전
        for (int node : path) {
            System.out.print(node + " ");
        }
        System.out.println();
    }
}
