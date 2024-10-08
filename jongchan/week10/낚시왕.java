import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * start: 10:20
 * 예상 유형: 구현
 * 1. 낚시King이 오른쪽으로 한 칸 이동
 * 2. 낚시King이 열에 있는 상어 중 가장 가까운 상어 죽잠음
 * 3. 상어가 이동 (이 때 한 칸에 상어가 여러마리 있는 경우 크기가 가장 큰 상어가 나머지 상어를 모두 죽임)
 * <p>
 * 필요 클래스
 * class Shark{
 * int dir;
 * int size;
 * int velocity;
 * }
 * 필요 변수 int sum int n, int m
 * 필요 메서드
 * catch(int col);
 * moveShark();
 */

public class Main {

    static class Shark {
        int x;
        int y;
        int velocity;
        int dir;
        int size;

        public Shark(int x, int y, int velocity, int dir, int size) {
            this.x = x;
            this.y = y;
            this.velocity = velocity;
            this.dir = dir;
            this.size = size;
        }

        @Override
        public String toString() {
            return "Shark{" +
                    "x=" + x +
                    ", y=" + y +
                    ", velocity=" + velocity +
                    ", dir=" + dir +
                    ", size=" + size +
                    '}';
        }
    }

    // 위 아래 오른쪽 왼쪽
    static int[][] delta = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    static int n;
    static int m;
    static int sum;
    static List<Shark> sharks = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        sum = 0;


        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int velocity = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken()) - 1;
            int size = Integer.parseInt(st.nextToken());
            sharks.add(new Shark(x, y, velocity, dir, size));
        }

        for (int i = 0; i < m; i++) {
            catchShark(i);
            moveShark();
        }
        System.out.println(sum);
    }

    private static void moveShark() {

        Shark[][] map = new Shark[n][m];

        for (int i = sharks.size() - 1; i >= 0; i--) {
            Shark shark = sharks.get(i);

            int nextX = shark.x + delta[shark.dir][0] * shark.velocity;
            int nextY = shark.y + delta[shark.dir][1] * shark.velocity;

            while (!isRange(nextX, nextY)) {
                int diff = nextX < 0 ? nextX : nextY;

                if (nextX >= n) {
                    diff = nextX - n + 1;
                }
                if (nextY >= m) {
                    diff = nextY - m + 1;
                }
                if (nextX < 0 || nextX >= n) {
                    nextX -= 2 * diff;
                }
                if (nextY < 0 || nextY >= m) {
                    nextY -= 2 * diff;
                }
                if (shark.dir % 2 == 0) {
                    shark.dir += 1;
                } else {
                    shark.dir -= 1;
                }
            }

            shark.x = nextX;
            shark.y = nextY;

            if (map[nextX][nextY] != null) {
                Shark tempShark = map[nextX][nextY];
                if (tempShark.size < shark.size) {
                    map[nextX][nextY] = shark;
                    sharks.remove(tempShark);
                } else {
                    sharks.remove(shark);
                }
            } else {
                map[nextX][nextY] = shark;
            }
        }
    }

    private static boolean isRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }


    private static void catchShark(int row) {

        Shark removeShark = null;

        for (Shark shark : sharks) {
            if (shark.y == row) {
                if (removeShark == null) {
                    removeShark = shark;
                } else if (removeShark.x > shark.x) {
                    removeShark = shark;
                }
            }
        }
        if (removeShark != null) {
            sharks.remove(removeShark);
            sum += removeShark.size;
        }
    }

}
