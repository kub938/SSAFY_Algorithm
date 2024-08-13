import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	public static int n, x;
	public static int[] arr, visited;
	public static String[] a;

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		a = new String[n];
		arr = new int[n + 1];
		visited = new int[10];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			a[i] = st.nextToken();
		}

		maxdfs(0);
		
		mindfs(0);

	}

	public static void maxdfs(int depth) {
		if (depth == n + 1) {
			if (x == 0) {
				for (int d : arr) {
					System.out.print(d);
				}
				System.out.println();
				x = 1;
			}
			return;
		}

		for (int i = 9; i >= 0; i--) {
			if (depth == 0) {
				arr[depth] = i;
				visited[i] = 1;
				maxdfs(depth + 1);
				visited[i] = 0;
			} else if (visited[i] == 0) {
				if(a[depth-1].equals("<")&& arr[depth-1] < i) {
					arr[depth] = i;
					visited[i] = 1;
					maxdfs(depth + 1);
					visited[i] = 0;
				}else if(a[depth-1].equals(">") && arr[depth-1] > i) {
					arr[depth] = i;
					visited[i] = 1;
					maxdfs(depth + 1);
					visited[i] = 0;
				}
			}
		}
	}
	
	public static void mindfs(int depth) {
		if (depth == n + 1) {
			if (x == 1) {
				for (int d : arr) {
					System.out.print(d);
				}
				System.out.println();
				x = 0;
			}
			return;
		}

		for (int i = 0; i < 10; i++) {
			if (depth == 0) {
				arr[depth] = i;
				visited[i] = 1;
				mindfs(depth + 1);
				visited[i] = 0;
			} else if (visited[i] == 0) {
				if(a[depth-1].equals("<")&& arr[depth-1] < i) {
					arr[depth] = i;
					visited[i] = 1;
					mindfs(depth + 1);
					visited[i] = 0;
				}else if(a[depth-1].equals(">") && arr[depth-1] > i) {
					arr[depth] = i;
					visited[i] = 1;
					mindfs(depth + 1);
					visited[i] = 0;
				}
			}
		}
	}
}
