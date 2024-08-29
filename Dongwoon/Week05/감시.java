import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static int N, M, ans = 100, cnt = 0;
	public static int[][] map, copyMap;
	public static int[][] cctv, cctv2;
	public static int[] arr;
	public static int[] dx = { -1, 0, 1, 0 };
	public static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 1. 입력받기
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		// 1-1. map, copyMap 만들기
		map = new int[N][M];
		copyMap = new int[N][M];
		cctv = new int[10][3];
		cctv2 = new int[10][3];
		int cnt2 = 0;
		// 1-2. map 입력 받기
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 5) {
					cctv2[cnt2][0] = i;
					cctv2[cnt2][1] = j;
					cctv2[cnt2][2] = map[i][j];
					cnt2++;
				} else if (map[i][j] > 0 && map[i][j] < 6) {
					cctv[cnt][0] = i;
					cctv[cnt][1] = j;
					cctv[cnt][2] = map[i][j];
					cnt++;
				}
			}
		}

		for (int i = 0; i < cnt2; i++) {
			
			int x = cctv2[i][0];
			int y = cctv2[i][1];
			
			bfs(map, x, y, 0);
			bfs(map, x, y, 1);
			bfs(map, x, y, 2);
			bfs(map, x, y, 3);
		}

		arr = new int[cnt];

		dfs(0);

		System.out.println(ans);

	}

	public static void dfs(int depth) {
		if (depth == cnt) {

			copy();
			for (int i = 0; i < cnt; i++) {
				int x = cctv[i][0];
				int y = cctv[i][1];
				int mod = cctv[i][2];
				
				if(mod == 1) {
					bfs(copyMap, x, y, arr[i]);
				} else if(mod == 2) {
					bfs(copyMap, x, y, arr[i]);
					bfs(copyMap, x, y, (arr[i] + 2) %4);
				} else if(mod == 3) {
					bfs(copyMap, x, y, arr[i]);
					bfs(copyMap, x, y, (arr[i] + 1) %4);
				} else if(mod == 4) {
					bfs(copyMap, x, y, arr[i]);
					bfs(copyMap, x, y, (arr[i] + 1) %4);
					bfs(copyMap, x, y, (arr[i] + 2) %4);
				}
				
			}
			
			
			int zero = 0;
			for(int i =0;i<N;i++) {
				for(int j =0;j<M;j++) {
					if(copyMap[i][j] == 0) {
						zero++;
					}
				}
			}
			
			if(zero < ans) {
				ans = zero;
			}
			
			return;
		}

		for (int i = 0; i < 4; i++) {
			if (cctv[depth][2] == 2 && (i == 2 || i == 3))
				continue;

			arr[depth] = i;
			dfs(depth + 1);
		}
	}

	private static void bfs(int[][] map, int x, int y, int dis) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] { x, y });
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int nx = cur[0] + dx[dis];
			int ny = cur[1] + dy[dis];
			if (nx < 0 || nx >= N || ny < 0 || ny >= M)
				continue;
			if (map[nx][ny] != 6) {
				map[nx][ny] = 7;
				q.offer(new int[] { nx, ny });
			}
		}
	}

	public static void copy() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				copyMap[i][j] = map[i][j];
			}
		}
	}

}
