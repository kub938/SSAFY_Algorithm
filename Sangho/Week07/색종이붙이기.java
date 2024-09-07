package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ColorPaper {

    static StringTokenizer st;

    static int[][] board = new int[10][10];

    static int[] papers = {0,5,5,5,5,5};

    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int r = 0; r < 10; r++){
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < 10; c++){
                board[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        DFS(0,0,0);

        if(answer == Integer.MAX_VALUE){
            answer = -1;
        }

        System.out.println(answer);


    }

    // 색종이 붙여보기
    public static void DFS(int x, int y, int depth) {
        if(x >= 9  && y > 9){
            answer = Math.min(answer, depth);
            return;
        }

        if(answer <= depth) {
            return;
        }

        if(y > 9){
            DFS(x+1,0,depth);
            return;
        }

        // 색종이 붙
        if(board[x][y] == 1){
            // 큰 색종이부터 붙이기
            for(int i = 5; i >= 1; i--){
                if(papers[i] > 0 && isValid(x,y,i)){
                    attach(x,y,i);
                    papers[i]--;
                    DFS(x,y+1,depth + 1);
                    detach(x,y,i);
                    papers[i]++;
                }
            }
        } else {
            DFS(x,y+1,depth);
        }
    }

    // 색종이 붙이기
    public static void attach(int x, int y, int size) {
        for(int r = x; r < x + size; r++){
            for(int c = y; c < y + size; c++){
                board[r][c] = 0;
            }
        }
    }

    // 색종이 복구
    public static void detach(int x, int y, int size) {
        for(int r = x; r < x + size; r++){
            for(int c = y; c < y + size; c++){
                board[r][c] = 1;
            }
        }
    }

    // 색종이 붙일 수 있는지 검증
    public static boolean isValid(int x, int y, int size){
        for(int r = x; r < x + size; r++){
            for(int c = y; c < y + size; c++){
                if (r < 0 || r >= 10 || c < 0 || c >= 10) {
                    return false;
                }

                if (board[r][c] != 1) {
                    return false;
                }
            }
        }

        return true;
    }
}
