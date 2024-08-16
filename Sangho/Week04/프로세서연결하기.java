package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ConnectProcessor {
    static StringTokenizer st;

    static int T;
    static int N;

    static int[][] board ;

    static List<Core> coreList;

    static int minLine;
    static int maxCore;

    // 1 상 2 하 3 좌 4 우
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static class Core{
        int x;
        int y;

        public Core(int x, int y){
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    // 전선을 고르는 조합 코어 인덱스, 코어 개수, 전선 개수
    public static void DFS(int index, int countCore, int countLine){
        // 모든 코어를 다 돌면
        if(index == coreList.size()){
            // 여기서 코어수, 라인 길이 비교 및 갱신
            if(maxCore < countCore){
                maxCore = countCore;
                minLine = countLine;
            } else if(maxCore == countCore) {
                minLine = Math.min(countLine,minLine);
            }
            return;
        }

        int x = coreList.get(index).getX();
        int y = coreList.get(index).getY();


        // 여기서 전선 놓을 수 있는지 체크

        // 4방 탐색
        for(int i = 0; i < 4; i++){
            int nx = x;
            int ny = y;
            int count = 0;

            while (true){
                nx += dx[i];
                ny += dy[i];

                if(nx < 0 || ny < 0 || nx >= N || ny >= N){
                    break;
                }

                if(board[nx][ny] == 1){
                    count = 0;
                    break;
                }

                count++;
            }

            // 그 방향으로 카운트 만큼 전선 채우기
            int tx = x;
            int ty = y;

            for(int j = 0; j < count; j++){
                tx += dx[i];
                ty += dy[i];

                if(tx > 0 && ty > 0 && tx < N && ty < N){
                    board[tx][ty] = 1;
                }
            }

            // 깔 수 있는 선이 없었다면
            if(count == 0){
                // 다음 코어를 탐색 하되, 현재 코어는 선택한것이 아니므로 숫자를 늘리지 아니한다.
                DFS(index+1,countCore,countLine);
            } else {
                // 선을 하나라도 깔았다면 다음 코어를 탐색하고 선택 코어 수를 ++ 전선수를 + count 해서 탐색
                DFS(index + 1, countCore + 1, countLine + count);

                tx = x;
                ty = y;

                for(int j = 0; j < count; j++){
                    tx += dx[i];
                    ty += dy[i];

                    if(tx > 0 && ty > 0 && tx < N && ty < N){
                        board[tx][ty] = 0;
                    }
                }
            }
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());

        for(int tc = 1; tc <= T; tc++){

            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());

            coreList = new ArrayList<>();

            board = new int[N][N];

            for(int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++){
                    board[i][j] = Integer.parseInt(st.nextToken());
                    if(board[i][j] == 1){
                        // 가장 자리에 코어가 존재하면 고려하지 아니함
                        if(i == 0 || j == 0 || i == N - 1 || j == N - 1){
                            continue;
                        }
                        coreList.add(new Core(i,j));
                    }
                }
            }

            minLine = Integer.MAX_VALUE;
            maxCore = Integer.MIN_VALUE;

            DFS(0,0,0);

            System.out.println("#"+tc+" "+minLine);

        }
    }
}
