import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n,m,score,dir;
    static int[] dice = {0,5,6,2,1,4,3};
    //우, 하, 좌, 상(시계방향)
    static int[][] turn = {{0,1,5,3,6,4,2},{0,4,1,2,3,5,6},{0,1,6,3,5,2,4},{0,2,3,4,1,5,6}};
    static int[][] map;
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dir = 0; //맨처음 오른쪽
        int x =0;
        int y = 0;
        for (int i = 0; i < m; i++) {
            int[] tmp = dice.clone();
            x = x+dx[dir];
            y = y+dy[dir];

            if(!inRange(x,y)){ //넘어가면
                dir = (dir+2)%4;
                x = x+dx[dir]*2;
                y = y+dy[dir]*2;
            }

            int mapValue = map[x][y];
            for (int j = 0; j < 7; j++) {
                tmp[turn[dir][j]] = dice[j];
            }
            dice = tmp;
            int diceValue = dice[2];

            setScore(x,y);
            if(mapValue < diceValue){
                dir = (dir+1)%4;
            }else if(mapValue>diceValue){
                dir = (dir+3)%4;
            }
        }
        System.out.println(score);
    }

    public static boolean inRange(int x, int y){
        return 0<=x && x<n && 0<=y && y<n;
    }

    public static void setScore(int X, int Y){
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[] {X,Y});

        int target = map[X][Y];
        int[][] visited = new int[n][n];
        score += target;
        visited[X][Y] = 1;

        while(!que.isEmpty()){
            int[] tmp = que.poll();
            int x = tmp[0];
            int y = tmp[1];
            for (int i = 0; i < 4; i++) {
                int nx = x+dx[i];
                int ny = y+dy[i];
                if(inRange(nx,ny) && visited[nx][ny]==0 && map[nx][ny] == target){
                    score += target;
                    que.add(new int[] {nx,ny});
                    visited[nx][ny] = 1;
                }
            }
        }
    }
}
