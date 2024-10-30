import java.io.*;
import java.util.*;

public class Main {

	static StringTokenizer st;

	static int N, M;

	static int[][] map;

	public static class Dice {
		int r;
		int c;
		int direction;
		int[] dice = {1,2,3,4,5,6};

		public Dice() {
			this.r = 0;
			this.c = 1;
			this.direction = 1;
		}
	}

	static int answer;
	
	static Dice dice;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][N];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		answer = 0;

		// 주사위 선언 초기값은 명확
		dice = new Dice();
		
		// M번 반복하기
		for (int i = 0; i < M; i++) {
			int currentScore = BFS(dice.r, dice.c);
			
			// 현재 주사위 밟고있는칸 점수 복사
			answer += currentScore;
			
			// 주사위 바닥이 현재 좌표 숫자보다 크면 우회전
			if (dice.dice[2] > map[dice.r][dice.c]) {
				dice.direction = (dice.direction + 1) % 4;
			// 주사위 바닥이 현재 좌표 숫자보다 작으면 좌회전	
			} else if (dice.dice[2] < map[dice.r][dice.c]) {
				dice.direction = (dice.direction + 3) % 4;
			}

			// 방향에 따른 벽 튕김 예외처리
			if (dice.r == 0 && dice.direction == 0) {
				dice.direction = 2;
			} else if (dice.r == N - 1 && dice.direction == 2) {
				dice.direction = 0;
			} else if (dice.c == 0 && dice.direction == 3) {
				dice.direction = 1;
			} else if (dice.c == N - 1 && dice.direction == 1) {
				dice.direction = 3;
			}

			// 방향에 맞게 구르기
			switch (dice.direction) {
			// 위로 구르기
			case 0:
				dice.r--;
				turnDice(0);
				break;
			case 1:
				dice.c++;
				turnDice(1);
				break;
			case 2:
				dice.r++;
				turnDice(2);
				break;
			case 3:
				dice.c--;
				turnDice(3);
				break;
			default:
				break;
			}

		}
		
		System.out.println(answer);

	}
	
	public static void turnDice(int direction) {
		int[] temp = dice.dice.clone();
		
		switch(direction) {
		// 위로 구를 경우
		case 0:
			dice.dice[0] = temp[0];
			dice.dice[1] = temp[2];
			dice.dice[2] = temp[4];
			dice.dice[3] = temp[1];
			dice.dice[4] = temp[3];
			dice.dice[5] = temp[5];
			break;
		case 1:
			dice.dice[0] = temp[3];
			dice.dice[1] = temp[1];
			dice.dice[2] = temp[0];
			dice.dice[3] = temp[5];
			dice.dice[4] = temp[4];
			dice.dice[5] = temp[2];
			break;
		case 2:
			dice.dice[0] = temp[0];
			dice.dice[1] = temp[3];
			dice.dice[2] = temp[1];
			dice.dice[3] = temp[4];
			dice.dice[4] = temp[2];
			dice.dice[5] = temp[5];	
			break;
		case 3:
			dice.dice[0] = temp[2];
			dice.dice[1] = temp[1];
			dice.dice[2] = temp[5];
			dice.dice[3] = temp[0];
			dice.dice[4] = temp[4];
			dice.dice[5] = temp[3];	
			break;
		}
		
	}

	// 점수 인식
	public static int BFS(int r, int c) {
		int answer = 1;

		int[] dx = { 1, -1, 0, 0 };
		int[] dy = { 0, 0, 1, -1 };

		Queue<int[]> q = new LinkedList<>();

		boolean[][] visited = new boolean[N][N];

		visited[r][c] = true;

		q.add(new int[] { r, c });

		int currentValue = map[r][c];

		while (!q.isEmpty()) {
	
			
			int[] temp = q.poll();
			int x = temp[0];
			int y = temp[1];

			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if (nx < 0 || nx >= N || ny < 0 || ny >= N)
					continue;
				if (visited[nx][ny])
					continue;
				if (map[nx][ny] != currentValue)
					continue;

				answer++;
				
				visited[nx][ny] = true;
				q.add(new int[] { nx, ny });
				

			}

		}
		
		return answer * currentValue;

	}

}
