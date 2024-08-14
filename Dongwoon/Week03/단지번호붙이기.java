import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static int[][] map, visited;
	public static int n, cnt;
	public static int[] dx = { 1, 0, -1, 0 };
	public static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		// 1. n값입력받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		// 1-1. n값으로 map 만들기 map[n][n]
		map = new int[n][n];
		visited = new int[n][n];
		// 1-2. map값 입력받기
		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			for (int j = 0; j < n; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		// 2. 집 단지가 몇개가 들어올지 모르니까 리스트 만들기
		List<Integer> danji = new LinkedList<>();
		// cnt = 1로 초기화(처음 들어온 집
		cnt = 1;
		// 2-1. visited가 0이고 map[i][j]가 1이면 dfs탐색
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if(visited[i][j] == 0 && map[i][j] == 1) {
					visited[i][j] = 1;
					dfs(i, j);
					// 2-2. 빠져나오면 리스트에 cnt값을 넣고 cnt 다시 1로 초기화
					danji.add(cnt);
					cnt = 1;
				}
			}
		}
		// 4. 리스트 sort
		Collections.sort(danji);
		// 4-1. 리스트 사이즈 출력
		System.out.println(danji.size());
		// 4-2. 리스트 원소 출력
		for(int d : danji) {
			System.out.println(d);
		}
	}

	public static void dfs(int x, int y) {
		// 3. 상하좌우를 확인하여 값이 유효하고 visited가 0이고 map[i][j]가 1이면 그곳으로 재귀 cnt 1증가
		for(int i = 0 ;i<4;i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx>=0 && nx < n && ny >= 0 && ny < n) {
				if(visited[nx][ny] == 0 && map[nx][ny] == 1) {
					visited[nx][ny] = 1;
					cnt++;
					dfs(nx, ny);
				}
			}
		}
	}

}
