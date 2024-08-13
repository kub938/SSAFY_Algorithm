import java.io.*;
import java.util.*;

//0813 1329 start
//0813 1512 end

/*
git commit -m "김성민 / 240808 / [BOJ_소용돌이 예쁘게 출력하기_1022] / 608ms"
풀이 사항
풀이 일자: 2024.08.13
풀이 시간: 1시간 45분
채점 결과: 정답 (로직 못찾아서 도움 받음)
예상 문제 유형: 구현

풀이방법
1. 달팽이 숫자가 1,1 / 2,2 / 3,3 식으로 움직이는 것을 바탕으로 로직을 구성하였다.
2. repeat만큼 칸을 이동하면서 숫자를 넣는다.
3. 전부 이동하면 방향을 변환한다.
4. 반복 2번마다 repeat값을 늘린다.
5. 표시할 부분이면 배열에 num값을 저장한다.
6. 가장 긴 숫자만큼 공백을 추가해서 출력한다.

피드백
로직을 찾는데 굉장히 시간이 오래걸렸다. 시간 복잡도는 충분하다고 생각했는데 메모리 부족으로 에러가 발생해서
이를 해결하는데 굉장히 애를 먹었다. 결국 혼자만의 힘으로 못풀었다.
*/
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        //달팽이로 채우고
        
        st = new StringTokenizer(br.readLine());
        int miny =Integer.parseInt(st.nextToken());
        int minx =Integer.parseInt(st.nextToken());
        int maxy =Integer.parseInt(st.nextToken());
        int maxx =Integer.parseInt(st.nextToken());

        int ysize = maxy-miny+1;
        int xsize = maxx-minx+1;
        int size = Math.max(ysize, xsize);

        String[][] arr = new String[ysize][xsize];

        //범위에 들면 저장한다.

        int[][] dd = {{0,1},{-1,0},{0,-1},{1,0}};
        int num = 1;
        int repeat = 1;
        int x = 5000;
        int y = 5000;
        int dir = 0;
        //repeat을 두번하기 위한 변수
        int t = 2;

        int maxLen = 0;

        while (x>=0 && x<=10000 && y>=0 && y<=10000){
            for (int i = 0; i < repeat; i++) {
                if(y>=miny+5000 && y<=maxy+5000 && x>=minx+5000 && x<= maxx+5000){
                    arr[y-(5000+miny)][x-(5000+minx)] = String.valueOf(num);

                    maxLen= Math.max(maxLen,String.valueOf(num).length());
                }
                num++;
                y+= dd[dir][0];
                x+= dd[dir][1];
            }
            dir= (dir+1)%4;
            t--;
            if(t==0){
                t=2;
                repeat++;
            }
        }


        for (int i = 0; i < ysize; i++) {
            for (int j =0; j <xsize; j++) {
                int diff = maxLen-arr[i][j].length();
                if(diff>0){
                    for (int k = 0; k < diff; k++) {
                        sb.append(" ");
                    }
                }
                sb.append(arr[i][j]+" ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }

}
/*

1   3   13  31  57
 2  10 18 26      차이가 수열
1 5 17 37 왼 대각선
 4 12 20 숫자간 차이
0 2 4 6 내리기
 */