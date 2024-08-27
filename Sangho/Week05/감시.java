import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int N, M; // 맵의 크기
    static int[][] map; // 원본 맵
    static ArrayList<CCTV> cctvs = new ArrayList<>(); // CCTV 목록
    static int minBlindSpot = Integer.MAX_VALUE; // 최소 사각지대 수

    // 방향 배열 (상, 우, 하, 좌)
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    // CCTV 클래스
    static class CCTV {
        int x, y, type;

        CCTV(int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        // 맵 정보 입력 및 CCTV 위치 저장
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] >= 1 && map[i][j] <= 5) {
                    cctvs.add(new CCTV(i, j, map[i][j]));
                }
            }
        }

        // 모든 경우의 수 탐색 시작
        dfs(0);
        System.out.println(minBlindSpot);
    }

    // DFS로 CCTV의 모든 방향을 탐색
    static void dfs(int depth) {
        if (depth == cctvs.size()) {
            // 모든 CCTV 배치를 확인한 후, 사각지대 계산
            minBlindSpot = Math.min(minBlindSpot, countBlindSpot());
            return;
        }

        CCTV cctv = cctvs.get(depth);

        // 각 CCTV의 가능한 방향 경우의 수 탐색
        for (int dir = 0; dir < 4; dir++) {
            int[][] backup = new int[N][M];

            // 맵 복사
            for (int i = 0; i < N; i++) {
                backup[i] = map[i].clone();
            }

            // CCTV 방향에 따른 감시 수행
            monitor(cctv, dir);

            // 다음 CCTV로 이동
            dfs(depth + 1);

            // 맵 복원
            for (int i = 0; i < N; i++) {
                map[i] = backup[i].clone();
            }
        }
    }

    // CCTV가 특정 방향으로 감시하는 함수
    static void monitor(CCTV cctv, int dir) {
        int type = cctv.type;

        switch (type) {
            case 1:
                watch(cctv.x, cctv.y, dir);
                break;
            case 2:
                watch(cctv.x, cctv.y, dir);
                watch(cctv.x, cctv.y, (dir + 2) % 4);
                break;
            case 3:
                watch(cctv.x, cctv.y, dir);
                watch(cctv.x, cctv.y, (dir + 1) % 4);
                break;
            case 4:
                watch(cctv.x, cctv.y, dir);
                watch(cctv.x, cctv.y, (dir + 1) % 4);
                watch(cctv.x, cctv.y, (dir + 2) % 4);
                break;
            case 5:
                watch(cctv.x, cctv.y, 0);
                watch(cctv.x, cctv.y, 1);
                watch(cctv.x, cctv.y, 2);
                watch(cctv.x, cctv.y, 3);
                break;
        }
    }

    // 특정 위치에서 특정 방향으로 감시하는 함수
    static void watch(int x, int y, int dir) {
        while (true) {
            x += dx[dir];
            y += dy[dir];

            if (x < 0 || x >= N || y < 0 || y >= M || map[x][y] == 6) { // 범위를 벗어나거나 벽을 만날 때까지
                break;
            }

            if (map[x][y] == 0) { // 빈 공간을 감시
                map[x][y] = 7; // 감시된 공간을 표시
            }
        }
    }

    // 사각지대 계산
    static int countBlindSpot() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    count++;
                }
            }
        }
        return count;
    }
}
