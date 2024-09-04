import java.io.*;
import java.util.*;

public class Main {
    static int[] dice; // 주사위의 각 면 상태
    static int[] dy = {1, 0, 0, -1}; // 남, 동, 서, 북 방향 y값 변경
    static int[] dx = {0, 1, -1, 0}; // 남, 동, 서, 북 방향 x값 변경
    static int row, col; // 첫 아랫면이 되는 row값과 col값

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        for (int i = 0; i < 3; i++) {
            int[][] arr = new int[6][6];
            boolean[][] visited = new boolean[6][6];
            dice = new int[7];

            // 6x6 배열 입력
            for (int j = 0; j < 6; j++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int s = 0; s < 6; s++) {
                    arr[j][s] = Integer.parseInt(st.nextToken());
                }
            }

            // 첫 번째 1의 위치를 찾음
            startPoint(arr);

            // 주사위를 굴리면서 종이를 접음
            cubeMake(arr, visited, row, col);

            // 모든 면이 사용되었는지 확인
            if (cubeCheck()) {
                bw.write("yes\n");
            } else {
                bw.write("no\n");
            }
        }
    }

    static void cubeMake(int[][] arr, boolean[][] visited, int row, int col) {
        int[] temp = new int[7];
        dice[1] = 1; // 아랫면 확인
        visited[row][col] = true; // 방문 확인

        // 4방향으로 이동
        for (int i = 0; i < 4; i++) {
            int tempRow = row + dy[i];
            int tempCol = col + dx[i];

            if (tempRow >= 0 && tempCol >= 0 && tempRow < 6 && tempCol < 6 && !visited[tempRow][tempCol]) {
                if (arr[tempRow][tempCol] == 1) {
                    cubeChange(i, temp); // 주사위를 굴림
                    cubeMake(arr, visited, tempRow, tempCol); // 다음 위치로 이동
                    cubeReverse(i, temp); // 주사위를 원래 상태로 되돌림
                }
            }
        }
    }

    static void cubeReverse(int direction, int[] temp) {
        cubeChange(3 - direction, temp);
    }

    static void cubeChange(int direction, int[] temp) {
        if (direction == 0) // 남쪽
            down(temp);
        else if (direction == 1) // 동쪽
            right(temp);
        else if (direction == 2) // 서쪽
            left(temp);
        else // 북쪽
            up(temp);

        tempToDice(temp);
    }

    static void tempToDice(int[] temp) {
        for (int i = 1; i < 7; i++) {
            dice[i] = temp[i];
        }
    }

    static void startPoint(int[][] arr) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (arr[i][j] == 1) {
                    row = i;
                    col = j;
                    return;
                }
            }
        }
    }
    static boolean cubeCheck() {
        for (int i = 1; i < 7; i++) {
            if (dice[i] == 0) {
                return false;
            }
        }
        return true;
    }

    static void down(int[] temp) {
        temp[4] = dice[1];
        temp[1] = dice[2];
        temp[2] = dice[3];
        temp[3] = dice[4];
        temp[5] = dice[5];
        temp[6] = dice[6];
    }

    static void right(int[] temp) {
        temp[5] = dice[1];
        temp[2] = dice[2];
        temp[6] = dice[3];
        temp[4] = dice[4];
        temp[3] = dice[5];
        temp[1] = dice[6];
    }

    static void left(int[] temp) {
        temp[6] = dice[1];
        temp[2] = dice[2];
        temp[5] = dice[3];
        temp[4] = dice[4];
        temp[1] = dice[5];
        temp[3] = dice[6];
    }

    static void up(int[] temp) {
        temp[2] = dice[1];
        temp[3] = dice[2];
        temp[4] = dice[3];
        temp[1] = dice[4];
        temp[5] = dice[5];
        temp[6] = dice[6];
    }
}
