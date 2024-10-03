import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * start 14:43
 * dp[i][i] = 펠린드롬
 * dp[i][i+1] = arr[i] = arr[i+1]이면 펠린드롬
 * dp[i][i+k] = dp[i+1][i+k-1] 이 펠린드롬, arr[i] = arr[i+k]
 */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int[][] dp = new int[n + 1][n + 1];
        int[] arr = new int[n + 1];

        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            dp[i][i] = 1;
        }

        for (int i = 1; i < n; i++) {
            if (arr[i] == arr[i + 1]) {
                dp[i][i+1] = 1;
            }
        }

        for (int len = 2; len < n; len++) {
            for (int i = 1; len + i <= n ; i++) {
                if (dp[i + 1][i + len - 1] == 1 && arr[i] == arr[i + len]) {
                    dp[i][i + len] = 1;
                }
            }
        }

        int k = Integer.parseInt(br.readLine());
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            sb.append(dp[start][end]).append("\n");
        }
        System.out.println(sb);
    }
}
