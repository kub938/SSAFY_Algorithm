import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

	public static int[][] a, home_map, chik_map;
	public static int[] arr, what;
	public static int n, m, home = 0, chik = 0, ans, min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.valueOf(st.nextToken());
		m = Integer.valueOf(st.nextToken());
		a = new int[n][n];
		
		chik_map = new int[2][13];
		home_map = new int[2][2 * n];
		what = new int[m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				a[i][j] = Integer.valueOf(st.nextToken());
				if (a[i][j] == 2) {
					chik_map[0][chik] = i;
					chik_map[1][chik] = j;
					chik++;
				} else if (a[i][j] == 1) {
					home_map[0][home] = i;
					home_map[1][home] = j;
					home++;
				}
			}
		}
		
		arr = new int[home];
		
		Arrays.fill(arr, Integer.MAX_VALUE);
		
		dfs(0,0);
		
		System.out.println(min);
		
	}
	
	public static void dfs(int at, int depth){
		
		if(depth == m) {
			Arrays.fill(arr, Integer.MAX_VALUE);
			for(int d : what) {
				distance(d);
			}
			int sum = 0;
			for(int d : arr) {
				sum += d;
			}
			if(min > sum)
				min = sum;
			return;
		}
		for(int i = at;i<chik;i++) {
			what[depth] = i;
			dfs(i + 1, depth+1);
		}
	}
	
	public static void distance(int chik) {
		for(int i = 0 ;i < home; i++) {
			int r = Math.abs(home_map[0][i] - chik_map[0][chik]);
			int c = Math.abs(home_map[1][i] - chik_map[1][chik]);
			if(arr[i] > r+c) {
				arr[i] = r+c;
			}
		}
	}
}
