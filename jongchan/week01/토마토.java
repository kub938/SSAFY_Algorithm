import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static class Tomato {
        int x;
        int y;
        int day;

        Tomato(int x, int y, int day) {
            this.x = x;
            this.y = y;
            this.day = day;
        }
    }

    static int n;
    static int m;

    static int[][] board;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public static void main(String[] args) {
        input();
        System.out.println(bfs());
    }

    private static void input() {
        Scanner scanner = new Scanner(System.in);
        m = scanner.nextInt();
        n = scanner.nextInt();
        board = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                board[i][j] = scanner.nextInt();
            }
        }
    }

    private static int bfs() {

        Queue<Tomato> queue = new LinkedList<>();

        int count = 0;
        int target = n * m;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 1) {
                    queue.add(new Tomato(i, j, 0));
                    count++;
                } else if(board[i][j] == -1) {
                    target--;
                }
            }
        }

        if (count == target) {
            return 0;
        }


        while (!queue.isEmpty()) {
            Tomato current = queue.poll();

            int x = current.x;
            int y = current.y;

            for (int d = 0; d < 4; d++) {
                int nextX = x + dx[d];
                int nextY = y + dy[d];

                if (range(nextX, nextY) &&  board[nextX][nextY] == 0) {
                    queue.add(new Tomato(nextX, nextY, current.day + 1));
                    board[nextX][nextY] = 1;
                    count++;
                }
            }

            if (count == target) {
                return current.day + 1;
            }

        }
        return -1;
    }

    private static boolean range(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }
}
