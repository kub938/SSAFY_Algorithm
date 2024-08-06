import java.util.Scanner;

public class Main {
    static int N;

    static int[][] arr;

    static int[][] dp;

    static int answer = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();

        // 디피 테이블
        dp = new int[N + 1][3];

        arr = new int[N + 1][3];

        // N개의 집 3개의 색상 비용 입력 받기~
        for(int i = 0; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        dp[0][0] = arr[0][0];
        dp[0][1] = arr[0][1];
        dp[0][2] = arr[0][2];

        // 인접한 집의 색은 달라야한다.
        for(int i = 1; i <= N; i++){
            // 이전집 최저색 + 둘째집 두번째 색 vs 이전집 최저색 + 둘째집 최저색
            dp[i][0] = Math.min(dp[i-1][1],dp[i-1][2]) + arr[i][0];
            dp[i][1] = Math.min(dp[i-1][0],dp[i-1][2]) + arr[i][1];
            dp[i][2] = Math.min(dp[i-1][1],dp[i-1][0]) + arr[i][2];
        }

        System.out.println(Math.min(dp[N][0],Math.min(dp[N][1],dp[N][2])));

    }
}
