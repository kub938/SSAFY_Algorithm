import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static int N;
	public static int[] L, cnt, min;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		// 1. 입력받기
		N = Integer.parseInt(br.readLine());
		L = new int[N + 1];
		cnt = new int[N + 1];
		min = new int[N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			L[i] = Integer.parseInt(st.nextToken());
			min[i] = -100000;
		}

		// 2. 왼쪽 보이는 건물 개수
		Stack<Integer> left = new Stack<>();
		for (int i = 1; i <= N; i++) {
			while (!left.isEmpty() && L[left.peek()] <= L[i]) { // 스택이 비어있지 않고 나보다 높은 건물이 나올 때 까지
				left.pop();
			}

			cnt[i] = left.size();
			if (cnt[i] > 0)
				min[i] = left.peek();
			left.push(i);
		}

		// 2-1. 오른쪽 보이는 건물 개수
		Stack<Integer> right = new Stack<>();
		for (int i = N; i >= 1; i--) {
			while (!right.isEmpty() && L[right.peek()] <= L[i]) { // 스택이 비어있지 않고 나보다 높은 건물이 나올 때 까지
				right.pop();
			}

			cnt[i] += right.size();
			if (right.size() > 0 && right.peek() - i < i - min[i])
				min[i] = right.peek();
			right.push(i);
		}

		// 3. 출력하기
		for (int i = 1; i <= N; i++) {
			if (cnt[i] == 0) {
				sb.append(0 + "\n");
			} else {
				sb.append(cnt[i] + " " + min[i] + "\n");
			}
		}

		System.out.println(sb);
	}
}
