import java.io.*;
import java.util.*;

public class Main {
	
	static StringTokenizer st;
	
	static int N,K;
	
	static int[] kit;
	
	static int[] temp;
	
	static boolean[] visited;
	
	static int answer = 0;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		kit = new int[N];
		temp = new int[N];
		visited = new boolean[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			kit[i] = Integer.parseInt(st.nextToken());
		}
		
		DFS(0);
		
		System.out.println(answer);

	}
	
	public static void DFS(int depth) {
		if(depth == kit.length) {
			simulate();
			return;
		}
		
		for(int i = 0; i < kit.length; i++) {
			if(!visited[i]) {
				visited[i] = true;
				temp[depth] = kit[i];
				DFS(depth + 1);
				visited[i] = false;
			}
		}
	}
	
	public static void simulate() {
		
		int m = 500;
		
		for(int i : temp) {
			// 현재 운동 순서 만큼 중량을 얻음
			m += i;
			// 중량 손실을 얻음
			m -= K;
			// 중량 총합이 500이 되지 아니하면
			if(m < 500) {
				return;
			}
		}
		
		answer++;
	}
	
	public static void snapShot() {
		for(int t : temp) {
			System.out.print(t+ " ");
		}
		System.out.println();
	}

}

