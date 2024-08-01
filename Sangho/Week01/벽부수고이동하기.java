package algorithm;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 벽부수고이동하기 {
    static int N;
    static int M;
    static int[][] map;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};

    static boolean[][][] visited;
    static int[][][] dist;

    static Queue<int[]> q;

    public static void BFS(int r, int c){
        q = new LinkedList<>();
        q.add(new int[]{r, c, 0});
        visited[r][c][0] = true;
        dist[r][c][0] = 1;

        while (!q.isEmpty()){
            int[] temp = q.poll();
            int x = temp[0];
            int y = temp[1];
            int broken = temp[2];

            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
                    // If the next cell is a free path and has not been visited in this state
                    if (map[nx][ny] == 0 && !visited[nx][ny][broken]) {
                        visited[nx][ny][broken] = true;
                        dist[nx][ny][broken] = dist[x][y][broken] + 1;
                        q.add(new int[]{nx, ny, broken});
                    }
                    // If the next cell is a wall and no wall has been broken yet
                    else if (map[nx][ny] == 1 && broken == 0 && !visited[nx][ny][1]) {
                        visited[nx][ny][1] = true;
                        dist[nx][ny][1] = dist[x][y][broken] + 1;
                        q.add(new int[]{nx, ny, 1});
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        map = new int[N][M];
        visited = new boolean[N][M][2];
        dist = new int[N][M][2];

        for(int r = 0; r < N; r++){
            String str = sc.next();
            for(int c = 0; c < M; c++){
                map[r][c] = str.charAt(c) - '0';
            }
        }

        BFS(0, 0);

        // Get the shortest distance to the bottom-right corner with and without breaking a wall
        int ans = -1;
        if (visited[N-1][M-1][0]) ans = dist[N-1][M-1][0];
        if (visited[N-1][M-1][1]) {
            if (ans == -1 || dist[N-1][M-1][1] < ans) {
                ans = dist[N-1][M-1][1];
            }
        }

        System.out.println(ans);
    }
}
