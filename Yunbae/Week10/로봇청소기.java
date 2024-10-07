package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 로봇청소기 {
    static int[][] map;
    static int n, m, cnt;
    static int[][] backDelta = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}}; //북서남동 반시계방향
    static int[][] delta = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    static int[][] visited = new int[n][m];


    public static void bfs(int X, int Y, int dir) {
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[]{X, Y});
        if (map[X][Y] == 0) {
            map[X][Y] = 2;
            cnt += 1;
        }

        int nx = 0;
        int ny = 0;

        while (!que.isEmpty()) {
            boolean cleanCheck = false;
            int[] tmp = que.poll();
            int x = tmp[0];
            int y = tmp[1];

            for (int i = 0; i < 4; i++) {
                nx = delta[i][0] + x;
                ny = delta[i][1] + y;
                if (map[nx][ny] == 0) {
                    cleanCheck = true;
                }
            }

            if (cleanCheck) {
                for (int i = 0; i < 4; i++) {
                    dir = (dir + 1) % 4;
                    nx = delta[dir][0] + x;
                    ny = delta[dir][1] + y;
                    if (map[nx][ny] == 0) {
                        cnt += 1;
                        map[nx][ny] = 2;
                        que.add(new int[]{nx, ny});
                        break;
                    }
                }
            }
            else{
                nx = delta[dir][0] * -1 + x;
                ny = delta[dir][1] * -1 + y;
                if (map[nx][ny] == 1) {
                    return;
                } else {
                    que.add(new int[]{nx, ny});
                }
            }
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int dir = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        cnt = 0;
        if(dir==1){
            dir = 3;
        }else if(dir==3){
            dir = 1;
        }

        bfs(x, y, dir);
        System.out.println(cnt);
    }
}
