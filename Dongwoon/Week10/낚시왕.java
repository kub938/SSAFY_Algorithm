import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static int R, C, M, ans = 0;
	public static List<shark> sharks = new ArrayList<>();
	public static shark[][] map;
	public static int[] dr = {0, -1, 1 ,0, 0};
	public static int[] dc = {0, 0, 0, 1, -1};

	public static class shark {
		int r, c, s, d, z;

		public shark(int r, int c, int s, int d, int z) {
			super();
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 1. 입력받기
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new shark[R + 1][C + 1];
		
		// 1-1. 상어 리스트 입력받기
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			if(d == 1 || d == 2) {// 방향이 세로인 경우
				s = s % ((2 * R) - 2);
			} else { // 방향이 가로인 경우
				s = s % ((2 * C) - 2);
			}
			shark cur = new shark(r, c, s, d, z);
			sharks.add(cur);
			map[r][c] = cur;
		}

		// 2. 한칸씩 이동하며 상어 포획
		for (int i = 1; i <= C; i++) { // 가로로 한칸씩 이동하며
			for (int j = 1; j <= R; j++) { // 가장 가까운 칸부터
				if (map[j][i] != null) { // 상어가 있는 지 검사
					shark tmp = map[j][i];
					ans += map[j][i].z;
					sharks.remove(tmp);
					break;
				}
			}
			move(); // 3. 상어 이동
		}

		// 4. 정답 출력
		System.out.println(ans);
	}

	private static void move() {
		// 3-1. map 비우기
		for (int i = 1; i <= R; i++) {
			Arrays.fill(map[i], null);
		}
		
		// 3-2. 상어 이동하기
		for (int i = sharks.size() - 1; i >= 0; i--) {
			shark cur = sharks.get(i);
			int cnt = cur.s;
			
			while(cnt > 0) {
				int nr = cur.r + dr[cur.d];
				int nc = cur.c + dc[cur.d];
				if(nr <= 0 || nr > R || nc <= 0 || nc > C) {
					if(cur.d % 2 == 1) {
						cur.d += 1;
					} else {
						cur.d -= 1;
					}
					nr = cur.r + dr[cur.d];
					nc = cur.c + dc[cur.d];
				}
				cur.r = nr;
				cur.c = nc;
				cnt--;
			}
			
			// 3-3. map에 채우기
			if (map[cur.r][cur.c] != null) { // 같은 공간에 상어가  있다면
				if(map[cur.r][cur.c].z < cur.z) { // 원래 있던 상어가 작다면
					shark tmp = map[cur.r][cur.c];
					// 지금 상어 넣어주기
					map[cur.r][cur.c] = cur;
					sharks.remove(tmp);
				} else { // 원래 있던 상어가 크다면
					sharks.remove(cur);
				}
			} else { // 같은 공간에 상어가 없다면
				map[cur.r][cur.c] = cur;
			}
		}
	}
}
