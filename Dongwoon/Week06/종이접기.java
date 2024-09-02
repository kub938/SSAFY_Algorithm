import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int k, h, N, curCnt, mode;
	public static char RR, CR;
	public static int[][] map;
	public static int[][] curMap = { { 0, 1, 2, 3 }, { 1, 0, 3, 2 }, { 2, 3, 0, 1 }, { 3, 2, 1, 0 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		// 1. 입력받기
		k = Integer.parseInt(br.readLine());
		N = (int) Math.pow(2, k);
		map = new int[N][N];

		// 1-1. 접는방법 입력
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < k * 2; i++) {
			String s = st.nextToken();
			char c = s.charAt(0);

			if (c == 'R' || c == 'L') {
				RR = c;
			} else {
				CR = c;
			}
		}

		// 1-2. 구멍 뚫는 위치 입력
		h = Integer.parseInt(br.readLine());
		
		// 1-3. 어느 위치에 구멍을 뚫는지 저장
		if (RR == 'R' && CR == 'D') {
			curCnt = 3;
		} else if (RR == 'R' && CR == 'U') {
			curCnt = 1;
		} else if (RR == 'L' && CR == 'D') {
			curCnt = 2;
		} else if (RR == 'L' && CR == 'U') {
			curCnt = 0;
		}

		// 2. 복사할 맵 유형 찾기
		for (int i = 0; i < 4; i++) {
			if (curMap[i][curCnt] == h)
				mode = i;
		}
		
		// 2-1. 맵 복사
		for (int i = 0; i < N; i+=2) {
			for (int j = 0; j < N; j+=2) {
				map[i][j] = curMap[mode][0];
				map[i][j + 1] = curMap[mode][1];
				map[i + 1][j] = curMap[mode][2];
				map[i + 1][j + 1] = curMap[mode][3];
			}
		}

		// 3. 출력
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}

	}
}
