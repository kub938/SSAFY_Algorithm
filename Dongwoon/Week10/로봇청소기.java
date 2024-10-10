import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int N, M, sr, sc, dis, ans = 0;
	public static int[][] map, visited;
	public static int[] dr = { -1, 0, 1, 0 };
	public static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 1. 입력받기
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new int[N][M];

		st = new StringTokenizer(br.readLine());
		sr = Integer.parseInt(st.nextToken());
		sc = Integer.parseInt(st.nextToken());
		dis = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) {
					visited[i][j] = 1;
				}
			}
		}

		// 2. 청소하기
		while (true) {
			if (visited[sr][sc] == 0) { // 청소되지 않은 칸이면 청소한다.
				visited[sr][sc] = 1;
				ans++;
			} else { // 청소된 칸이면
				// 2-1. 4칸 검사
				int cnt = 0;
				int nr = 0, nc = 0;
				for (int d = 0; d < 4; d++) {
					nr = sr + dr[d];
					nc = sc + dc[d];
					if (nr < 0 || nr >= N || nc < 0 || nc >= M)
						continue;
					if (visited[nr][nc] == 0) {
						cnt++;
					}
				}
				
				// 2-2. 청소된 칸 유무 체크
				if (cnt == 0) { // 4방에 청소되지 않은 빈칸이 없다면
					int ndis = (dis + 2) % 4;
					nr = sr + dr[ndis];
					nc = sc + dc[ndis];
					if (nr < 0 || nr >= N || nc < 0 || nc >= M || map[nr][nc] == 1) {
						// 후진할 수 없다면
						break;
					}
					sr = nr;
					sc = nc;
				} else { // 4방에 청소되지 않은 빈칸이 있다면
					dis = (dis + 3) % 4;
					nr = sr + dr[dis];
					nc = sc + dc[dis];
					if (nr >= 0 && nr < N && nc >= 0 && nc < M && visited[nr][nc] == 0) {
						sr = nr;
						sc = nc;
					}
				}
			}
		}
		
		// 3. 출력하기
		System.out.println(ans);
	}
}
