import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static int N, ans = 0, s_cnt = 0, s_size = 2;
	public static int[][] map, visited;
	public static int[] shark;
	public static List<Fish> fish;
	public static PriorityQueue<Fish> pq = new PriorityQueue<>();
	public static int[] dr = { -1, 0, 1, 0 };
	public static int[] dc = { 0, 1, 0, -1 };

	public static class Fish implements Comparable<Fish> {
		int r, c, dis;

		public Fish(int r, int c, int dis) {
			super();
			this.r = r;
			this.c = c;
			this.dis = dis;
		}

		@Override
		public int compareTo(Fish o) {
			int result = this.dis - o.dis;
			if (result == 0) {
				result = this.r - o.r;
				if (result == 0) {
					result = this.c - o.c;
				}
			}
			return result;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		// 1. 입력받기 N
		N = Integer.parseInt(br.readLine());
		// 1-1. map 설정, shark, fish
		map = new int[N][N];
		shark = new int[2];
		fish = new LinkedList<>();
		visited = new int[N][N];
		// 1-2. map 입력 받기, shark 넣기
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9) {
					shark[0] = i;
					shark[1] = j;
					map[i][j] = 0;
				}
			}
		}

		while (true) {
			pq.clear();
			bfs();
			if (pq.isEmpty()) {
				break;
			}
			Fish cur = pq.poll();
			move(cur.r, cur.c, cur.dis);
		}

		System.out.println(ans);
	}

	public static void bfs() {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] { shark[0], shark[1], 0 });
		int[][] visitedmap = new int[N][N];
		visitedmap[shark[0]][shark[1]] = 1;
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int r = cur[0];
			int c = cur[1];
			int dis = cur[2];
			if (map[r][c] != 0 && visited[r][c] == 0 && map[r][c] < s_size) {
				pq.offer(new Fish(r, c, dis));
			}

			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if (nr < 0 || nr >= N || nc < 0 || nc >= N)
					continue;
				if (visitedmap[nr][nc] == 0 && map[nr][nc] <= s_size) {
					visitedmap[nr][nc] = 1;
					q.offer(new int[] { nr, nc, dis + 1 });
				}
			}
		}
	}

	public static void move(int r, int c, int dis) {
		visited[r][c] = 1;
		shark[0] = r;
		shark[1] = c;
		ans += dis;
		s_cnt++;
		if (s_cnt == s_size) {
			s_size += 1;
			s_cnt = 0;
		}
	}
}
