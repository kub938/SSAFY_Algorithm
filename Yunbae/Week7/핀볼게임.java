import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 핀볼_게임 {
    static int[][] delta = {{-1,0},{1,0},{0,-1},{0,1}};
    static int[][] block = {{0,0,0,0},{1,3,0,2},{3,0,1,2},{2,0,3,1},{1,2,3,0},{1,0,3,2}};
    static int[][] board;
    static int n;
    static ArrayList<int[]> wormhole;

    public static boolean inRange(int x, int y){
        return 0<=x && x<n && 0<=y && y<n;
    }

    public static int pinball(int x, int y, int nowDir){
        int cnt = 0;
        int nx = x;
        int ny = y;
        while(true) {
            nx += delta[nowDir][0];
            ny += delta[nowDir][1];

            if(!inRange(nx,ny)){
//                nx -= delta[nowDir][0];
//                ny -= delta[nowDir][1];
                nowDir = block[5][nowDir];
                cnt+=1;
                continue;
            }

            if(x == nx && y == ny){
                return cnt;
            }

            if(board[nx][ny] != 0){
                int blockType = board[nx][ny];

                if(blockType==-1){
                    return cnt;
                }

                else if(blockType<6){
                    nowDir = block[blockType][nowDir];
                    cnt+=1;
                }
                else {
                    for (int[] ints : wormhole) {
                        if (ints[0] == blockType && (ints[1] != nx || ints[2] != ny)) {
                            nx = ints[1];
                            ny = ints[2];
                            break;
                        }
                    }
                }
            }


        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for (int t = 1; t < T+1; t++) {
            n = Integer.parseInt(br.readLine().trim());
            StringTokenizer st;
            wormhole = new ArrayList<>();
            board = new int[n][n];

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine().trim());
                for (int j = 0; j < n; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                    if(board[i][j]>5){
                        wormhole.add(new int []{board[i][j], i,j});// 넘버랑 좌표값
                    }
                }
            }


            int ans = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if(board[i][j] == 0){
                        for (int k = 0; k < 4; k++) {
//                        System.out.println(i+" "+j+" "+k);
                            int result = pinball(i,j,k);
                            if(ans<result){
                                ans = result;
                            };
                        }
                    }
                }
            }
            //블럭 1~5일때 웜홀 6~10 일때, 블랙홀 -1 일때 경우의 수 확인

            System.out.println("#" +t+" "+ ans);
        }


    }
}
