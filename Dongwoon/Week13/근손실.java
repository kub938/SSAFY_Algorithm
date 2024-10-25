import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static int N, K, ans = 0;
	public static int[] A, arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 1. 입력 받기
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		A = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		// 2.조합 짜기
		dfs(0, new int[N], 500);

		// 3. 출력하기
		System.out.println(ans);
	}

	public static void dfs(int depth, int[] visited, int weight) {
		if (depth == N) {
			ans++;
			return;
		}
		for (int i = 0; i < N; i++) {
			if (visited[i] == 0) {
				visited[i] = 1;
				int cur = weight + A[i] - K;
				if(cur >= 500) {
					dfs(depth+1, visited, cur);
				}
				visited[i] = 0;
			}
		}
	}
}
