import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static int T, N, M, K, S, G, H;
	public static int[] k;
	public static int[][] graph;
	public static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			// 1. 입력받기
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			graph = new int[N + 1][N + 1];
			k = new int[K];

			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					graph[i][j] = INF;
				}
			}

			st = new StringTokenizer(br.readLine());
			S = Integer.parseInt(st.nextToken());
			G = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());

			// 1-1. 도로 입력받기
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int at = Integer.parseInt(st.nextToken());
				int value = Integer.parseInt(st.nextToken());

				graph[from][at] = Math.min(graph[from][at], value);
				graph[at][from] = Math.min(graph[at][from], value);
			}

			// 1-2. 도착지 입력받기
			for (int i = 0; i < K; i++) {
				k[i] = Integer.parseInt(br.readLine());
			}

			int sh = dijk(S, H);
			int sg = dijk(S, G);
			int gh = graph[G][H];

			Arrays.sort(k);

			// 2. 최단 경로 계산
			for (int i = 0; i < K; i++) {
				int hk = dijk(H, k[i]);
				int gk = dijk(G, k[i]);
				int sk = dijk(S, k[i]);

				int min = INF;

				// S - G - H - K
				if (sg != INF && gh != INF && hk != INF) {
					min = sg + gh + hk;
				}

				// S - H - G - K
				if (sh != INF && gh != INF && gk != INF) {
					min = Math.min(min, sh + gh + gk);
				}

				// S - K 로 가는 경로의 최솟값과 같다면 출력 아니면 continue;
				if (min != INF && min == sk) {
					sb.append(k[i] + " ");
				}
			}

			// 3. 출력
			sb.append("\n");
		}

		System.out.println(sb.toString());

	}

	private static int dijk(int start, int end) {
		int[] min = new int[N + 1];
		int[] visited = new int[N + 1];
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));

		for (int i = 1; i <= N; i++) {
			min[i] = INF;
		}

		min[start] = 0;
		pq.offer(new int[] { start, min[start] });

		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int index = cur[0];
			int value = cur[1];
			
			if(visited[index] == 1) continue;
			visited[index] = 1;
			
			if(index == end) {
				return value;
			}

			for (int i = 1; i <= N; i++) {
				if (graph[index][i] != INF && min[i] > min[index] + graph[index][i]) {
					min[i] = min[index] + graph[index][i];
					pq.offer(new int[] { i, min[i] });
				}
			}
		}

		return min[end];
	}
}
