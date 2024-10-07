import java.util.Scanner;

public class Main {

    public static int N;
    public static int M;
    public static int[][] board;
    public static int r, c, d;

    // 방향 배열: 북, 동, 남, 서
    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        N = scanner.nextInt();
        M = scanner.nextInt();
        r = scanner.nextInt();
        c = scanner.nextInt();
        d = scanner.nextInt();

        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                board[i][j] = scanner.nextInt();
            }
        }

        System.out.println(cleanRoom());
    }

    public static int cleanRoom() {
        int count = 0;

        while (true) {
            // 현재 칸 청소
            if (board[r][c] == 0) {
                board[r][c] = 2; // 청소한 칸을 2로 표시
                count++;
            }

            boolean cleaned = false;
            for (int i = 0; i < 4; i++) {
                d = (d + 3) % 4; // 반시계 방향으로 회전
                int nx = r + dx[d];
                int ny = c + dy[d];

                if (nx >= 0 && ny >= 0 && nx < N && ny < M && board[nx][ny] == 0) {
                    r = nx;
                    c = ny;
                    cleaned = true;
                    break;
                }
            }

            if (!cleaned) {
                int back = (d + 2) % 4;
                int nx = r + dx[back];
                int ny = c + dy[back];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M || board[nx][ny] == 1) {
                    break;
                }

                r = nx;
                c = ny;
            }
        }

        return count;
    }
}
