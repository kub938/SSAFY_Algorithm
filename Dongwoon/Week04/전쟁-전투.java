import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static int N, M, cnt, w_cnt = 0, b_cnt = 0;
	public static int[][] visited;
	public static char[][] map;
	public static List<Integer> W, B;
	public static int[] dx = {-1, 0, 1, 0};
	public static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 1. N, M 입력
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		// 1-1. map[M][N], visited[M][N];
		map = new char[M][N];
		visited = new int[M][N];

		for (int i = 0; i < M; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		
		// 2. W,B 리스트 만들기a
		W = new ArrayList<>();
		B = new ArrayList<>();
		
		// 3. dfs 탐색
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if(visited[i][j] == 0) {
					if(map[i][j] == 'W') {
						cnt = 1;
						visited[i][j] = 1;
						dfs(i, j, 'W');
						W.add(cnt);
						cnt = 0;
					} else if(map[i][j] == 'B') {
						cnt = 1;
						visited[i][j] = 1;
						dfs(i, j, 'B');
						B.add(cnt);
						cnt = 0;
					}
				}
			}
		}
		
		// n^2 계산
		for (int d : W) {
			w_cnt += d * d;
		}
		
		for (int d : B) {
			b_cnt += d * d;
		}
		
		
		// 4. 출력
		System.out.println(w_cnt + " " + b_cnt);

	}
	
	public static void dfs(int x, int y, char c){
		for(int i =0;i<4;i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx < 0 || nx >= M || ny < 0 || ny >=N) continue;
			
			if(visited[nx][ny] == 0) {
				if(map[nx][ny] == c) {
					cnt++;
					visited[nx][ny] = 1;
					dfs(nx, ny, c);
				}
			}
		}
	}
}
