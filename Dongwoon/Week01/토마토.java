import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	public static int n, m, ans = 0;
	public static int[][] a;
	public static int[] dx = { 0, 0, 1, -1 };
	public static int[] dy = { 1, -1, 0, 0 };

	public static class tomato {
		int x = 0;
		int y = 0;
		int day = 0;

		public tomato(int x, int y, int day) {
			super();
			this.x = x;
			this.y = y;
			this.day = day;
		}
	}

	public static Queue<tomato> q = new LinkedList<>();

	public static void main(String[] args) throws IOException {

		// 1. 입력 > N, M, V, graph
		Scanner sc = new Scanner(System.in);
		m = sc.nextInt();
		n = sc.nextInt();
		a = new int[m][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				a[j][i] = sc.nextInt();
				if (a[j][i] == 1) {
					q.add(new tomato(j, i, 0));
				}
			}
		}

		// 4. bfs 호출
		bfs();

	}

	public static void bfs() {

		while (!q.isEmpty()) {
			// 1. Q에서 방문 지점 꺼냄
			tomato cur = q.poll();
			int x = cur.x;
			int y = cur.y;
			int day = cur.day;
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
					if (a[nx][ny] == 0) {
						q.add(new tomato(nx, ny, day + 1));
						a[nx][ny] = 1;
						System.out.println(nx + " " + ny);
						if (day + 1 > ans) {
							ans = day + 1;
						}
					}
				}
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (a[j][i] == 0) {
					System.out.println(-1);
					return;
				}
			}
		}

		System.out.println(ans);
	}
}