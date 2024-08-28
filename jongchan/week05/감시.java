import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int[][] map = new int[9][9];
    static int n;
    static int m;

    static List<int[]> list = new ArrayList<>();
    static int[] wall = new int[2];
    static int min = 63;
    static int[] direction;

    static int[][] cpyMap;

    static int[][] delta = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        input();
        permutation(0);
        System.out.println(min);
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 6) {
                    wall[0] = i;
                    wall[1] = j;
                } else if (map[i][j] != 0) {
                    list.add(new int[]{i, j, map[i][j]});
                }

            }
        }
        direction = new int[list.size()];
    }

    private static void permutation(int depth) {

        if (depth == list.size()) {

            cpyMap = new int[n][m];
            for (int i = 0; i < n; i++) {
                System.arraycopy(map[i], 0, cpyMap[i], 0, m);
            }

            for (int i = 0; i < list.size(); i++) {
                solve(list.get(i), i);
            }

            getArea();
            return;
        }

        for (int i = 0; i < 4; i++) {
            direction[depth] = i;
            permutation(depth + 1);
        }
    }


    private static void getArea() {

        int sum = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (cpyMap[i][j] == 0) {
                    sum++;
                }
            }
        }
        min = Math.min(sum, min);
    }

    private static void solve(int[] cctv, int order) {

        int x = cctv[0];
        int y = cctv[1];
        int look = cctv[2];
        int dir = direction[order];


        if (look == 1) {
            watch(new int[]{x, y}, dir);

        } else if (look == 2) {

            if (dir == 0 || dir == 2) {
                watch(new int[]{x,y}, 0);
                watch(new int[]{x,y}, 2);
            } else if (dir == 1 || dir == 3) {
                watch(new int[]{x,y}, 1);
                watch(new int[]{x,y}, 3);
            }

        } else if (look == 3) {
            if (dir == 0) {
                watch(new int[]{x,y}, 0);
                watch(new int[]{x,y}, 1);
            } else if (dir == 1) {
                watch(new int[]{x,y}, 1);
                watch(new int[]{x,y}, 2);
            } else if (dir == 2) {
                watch(new int[]{x,y}, 2);
                watch(new int[]{x,y}, 3);
            } else {
                watch(new int[]{x,y}, 3);
                watch(new int[]{x,y}, 0);
            }

        } else if (look == 4) {
            if (dir == 0) {
                watch(new int[]{x,y}, 3);
                watch(new int[]{x,y}, 0);
                watch(new int[]{x,y}, 1);
            } else if(dir == 1) {
                watch(new int[]{x,y}, 0);
                watch(new int[]{x,y}, 1);
                watch(new int[]{x,y}, 2);
            } else if(dir == 2) {
                watch(new int[]{x,y}, 1);
                watch(new int[]{x,y}, 2);
                watch(new int[]{x,y}, 3);
            } else {
                watch(new int[]{x,y}, 2);
                watch(new int[]{x,y}, 3);
                watch(new int[]{x,y}, 0);
            }

        } else {
            watch(new int[]{x,y}, 0);
            watch(new int[]{x,y}, 1);
            watch(new int[]{x,y}, 2);
            watch(new int[]{x,y}, 3);
        }

    }

    private static void watch(int[] point, int dir) { //0 상 1 우 2 하 3 좌
        int x = point[0] + delta[dir][0];
        int y = point[1] + delta[dir][1];

        while(isRange(x,y) && cpyMap[x][y] != 6) {
            cpyMap[x][y] = -1;
            x += delta[dir][0];
            y += delta[dir][1];
        }
    }


    private static boolean isRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }


}