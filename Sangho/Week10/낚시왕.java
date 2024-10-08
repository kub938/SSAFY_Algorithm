import java.util.*;
import java.io.*;

public class Main {
    static int R, C, M;
    static Shark[][] map;

    static class Shark {
        int speed, direction, size;

        public Shark(int speed, int direction, int size) {
            this.speed = speed;
            this.direction = direction;
            this.size = size;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new Shark[R + 1][C + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());  // 속력
            int d = Integer.parseInt(st.nextToken());  // 이동 방향
            int z = Integer.parseInt(st.nextToken());  // 크기
            map[r][c] = new Shark(s, d, z);
        }

        int totalSize = 0;

        // 낚시왕의 이동 및 상어 잡기
        for (int col = 1; col <= C; col++) {
            // 가장 가까운 상어 잡기
            for (int row = 1; row <= R; row++) {
                if (map[row][col] != null) {
                    totalSize += map[row][col].size;
                    map[row][col] = null;
                    break;
                }
            }

            // 상어 이동
            map = moveSharks();
        }

        System.out.println(totalSize);
    }

    // 상어의 이동 처리
    private static Shark[][] moveSharks() {
        Shark[][] newMap = new Shark[R + 1][C + 1];
        int[] dr = {0, -1, 1, 0, 0}; // 1: 위, 2: 아래, 3: 오른쪽, 4: 왼쪽
        int[] dc = {0, 0, 0, 1, -1};

        for (int r = 1; r <= R; r++) {
            for (int c = 1; c <= C; c++) {
                if (map[r][c] != null) {
                    Shark shark = map[r][c];
                    int speed = shark.speed;
                    int dir = shark.direction;
                    int size = shark.size;

                    int nr = r, nc = c;

                    // 속력에 맞게 상어 이동
                    for (int s = 0; s < speed; s++) {
                        nr += dr[dir];
                        nc += dc[dir];

                        // 경계를 넘으면 방향 전환
                        if (nr < 1 || nr > R || nc < 1 || nc > C) {
                            if (dir == 1 || dir == 2) dir = (dir == 1) ? 2 : 1;
                            if (dir == 3 || dir == 4) dir = (dir == 3) ? 4 : 3;
                            nr += 2 * dr[dir];
                            nc += 2 * dc[dir];
                        }
                    }

                    // 이동 후 같은 자리에 다른 상어가 있으면 크기 비교
                    if (newMap[nr][nc] == null || newMap[nr][nc].size < size) {
                        newMap[nr][nc] = new Shark(speed, dir, size);
                    }
                }
            }
        }

        return newMap;
    }
}
