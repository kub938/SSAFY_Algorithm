import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    /**
     * start 16:06
     * end 16 27
     * 예상 유형: BFS
     * 1. 지훈이 이동
     * 2. 불 붙이기
     * 필요 변수
     * char[][] map, int[][] delta, int n, int m
     * 필요 메서드 boolean IsRange(int x, int y), boolean canMove(int x, int y)
     */

    static char[][] map;
    static int[][] delta = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int n;
    static int m;


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        int result = bfs();

        if (result == -1) {
            System.out.println("IMPOSSIBLE ");
            return;
        }

        System.out.println(result);
    }

    private static int bfs() {
        Queue<int[]> q = new LinkedList<>();
        Queue<int[]> fire = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 'J') {
                    q.add(new int[]{i, j, 0});
                    map[i][j] = '.';
                    visited[i][j] = true;
                } else if (map[i][j] == 'F') {
                    fire.add(new int[]{i, j});
                }
            }
        }


        while (!q.isEmpty()) {
            int size = fire.size();

            for (int i = 0; i < size; i++) {
                int[] cur = fire.poll();
                for (int[] d : delta) {
                    int x = cur[0] + d[0];
                    int y = cur[1] + d[1];

                    if (isRange(x, y) && map[x][y] != 'F' && map[x][y] != '#') {
                        fire.add(new int[]{x, y});
                        map[x][y] = 'F';
                    }
                }
            }

            size =
                    q.size();

            for (int i = 0; i < size; i++) {
                int[] cur = q.poll();
                for (int[] d : delta) {
                    int x = cur[0] + d[0];
                    int y = cur[1] + d[1];
                    if (!isRange(x, y)) {
                        return cur[2] + 1;
                    } else if (canMove(x, y) && !visited[x][y]) {
                        q.add(new int[]{x, y, cur[2] + 1});
                        visited[x][y] = true;
                    }
                }
            }


        }

        return -1;
    }

    private static boolean canMove(int x, int y) {
        return !(map[x][y] == '#' || map[x][y] == 'F');
    }

    private static boolean isRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }
}
