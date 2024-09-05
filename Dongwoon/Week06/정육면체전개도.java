import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int N = 6, sr, sc;
	public static int[] dice;
	public static int[][] map, visited;
	public static boolean ans;
	public static int[] dr = { -1, 0, 1, 0 };
	public static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for (int tc = 1; tc <= 3; tc++) {
			map = new int[N][N];
			visited = new int[N][N];
			dice = new int[N];
			ans = true;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 1) {
						sr = i;
						sc = j;
					}
				}
			}

			visited[sr][sc] = 1;
			dice[2] = map[sr][sc];
			dfs(sr, sc);
			
			for (int i = 0; i < 6; i++) {
				if (dice[i] == 0) {
					ans = false;
				}
			}
			
			if (ans) {
				System.out.println("yes");
			} else {
				System.out.println("no");
			}
		}
	}

	public static void dfs(int r, int c) {
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if (nr < 0 || nr >= N || nc < 0 || nc >= N)
				continue;
			if (map[nr][nc] == 1 && visited[nr][nc] == 0) {
				visited[nr][nc] = 1;
				dice(i);
				dfs(nr, nc);
				dice((i + 2) %4);
			}
		}

	}

	public static void dice(int d) {
		int temp;
		switch (d) {
		case 0:
			temp = dice[0];
			dice[0] = dice[1];
			dice[1] = dice[2];
			dice[2] = dice[3];
			dice[3] = temp;
			break;
		case 1:
			temp = dice[4];
			dice[4] = dice[2];
			dice[2] = dice[5];
			dice[5] = dice[0];
			dice[0] = temp;
			break;
		case 2:
			temp = dice[3];
			dice[3] = dice[2];
			dice[2] = dice[1];
			dice[1] = dice[0];
			dice[0] = temp;
			break;
		case 3:
			temp = dice[5];
			dice[5] = dice[2];
			dice[2] = dice[4];
			dice[4] = dice[0];
			dice[0] = temp;
			break;
		}
		dice[2] = 1;
	}
}
