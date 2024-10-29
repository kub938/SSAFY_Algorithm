import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static int N, M, dis = 1, ans = 0, cnt;
	public static int[][] map, smap;
	public static int[] dr = { -1, 0, 1, 0 };
	public static int[] dc = { 0, 1, 0, -1 };
	public static int[] dice = { 1, 4, 2, 3, 6, 5 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 1. 입력받기
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		smap = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 2. 인접 점수 맵 구하기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				cnt = 1;
				dfs(i, j, new int[N][N], map[i][j]);
				smap[i][j] = cnt * map[i][j];
			}
		}
		int sr = 0, sc = 0;

		// 3. 주사위 굴리면서 점수 얻기
		for(int i =0;i<M;i++) {
//			System.out.println(dis);
			int nr, nc;
			// 3-1. 방향대로 주사위를 굴린다.
			nr = sr + dr[dis];
			nc = sc + dc[dis];
			
			// 격자판을 벗어나는지 체크
			if(nr < 0 || nr >= N || nc < 0 || nc >= N) {
				dis = (dis + 2) % 4;
				nr = sr + dr[dis];
				nc = sc + dc[dis];
			}
			
			sr = nr;
			sc = nc;
			
			// 주사위 눈 변경
			roll(dis);
			
			// 3-2. 점수를 얻는다.
			ans += smap[nr][nc];
			
			// 3-3. 다음 방향을 결정한다.
//			System.out.println("다이스" + dice[4]);
			if(dice[4] > map[nr][nc]) {
				dis = (dis + 1) % 4;
			}else if(dice[4] < map[nr][nc]){
				dis = (dis + 3) % 4;
			}
		}

//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				System.out.print(smap[i][j] + " ");
//			}
//			System.out.println();
//		}

		// 4. 출력하기
		System.out.println(ans);
	}

	private static void roll(int dis) {
		int tmp;
		switch(dis){
		case 0:
			tmp = dice[0];
			dice[0] = dice[2];
			dice[2] = dice[4];
			dice[4] = dice[5];
			dice[5] = tmp;
			break;
		case 1:
			tmp = dice[0];
			dice[0] = dice[1];
			dice[1] = dice[4];
			dice[4] = dice[3];
			dice[3] = tmp;
			break;
		case 2:
			tmp = dice[0];
			dice[0] = dice[5];
			dice[5] = dice[4];
			dice[4] = dice[2];
			dice[2] = tmp;
			break;
		case 3:
			tmp = dice[0];
			dice[0] = dice[3];
			dice[3] = dice[4];
			dice[4] = dice[1];
			dice[1] = tmp;
			break;
		}
		
	}

	private static void dfs(int sr, int sc, int[][] visited, int num) {
		visited[sr][sc] = 1;
		for (int d = 0; d < 4; d++) {
			int nr = sr + dr[d];
			int nc = sc + dc[d];
			if (nr < 0 || nr >= N || nc < 0 || nc >= N)
				continue;

			if (visited[nr][nc] == 0 && map[nr][nc] == num) {
				cnt++;
				dfs(nr, nc, visited, num);
			}
		}
	}
}
