import java.io.*;
import java.util.*;

//bfs, 메모이제이션


public class Main {
    static int[] time = new int[100001];
    static int[] parents = new int[100001];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        if(n>=k){
            System.out.println(n-k);
            for (int i = n; i >= k; i--) {
                System.out.print(i+" ");
            }
            return;
        }

        Queue<Integer> q = new LinkedList<>();
        q.add(n);
        time[n] = 1;
        parents[n] = -1;
        int[] mix = {1, 2, 1};
        int[] add = {-1, 0, 1};

        while (!q.isEmpty()) {
            int node = q.poll();
            if (node == k) break;

            for (int i = 0; i < 3; i++) {
                if(node>k && i>=1){
                    continue;
                }
                int next = node + add[i];

                next *= mix[i];
                if (next >= 0 && next <= 100000 && time[next] == 0) {
                    time[next] = time[node] + 1;
                    parents[next] = node;
                    q.add(next);
                }
            }
        }

        System.out.println(time[k] - 1);
        int a = k;
        StringBuilder sb = new StringBuilder();
        while (parents[a] != -1) {
            sb.insert(0, a + " ");
            a = parents[a];
        }
        sb.insert(0, a + " ");
        System.out.println(sb);
    }


}