import java.util.*;
import java.io.*;

/*
git commit -m "김성민 / 241025 / [BOJ_근손실_18429] / 120ms"

### 풀이 사항
풀이 일자: 2024.10.25
풀이 시간: 15분
채점 결과: 정답
예상 문제 유형: 순열 백트래킹
시간: 120 ms
메모리: 14180 kb

### 풀이방법
1. 데이터를 입력받는다.
2. 순열을 돈다
3. 500이하로 내려가는 선택지가 나오면 백트래킹
4. 끝까지 간 순열 리턴

### 느낀점




특이사항)
순열을 하면서 백트래킹을 한다.
 */

public class Main {
    static int cnt;
    static int[] arr;
    static boolean[] visited;
    static int loss;
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        loss = Integer.parseInt(st.nextToken());
        visited = new boolean[n];
        st = new StringTokenizer(br.readLine());
        arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        cnt = 0;

        permutation(500,0);

        sb.append(cnt);
        System.out.print(sb);
    }

    private static void permutation(int weight, int d) {
        if(d>= n) cnt++;

        for (int i = 0; i < n; i++) {
            if(!visited[i] && weight-loss+arr[i]>=500){
                visited[i] = true;
                permutation(weight-loss+arr[i],d+1);
                visited[i] = false;
            }
        }

    }

}