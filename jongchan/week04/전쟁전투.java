import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static int white;
    private static int black;

    static int n;
    static int m;
    static char[][] board;
    static boolean[][] visited;

    static int[][] delta = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        init();
        solve();

        System.out.println(white + " " + black);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        white = 0;
        black = 0;
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new char[m][n];
        visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            String str = br.readLine();

            for (int j = 0; j < n; j++) {
                board[i][j] = str.charAt(j);
            }
        }

    }

    private static void solve() {

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (visited[i][j]) {
                    continue;
                }

                if (board[i][j] == 'W') {
                    white += bfs(i, j, 'W');
                } else {
                    black += bfs(i, j, 'B');
                }
            }
        }

    }

    private static int bfs(int x, int y, char ch) {

        Queue<int[]> queue = new LinkedList<>();

        queue.add(new int[]{x,y});
        int n = 1;
        visited[x][y] = true;

        while(!queue.isEmpty()) {

            int[] current = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nextX = current[0] + delta[d][0];
                int nextY = current[1] + delta[d][1];

                if (range(nextX, nextY) && board[nextX][nextY] == ch && !visited[nextX][nextY]) {
                    queue.add(new int[]{nextX, nextY});
                    n++;
                    visited[nextX][nextY] = true;
                }

            }
        }

        return n* n;
    }

    private static boolean range(int x, int y) {
        return 0 <= x && x < m && 0 <= y && y < n;
    }


}