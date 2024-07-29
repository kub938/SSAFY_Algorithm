package boj.silver.b1260;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static int n;
    static int m;
    static int startVertex;

    static boolean[][] graph;
    static boolean[] visited;

    static StringBuilder sb;

    public static void main(String[] args) {
        input();

        visited[startVertex] = true;
        sb.append(startVertex + 1).append(" ");
        dfs(startVertex);
        sb.append("\n");

        bfs();
        System.out.println(sb);
    }

    private static void input() {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();
        startVertex = scanner.nextInt() - 1;

        sb = new StringBuilder();

        graph = new boolean[n][n];
        visited = new boolean[n];

        for (int i = 0; i < m; i++) {
            int from = scanner.nextInt() - 1;
            int to = scanner.nextInt() - 1;
            graph[from][to] = true;
            graph[to][from] = true;
        }
    }

    private static void dfs(int vertex) {

        for (int i = 0; i < n; i++) {
            if (!visited[i] && graph[vertex][i]) {
                sb.append(i + 1).append(" ");
                visited[i] = true;
                dfs(i);
            }
        }
    }

    private static void bfs() {
        visited = new boolean[n];

        sb.append(startVertex + 1).append(" ");

        Queue<Integer> queue = new LinkedList<>();
        queue.add(startVertex);
        visited[startVertex] = true;


        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            for (int i = 0; i < n; i++) {
                if (!visited[i] && graph[vertex][i]) {
                    queue.add(i);
                    visited[i] = true;
                    sb.append(i + 1).append(" ");
                }
            }
        }


    }

}
