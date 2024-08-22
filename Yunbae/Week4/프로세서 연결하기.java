// 외각 제외 코어배열 coreList 수집
// dfs() 조건 : core 갯수가 coreList.length와 같으면 리턴, 이때 코어수, 라인갯수, 라인 길이 비교

import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class SWProcessor{
    static int T;
    static int n;
    static int[][] board;
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    static ArrayList<int []> coreList;
    static int maxCore=0;
    static int line = 0;
    static int minLine;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        T = sc.nextInt();

        for (int t = 1; t < T+1; t++) {
        n = sc.nextInt();
        coreList = new ArrayList<>();
        board = new int[n][n];
        for(int i=0; i<n;i++){                             //board에서 좌표 코어좌표 추출
            for(int j=0;j<n;j++){
                board[i][j] = sc.nextInt();
                if(i==0 || i==n-1 || j==0 || j==n-1){
                    continue;
                }
                coreList.add(new int[] {i,j});
            }
        }

        for (int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(coreList.get(i)));
        }

        dfs(0,0,0);

        System.out.println("#"+t+" "+minLine);
        }
    }


    public static boolean inRange(int x,int y){
        return 0<=x && x<n && 0<=y && y<n;
    }


    public static void dfs(int depth, int coreCnt, int lineLength){
        if(depth == coreList.size()){
            if(maxCore<coreCnt){
                maxCore = coreCnt;
            }
            if(maxCore == coreCnt){
                minLine = Math.min(lineLength,minLine);
            }
            return;
        }
        for (int i = 0; i < coreList.size(); i++) {
            int cnt = 0;
            int x = coreList.get(i)[0];
            int y = coreList.get(i)[1];

            for (int j = 0; j < 4; j++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                while (true) {
                    if (inRange(nx, ny)) {
                        if (board[nx][ny] == 1) {
                            cnt = 0;
                            break;
                        } else if (board[nx][ny] == 0) {
                            cnt++;
                        }
                    } else {
                        break;
                    }
                }
                if (cnt == 0) {
                    dfs(depth+1, coreCnt,lineLength);
                } else if (cnt != 0) {
                    dfs(depth + 1, coreCnt + 1, lineLength + cnt);
                    for (int k = 0; k < cnt; k++) {
                        x +=dx[i];
                        y +=dy[i];
                        if(inRange(x,y)){
                            board[x][y] = 0;
                        }
                    }
                }
            }
        }
    }
}
