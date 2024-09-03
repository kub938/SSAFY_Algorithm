import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[][] map = new int[21][21];
    static int[] shark = {0, 0, 0, 2, 0}; // xy 좌표, 시간, 크기, 먹은 물고기
    static int[][] delta = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    shark[0] = i;
                    shark[1] = j;
                    map[i][j] = 0;
                }
            }
        }
        while (true) {
            if (!bfs(shark[0], shark[1])) {
                break;
            }
        }
        sb.append(shark[2]).append("\n");
        System.out.println(sb);
    }

    private static boolean bfs(int x, int y) {
        boolean[][] visited = new boolean[n][n];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        visited[x][y] = true;
        int t = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            t++;
            int minX = 21;
            int minY = 21;
            for (int s = 0; s < size; s++) {
                int[] cur = queue.poll();
                for (int i = 0; i < 4; i++) {
                    int nx = cur[0] + delta[i][0];
                    int ny = cur[1] + delta[i][1];
                    if (isRange(nx, ny) && !visited[nx][ny] && map[nx][ny] <= shark[3]) {
                        if (map[nx][ny] == 0 || map[nx][ny] == shark[3]) {
                            queue.add(new int[]{nx, ny});
                            visited[nx][ny] = true;
                        } else { // 잡아 먹음
                            if (minX > nx) {
                                minX = nx;
                                minY = ny;
                            } else if (minX == nx && minY > ny) {
                                minY = ny;
                            }
                        }
                    }
                }
            }
            if (minX != 21 && minY != 21) {
                shark[0] = minX;
                shark[1] = minY;
                map[shark[0]][shark[1]] = 0;
                shark[2] += t;
                shark[4]++;

                if (shark[4] == shark[3]) {
                    shark[4] = 0;
                    shark[3]++;
                }

                return true;
            }
        }
        return false;
    }

    private static boolean isRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }
}