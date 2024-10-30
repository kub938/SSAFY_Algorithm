import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    /**
     * start 10:29 11:20
     * 필요 변수 int[] dice, int dir, int sum, int[][] delta
     * 필요 메서드 left, right, up, down, isRange, solve
     */

    // 0 윗면, 1 정면, 2 오른, 3 뒷, 4 왼, 5 아랫
    static int[] dice = new int[]{1, 2, 3, 5, 4, 6};
    static int dir = 0;
    static int[][] delta = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // right, down, left, up
    static int sum = 0;

    static int[][] board;
    static int n;
    static int m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        solve();
        System.out.println(sum);

    }

    private static void solve() {
        int[] current = {0, 0};

        for (int i = 0; i < m; i++) {
            sum += move(current);

        }

    }


    private static int move(int[] current) {

        int nextX = current[0] + delta[dir][0];
        int nextY = current[1] + delta[dir][1];

        if (!isRange(nextX, nextY)) {
            dir = dir >= 2 ? dir - 2 : dir + 2;

        }

        switch (dir) {
            case 0:
                right();
                break;
            case 1:
                down();
                break;
            case 2:
                left();
                break;
            case 3:
                up();
                break;
        }


        current[0] += delta[dir][0];
        current[1] += delta[dir][1];


        if (dice[5] > board[current[0]][current[1]]) {
            dir = (dir + 1) % 4;
        } else if (dice[5] < board[current[0]][current[1]]) {
            dir = (dir + 3) % 4;
        }


        return bfs(current[0], current[1]);


    }

// 0 윗면, 1 정면, 2 오른, 3 뒷, 4 왼, 5 아랫

    private static void right() {
        int temp = dice[0];
        dice[0] = dice[4];
        dice[4] = dice[5];
        dice[5] = dice[2];
        dice[2] = temp;

    }

    private static void left() {
        int temp = dice[0];
        dice[0] = dice[2];
        dice[2] = dice[5];
        dice[5] = dice[4];
        dice[4] = temp;

    }

    private static void up() {
        int temp = dice[0];
        dice[0] = dice[1];
        dice[1] = dice[5];
        dice[5] = dice[3];
        dice[3] = temp;
    }

    private static void down() {
        int temp = dice[0];
        dice[0] = dice[3];
        dice[3] = dice[5];
        dice[5] = dice[1];
        dice[1] = temp;
    }


    private static int bfs(int x, int y) {

        int value = board[x][y];
        int s = value;
        boolean[][] visited = new boolean[n][n];
        Queue<int[]> q = new LinkedList<>();
        visited[x][y] = true;
        q.add(new int[]{x, y});

        while (!q.isEmpty()) {

            int[] point = q.poll();

            for (int[] d : delta) {
                int nextX = point[0] + d[0];
                int nextY = point[1] + d[1];

                if (isRange(nextX, nextY) && !visited[nextX][nextY] && board[nextX][nextY] == value) {
                    visited[nextX][nextY] = true;
                    q.add(new int[]{nextX, nextY});
                    s += value;
                }

            }

        }
        return s;
    }

    private static boolean isRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }
}
