import java.util.*;
import java.io.*;

/*
git commit -m "김성민 / 241006 / [BOJ_로봇 청소기_14503] / 104ms"
### 풀이 사항
풀이 일자: 2024.10.06
풀이 시간: 30분 00초
채점 결과: 정답
예상 문제 유형: 구현
시간: 104 ms
메모리: 14356 kb

### 풀이방법
1. 데이터를 입력받는다. (배열크기, 현재 위치 방향, 배열정보)
2. 벽을 만날때 까지 아래를 반복한다.
 2-1 현재 칸이 0이면 청소 1이면 반복 종료
 2-2 주변 4칸 확인
        반시계 방향으로 회전하면서 앞이 빈칸이면 이동
        없으면 후진
  
### 느낀점

*/

/**

 */
public class Main
{

    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;


        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int y = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int dir = Integer.parseInt(st.nextToken());

        int[][] dd = {{-1,0},{0,1},{1,0},{0,-1}};
        int answer = 0;

        int[][] map = new int[r][c];

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < c; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        // n,m 50 완탐을 해도 충분한 크기
        //while로 구현
        while(true){
            //현재 칸 청소 //해당 칸이 벽이면 작동 중지
            if(map[y][x] == 0){
                map[y][x] =2;
                answer++;
            }else if(map[y][x] == 1) break;
            //주변 4칸 확인
            int cur = 5;
            for(int i = 0; i<4;i++){
                dir= (dir+3)%4;
                int hy = y+dd[dir][0];
                int hx = x+dd[dir][1];
                //있을 시 반시계 방향으로 회전 후 이동
                if(hy>=0 && hy<r && hx>=0 && hx<c && map[hy][hx] == 0){
                    y = hy; x = hx;
                    cur = dir;
                    break;
                }
            }
            if(cur == 5){
                cur =(dir+2)%4;
                y = y+dd[cur][0];
                x = x+dd[cur][1];
            }
        }

        System.out.println(answer);
    }

}