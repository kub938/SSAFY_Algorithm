package boj.silver.b14501;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int[][] arr;
    static int max;

    public static void main(String[] args) throws IOException {
        input();
        dfs(0,0);

        System.out.println(max);
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n][2];
        max = 0;

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }
    }

    private static void dfs(int index, int sum) {

        if (index > n) {
            return;
        } else if(index == n) {
            max = Math.max(max, sum);
            return;
        }

        max = Math.max(max, sum);

        // 현재 날짜를 선택하지 않고 다음 날짜로 감
        dfs(index + 1, sum);

        //현재 날짜를 선택
        dfs(index + arr[index][0], sum + arr[index][1]);


    }


}
