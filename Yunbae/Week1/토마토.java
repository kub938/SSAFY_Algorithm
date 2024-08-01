import java.io.IOException;
import java.util.*;

public class Main1 {
    static int n;
    static int m;
    static int[][] board;
    static int dx[] = {-1, 0, 1, 0};
    static int dy[] = {0, 1, 0, -1};
    static Queue<int[]> que = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        m = sc.nextInt();
        n = sc.nextInt();
        board = new int[n][m];
        List<int[]> targetList = new ArrayList<>();
        int tomatoCnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                board[i][j] = sc.nextInt();
                if (board[i][j] == 1) {
                    que.add(new int[]{i, j});
                }
            }
        }
        bfs();
        System.out.println(cntDay());
    }


    public static int cntDay() {
        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 0) {
                    return -1;
                }
                result = Math.max(result, board[i][j]);
            }
        }
        return result-1;
    }

    public static boolean inRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }


    public static void bfs() {
        int day = 0;
        int x;
        int y;
        while (!que.isEmpty()) {
            int[] coord = que.poll();
            x = coord[0];
            y = coord[1];
            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + x;
                int ny = dy[i] + y;
                if (inRange(nx, ny) && board[nx][ny] == 0) {
                    que.add(new int[]{nx, ny});
                    board[nx][ny] = board[x][y] + 1;
                    day = board[nx][ny];
                }
            }
        }
    }
}
