import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/*

git commit -m "김성민 / 240909 / [SWEA_벽돌 깨기_5650] / 412ms"
***풀이 사항
풀이 일자: 2024.09.09
풀이 시간: 30분
채점 결과: 정답
예상 문제 유형: 중복순열, DFS

***풀이방법
1. 데이터를 입력받음
던지는 공개수, 가로, 세로
벽돌 위치
2. 중복 순열로 떨어트릴 위치 구함
3. 위치에 따라 벽돌 부수는 함수 실행
    3.1 공을 떨어트려서 스플래시 계산해서 부숨(DFS)
    3.2 부순거에 따라 벽돌 내리기
4. 남아있는 벽돌 개수 세기 (min)

*/

public class Solution {
    /**
     * 1. 데이터를 입력받음
     * 던지는 공개수, 가로, 세로
     * 벽돌 위치
     * 2. 중복 순열로 떨어트릴 위치 구함
     * 3. 위치에 따라 벽돌 부수는 함수 실행
     * 3.1 공을 떨어트려서 스플래시 계산해서 부숨(DFS)
     * 3.2 부순거에 따라 벽돌 내리기
     * 4. 남아있는 벽돌 개수 세기 (min)
     */
    static int ballCnt;
    static int width;
    static int height;
    static int min;
    static int[][] map;
    static int[] top; //가장 위에 있는 벽돌
    static int[][] dd = {{1,0},{0,-1},{0,1},{-1,0}};
    static ArrayList<Integer> location;
    static int[][] tmp;
    static int[] copyTop;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            sb.append("#" + tc + " ");
//      * 1. 데이터를 입력받음
//     *  던지는 공개수, 가로, 세로
//     *  벽돌 위치
            st = new StringTokenizer(br.readLine());
            ballCnt = Integer.parseInt(st.nextToken());
            width = Integer.parseInt(st.nextToken());
            height = Integer.parseInt(st.nextToken());

            map = new int[height][width];
            top = new int[width];
            min = Integer.MAX_VALUE;
            location = new ArrayList<>();
            for (int i = 0; i < width; i++) {
                top[i] = height;
            }
            for (int i = 0; i < height; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < width; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());

                    if(top[j]==height && map[i][j] != 0) top[j] = i;
                }
            }
            //System.out.println(Arrays.toString(top));
            back(0);

            sb.append(min+"\n");
        }
        System.out.print(sb);
    }
    //순열을 고르는 코드
    private static void back(int d) {
        if(d>=ballCnt){
            //System.out.println(location.toString());
            breakStone();
            return;
        }
        if(min == 0) return;

        for (int i = 0; i < width; i++) {
            location.add(i);
            back(d+1);
            location.remove(location.size()-1);
        }
    }
    private static void breakStone() {
        //복사배열
        tmp = new int[height][width];
        copyTop = top.clone();

        for (int i = 0; i < height; i++) {
            tmp[i] = map[i].clone();
        }

        for (int i = 0; i < ballCnt; i++) {
            int loc = location.get(i);
            //부수고
            if(copyTop[loc] == height) continue;
            dfs(loc, copyTop[loc]);
            //내리고
            down();
        }

        int cnt = 0;
        //순회하면서 개수세기
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if(tmp[i][j] != 0) cnt++;
            }
        }
        min = Math.min(min, cnt);
    }

    private static void dfs(int x, int y) {
        int power = tmp[y][x]-1;
        tmp[y][x] = 0;

        for(int[] d: dd){
            int hy = y;
            int hx = x;

            for (int i = 0; i < power; i++) {
                hy += d[0]; hx+= d[1];
                if(hy<0 || hy>=height || hx <0 || hx>=width) break;

                if(tmp[hy][hx] != 0){
                    dfs(hx,hy);
                }
            }
        }
    }
    static ArrayList<Integer> arr = new ArrayList<>();
    private static void down() {
        //System.out.println(Arrays.toString(top));
        for (int i = 0; i < width; i++) {
            arr.clear();

            for (int j = height-1; j >= 0; j--) {
                if(tmp[j][i] != 0){
                    arr.add(tmp[j][i]);
                }
                tmp[j][i] = 0;
            }

            for (int j = 0; j < arr.size(); j++) {
                tmp[height-j-1][i] = arr.get(j);
            }
            copyTop[i] = height-arr.size();
        }
        //System.out.println(Arrays.toString(top));

    }




}