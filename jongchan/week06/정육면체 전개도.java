package boj.gold.b1917;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] board = new int[6][6];
    static boolean flag;
    static boolean[] dice;
    // dice 0이 밑면, 1이 정면, 2가 윗면, 3이 뒷면, 4가 왼쪽, 5가 오른쪽

    static int[][] delta = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; //상, 하, 좌, 우
    static int cnt;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        for (int t = 0; t < 3; t++) {
            flag = false;
            dice = new boolean[6];
            visited = new boolean[6][6];
            cnt = 0;
            for (int i = 0; i < 6; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 6; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 6; j++) {
                    if (board[i][j] == 1) {
                        visited[i][j] = true;
                        dice[0] = true;
                        cnt++;
                        dfs(i, j);
                        break;
                    }
                }
            }
            if (flag) {
                sb.append("yes").append("\n");
            } else {
                sb.append("no").append("\n");
            }
        }
        System.out.println(sb);
    }

    private static void dfs(int x, int y) {
        if (cnt == 6) {

            for (int i = 0; i < 6; i++) {
                if (!dice[i]) {
                    return;
                }
            }
            flag = true;
            return;
        }
        for (int d = 0; d < 4; d++) {
            if (cnt == 6) {
                return;
            }
            int nextX = x + delta[d][0];
            int nextY = y + delta[d][1];

            if (isRange(nextX, nextY) && board[nextX][nextY] == 1 && !visited[nextX][nextY]) {
                visited[nextX][nextY] = true;
                cnt++;
                move(d);
                dice[0] = true;

                dfs(nextX, nextY);
                // 주사위 원복
                if (d % 2 == 0) {
                    move(d + 1);
                } else {
                    move(d - 1);
                }
            }
        }
    }

    // dice 0이 밑면, 1이 정면, 2가 윗면, 3이 뒷면, 4가 왼쪽, 5가 오른쪽
    private static void move(int d) {
        if (d == 0) {
            boolean temp = dice[0];
            dice[0] = dice[1];
            dice[1] = dice[2];
            dice[2] = dice[3];
            dice[3] = temp;
        } else if (d == 1) {
            boolean temp = dice[3];
            dice[3] = dice[2];
            dice[2] = dice[1];
            dice[1] = dice[0];
            dice[0] = temp;
        } else if(d == 2) {
            boolean temp = dice[0];
            dice[0] = dice[4];
            dice[4] = dice[2];
            dice[2] = dice[5];
            dice[5] = temp;
        } else {
            boolean temp = dice[0];
            dice[0] = dice[5];
            dice[5] = dice[2];
            dice[2] = dice[4];
            dice[4] = temp;
        }
    }
    private static boolean isRange(int x, int y) {
        return 0 <= x && x < 6 && 0 <= y && y < 6;
    }
}

