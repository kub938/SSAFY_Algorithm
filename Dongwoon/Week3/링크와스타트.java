import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	public static int n, min = Integer.MAX_VALUE;
	public static int[] arr, arr2, visited;
	public static int[][] a;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 1. 입력값 받아오기
		n = Integer.valueOf(st.nextToken());
		a = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				a[i][j] = Integer.valueOf(st.nextToken());
			}
		}

		// 2. 팀을 나누기 위한 팀 배열 선언
		// 2-1. 인원수를 다르게 팀원을 분배해서 팀 배열 만들기
		for (int i = 1; i <= n / 2; i++) {
			arr = new int[i];
			arr2 = new int[n - i];
			visited = new int[n];
			dfs(0, 0, i);
		}

		System.out.println(min);

	}

	// 3. 팀 인원수에 따른 dfs 조합 만들기
	public static void dfs(int at, int depth, int num) {
		if (depth == num) {
			int cnt = 0;
			for(int i = 0;i<n;i++) {
				if(visited[i] == 0) {
					arr2[cnt] = i;
					cnt++;
				}
			}

			// 4-1. 1,2 번팀 능력치의 차이 구하기
			int sum = Math.abs(power(arr, num) - power(arr2, n-num));
			
			// 5. min과 비교했을때 더 작다면 min 초기화
			if(min>sum) {
				min = sum;
			}

			return;
		}

		for (int i = at; i < n; i++) {
			arr[depth] = i;
			visited[i] = 1;
			dfs(i + 1, depth + 1, num);
			visited[i] = 0;
		}

	}

	// 팀의 능력치 합 구하는 함수
	public static int power(int arr[], int num) {
		int sum = 0;

		for (int i = 0; i < num; i++) {
			for (int j = 0; j < num; j++) {
				sum += a[arr[i]][arr[j]];
			}
		}

		return sum;
	}
}
