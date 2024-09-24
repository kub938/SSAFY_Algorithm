import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static int N, M, best = 0, worst = 0, ans = 0;
	public static int[] visited;
	public static List<int[]>[] list;
	public static int[][] graph;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 1. 입력받기
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		visited = new int[N + 1];
		list = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++) {
			list[i] = new ArrayList<>();
		}

		for (int i = 0; i < M + 1; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());

			list[from].add(new int[] { to, value });
			list[to].add(new int[] { from, value });
		}

		// 2. 최소거리
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));

		pq.add(new int[] { 0, 1 });

		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int to = cur[0];
			int value = cur[1];

			// 간선 비용 비교
			if (visited[to] == 1)
				continue;

			visited[to] = 1;
			if (value == 0) {
				best += 1;
			}

			for (int i = 0; i < list[to].size(); i++) {
				int[] hae = list[to].get(i);

				if (visited[hae[0]] == 0) {
					pq.add(new int[] { hae[0], hae[1] });
				}
			}
		}

		// 3. 최장거리
		PriorityQueue<int[]> pq2 = new PriorityQueue<>((o1, o2) -> Integer.compare(o2[1], o1[1]));

		pq2.add(new int[] { 0, 1 });

		while (!pq2.isEmpty()) {
			int[] cur = pq2.poll();
			int to = cur[0];
			int value = cur[1];

			// 간선 비용 비교
			if (visited[to] == 0)
				continue;

			visited[to] = 0;
			if (value == 0) {
				worst += 1;
			}

			for (int i = 0; i < list[to].size(); i++) {
				int[] hae = list[to].get(i);

				if (visited[hae[0]] == 1) {
					pq2.add(new int[] { hae[0], hae[1] });
				}
			}
		}
		ans = (int) (Math.pow(best, 2) - (Math.pow(worst, 2)));

		// 4. 출력하기
		System.out.println(ans);

	}
}
