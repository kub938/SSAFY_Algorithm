package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class InequalitySign {
    static int N;
    static String[] arr;
    static String[] numList = {"0","1","2","3","4","5","6","7","8","9"};
    static boolean[] visited = new boolean[10];

    static long max_value = Long.MIN_VALUE;
    static long min_value = Long.MAX_VALUE;

    static String max_string = "";
    static String min_string = "";

    public static void DFS(int depth, String result) {
        if (depth == N + 1) {
            if (isValid(result)) {
                long num = Long.parseLong(result);
                if (num > max_value) {
                    max_value = num;
                    max_string = result;
                }
                if (num < min_value) {
                    min_value = num;
                    min_string = result;
                }
            }
            return;
        }

        for (int i = 0; i < 10; i++) {
            if (!visited[i]) {
                visited[i] = true;
                DFS(depth + 1, result + numList[i]);
                visited[i] = false;
            }
        }
    }

    public static boolean isValid(String result) {
        for (int i = 0; i < N; i++) {
            if (arr[i].equals("<")) {
                if (!(result.charAt(i) < result.charAt(i + 1))) {
                    return false;
                }
            } else if (arr[i].equals(">")) {
                if (!(result.charAt(i) > result.charAt(i + 1))) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new String[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = st.nextToken();
        }

        DFS(0, "");
        System.out.println(max_string);
        System.out.println(min_string);
    }
}
