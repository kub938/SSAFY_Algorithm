package boj.gold.b12100;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    /**
     * start 13:38 1. 중복 순열을 이용하여 이동시키는 경로를 구한다. 2. 경로를 구한 후 해당 경로에 맞게 보드를 이동시킨다. 중복 순열 구현: DFS 보드
     * 이동: 보드를 아래로 이동 시키는 한가지 메서드만 구현하고, 나머지는 회전시켜서 구한다. 필요 변수 int[][] map, int n, int[] perm, int
     * max 필요 메서드: void dfs(int depth), void move(), void rotate(), int[][] cpy;
     */

    //0 상, 1 우, 2 하, 3 좌

    private static int[][] map;
    private static int n;
    private static int[] perm;
    private static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        perm = new int[5];
        max = 0;

        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0);
        System.out.println(max);
    }

    private static void dfs(int depth) {
        if (depth == 5) {
            int[][] newMap = cpy();

            for (int i = 0; i < 5; i++) {
                newMap = switch (perm[i]) {
                    case 0 -> rotate(newMap, 2);
                    case 1 -> rotate(newMap, 1);
                    case 3 -> rotate(newMap, 3);
                    default -> newMap;
                };

                move(newMap);

                newMap = switch (perm[i]) {
                    case 0 -> rotate(newMap, 2);
                    case 1 -> rotate(newMap, 3);
                    case 3 -> rotate(newMap, 1);
                    default -> newMap;
                };
            }
            max = Math.max(max, getMax(newMap));
            return;
        }

        for (int i = 0; i < 4; i++) {
            perm[depth] = i;
            dfs(depth + 1);
        }
    }

    private static int[][] cpy() {

        int[][] newMap = new int[n][n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(map[i], 0, newMap[i], 0, n);
        }
        return newMap;
    }

    private static int[][] rotate(int[][] newMap, int direction) {

        for (int d = 0; d < direction; d++) {
            int[][] rotateMap = new int[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    rotateMap[j][n - 1 - i] = newMap[i][j];
                }
            }
            newMap = rotateMap;
        }
        return newMap;
    }

    private static void move(int[][] newMap) {

        for (int col = 0; col < n; col++) {
            moveCol(newMap, col);
            mergeCol(newMap, col);
            moveCol(newMap, col);
        }

    }

    private static void moveCol(int[][] newMap, int col) {
        int row = n - 1;
        while (row >= 0) {
            if (newMap[row][col] != 0) {
                row--;
                continue;
            }
            int nextR = row - 1;

            while (nextR >= 0 && newMap[nextR][col] == 0) {
                nextR--;
            }
            if (nextR == -1) {
                break;
            }
            newMap[row][col] = newMap[nextR][col];
            newMap[nextR][col] = 0;
            row--;
        }
    }

    private static void mergeCol(int[][] newMap, int col) {

        for (int row = n - 1; row > 0; row--) {
            if (newMap[row][col] == 0) {
                break;
            }
            if (newMap[row][col] == newMap[row - 1][col]) {
                newMap[row][col] += newMap[row - 1][col];
                newMap[row - 1][col] = 0;
                row--;
            }
        }

    }

    private static int getMax(int[][] map) {

        int m = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                m = Math.max(map[i][j], m);
            }
        }
        return m;
    }
}
