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
	public static int N, M, V;
	public static List<Integer>[] graph;
	public static int[] visited;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {

		// 1. 입력 > N, M, V, graph

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		graph = new ArrayList[N + 1];
		visited = new int[N + 1];
		for (int i = 1; i < N + 1; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int i1 =Integer.parseInt(st.nextToken());
			int i2 =Integer.parseInt(st.nextToken());
			graph[i1].add(i2);
			graph[i2].add(i1);
		}
		for(int i=1; i<N+1; i++) {
			Collections.sort(graph[i]);
		}
		

		// 2. dfs 호출
		visited[V] = 1;
		dfs(V);
//		visited[V] = 0;


		// 4. bfs 호출
		sb.append("\n");
		bfs(V);
		System.out.println(sb);

	}

	public static void bfs(int start) {
		Queue<Integer> q = new LinkedList<>();
		visited[start] = 0;
		q.offer(start);
		while(!q.isEmpty()) {
			// 1. Q에서 방문 지점 꺼냄
			int cur = q.poll();
			// 2. 출력
			sb.append(cur+" ");
			
			// 3. 방문 지점으로부터 가능한 다음 지점 탐색
			for(int i = 0; i<graph[cur].size(); i++) {
				int next = graph[cur].get(i);
				if(visited[next] == 1) {
					visited[next] = 0;
					q.offer(next);
				}
			}
		}
	}

	public static void dfs(int start) {
		sb.append(start + " ");
		for(int i = 0; i<graph[start].size(); i++) {
			int next = graph[start].get(i);
			if(visited[next] == 0) {
				visited[next] = 1;
				dfs(next);
//				visited[next] = 0;
			}
		}
		
	}

}