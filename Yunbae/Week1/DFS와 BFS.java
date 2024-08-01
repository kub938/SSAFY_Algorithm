import java.util.*;

public class Main {
    static boolean[] visited;
    static boolean[][] board;
    static int n;
    static int m;
    static int v;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        v = sc.nextInt();
        board = new boolean[1001][1001];

        for (int i = 0; i < m; i++) {
            int r = sc.nextInt();
            int c = sc.nextInt();
            board[r][c] = board[c][r] = true;
        }

        visited = new boolean[n+1];
        dfs(v);
        visited = new boolean[n+1];
        System.out.println();
        bfs(v);
    }

    public static void dfs(int start) {
        visited[start] = true;
        System.out.print(start + " ");
        for (int i = 1; i <= n; i++) {
            if (board[start][i] && !visited[i]) {
                dfs(i);
            }
        }
    }

    public static void bfs(int start){
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;
        while(!q.isEmpty()){
            int cur = q.poll();
            System.out.print(cur + " ");
            for (int i = 1; i <= n; i++) {
                if (board[cur][i] && !visited[i]) {
                    q.add(i);
                    visited[i] = true;
                }
            }
        }
    }
}
