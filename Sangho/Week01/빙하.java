import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 빙산 {
    static int N;
    static int M;
    static int[][] board;
    static int[][] visited;
    static int[][] DFS_visited;

    // 방향 탐색
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static Queue<int[]> q;

    // 몇년 지났는지
    static int year;
    static int dfs_count;

    // 빙산 녹이는 BFS
    public static void BFS(int r, int c) {
        q = new LinkedList<>();
        q.add(new int[]{r, c});
        visited[r][c] = 1;

        while (!q.isEmpty()) {
            int[] temp = q.poll();
            int x = temp[0];
            int y = temp[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
                    // 방문하지 않았는데, 그게 바다면 빙하가 하나 깎이는게 맞음
                    if (visited[nx][ny] == 0 && board[nx][ny] == 0) {
                        if (board[x][y] > 0) {
                            board[x][y] -= 1;
                        }
                        // 빙하를 만나면 큐에 넣고 방문 처리함
                    } else if (board[nx][ny] != 0 && visited[nx][ny] == 0) {
                        visited[nx][ny] = 1;
                        q.add(new int[]{nx, ny});
                    }
                }
            }
        }
    }

    public static void DFS(int r, int c) {
        DFS_visited[r][c] = 1;

        for (int i = 0; i < 4; i++) {
            int nx = r + dx[i];
            int ny = c + dy[i];

            if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
                if (board[nx][ny] != 0 && DFS_visited[nx][ny] == 0) {
                    DFS(nx, ny);
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        year = 0;

        board = new int[N][M];

        // 초기화
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                board[r][c] = sc.nextInt();
            }
        }

        while (true) {
            visited = new int[N][M];
            DFS_visited = new int[N][M];
            dfs_count = 0;

            // 빙하 녹이기 1년
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < M; c++) {
                    if (board[r][c] != 0 && visited[r][c] == 0) {
                        BFS(r, c);
                    }
                }
            }

            // DFS 탐색으로 분리된 영역 개수 세기
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < M; c++) {
                    if (board[r][c] != 0 && DFS_visited[r][c] == 0) {
                        DFS(r, c);
                        dfs_count++;
                    }
                }
            }

            year++;

            // 빙하가 두개 이상이 되는 시점
            if (dfs_count > 1) {
                break;
            }

            if (dfs_count == 0) {
                year = 0;
                break;
            }

        }

        System.out.println(year);


    }
}
