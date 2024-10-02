import java.util.*;
import java.io.*;

public class Main {
  
    static int N, M;
    static int[] numList;
    static boolean[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        numList = new int[N];
        dp = new boolean[N][N]; // DP 테이블 초기화

        // 수열 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numList[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine()); // 질문 수

        // 길이 1인 경우
        for (int i = 0; i < N; i++) {
            dp[i][i] = true; // 길이가 1인 것은 항상 팰린드롬
        }

        // 길이 2인 경우
        for (int i = 0; i < N - 1; i++) {
            if (numList[i] == numList[i + 1]) {
                dp[i][i + 1] = true; // 길이가 2이고 같으면 팰린드롬
            }
        }

        // 길이 3 이상인 경우
        for (int length = 3; length <= N; length++) {
            for (int start = 0; start <= N - length; start++) {
                int end = start + length - 1; 
                if (numList[start] == numList[end]) {
                    dp[start][end] = dp[start + 1][end - 1]; 
                }
            }
        }

        // 질문 처리
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < M; i++) {
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st1.nextToken()) - 1; 
            int end = Integer.parseInt(st1.nextToken()) - 1;  

            result.append(dp[start][end] ? 1 : 0).append("\n");
        }

        // 결과 출력
        System.out.print(result);
    }
}

