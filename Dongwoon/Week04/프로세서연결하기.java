import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static int T, N, ans, core, coreMin;
	public static int[][] map, copyMap, coreMap;
	public static int[] arr;
	public static int[] dx = { 0, 0, 1, -1 };
	public static int[] dy = { 1, -1, 0, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= T; tc++) {
			// 1. 입력값 받기
			// 1-1. N 입력 받기
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			// 1-2. ans, core, coreMin, map, copyMap, coreMap 초기화;
			ans = 0;
			core = 0;
			coreMin = 0;
			map = new int[N][N];
			copyMap = new int[N][N];
			coreMap = new int[12][2];
			// 1-3. map 입력받기
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					// 1-4. 외각에 붙어있지 않은 core 수와 coreMap 입력받기
					if (i == 0 || j == 0 || i == N - 1 || j == N - 1)
						continue;
					if (map[i][j] == 1) {
						coreMap[core][0] = i;
						coreMap[core][1] = j;
						core++;
					}
				}
			}
			// 1-5. arr 초기화
			arr = new int[core];
			
			dfs(0);
			
			// 4. ans 출력
			System.out.println("#" + tc + " " + ans);

		}
	}

	// 2. copyMap 구현
	public static void copymap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				copyMap[i][j] = map[i][j];
			}
		}
	}
	// 3. dfs 구현0~3 중복 순열
	public static void dfs(int depth) {
		if(depth == core) {
			// 3-1. copyMap
			copymap();
			// 3-2. core의 갯수만큼 반복
			int lineCnt = 0;
			int coreCnt = 0;
			for(int i =0; i<core;i++) {
				// 더 탐색을 해봤자 정답이 나올 수가 없는 구조라면 그냥 break;
				if(core - i + coreCnt < coreMin)
					break;
				int cur = arr[i];
				int x = coreMap[i][0];
				int y = coreMap[i][1];
				// 3-3. arr의 숫자대로 끝까지 찍기, 성공했다면 간선 lineCnt에 더하기, coreCnt 추가
				int line = 0;
				while(true) {
					x = x+dx[cur];
					y = y+dy[cur];
					if(copyMap[x][y] == 1) {
						break;
					}
					if (x == 0 || y == 0 || x == N - 1 || y == N - 1) {
						copyMap[x][y] = 1;
						lineCnt += line + 1;
						coreCnt++;
						break;
					}
					copyMap[x][y] = 1;
					line++;
				}
			}
			// 3-4. coreMin이 coreCnt 보다 작다면
			if(coreMin < coreCnt) {
				// 3-5. coreMin 초기화, ans 초기화
				coreMin = coreCnt;
				ans = lineCnt;
			}
			// 3-6. coreMin과 coreCnt가 같다면 ans 비교
			else if(coreMin == coreCnt) {
				if(ans > lineCnt) {
					ans = lineCnt;
				}
			}
			return;
		}
		for(int i = 0;i <4;i++) {
			arr[depth] = i;
			dfs(depth+1);
		}
	}
}
