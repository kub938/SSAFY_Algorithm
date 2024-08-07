import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Dice {
    static int N, M, x, y, K;
    static int[][] map;
    static List<Integer> commands;
    static int[] diceStates = new int[6];

    // 동 서 북 남 순서
    static int[] dx = {0, 0, 0, -1, 1};
    static int[] dy = {0, 1, -1, 0, 0};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 맵 사이즈
        N = sc.nextInt();
        M = sc.nextInt();

        // 주사위 초기 위치
        x = sc.nextInt();
        y = sc.nextInt();

        // 명령어 개수
        K = sc.nextInt();

        map = new int[N][M];
        commands = new ArrayList<>();

        // 맵 입력 받기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        // 명령 입력 받기
        for (int i = 0; i < K; i++) {
            commands.add(sc.nextInt());
        }

        for (int command : commands) {
            int nx = x + dx[command];
            int ny = y + dy[command];

            // 지도 범위 체크
            if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                continue; // 지도 밖으로 이동 시도 시 무시
            }

            rollDice(command);

            // 주사위 바닥면과 지도 칸의 숫자 복사
            if (map[nx][ny] == 0) {
                map[nx][ny] = diceStates[5];
            } else {
                diceStates[5] = map[nx][ny];
                map[nx][ny] = 0;
            }

            x = nx;
            y = ny;

            // 주사위 상단 값 출력
            System.out.println(diceStates[2]);
        }
    }

    static void rollDice(int direction) {
        int[] temp = diceStates.clone();
        switch (direction) {
            case 1: // 동
                diceStates[0] = temp[0];
                diceStates[2] = temp[3];
                diceStates[3] = temp[5];
                diceStates[5] = temp[1];
                diceStates[1] = temp[2];
                diceStates[4] = temp[4];
                break;
            case 2: // 서
                diceStates[0] = temp[0];
                diceStates[2] = temp[1];
                diceStates[1] = temp[5];
                diceStates[5] = temp[3];
                diceStates[3] = temp[2];
                diceStates[4] = temp[4];
                break;
            case 3: // 북
                diceStates[2] = temp[0];
                diceStates[0] = temp[5];
                diceStates[5] = temp[4];
                diceStates[4] = temp[2];
                diceStates[1] = temp[1];
                diceStates[3] = temp[3];
                break;
            case 4: // 남
                diceStates[2] = temp[4];
                diceStates[4] = temp[5];
                diceStates[5] = temp[0];
                diceStates[0] = temp[2];
                diceStates[1] = temp[1];
                diceStates[3] = temp[3];
                break;
        }
    }
}
