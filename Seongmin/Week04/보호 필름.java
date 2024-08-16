import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//0816 1000 start
//0816 1050 end

/*
git commit -m "김성민 / 240816 / [SWEA_보호 필름_2112] / 627ms"
풀이 사항
풀이 일자: 2024.08.16
풀이 시간: 50분
채점 결과: 정답
예상 문제 유형: 부분 집합

풀이 방법)
1. 데이터를 입력받는다.
2. 부분집합 함수를 돌면서 최솟값을 갱신한다.
    2.1 먼저 통과기준에 맞는다면 최솟값을 갱신한다.(기저조건 1)
    2.2 모든 막을 변경하였으면 종료한다.(기저조건 2)
    2.3 해당 막을 가만히 두는 경우, A로 변경하는 경우, B로 변경하는 경우로 나누어 재귀 호출한다.
    2.4 현재 최솟값보다 막을 변경하는 개수가 많은 경우는 더 재귀를 호출할 필요가 없다(백트래킹, 가지치기)
3. 최솟값을 출력한다.

가지치기 없이 부분집합으로만 구성하니 시간 초과가 발생하여 꽤나 까다로웠다.
가지치기를 통해서 탐색할 필요가 없는 경우의 수를 생각하는 것이 중요하다는 걸 느꼈다.
*/

/**
 * 부분 집합
 *
 * 1. 데이터를 입력받는다.
 * 2. 부분집합 함수를 돌면서 최솟값을 갱신한다.
 *  2.1 먼저 통과기준에 맞는다면 최솟값을 갱신한다.(기저조건 1)
 *  2.2 모든 막을 변경하였으면 종료한다.(기저조건 2)
 *  2.3 해당 막을 가만히 두는 경우, A로 변경하는 경우, B로 변경하는 경우로 나누어 재귀 호출한다.
 *  2.4 현재 최솟값보다 막을 변경하는 경우의 수는 더 재귀를 호출할 필요가 없다(백트래킹, 가지치기)
 * 3. 최솟값을 출력한다.
 */
public class Solution {
    static int height;
    static int width;
    static int passNum;
    static int[][] film;
    static int min;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine().trim());
            /** 1. 데이터를 입력받는다.*/
            height = Integer.parseInt(st.nextToken());
            width = Integer.parseInt(st.nextToken());
            passNum = Integer.parseInt(st.nextToken());
            min = Integer.MAX_VALUE;

            film = new int[height][width];

            for (int i = 0; i < height; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < width; j++) {
                    film[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            /** 2. 부분집합 함수를 돌면서 최솟값을 갱신한다.*/
            back(0,0);
            /** 3. 최솟값을 출력한다.*/
            sb.append("#" + tc+" ").append(min + "\n");
        }
        System.out.print(sb);
    }

    /**
     *  *  2.1 먼저 통과기준에 맞는다면 최솟값을 갱신한다.(기저조건 1)
     *  *  2.2 모든 막을 변경하였으면 종료한다.(기저조건 2)
     *  *  2.3 해당 막을 가만히 두는 경우, A로 변경하는 경우, B로 변경하는 경우로 나누어 재귀 호출한다.
     *  *  2.4 현재 최솟값보다 막을 변경하는 경우의 수는 더 재귀를 호출할 필요가 없다(백트래킹, 가지치기)
     */
    private static void back(int d, int change) {
        //백트래킹 가지치기
        if(change >= min){
            return;
        }
        //기저조건1
        if(pass()){
            min = Math.min(min,change);
            return;
        }
        //기저조건2
        if(d>= height){
            return;
        }
        //변경하지 않는 경우의 수
        back(d+1,change);

        int[] tmp = film[d].clone();
        for (int i = 0; i < width; i++) {
            film[d][i] = 1;
        }
        //A로 변경하는 경우의 수
        back(d+1,change+1);
        for (int i = 0; i < width; i++) {
            film[d][i] = 0;
        }
        //B로 변경하는 경우의수
        back(d+1, change+1);

        film[d] = tmp;
    }

    //통과기준에 맞는지 체크하는 함수
    private static boolean pass() {
        for (int i = 0; i < width; i++) {
            int cnt = 1;
            int max = 0;

            for (int j = 1; j < height; j++) {
                if(film[j][i] == film[j-1][i]) cnt++;
                else cnt = 1;
                max = Math.max(max,cnt);
            }
            if(max<passNum) return false;
        }
        return true;
    }
}