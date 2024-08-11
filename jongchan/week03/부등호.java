import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] min;
    static int[] max;

    static int n;
    static char[] arr;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        input();
        dfs(0, new int[n + 1]);

        for (int i = 0; i < n + 1; i++) {
            System.out.print(max[i]);
        }
        System.out.println();

        for (int i = 0; i < n + 1; i++) {
            System.out.print(min[i]);
        }
        System.out.println();


    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new char[n];
        visited = new boolean[10];

        for (int i = 0; i < n; i++) {
            arr[i] = st.nextToken().charAt(0);
        }
    }

    private static void dfs(int depth, int[] num) {
        if (depth == n + 1) {

            if (!check(num)) {
                return;
            }

            if (min == null) {
                min = num.clone();
            }
            max = num.clone();
            return;
        }

        for (int i = 0; i < 10; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            num[depth] = i;
            dfs(depth + 1, num);
            visited[i] = false;
        }

    }

    private static boolean check(int[] num) {

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '<') {
                if (num[i] > num[i+1]) {
                    return false;
                }
            } else {
                if (num[i] < num[i+1]) {
                    return false;
                }
            }
        }
        return true;
    }

}
