import java.io.*;
import java.util.*;

//0816 1625 start
//0813 1110 end

/*
git commit -m "김성민 / 240825 / [BOJ_Puyo Puyo_11559] / 96ms"
풀이 사항
풀이 일자: 2024.08.25
풀이 시간: 30분
채점 결과: 정답
예상 문제 유형: DFS

풀이방법
1. 데이터를 입력받는다. 12줄의 char[][]
2. 연쇄가 발생 안할때까지 2,3,4 반복
3. 바닥 칸부터 .이아닌 모든 칸에 대해서 dfs를 돌린다.
     3.1 칸을 '?'로 설정하고 dfs 호출 횟수를 증가시킨다.
     3.2 상하좌우의 자신과 같은 알파벳이면 dfs호출
4. dfs호출횟수가 4회 이상이면 '?'를 '.'으로 바꾸고 아니면 원상복구 시킨다
5. 모든 블럭을 아래로 떨어트린다

처음에는 개수를 세고 값을 바꾸는것을 하나의 dfs로 구현했지만
dfs 특징에 대해 잘 생각하지 못해서 값이 정상적으로 갱신되지 않는 문제가 있었다.
나같은 경우, 개수파악 dfs, 값 갱신하는dfs 두개를 사용했지만 다른 답안을 찾아보니
dfs하나만 사용해서 list에 바꿀 좌표를 저장하고 for문으로 한번에 바꾸는게 더 직관적인 구현방식인거 같다.
*/

/**
 * 뿌요 상하좌우로 4개 이상 연결되어있으면 한꺼번에 없어짐 <= 1연쇄
 * 없어진 공백 위에 뿌요 떨어짐 만약 또 4개 이상이면 <= 연쇄
 * <p>
 * 터질 뿌요가 여러 그룹이여도 동시에 터지고 1번의 연쇄 (연쇄가 몇번 일어날지 리턴)
 * height 12
 * width 6
 * ......
 * RGBPY
 * 1. 데이터를 입력받는다. 12줄의 char[][]
 * 2. 연쇄가 발생 안할때까지 반복
 * 2. 바닥 칸부터 .이아닌 모든 칸에 대해서 dfs를 돌린다.
 *  2.1 칸을 '?'로 설정하고 dfs 호출 횟수를 증가시킨다.
 *  2.2 상하좌우의 자신과 같은 알파벳이면 dfs호출
 * 3. dfs호출횟수가 4회 이상이면 '?'를 '.'으로 바꾸고 아니면 원상복구 시킨다
 * 4. 모든 블럭을 아래로 떨어트린다
 */

public class Main {
    static char[][] arr = new char[12][6];
    static int boomCnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < 12; i++) {
            arr[i] = br.readLine().toCharArray();
        }

        while (isBoom()) {
            boomCnt++;
            down();
        }

        System.out.println(boomCnt);
    }
    static int fuyoCnt = 0;

    private static boolean isBoom() {
        boolean bean = false;
        for (int i = 11; i >= 0; i--) {
            for (int j = 5; j >= 0; j--) {
                if (arr[i][j] != '.') {
                    fuyoCnt = 0;
                    char c = arr[i][j];
                    dfs(i, j, c);
                    if(fuyoCnt>=4) {
                        dfs2(i,j,'.');
                        bean = true;
                    }else{
                        dfs2(i,j,c);
                    }
                }
            }
        }
        return bean;
    }

    /* RGBPY
     * 1. 데이터를 입력받는다. 12줄의 char[][]
     * 2. 연쇄가 발생 안할때까지 반복
     *  2. 바닥 칸부터 .이아닌 모든 칸에 대해서 dfs를 돌린다.
     *      2.1 상하좌우의 자신과 같은 알파벳이면 dfs호출
     *      2.2 dfs호출이 4회 이상이면 빠져나오면서 해당 칸을 .으로 바꿈
     *  3. 모든 블럭을 아래로 떨어트린다
     */
    static int[][] dd = {{1,0},{0,1},{0,-1},{-1,0}};

    private static void dfs2(int y, int x, char c) {
        arr[y][x] = c;
        for(int[] d:dd){
            int hy = y+d[0];
            int hx = x+d[1];

            if(hy>=0 && hy<12 && hx>=0 && hx<6 && arr[hy][hx]=='?'){
                dfs2(hy,hx,c);
            }
        }
    }
    private static void dfs(int y, int x, char c) {
        fuyoCnt++;
        arr[y][x] = '?';
        for(int[] d:dd){
            int hy = y+d[0];
            int hx = x+d[1];

            if(hy>=0 && hy<12 && hx>=0 && hx<6 && c==arr[hy][hx]){
                dfs(hy,hx,c);
            }
        }
    }

    private static void down() {
        ArrayList<Character> alist = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            alist.clear();
            for (int j = 11; j >= 0; j--) {
                if(arr[j][i]!='.') alist.add(arr[j][i]);
                arr[j][i] = '.';
            }
            int a = 11;
            for(char c : alist){
                arr[a--][i] = c;
            }
        }
    }
}
