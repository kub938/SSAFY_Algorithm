package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class War {
    static int N;
    static int M;
    static char[][] board;  // char 배열로 수정
    static boolean[][] visited;
    static int[] dx = {0,1,0,-1};
    static int[] dy = {-1,0,1,0};
    static int redScore = 0;
    static int blueScore = 0;

    public static int BFS(int x, int y) {
        int p = 1;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        visited[x][y] = true;
        char team = board[x][y];  // 현재 팀

        while (!q.isEmpty()) {
            int[] temp = q.poll();
            int tx = temp[0];
            int ty = temp[1];

            for (int i = 0; i < 4; i++) {
                int nx = tx + dx[i];
                int ny = ty + dy[i];

                if (nx >= 0 && ny >= 0 && nx < M && ny < N) {
                    if (!visited[nx][ny] && board[nx][ny] == team) {
                        q.add(new int[]{nx, ny});
                        visited[nx][ny] = true;
                        p++;
                    }
                }
            }
        }
        return p;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new char[M][N];  // 배열 크기 동적 할당
        visited = new boolean[M][N];

        for (int i = 0; i < M; i++) {
            String str = bf.readLine();
            for (int j = 0; j < N; j++) {
                board[i][j] = str.charAt(j);  // char로 저장
            }
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    int people = BFS(i, j);
                    if (board[i][j] == 'W') {
                        redScore += people * people;
                    } else if (board[i][j] == 'B') {
                        blueScore += people * people;
                    }
                }
            }
        }

        System.out.println(redScore + " " + blueScore);
    }
}
