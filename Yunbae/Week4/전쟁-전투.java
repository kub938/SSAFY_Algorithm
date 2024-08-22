import java.util.Scanner;
import java.util.LinkedList;

public class BOJ1303 {
    static int[][] visited;
    static int n;
    static int m;
    static char[][] board;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        m = sc.nextInt();
        n = sc.nextInt();
        board = new char[n][m];
        visited = new int[n][m];
        for (int i = 0; i < n; i++) {
            String str = sc.next();
            for (int j = 0; j < m; j++) {
                board[i][j] = str.charAt(j);
            }
        }


        int whitePower = 0;
        int bluePower = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visited[i][j] == 0) {
                    char color = board[i][j];
                    visited[i][j] = 1;
                    int result = bfs(i,j,color);
                    if (color=='W'){
                        whitePower +=result;
                    }else if(color=='B'){
                        bluePower += result;
                    }
                }


            }
        }
        System.out.println(whitePower+" "+bluePower);
    }

    public static boolean inRange(int x,int y){
        return 0<=x && x<n && 0<=y && y<m;
    }


    public static int bfs(int x,int y, char color){
        LinkedList<int[]> que = new LinkedList<>();
        int[] dx = {0,1,0,-1};
        int[] dy = {1,0,-1,0};
        que.add(new int[]{x, y});
        int cnt=1;
        while(!que.isEmpty()){
            int[] coord = que.poll();
            x = coord[0];
            y = coord[1];
            for (int i = 0; i < 4; i++) {
                int nx = x+dx[i];
                int ny = y+dy[i];
                if (inRange(nx,ny) && visited[nx][ny]==0 && board[nx][ny]==color){
                    visited[nx][ny] = 1;
                    cnt++;
                    que.add(new int[] {nx,ny});
                }
            }

        }
        return cnt*cnt;
    }
}
