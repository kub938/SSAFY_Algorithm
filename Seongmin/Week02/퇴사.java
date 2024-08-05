import java.io.*;
import java.util.*;

//0805 - 12:40-13:25
/* 
풀이 사항
풀이 일자: 2024.08.05
풀이 시간: 30분 00초
채점 결과: 정답
예상 문제 유형: DP, 백트래킹

풀이 방법
백트래킹을 통해서 가능한 모든 경우의 수를 구하고 그중 최대값을 리턴하였다.
DP를 통해서도 풀수있을거 같지만 머리가 굳어서 생각이 잘안난다.
*/
public class Main {
    static int[][] arr;
    static int n;
    static int max = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        //[i][0] 걸리는 날짜
        //[i][1] 보상
        arr = new int[n][2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        //백트래킹 , DP
        back(0,0);

        System.out.println(max);
    }
    //모든 경우의 수 탐색
        //날짜가 n을 넘어가지 않으면 sum에 보상을 더함 
    private static void back(int d, int sum) {
        if (d>=n){
            max = Math.max(sum,max);
        }else{
            back(d+1,sum);
            if(d+arr[d][0] > n){
                back(d+arr[d][0],sum);
            }else{
                back(d+arr[d][0],sum+arr[d][1]);
            }
        }
    }
}

