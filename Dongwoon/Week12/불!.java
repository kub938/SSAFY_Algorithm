import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static int R, C, ans = 0;
	public static char[][] map;
	public static int[][] visited;
	public static int[] J;
	public static int[] dr = { -1, 0, 1, 0 };
	public static int[] dc = { 0, 1, 0, -1 };
	public static List<int[]> fire = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 1. 입력받기
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visited = new int[R][C];
		J = new int[2];

		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
				if (map[i][j] == 'J') {
					J[0] = i;
					J[1] = j;
				} else if (map[i][j] == 'F') {
					fire.add(new int[] { i, j });
				}
			}
		}

		
		bfs();

	}

	private static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		Queue<int[]> fq = new LinkedList<>();
		int time = 0;

		q.offer(new int[] { J[0], J[1] });
		visited[J[0]][J[1]] = 1;

		for (int i = 0; i < fire.size(); i++) {
			fq.offer(fire.get(i));
		}

		while (!q.isEmpty()) {
			time++;
			
			// 불 번지기
			int fsize = fq.size();
			for (int i = 0; i < fsize; i++) {
				int[] fcur = fq.poll();
				for (int d = 0; d < 4; d++) {
					int nr = fcur[0] + dr[d];
					int nc = fcur[1] + dc[d];

					if (nr < 0 || nr >= R || nc < 0 || nc >= C)
						continue;

					if (map[nr][nc] == 'F' || map[nr][nc] == '#')
						continue;

					map[nr][nc] = 'F';
					fq.offer(new int[] { nr, nc });

				}
			}

			// 다음 경로 찾기
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int[] cur = q.poll();
				// 큐에 다음 위치 넣기
				for (int d = 0; d < 4; d++) {
					int nr = cur[0] + dr[d];
					int nc = cur[1] + dc[d];

					if (nr < 0 || nr >= R || nc < 0 || nc >= C) { // 가장자리를 넘어가는지 확인
						System.out.println(time);
						return;
					}

					if (map[nr][nc] == 'F' || map[nr][nc] == '#' || visited[nr][nc] == 1)
						continue;
					
					visited[nr][nc] = 1;
					q.offer(new int[] { nr, nc });

				}
			}

		}
		System.out.println("IMPOSSIBLE");
	}
}
