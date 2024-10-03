package algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;
import java.util.StringTokenizer;

public class Top {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] heights = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
        }

        int[] leftCount = new int[N];  // 왼쪽에서 볼 수 있는 건물의 개수
        int[] rightCount = new int[N]; // 오른쪽에서 볼 수 있는 건물의 개수
        int[] leftClosest = new int[N];  // 왼쪽에서 가장 가까운 건물 번호
        int[] rightClosest = new int[N]; // 오른쪽에서 가장 가까운 건물 번호

        // 왼쪽에서 볼 수 있는 건물 계산
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] <= heights[i]) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                leftCount[i] = leftCount[stack.peek()] + 1;
                leftClosest[i] = stack.peek() + 1; // 1-based index
            }
            stack.push(i);
        }

        // 오른쪽에서 볼 수 있는 건물 계산
        stack.clear(); // 스택 초기화
        for (int i = N - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[stack.peek()] <= heights[i]) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                rightCount[i] = rightCount[stack.peek()] + 1;
                rightClosest[i] = stack.peek() + 1; // 1-based index
            }
            stack.push(i);
        }

        // 결과 출력
        for (int i = 0; i < N; i++) {
            int totalCount = leftCount[i] + rightCount[i];  // 왼쪽과 오른쪽에서 보이는 건물의 개수 합
            if (totalCount == 0) {
                System.out.println(0);
            } else {
                int closest = 0;
                if (leftClosest[i] == 0) {
                    closest = rightClosest[i];
                } else if (rightClosest[i] == 0) {
                    closest = leftClosest[i];
                } else {
                    // 더 가까운 건물을 선택 (거리가 같으면 왼쪽 건물 선택)
                    closest = Math.abs(i - (leftClosest[i] - 1)) <= Math.abs(i - (rightClosest[i] - 1))
                            ? leftClosest[i] : rightClosest[i];
                }
                System.out.println(totalCount + " " + closest);
            }
        }
    }
}
