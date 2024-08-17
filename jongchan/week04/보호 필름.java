import java.util.Scanner;

public class Solution {

    static int changeCount;
    static int k;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();
        StringBuilder sb = new StringBuilder();

        for (int test_case = 1; test_case <= T; test_case++) {
            sb.append("#").append(test_case).append(" ");

            int d = sc.nextInt();
            int w = sc.nextInt();
            k = sc.nextInt();

            int[][] arr = new int[d][w];

            for (int i = 0; i < d; i++) {
                for (int j = 0; j < w; j++) {
                    arr[i][j] = sc.nextInt();
                }
            }

            changeCount = k;
            dfs(0, 0, arr);

            sb.append(changeCount).append("\n");
        }
        System.out.println(sb);
    }

    private static void dfs(int cnt, int depth, int[][] arr) {

        if (check(arr)) {
            changeCount = Math.min(cnt, changeCount);
            return;
        }

        if (depth == arr.length || cnt >= k) {
            return;
        }

        //1. depth열을 0으로 바꿈
        int[][] cpy = new int[arr.length][arr[0].length];
        copy(cpy, arr);
        change(cpy, depth, 0);
        dfs(cnt + 1, depth + 1, cpy);

        //2. depth열을 1으로 바꿈
        copy(cpy, arr);
        change(cpy, depth, 1);
        dfs(cnt + 1, depth + 1, cpy);

        //3. 바꾸지 않음
        copy(cpy, arr);
        dfs(cnt, depth + 1, cpy);
    }

    private static void copy(int[][] cpy, int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.arraycopy(arr[i], 0, cpy[i], 0, arr[0].length);
        }
    }

    private static void change(int[][] arr, int row, int num) {
        for (int i = 0; i < arr[0].length; i++) {
            arr[row][i] = num;
        }
    }


    private static boolean check(int[][] arr) {

        for (int i = 0; i < arr[0].length; i++) {
            int cnt = 1;
            int max = 1;

            for (int j = 1; j < arr.length; j++) {
                if (arr[j - 1][i] == arr[j][i]) {
                    cnt++;
                    max = Math.max(max, cnt);
                } else {
                    cnt = 1;
                }
            }
            if (max < k) {
                return false;
            }
        }
        return true;
    }
}