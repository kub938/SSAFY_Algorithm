import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	public static int N = 5, ans = 0;
	public static char[][] map = new char[5][5];
	public static int[] arr = new int[7];
	public static int[] dx = { -1, 0, 1, 0 };
	public static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		// 1. map 입력받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j);
			}
		}

		// 2. 원소가 7개인 부분집합구하기
		dfs(0, 0, 0);

		System.out.println(ans);
		// 5. ans 출력
	}

	public static void dfs(int y, int at, int depth) {
		if (y >= 4) {
			return;
		}
		
		if (depth == 7) {
			// 3. 원소들이 서로 이어져있는지 확인하기
			bfs();
			return;
		}
		
		for (int i = at; i < N * N; i++) {
			arr[depth] = i;
			if (map[i / N][i % N] == 'Y')
				dfs(y + 1, i + 1, depth + 1);
			else
				dfs(y, i + 1, depth + 1);
		}
	}

	public static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		int[][] map2 = new int[5][5];
		for (int i = 1; i < 7; i++) {
			int r = arr[i] / 5;
			int c = arr[i] % 5;
			map2[r][c] = 1;
		}
		
		int cnt = 0;
		q.offer(new int[] {arr[0] / 5, arr[0] % 5});
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int x = cur[0];
			int y = cur[1];
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (nx < 0 || ny < 0 || nx >= N || ny >= N)
					continue;
				if(map2[nx][ny] == 1) {
					map2[nx][ny] = 0;
					cnt++;
					q.offer(new int[] {nx, ny});
					
				}
			}
		}
		// 4. 원소가 이어져있다면 ans++
		if(cnt == 6) {
			ans++;
		}
	}
}
