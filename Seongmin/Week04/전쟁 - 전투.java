import java.io.*;
import java.util.*;

//0816 1050 start
//0813 1110 end

/*
git commit -m "김성민 / 240808 / [BOJ_전쟁 - 전투_1303] / 132ms"
풀이 사항
풀이 일자: 2024.08.16
풀이 시간: 20분
채점 결과: 정답
예상 문제 유형: DFS,BFS

풀이방법
1. 데이터를 입력받는다.
    1.1 width, height를 입력받고
    1.2 맵(배열)의 정보를 받는다
2.맵을 전부 돌면서 'B' 'W'의 집합 위력 합을 더한다.
    2.1 BFS 혹은 DFS를 통해 상하좌우에 있는 같은 팀을 찾는다.
    2.2 한번 탐색한 부분은 다시 방문하지 않도록 '0'으로 바꾼다.
3. 위력을 출력한다.
*/

/**
 * 1. 데이터를 입력받는다.
 *  1.1 width, height를 입력받고
 *  1.2 맵(배열)의 정보를 받는다
 * 2.맵을 전부 돌면서 'B' 'W'의 집합 위력 합을 더한다.
 *  2.1 BFS 혹은 DFS를 통해 상하좌우에 있는 같은 팀을 찾는다.
 *  2.2 한번 탐색한 부분은 다시 방문하지 않도록 '0'으로 바꾼다.
 * 3. 위력을 출력한다.
 */

public class Main {
    static int width;
    static int height;
    static char[][] map;
    static int cnt = 0;
    static int blue = 0;
    static int white = 0;

    static int[][] dd = {{0,1},{-1,0},{1,0},{0,-1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        width = Integer.parseInt(st.nextToken());
        height = Integer.parseInt(st.nextToken());

        map = new char[height][width];

        for (int i = 0; i < height; i++) {
            String s = br.readLine();
            for (int j = 0; j < width; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                cnt = 0;
                if(map[i][j] == 'B'){
                    int num = dfs(i,j,'B');
                    blue += num*num;
                }else if(map[i][j]=='W'){
                    int num = dfs(i,j,'W');
                    white += num*num;
                }
            }
        }

        System.out.println(white+" "+blue);
    }

    private static int dfs(int y, int x, char c) {
        map[y][x] = '0';
        int sum = 1;
        for(int[] d: dd){
            int hy = y+d[0];
            int hx = x+d[1];

            if(hy>=0 && hy<height && hx>=0 && hx<width && c==map[hy][hx]){
                sum+= dfs(hy,hx,c);
            }
        }
        return sum;
    }
}