import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Solution {
	public static int T, N, ans = 0;
	public static int[][] map, visited, min;
	public static int[] dr = { -1, 0, 1, 0 };
	public static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			visited = new int[N][N];

			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = str.charAt(j) - '0';
				}
			}

			ans = 0;
			daik();

			System.out.println("#" + tc + " " + ans);
		}
	}

	public static void daik() {
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[2], o2[2]));

		min = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				min[i][j] = Integer.MAX_VALUE;
			}
		}

		min[0][0] = 0;
		pq.add(new int[] { 0, 0, 0 });
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int r = cur[0];
			int c = cur[1];
			int time = cur[2];

			if (r == N - 1 && c == N - 1) {
				ans = time;
				return;
			}

			if (visited[r][c] == 1)
				continue;
			visited[r][c] = 1;

			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (nr < 0 || nr >= N || nc < 0 || nc >= N)
					continue;
				if (visited[nr][nc] == 0 && min[nr][nc] > time + map[nr][nc]) {
					min[nr][nc] = time + map[nr][nc];
					pq.offer(new int[] { nr, nc, min[nr][nc] });
				}
			}

		}
		ans = -1;
		return;
	}
}
