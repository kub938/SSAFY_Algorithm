import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * start 15:33
 * end 15:58
 * 예상: 구현, 백트래킹
 * 문제 조건: 색종이는 1x1, 2x2, 3x3, 4x4, 5x5 가 각각 5개씩 있음
 * 1이 적힌 칸은 모두 색종이를 붙여야 하고, 0은 붙이면 안됨
 * 색종이는 경계 밖으로 나가면 안되고, 겹쳐도 안됨
 * 시간: 1초 백트래킹하면 가능할 듯 싶음
 * 구현
 * 1. 1이 적힌 점들을 기록
 * 2. 만약 기록한 사이즈가 0이면 0출력 return
 * 3. 1이 적힌 점들을 순회하며 색종이를 붙임. visited 있어야 할듯
 * 필요 변수 List<int[]> one, int[] papers: 색종이 수 관리, visited[][] 방문 관리, min 최솟 값 관리
 * 필요 메서드
 * range(int x, int y)
 * back(int depth, int cnt)
 * paste(int[] point, int paper, boolean flag)
 */

public class Main {

    static List<int[]> one = new ArrayList<>();
    static int[] papers = {5, 5, 5, 5, 5};
    static boolean[][] visited = new boolean[10][10];
    static int min = Integer.MAX_VALUE;
    static int[][] map = new int[10][10];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 10; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    one.add(new int[]{i, j});
                }
            }
        }

        if (one.size() <= 3) {
            System.out.println(one.size());
            return;
        }

        back(0, 0);

        if (min == Integer.MAX_VALUE) {
            min = -1;
        }
        System.out.println(min);
    }

    private static void back(int depth, int cnt) {
        if (cnt >= min) {
            return;
        }
        if (depth == one.size()) {
            min = cnt;
            return;
        }

        int[] cur = one.get(depth);

        if (visited[cur[0]][cur[1]]) {
            back(depth + 1, cnt);
            return;
        }

        for (int p = 4; p >= 0; p--) {
            if (!check(cur, p) || papers[p] <= 0) {
                continue;
            }

            paste(cur, p, true);
            papers[p]--;
            back(depth + 1, cnt + 1);
            papers[p]++;
            paste(cur, p, false);
        }

    }

    private static boolean check(int[] cur, int p) {

        if (!isRange(cur[0] + p, cur[1] + p)) {
            return false;
        }
        for (int i = cur[0]; i <= cur[0] + p; i++) {
            for (int j = cur[1]; j <= cur[1] + p; j++) {
                if (visited[i][j] || map[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void paste(int[] cur, int p, boolean flag) {
        for (int i = cur[0]; i <= cur[0] + p; i++) {
            for (int j = cur[1]; j <= cur[1] + p; j++) {
                visited[i][j] = flag;
            }
        }
    }

    private static boolean isRange(int x, int y) {
        return 0 <= x && x < 10 && 0 <= y && y < 10;
    }
}
