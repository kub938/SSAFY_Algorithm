package swea.d.d5656;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 1. 중복순열로 몇번째 열에 있는 것을 부실지 선택한다.
 * 2. n개를 다 뽑았을 경우 boom과 move를 실행한다.
 * 3. boom과 move를 실행하는데, 현재 열에 벽돌이 없으면 함수를 끝낸다.
 */

public class Solution {

    static int[][] map = new int[16][12];
    static int[][] delta = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int[] perm = new int[4];
    static int min;
    static int n;
    static int w;
    static int h;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            min = w * h;

            for (int i = 0; i < w; i++) {
                map[0][i] = -1;
            }

            for (int i = 1; i <= h; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[0][j] == -1 && map[i][j] != 0) {
                        map[0][j] = i;
                    }
                }
            }


            dfs(0);
            sb.append("#").append(t).append(" ").append(min).append("\n");
        }
        System.out.println(sb);

    }

    private static void dfs(int depth) {

        if (depth == n) {

            int[][] cpy = deepCopy();


            for (int i = 0; i < n; i++) {
                int col = perm[i];
                // 남아있는 벽돌이 없는 경우
                if (map[0][col] == -1) {

                    boolean flag = true;

                    for (int j = 0; j < w; j++) {
                        if (map[0][j] != -1) {
                            flag = false;
                        }
                    }
                    map = cpy;
                    if (flag) {
                        min = 0;
                    }
                    return;
                }
                boom(map[0][col], col);
                move();

            }
            int cnt = 0;
            for (int i = 0; i < w; i++) {
                if (map[0][i] == -1) {
                    continue;
                }
                cnt += h - map[0][i] + 1;
            }
            min = Math.min(min, cnt);

            map = cpy;
            return;
        }

        for (int i = 0; i < w; i++) {
            perm[depth] = i;
            dfs(depth + 1);
        }

    }

    private static void boom(int x, int y) {
        if (map[x][y] == 1) {
            map[x][y] = 0;
            return;
        }
        boolean[][] visited = new boolean[h + 1][w];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        visited[x][y] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int len = map[cur[0]][cur[1]];
            map[cur[0]][cur[1]] = 0;
            if (len == 1) {
                continue;
            }


            for (int i = 1; i < len; i++) {
                for (int[] d: delta) {
                    int nextX = cur[0] + d[0] * i;
                    int nextY = cur[1] + d[1] * i;

                    if (isRange(nextX, nextY) && !visited[nextX][nextY] && map[nextX][nextY] != 0) {
                        q.add(new int[]{nextX, nextY});
                        visited[nextX][nextY] = true;
                    }
                }
            }

        }
    }

    private static void move() {

        for (int j = 0; j < w; j++) {
            Queue<Integer> q = new LinkedList<>();
            for (int i = h; i > 0; i--) {
                if (map[i][j] != 0) {
                    q.offer(map[i][j]);
                }
            }
            int idx = h;
            if (q.isEmpty()) {
                map[0][j] = -1;
                continue;
            }
            while(!q.isEmpty()) {
                map[idx--][j] = q.poll();
            }
            map[0][j] = idx + 1;
            for (int i = idx; i > 0; i--) {
                map[i][j] = 0;
            }
        }
    }

    private static boolean isRange(int x, int y) {
        return 0 < x && x <= h && 0 <= y && y < w;
    }

    private static int[][] deepCopy() {
        int[][] copy = new int[16][12];
        for (int i = 0; i < 16; i++) {
            System.arraycopy(map[i], 0, copy[i], 0, 12);
        }
        return copy;
    }

}