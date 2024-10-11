import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static int N, ans = 0;
	public static int[][] map, copyMap;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		// 1. 입력받기
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		copyMap = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 2. 이동 가능한 경우의 수 구하기 (0~3까지 중복 순열)
		dfs(0, map);

		// 4. 정답 출력
		System.out.println(ans);
	}

	public static void dfs(int depth, int[][] curMap) {
		if (depth == 5) {
			int max = chk(curMap);
			if (max > ans) {
				ans = max;
			}
			return;
		}
		
		int[][] nMap = new int[N][N];
		for (int t = 0; t < 4; t++) {
			// 3. 맵 이동하기
			nMap = move(t, curMap);
			dfs(depth + 1, nMap);
		}
	}

	private static int chk(int[][] curMap) {
		int max = 0;
		for(int i =0;i<N;i++) {
			for(int j = 0;j<N;j++) {
				if(curMap[i][j] > max) {
					max = curMap[i][j];
				}
			}
		}
		
		return max;
	}

	public static int[][] move(int mode, int[][] map) {
		int[][] nextMap = new int[N][N];
		switch (mode) {
		case 0:
			for (int j = 0; j < N; j++) { // 가로를 돌며 반복
				Stack<Integer> st = new Stack<>();
				Queue<Integer> q = new LinkedList<>();
				for (int i = 0; i < N; i++) { // 세로를 돌며 반복
					if (map[i][j] != 0) { // map값이 0이 아닐때
						if(st.isEmpty()) { // 직전 수가 없다면 
							st.add(map[i][j]);
						} else { // 직전 수가 있다면
							int cur = st.pop(); 
							if(cur == map[i][j]) { // 직전값과 현재값이 같다면
								q.add(cur*2); // 두배 증가해서 큐에 넣기
							} else { // 직전값과 현재값이 다르다면
								q.add(cur); // 큐에 직전값 넣기
								st.add(map[i][j]); // 직전값 초기화
							}
						}
					}
				}
				
				if(!st.isEmpty()) {
					q.add(st.pop());
				}
				
				int cnt = 0;
				while(!q.isEmpty()) {
					nextMap[cnt++][j] = q.poll();
				}
			}
			break;
		case 1:
			for (int i = 0; i < N; i++) { // 세로를 돌며 반복
				Stack<Integer> st = new Stack<>();
				Queue<Integer> q = new LinkedList<>();
				for (int j = N-1; j >= 0; j--) { // 뒤부터 가로를 돌며 반복
					if (map[i][j] != 0) { // map값이 0이 아닐때
						if(st.isEmpty()) { // 직전 수가 없다면 
							st.add(map[i][j]);
						} else { // 직전 수가 있다면
							int cur = st.pop(); 
							if(cur == map[i][j]) { // 직전값과 현재값이 같다면
								q.add(cur*2); // 두배 증가해서 큐에 넣기
							} else { // 직전값과 현재값이 다르다면
								q.add(cur); // 큐에 직전값 넣기
								st.add(map[i][j]); // 직전값 초기화
							}
						}
					}
				}
				
				if(!st.isEmpty()) {
					q.add(st.pop());
				}
				
				int cnt = N-1;
				while(!q.isEmpty()) {
					nextMap[i][cnt--] = q.poll();
				}
			}
			break;
		case 2:
			for (int j = 0; j < N; j++) { // 가로를 돌며 반복
				Stack<Integer> st = new Stack<>();
				Queue<Integer> q = new LinkedList<>();
				for (int i = N-1; i >= 0; i--) { // 세로를 반대로 돌며 반복
					if (map[i][j] != 0) { // map값이 0이 아닐때
						if(st.isEmpty()) { // 직전 수가 없다면 
							st.add(map[i][j]);
						} else { // 직전 수가 있다면
							int cur = st.pop(); 
							if(cur == map[i][j]) { // 직전값과 현재값이 같다면
								q.add(cur*2); // 두배 증가해서 큐에 넣기
							} else { // 직전값과 현재값이 다르다면
								q.add(cur); // 큐에 직전값 넣기
								st.add(map[i][j]); // 직전값 초기화
							}
						}
					}
				}
				
				if(!st.isEmpty()) {
					q.add(st.pop());
				}
				
				int cnt = N-1;
				while(!q.isEmpty()) {
					nextMap[cnt--][j] = q.poll();
				}
			}
			break;
		case 3:
			for (int i = 0; i < N; i++) { // 세로를 돌며 반복
				Stack<Integer> st = new Stack<>();
				Queue<Integer> q = new LinkedList<>();
				for (int j = 0; j < N; j++) { // 가로를 돌며 반복
					if (map[i][j] != 0) { // map값이 0이 아닐때
						if(st.isEmpty()) { // 직전 수가 없다면 
							st.add(map[i][j]);
						} else { // 직전 수가 있다면
							int cur = st.pop(); 
							if(cur == map[i][j]) { // 직전값과 현재값이 같다면
								q.add(cur*2); // 두배 증가해서 큐에 넣기
							} else { // 직전값과 현재값이 다르다면
								q.add(cur); // 큐에 직전값 넣기
								st.add(map[i][j]); // 직전값 초기화
							}
						}
					}
				}
				
				if(!st.isEmpty()) {
					q.add(st.pop());
				}
				
				int cnt = 0;
				while(!q.isEmpty()) {
					nextMap[i][cnt++] = q.poll();
				}
			}
			break;
		}
		return nextMap;
	}
}
