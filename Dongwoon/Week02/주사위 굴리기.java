import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	public static int n, m, r, c, x, y, k;
	public static int[][] a;
	public static int[] b = new int[7];
	public static int[] dx = { 1, -1, 0, 0 };
	public static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		a = new int[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				a[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < k; i++) {
			int cnt = Integer.parseInt(st.nextToken());
			int nx = x + dx[cnt - 1];
			int ny = y + dy[cnt - 1];
			if(nx <0 || ny < 0 || nx > m-1 || ny > n-1)
				continue;
			x = nx;
			y = ny;
			dice(cnt,x,y);
		}
	}

	public static void dice(int cnt, int x, int y) {
		int tmp = b[3];
		switch (cnt) {
		case 1:
			b[3] = b[4];
			b[4] = b[6];
			b[6] = b[2];
			b[2] = tmp;
			break;
		case 2:
			b[3] = b[2];
			b[2] = b[6];
			b[6] = b[4];
			b[4] = tmp;
			break;
		case 3:
			b[3] = b[5];
			b[5] = b[6];
			b[6] = b[1];
			b[1] = tmp;
			break;
		case 4:
			b[3] = b[1];
			b[1] = b[6];
			b[6] = b[5];
			b[5] = tmp;
			break;
		}
		if (a[y][x] == 0) {
			a[y][x] = b[6];
		}else {
			b[6] = a[y][x];
			a[y][x] = 0;
		}
		
		System.out.println(b[3]);
		
	}
}
