import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static int N = 10, ans;
	public static int[] p_cnt = new int[5];
	public static int[][] map = new int[N][N];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 1. 입력받기
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		ans = Integer.MAX_VALUE;

		// 2. 색종이 붙이기
		dfs(0, new int[6], 0);

		// 4. 출력하기

		if (ans == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(ans);
		}
	}

	public static void dfs(int at, int[] num, int cnt) {
		if(cnt > ans) {
			return;
		}
		for (int i = at; i < N * N; i++) {
			if (map[i / N][i % N] == 1) {
				// 3. 큰 색종이부터 검사
				for (int j = 5; j >= 1; j--) {
//					if (i / N + j >= N || i % N + j >= N)
//						continue;
					if (num[j] < 5 && check(i / N, i % N, j)) {
						// 3-1. 붙일 수 있다면 종이 수 감소 후 방문처리
						num[j]++;
						fill(i / N, i % N, j, 0);
						dfs(i + 1, num, cnt + 1);
						num[j]--;
						fill(i / N, i % N, j, 1);
					}
				}
				return;
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1)
					return;
			}
		}

		if (cnt < ans) {
			ans = cnt;
		}
	}

	public static boolean check(int r, int c, int size) {
		for (int i = r; i < r + size; i++) {
			for (int j = c; j < c + size; j++) {
				if(i >= N || j >= N) return false;
				if (map[i][j] == 0)
					return false;
			}
		}
		return true;
	}

	public static void fill(int r, int c, int size, int k) {
		for (int i = r; i < r + size; i++) {
			for (int j = c; j < c + size; j++) {
				map[i][j] = k;
			}
		}
	}
}
