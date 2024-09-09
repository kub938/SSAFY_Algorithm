import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution {
	public static int T, N, W, H, ans = 0;
	public static int[] arr;
	public static int[][] map, copyMap;
	public static int[] dr = { -1, 0, 1, 0 };
	public static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());

			map = new int[H][W];
			copyMap = new int[H][W];
			arr = new int[N];

			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			ans = W * H;

			dfs(0);

			System.out.println("#" + tc + " " + ans);
		}

	}

	public static void dfs(int depth) {
		if (depth == N) {
			copy();
			// 벽돌 부수기
			for (int d : arr) {
				busu(d);
			}

			// 남은 벽돌 계산
			int cnt = 0;
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					if (copyMap[i][j] > 0) {
						cnt++;
					}
				}
			}

			if (cnt < ans) {
				ans = cnt;
			}

			return;
		}
		for (int i = 0; i < W; i++) {
			arr[depth] = i;
			dfs(depth + 1);
		}
	}

	public static void busu(int at) {
		int r = 0;
		int c = at;
		Queue<int[]> q = new LinkedList<>();
		while (r <= H-1) {
			if (copyMap[r][c] == 0) {
				r += 1;
			} else {
				q.offer(new int[] { r, c, copyMap[r][c] });
				copyMap[r][c] = 0;
				break;
			}
		}

		while (!q.isEmpty()) {
			int[] cur = q.poll();

			if (cur[2] == 1) {
				copyMap[cur[0]][cur[1]] = 0;
				continue;
			}

			int size = cur[2];
			for (int i = 1; i < size; i++) {
				for (int d = 0; d < 4; d++) {
					int nr = cur[0] + dr[d] * i;
					int nc = cur[1] + dc[d] * i;
					if (nr < 0 || nr >= H || nc < 0 || nc >= W)
						continue;
					if (copyMap[nr][nc] != 0) {
						q.offer(new int[] { nr, nc, copyMap[nr][nc] });
						copyMap[nr][nc] = 0;
					}
				}
			}
		}

		down();

		
	}

	public static void down() {
		
		for(int i =0;i<W;i++) {
			Stack<Integer> st = new Stack<>();
			for(int j =0;j<H;j++) {
				if(copyMap[j][i] > 0) {
					st.add(copyMap[j][i]);
					copyMap[j][i] = 0;
				}
			}
			int ct = H-1;
			while(!st.isEmpty()) {
				int cur = st.pop();
				copyMap[ct][i] = cur;
				ct--;
			}
		}
	}

	public static void copy() {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				copyMap[i][j] = map[i][j];
			}
		}
	}
}
