import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
	public static int N, K, ans = 0;
	public static int[] time, before;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		// 1. 입력받기
		N = sc.nextInt();
		K = sc.nextInt();
		time = new int[100001];
		before = new int[100001];
		
		// 2. 예외 처리
		if(N == K) { // 수빈이랑 동생이 같은 위치에 있는 경우
			sb.append(ans + "\n");
			sb.append(N);
			System.out.println(sb);
			return;
		} else if(N > K) { // 수빈이가 동생보다 더 큰 위치에 있는 경우
			ans = N - K;
			sb.append(ans + "\n");
			for(int i=N;i>=K;i--) {
				sb.append(i + " ");
			}
			System.out.println(sb);
			return;
		}
		// 동생이 수빈이보다 더 큰 위치에 있는 경우
		// 3. BFS
		Queue<Integer> q = new LinkedList<>();
		q.offer(N);
		time[N] = 1;
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			if(cur == K) {
				ans = time[cur] - 1;
				break;
			}
			
			
			int next;
			
			for(int i =0;i<3;i++) {
				
				if(i == 0) {
					next = cur + 1;
				} else if (i == 1) {
					next = cur - 1;
				} else {
					next = cur * 2;
				}
				
				if(next < 0 || next > 100000) {
					continue;
				}
				
				if(time[next] == 0) {
					time[next] = time[cur] + 1;
					before[next] = cur;
					q.offer(next);
				}
				
			}
			
		}
		
		sb.append(ans + "\n");
		
		Stack<Integer> st = new Stack<>();
		int cnt = K;
		st.add(K);
		while(cnt != N) {
			cnt = before[cnt];
			st.push(cnt);
		}
		
		while(!st.isEmpty()) {
			sb.append(st.pop() + " ");
		}
		
		
		System.out.println(sb);
	}

}
