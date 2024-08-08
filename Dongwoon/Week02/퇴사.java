import java.util.*;

public class Main {
	static int n, max = 0;
	static int[][] a;
	static int arr = 0;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		n = sc.nextInt();

		a = new int[2][n];

		for (int i = 0; i < n; i++) {
			a[0][i] = sc.nextInt();
			a[1][i] = sc.nextInt();
		}
		
		dfs(0,0);
		sb.append(max);
		System.out.println(sb);

	}

	static void dfs(int at, int cost) {
		if(at <= n) {
			if(cost>max) {
				max = cost;
			}
		} else {
			return;
		}
		
		for(int i = at;i < n;i ++) {
			dfs(i + a[0][i], cost + a[1][i]);
			
		}
	}
}
