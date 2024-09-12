import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	public static int T, N, ans, cnt;
	public static int[][] map;
	public static List<int[]>[] warm;
	public static int[] dr = { -1, 0, 1, 0 };
	public static int[] dc = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			// 1. 입력받기
			N = Integer.parseInt(br.readLine().trim());
			map = new int[N][N];
			warm = new ArrayList[11];
			for (int i = 6; i <= 10; i++) {
				warm[i] = new ArrayList<>();
			}
			ans = 0;

			// 1-1. map, warm 입력받기
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] > 5) {
						warm[map[i][j]].add(new int[] { i, j });
					}
				}
			}

			// 2. 0인 구역 4방 탐색
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == 0) {
						for (int k = 0; k < 4; k++) {
							cnt = 0;
							solve(i, j, k);
							if(cnt > ans) {
								ans = cnt;
							}
						}
					}
				}
			}

			System.out.println("#" + tc + " " + ans);
		}
	}

	public static void solve(int i, int j, int dir) {
		int nr = i + dr[dir];
		int nc = j + dc[dir];
		int k = dir;
		while(true) {
			// 윗벽에 부딪히다.
			if(nr < 0) {
				nr += 1;
				k += 2;
				cnt++;
				continue;
			}
			
			// 아래벽에 부딪히다.
			if(nr >= N) {
				nr -= 1;
				k -= 2;
				cnt++;
				continue;
			}
			
			// 오른쪽벽에 부딪히다.
			if(nc >= N) {
				nc -= 1;
				k += 2;
				cnt++;
				continue;
			}
			
			// 왼쪽벽에 부딪히다.
			if(nc < 0) {
				nc += 1;
				k -= 2;
				cnt++;
				continue;
			}
			
			// 블랙홀
			if(map[nr][nc] == -1) {
				break;
			}
			
			// 자기 자리로 오면
			if(nr == i && nc == j) {
				break;
			}
			
			if(map[nr][nc] > 5) { // 웜홀
				int num = map[nr][nc];
				int[] cur = {nr, nc};
				if(warm[num].get(0)[0] == nr && warm[num].get(0)[1] == nc) {
					nr = warm[num].get(1)[0] + dr[k];
					nc = warm[num].get(1)[1] + dc[k];
				} else {
					nr = warm[num].get(0)[0] + dr[k];
					nc = warm[num].get(0)[1] + dc[k];
				}
			} else if(map[nr][nc] <6 && map[nr][nc] > 0) { // 블록
				switch(map[nr][nc]) {
				case 1:
					if(k == 0) {
						nr +=1;
						k = 2;
					}else if(k == 1) {
						nc -= 1;
						k = 3;
					}
					else if(k == 2) {
						nc += 1;
						k = 1;
					}
					else if(k == 3) {
						nr -= 1;
						k = 0;
					}
						
					break;
				case 2:
					if(k == 0) {
						nc +=1;
						k = 1;
					}else if(k == 1) {
						nc -= 1;
						k = 3;
					}
					else if(k == 2) {
						nr -= 1;
						k = 0;
					}
					else if(k == 3) {
						nr += 1;
						k = 2;
					}
					break;
				case 3:
					if(k == 0) {
						nc -=1;
						k = 3;
					}else if(k == 1) {
						nr += 1;
						k = 2;
					}
					else if(k == 2) {
						nr -= 1;
						k = 0;
					}
					else if(k == 3) {
						nc += 1;
						k = 1;
					}
					break;
				case 4:
					if(k == 0) {
						nr +=1;
						k = 2;
					}else if(k == 1) {
						nr -= 1;
						k = 0;
					}
					else if(k == 2) {
						nc -= 1;
						k = 3;
					}
					else if(k == 3) {
						nc += 1;
						k = 1;
					}
					break;
				case 5:
					if(k == 0) {
						nr +=1;
						k = 2;
					}else if(k == 1) {
						nc -= 1;
						k = 3;
					}
					else if(k == 2) {
						nr -= 1;
						k = 0;
					}
					else if(k == 3) {
						nc += 1;
						k = 1;
					}
					break;
				}
				cnt++;
			} else if(map[nr][nc] == 0) { // 빈공간
				nr = nr + dr[k];
				nc = nc + dc[k];
			}
		}
	}
}
