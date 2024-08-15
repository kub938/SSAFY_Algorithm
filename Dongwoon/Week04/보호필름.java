import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	public static int D, W, K, MIN = 0, test_case;
	public static int[][] map;
	public static int[] arr, arr2;
	public static boolean chk;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (test_case = 1; test_case <= T; test_case++) {
			chk = false;
			StringTokenizer st = new StringTokenizer(br.readLine());
			// 1.입력값 받아오기
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[D][W];
			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			MIN = 0;
			if(K == 1) {
				System.out.println("#" + test_case + " " + MIN);
				continue;
			}
			// 2. 해당 기준을 통과하는지 유효성 검사
			if (save(map) == 1) {
				System.out.println("#" + test_case + " " + MIN);
				continue;
			}
			MIN++;
			// 3. 통과하지 못했다면 부분집합으로 막을 바꿔 유효성검사
			for (int i = 1; i <= W; i++) {
				arr = new int[i];
				
				dfs(0, 0, i);
				
				if(chk) {
					break;
				}
				// 3-3. 유효성 검사
				MIN += 1;

			}

			// 4. 최소값 출력
			System.out.println("#" + test_case + " " + MIN);
		}
	}

	public static int save(int[][] map) {
		int min = 14;
		int[] cur = new int[D]; // 최대 이어지는 특성을 저장할 저장소 최소값은 1이다.
		int[] cnt = new int[W];
		for (int i = 0; i < W; i++) {
			cnt[i] = map[0][i]; // cnt[0]째에 제일 첫번째 막의 정보 입력

		}

		for (int i = 0; i < W; i++) { // 가로만큼 반복
			cur[0] = 1;
			for (int j = 1; j < D; j++) { // 세로만큼 반복
				if (map[j][i] != cnt[i]) { // 현재 값이 이전값과 다르다면
					cnt[i] = map[j][i]; // 이전값 초기화
					cur[j] = 1; // 이어지는 수도 1
				} else {
					cur[j] = cur[j - 1] + 1; // 아니면 이어지는 수 증가
				}
				if(cur[j] >= K) {
					break;
				}
			}
			for (int j = 1; j < D; j++) { // 각 층 검사
				if (cur[j] > cur[0]) { // 0번째 값 (1) 보다 크다면
					cur[0] = cur[j]; // 0번쨰 값 초기화
				}
			}
			if (cur[0] < K) { // 기준보다 작다면
				return 0; // 0리턴
			}
		}
		return 1; // 모든 객체 통과 1리턴
	}

	public static void dfs(int at, int depth, int c) {
		if (depth == c) {
			int[][] copyMap = new int[D][W];
			arr2 = new int[c];
			dfs2(0, c, copyMap);
			
			return;
		}
		for (int i = at; i < D; i++) {
			arr[depth] = i;
			dfs(i + 1, depth + 1, c);
		}
	}

	public static void dfs2(int depth, int c, int[][] copiMap) {
		if (depth == c) {
			// 3-1. 맵 복사
			for (int j = 0; j < D; j++) {
				copiMap[j] = Arrays.copyOf(map[j], map[j].length);
			}
			// 3-2. 고른 층 바꾸기

			for (int k = 0; k < c; k++) {
				for (int j = 0; j < W; j++) {
					int ch = arr[k];
					int ch2 = arr2[k];
					copiMap[ch][j] = ch2;
				}
			}
			
			if(save(copiMap) == 1) {
				chk = true;
			}
			
			return;
		}
		for (int i = 0; i <= 1; i++) {
			arr2[depth] = i;
			dfs2(depth + 1, c, copiMap);
		}
	}
}
