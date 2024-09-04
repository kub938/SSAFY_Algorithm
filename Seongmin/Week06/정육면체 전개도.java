import java.io.*;
import java.util.*;

//0816 1625 start
//0813 1110 end

/*
git commit -m "김성민 / 240904 / [BOJ_정육면체 전개도_1917] / 104ms"
풀이 사항
풀이 일자: 2024.09.04
풀이 시간: 4시간
채점 결과: 정답
예상 문제 유형: 구현

풀이방법
1. 주사위의 6가지 면을 표시하는 변수를 설정한다
2. 전개도 위에서 dfs를 실행하면서 주사위를 회전시키며 바닥과 맞닿는 숫자를 방문체크한다.
3. 만약 모든면이 만났으면 yes 아니면 no

느낀점
맵 위로 주사위를 굴리는게 아니라 전개도를 접는 느낌으로 문제를 풀려고 했는데 결국 성공하지 못했다.
멘탈이 나가면 문제 해답 방식을 접근하는데 너무 오래걸리는 거 같다.
시험때는 아예 싹 지우고 다시 생각하는 방식을 사용해야할거같다.

*/

public class Main {

    static class Hexa {
        int up, down, west, south, east, north;

        public Hexa() {
            up = 6;
            down = 1;
            west = 2;
            south = 3;
            east = 4;
            north = 5;
        }
    }

    static Hexa hexa;
    static boolean[] sides;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = 3;
        arr = new int[6][6];

        for (int tc = 0; tc < t; tc++) {
            sides = new boolean[7];
            hexa = new Hexa();

            int startY = 0;
            int startX = 0;

            for (int i = 0; i < 6; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 6; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                    if (arr[i][j] == 1) {
                        startY = i;
                        startX = j;
                    }
                }
            }

            dfs(startY, startX);

            if (check())
                System.out.println("yes");
            else
                System.out.println("no");

        }

    }

    static int[][] dd = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private static void dfs(int y, int x) {
        sides[hexa.down] = true;
        arr[y][x] = 0;

        for (int i = 0; i < 4; i++) {
            int hy = y+dd[i][0];
            int hx = x+dd[i][1];

            if(hy>=0 && hy<6 && hx>=0 && hx<6 && arr[hy][hx]==1){
                rotate(i);
                dfs(hy,hx);
                int a;
                if(i<2) a = 1-i;
                else{
                    a = i+1;
                    if(a>3) a=2;
                }
                rotate(a);
            }
        }
    }

    private static void rotate(int dir) {
        int tmp;
        //북쪽으로 회전
        if(dir==0){
            //바닥->남
            //남 -> 위
            //위 -> 북
            //북 -> 바닥
            tmp = hexa.down;
            hexa.down = hexa.north;
            hexa.north = hexa.up;
            hexa.up = hexa.south;
            hexa.south = tmp;
        }
        //남쪽으로 회전
        else if (dir==1) {
            //남->바닥
            //바닥->북
            //북-> 위
            //위 -> 남
            tmp = hexa.south;
            hexa.south = hexa.up;
            hexa.up = hexa.north;
            hexa.north = hexa.down;
            hexa.down= tmp;
        }
        //서쪽으로 회전
        else if (dir == 2){
            //서쪽 -> 바닥
            //바닥 -> 동
            //동 -> 위
            //위 -> 서
            tmp = hexa.west;
            hexa.west = hexa.up;
            hexa.up = hexa.east;
            hexa.east = hexa.down;
            hexa.down = tmp;
        }
        //동쪽으로 회전
        else{
            tmp = hexa.east;
            hexa.east = hexa.up;
            hexa.up = hexa.west;
            hexa.west = hexa.down;
            hexa.down = tmp;
        }
    }

    private static boolean check() {
        for (int i = 1; i <= 6; i++) {
            if (!sides[i]) return false;
        }
        return true;
    }

}
