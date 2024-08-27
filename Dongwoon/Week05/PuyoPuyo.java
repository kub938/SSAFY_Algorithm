import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	public static int R = 12, C = 6, ans = 0, cnt;
	public static char[][] map = new char[12][6];
	public static int[][] visited;
	public static int[][] arr;
	public static int[] dx = { -1, 0, 1, 0 };
	public static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		// 1. 입력값 받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
			}
		}

		arr = new int[72][2];

		// 2. . 이 아닌 원소 순회
		while (true) {
			// 2-1. b, visited 초기화
			boolean chk = false;
			visited = new int[R][C];

			// 2-2. 4개이상 이어진다면 화면에서 없애고
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if (visited[i][j] == 0 && map[i][j] != '.') {
						visited[i][j] = 1;
						arr[0][0] = i;
						arr[0][1] = j;
						cnt = 1;
						dfs(i, j, cnt, map[i][j]);
						if (cnt >= 4) {
							chk = true;
							for (int k = 0; k < cnt; k++) {
								int r = arr[k][0];
								int c = arr[k][1];
								map[r][c] = '.';
							}
						}
					}
				}
			}
			if (!chk) // 바뀐적이 없다면 break;
				break;
			
			// 빈공간을 떨어지게 만들기 값넣어주기
			for (int j = 0; j < C; j++) {
				Stack<Character> st = new Stack<>();
				for (int i = 0; i < R; i++) {
					if (map[i][j] != '.') {
						st.add(map[i][j]);
						map[i][j] = '.';
					}
				}
				int cur = R - 1;
				while (!st.isEmpty()) {
					map[cur--][j] = st.pop();
				}
			}

//			for (int i = 0; i < R; i++) {
//				for (int j = 0; j < C; j++) {
//					System.out.print(map[i][j]);
//				}
//				System.out.println();
//			}
			
			// 3. ans++후 반복
			ans++;
		}

		System.out.println(ans);

	}

	public static void dfs(int x, int y, int depth, char color) {

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx < 0 || nx >= R || ny < 0 || ny >= C)
				continue;
			if (map[nx][ny] == color && visited[nx][ny] == 0) {
				visited[nx][ny] = 1;
				arr[cnt][0] = nx;
				arr[cnt][1] = ny;
				cnt++;
				dfs(nx, ny, depth + 1, color);

			}
		}
	}
}
