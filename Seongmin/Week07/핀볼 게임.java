import java.io.*;
import java.util.*;
 
/*

git commit -m "김성민 / 240906 / [SWEA_핀볼 게임_5650] / 570ms"
***풀이 사항
풀이 일자: 2024.09.06
풀이 시간: 45분
채점 결과: 정답
예상 문제 유형: 구현

***풀이방법
1. 데이터를 입력받는다.
 1-1 웜홀을 따로 배열에 저장한다
2. 0인 칸에 상하좌우에 대해서 모두 핀볼 함수를 실행한다.
3. 경로에 부딪힌 블록수(score)를 리턴한다.
    3-1 시작칸, 블랙홀을 만나면 score리턴
    3-2 벽이나 블록에 의해 그대로 반대방향으로 튕겨져나오는 경우, score*2+1을 리턴한다.
4. 이중 최대값을 리턴한다.

***느낀부분
블록 모양에 따라 상하좌우바뀌는 것을 구현하는데 시간이 걸렸다.
어짜피 반대방향으로 튕겨져 나오면 같은 루트를 걸쳐서 재자리로 돌아오는걸 파악하고
score*2+1을 리턴하면 시간을 많이 줄일 수 있다.
*/

 
public class Solution {
    static int[][] blockDir = {{},{99,0,3,99},{3,2,99,99},{1,99,99,2},{99,99,1,0},{99,99,99,99}};
    static int[][] dd = {{-1,0},{0,-1},{1,0},{0,1}};
    static int max = 0;
    static int n;
    static int[][] map = new int[100][100];
    static Point[][] holes = new Point[5][2];
    static int[] idx = new int[5];
    static class Point{
        int y,x;
 
        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
 
        int T = Integer.parseInt(br.readLine().trim());
 
        for (int tc = 1; tc <= T; tc++) {
            n = Integer.parseInt(br.readLine().trim());
            max = 0;
            idx = new int[5];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine().trim());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
 
                    if(map[i][j] >= 6 && map[i][j] <= 10) {
                        int a = map[i][j]-6;
                        holes[a][idx[a]++] = new Point(i,j);
                    }
                }
            }
 
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if(map[i][j] == 0) {
                        for (int k = 0; k < 4; k++) {
                            max = Math.max(max,pinball(i,j,k));
                        }
                        //System.out.println(i+" "+j+" max:"+max);
                    }
                }
            }
 
            sb.append('#').append(tc).append(' ').append(max).append('\n');
        }
        System.out.print(sb);
    }
 
    private static int pinball(int y, int x, int dir) {
        int hy =y;
        int hx =x;
        int score = 0;
 
        while(true){
            hy += dd[dir][0];
            hx += dd[dir][1];
            if(hy==y && hx==x){ return score; }
            if(hy< 0 || hx < 0 || hx >= n || hy >= n){
                return score*2 + 1;
            }
            if(map[hy][hx] == -1){
                return score;
            }
 
            if(map[hy][hx] >= 1 && map[hy][hx] <= 5){
                dir = blockDir[map[hy][hx]][dir];
                if(dir==99) return score*2 +1;
                score++;
 
            }
            else if(map[hy][hx] >= 6 && map[hy][hx] <= 10){
                //웜홀
                int idx = map[hy][hx]-6;
                Point next;
                if(hy==holes[idx][0].y && hx==holes[idx][0].x){
                    next = holes[idx][1];
                }else{
                    next = holes[idx][0];
                }
                hy = next.y;
                hx = next.x;
            }
        }
    }
}