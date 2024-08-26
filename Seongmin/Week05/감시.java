import java.io.*;
import java.util.*;
/*
start:0307 0422

git commit -m "김성민 / 240826 / [BOJ_감시_15683] / 224ms"
풀이 사항
풀이 일자: 2024.08.26
풀이 시간: 1시간 10분
채점 결과: 정답
예상 문제 유형: 중복 순열

풀이방법
1. 데이터를 입력받는다.
 1-1 cctv의 정보를 저장하는 리스트를 생성하고 저장한다.
2. 각 cctv에 대해서 방향을 중복순열로 구한다
3. 중복 순열이 끝나면 선을 긋고 개수를 파악해서 최댓값을 구한다

느낀 점)
로직자체는 빠르게 파악했으나 구현과정에서 미흡한 부분이 많았다.
잦은 실수를 방지하기 위해서 처음 짤때 막짜지말고 설계에 따라서 차근차근 작성해야할 거 같다.
또한 -1 % 4 는 -3이라는걸 파악하지 못해서 해결에 애를 먹었다 이 부분도 확실하게 기억해야 할 거 같다.

5번 cctv나 2번 cctv같은 경우는 회전이 의미 없는 경우가 생기므로 해당 경우를 돌지 않도록 구현하였다.
*/

/**
 * 1. 데이터를 입력받는다.
 *  1-1 cctv의 정보를 저장하는 리스트를 생성하고 저장한다.
 * 2. 각 cctv에 대해서 4방향 중 어디로 돌지 중복순열을 진행한다.
 * 3. 중복 순열이 끝나면 선을 긋고 개수를 파악해서 최댓값을 구한다
 *
 */

public class Main {
    static ArrayList<CCTV> cctvList = new ArrayList<>();
    static int max = 0;
    static int rows;
    static int cols;
    static int[][] matrix;
    static int[][] copy;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        rows = Integer.parseInt(st.nextToken());
        cols = Integer.parseInt(st.nextToken());
        matrix = new int[rows][cols];

        int blind = 0;

        for (int i = 0; i < rows; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
                if (matrix[i][j] == 1 || matrix[i][j] == 2 ||
                        matrix[i][j] == 3 || matrix[i][j] == 4 || matrix[i][j] == 5) {
                    cctvList.add(new CCTV(i, j, matrix[i][j]));
                } else if(matrix[i][j]==0){

                    blind++;
                }
            }
        }
        //중복 순열로 cctv
        duPermutation(0);

        System.out.println(blind-max);
    }
    static int[][] dd = {{1,0},{0,1},{-1,0},{0,-1}};
    static ArrayList<Integer> dirList = new ArrayList<>();

    private static void duPermutation(int depth) {
        if (depth == cctvList.size()) {
            max = Math.max(max, getScore());
            return;
        }
        int type = cctvList.get(depth).type;

        for (int i = 0; i < 4; i++) {
            if(type == 5 && i>0) break;
            if(type == 2 && i>1) break;

            dirList.add(i);
            duPermutation(depth+1);
            dirList.remove(dirList.size()-1);
        }
    }

    private static int getScore() {
        copy = new int[rows][];
        for (int i = 0; i < rows; i++) {
            copy[i] = matrix[i].clone();
        }
        int score = 0;

        for (int i = 0; i < cctvList.size(); i++) {
            //중복순열 리스트에 방향과 cctv 타입에 따라 선을 긋기
            int dir = dirList.get(i);
            int type = cctvList.get(i).type;
            int y = cctvList.get(i).r;
            int x = cctvList.get(i).c;
            score+= draw(y,x,dir);
            if(type == 2){
                score+=draw(y,x,(dir+2)%4);
            }
            else if(type == 3){
                score+=draw(y,x,(dir+1)%4);
            }
            else if(type == 4){
                score+=draw(y,x,(dir+1)%4);
                int a = (dir-1)%4;
                score+=draw(y,x,a<0 ? a+4:a);
            }
            else if(type == 5){
                score+=draw(y,x,(dir+1)%4);
                score+=draw(y,x,(dir+2)%4);
                score+=draw(y,x,(dir+3)%4);
            }
        }
        return score;
    }
    private static int draw(int y, int x, int dir){
        int cnt = 0;
        int hy = y+dd[dir][1];
        int hx = x+dd[dir][0];

        while(hy>=0 && hy<rows && hx>=0 && hx<cols && copy[hy][hx]!=6){
            if(copy[hy][hx]==0){
                cnt++;
                copy[hy][hx]=99;
            }
            hy+=dd[dir][1];
            hx+=dd[dir][0];
        }
        return cnt;
    }

    static class CCTV {
        int r;
        int c;
        int type;

        public CCTV(int r, int c, int type) {
            this.r = r;
            this.c = c;
            this.type = type;
        }
    }

}
