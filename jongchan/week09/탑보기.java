import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * start 12:55
 * 건물 i를 기준으로 왼쪽 오름차순, 오른쪽 오름차순의 개수??
 * 풀이 1. 스택을 사용하여 인덱스를 넣는데, 만약 현재 스택의 피크가 현재 인덱스의 크기보다 작다면, pop
 * 필요 변수 int n, int[][] result, int[][] arr
 */

public class Main {

    static int n;
    static int[][] result = new int[100_001][2];
    static int[] arr = new int[100_001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            result[i][1] = -100_000;
        }

        solve();

        for (int i = 1; i <= n; i++) {
            sb.append(result[i][0]);
            if (result[i][0] == 0) {
                sb.append("\n");
            } else {
                sb.append(" ").append(result[i][1]).append("\n");
            }
        }
        System.out.println(sb);

    }

    private static void solve() {
        Stack<Integer> stack = new Stack<>();

        for (int i = 1; i <= n; i++) {
            while(!stack.isEmpty() && arr[stack.peek()] <= arr[i]) {
                stack.pop();
            }
            result[i][0] = stack.size();
            if (!stack.isEmpty()) {
                result[i][1] = stack.peek();
            }
            stack.push(i);
        }

        stack.clear();

        for (int i = n; i >= 1; i--) {
            while(!stack.isEmpty() && arr[stack.peek()] <= arr[i]) {
                stack.pop();
            }
            result[i][0] += stack.size();

            if (!stack.isEmpty() && (stack.peek() - i) < i - result[i][1]) {
                result[i][1] = stack.peek();
            }
            stack.push(i);
        }
    }
}