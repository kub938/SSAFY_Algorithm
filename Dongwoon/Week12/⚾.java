import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int N, ans = 0;
	public static int[][] result;
	public static int[] arr = new int[9], visited = new int[9];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		// 1. 입력받기
		N = Integer.parseInt(br.readLine());
		result = new int[N][9];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				result[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 2. 중복 순열을 통해 타순 정하기
		visited[0] = 1;
		dfs(0); // 27 * 8!

		System.out.println(ans);
	}

	private static void dfs(int depth) {
		if (depth == 9) {
			// 3. 이닝 반복하기
			int cur = ining();

			if (cur > ans) {
				ans = cur;
			}

			return;
		}

		for (int i = 1; i < 9; i++) {
			if (depth == 3) {
				dfs(depth + 1);
				break;
			}
			if (visited[i] == 0) {
				visited[i] = 1;
				arr[depth] = i;
				dfs(depth + 1);
				visited[i] = 0;
			}
		}
	}

	private static int ining() {
		int cnt = 0;
		int num = 0;
		int[] ru;
		for (int i = 0; i < N; i++) {
			int out = 0;
			ru = new int[3];
			while (out < 3) {
				int cur = arr[num]; // 현재 타석 타자 번호
				int ta = result[i][cur]; // 현재 이닝의 타자 결과

				switch (ta) {
				case 1: // 안타
					if(ru[2] == 1) // 3루에 타자가 있으면
						cnt++;
					for (int j = 1; j >= 0; j--) { // 뒤로 밀기
						ru[j+1] = ru[j];
					}
					ru[0] = 1;
					break;
				case 2: // 2루타
					for (int j = 1; j < 3; j++) {
						if (ru[j] == 1) { // 2,3루에 타자가 있으면
							ru[j] = 0; // 초기화
							cnt++; // 점수 증가
						}
					}
					if(ru[0] == 1) { // 1루에 타자가 있으면
						ru[0] = 0;
						ru[2] = 1; // 3루로 이동
					}
					ru[1] = 1;
					break;
				case 3: // 3루타
					for (int j = 0; j < 3; j++) {
						if (ru[j] == 1) { // 루에 타자가 있으면
							ru[j] = 0; // 초기화
							cnt++; // 점수 증가
						}
					}
					ru[2] = 1;
					break;
				case 4: // 홈런
					cnt++; // 자신은 들어옴
				 	for (int j = 0; j < 3; j++) {
						if (ru[j] == 1) { // 루에 타자가 있으면
							ru[j] = 0; // 초기화
							cnt++; // 점수 증가
						}
					}
					break;
				case 0:
					out++;
					break;
				}

				// 타순 돌기
				if (num == 8) {
					num = 0;
				} else {
					num++;
				}
			}
		}
		return cnt;
	}
}
