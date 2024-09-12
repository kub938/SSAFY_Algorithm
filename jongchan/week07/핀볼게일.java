import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
    static int[][] map = new int[100][100];
    static int n;
    static int[][] delta = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; //상 우 하 좌
    static int max;
    static HashMap<Integer, List<int[]>> hole;
    static int dir;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine().trim());

        for (int t = 1; t <= T; t++) {
            n = Integer.parseInt(br.readLine().trim());
            hole = new HashMap<>();
            max = 0;

            for (int i = 6; i <= 10; i++) {
                hole.put(i, new ArrayList<>());
            }

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine().trim());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] >= 6) {
                        hole.get(map[i][j]).add(new int[]{i, j});
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (map[i][j] != 0) {
                        continue;
                    }
                    for (int d = 0; d < 4; d++) {
                        dir = d;
                        search(i, j);
                    }
                }
            }
            sb.append("#").append(t).append(" ").append(max).append("\n");
        }
        System.out.println(sb);
    }

    private static void search(int x, int y) {

        int sX = x;
        int sY = y;
        int cnt = 0;

        while (true) {

            do {
                x += delta[dir][0];
                y += delta[dir][1];
            } while (isRange(x, y) && (x != sX || y != sY) && map[x][y] == 0);


            if (!isRange(x, y)) {
                if (dir >= 2) {
                    dir -= 2;
                } else {
                    dir += 2;
                }
                cnt = 2 * cnt + 1;
                max = Math.max(cnt, max);
                return;
            }

            if (map[x][y] == - 1 || (x == sX && y == sY)) {
                max = Math.max(max, cnt);
                return;
            } else if(map[x][y] >= 6) {
                List<int[]> holes = hole.get(map[x][y]);
                if (holes.get(0)[0] == x && holes.get(0)[1] == y) {
                    x = holes.get(1)[0];
                    y = holes.get(1)[1];
                } else {
                    x = holes.get(0)[0];
                    y = holes.get(0)[1];
                }
                continue;
            } else {
                changeDir(x, y);
            }
            cnt++;
        }

    }

    private static void changeDir(int x, int y) {

        switch (dir) {
            case 0:
                if (map[x][y] == 1 || map[x][y] == 4 || map[x][y] == 5) {
                    dir = 2;
                } else if (map[x][y] == 2) {
                    dir = 1;
                } else {
                    dir = 3;
                }
                break;
            case 1:
                if (map[x][y] == 1 || map[x][y] == 2 || map[x][y] == 5) {
                    dir = 3;
                } else if (map[x][y] == 3) {
                    dir = 2;
                } else {
                    dir = 0;
                }
                break;
            case 2:
                if (map[x][y] == 2 || map[x][y] == 3 || map[x][y] == 5) {
                    dir = 0;
                } else if (map[x][y] == 1) {
                    dir = 1;
                } else {
                    dir = 3;
                }
                break;
            case 3:
                if (map[x][y] == 3 || map[x][y] == 4 || map[x][y] == 5) {
                    dir = 1;
                } else if (map[x][y] == 1) {
                    dir = 0;
                } else {
                    dir = 2;
                }
                break;
        }

    }

    private static boolean isRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }

}

/**
 * start 23:01
 * 예상 유형: 구현, DFS
 * 구현 메서드
 * dfs(int[] point, int dir, int cnt) -> point를 유지 해야하나?
 * if (point == s || visited[point[0]][point[1]][dir] return
 * 필요 변수
 * int map[][], boolean[][] visited, int max, int[][] delta;
 * 웜홀은 어떻게 관리할 것?
 * HashMap<Integer, List<int[]>> 이걸로 관리하면 될듯?
 */