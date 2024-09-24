import java.util.*;
import java.io.*;

/*
git commit -m "김성민 / 240924 / [BOJ_학교 탐방하기_13418] / 792ms"
### 풀이 사항
풀이 일자: 2024.09.24
풀이 시간: 40분
채점 결과: 정답
예상 문제 유형: 최소신장트리

### 풀이방법
 1. 데이터를 입력받는다.
 1-1 간선을 우선순위 큐를 큰 순, 작은 순으로 두개에 모두 넣는다.
 2. mst 크루스칼을 돌리고 결과값을 제곱하여 계산한다.

### 느낀점
문제를 잘못읽어서 문제 풀이에 시간이 걸렸다. 잘좀 읽자
*/

/**

 */
public class Main
{
    static int n;
    static int m;
    static PriorityQueue<int[]> pq2 = new PriorityQueue<>((o1, o2) -> {
        return o1[2]-o2[2];
    });
    static PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
        return o2[2]-o1[2];
    });
    static int[] parents;

    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            pq.add(new int[]{a,b,w});
            pq2.add(new int[]{a,b,w});
        }
        int result1 =0;
        int result2 = 0;

        //작은거 찾기
        int cnt = 0;
        parents = new int[n+1];
        for (int i = 0; i <= n; i++) {
            parents[i] = i;
        }
        for(;cnt<n;){
            int[] edge = pq.poll();
            if(find(edge[0]) == find(edge[1])) continue;

            union(edge[0], edge[1]);
            if(edge[2]==0) result1++;
            cnt++;
        }
        //큰거 찾기
        for (int i = 0; i <= n; i++) {
            parents[i] = i;
        }
        cnt = 0;
        for(;cnt<n;){
            int[] edge = pq2.poll();
            if(find(edge[0]) == find(edge[1])) continue;

            union(edge[0], edge[1]);
            if(edge[2]==0) result2++;

            cnt++;
        }

        System.out.println(result2*result2 - result1*result1);
    }

    static int find(int a){
        if(a == parents[a]) return a;
        return parents[a] = find(parents[a]);
    }

    static void union(int a, int b){
        int rootA = find(a);
        int rootB = find(b);

        if(rootA>rootB) parents[rootB] = rootA;
        else parents[rootA] = rootB;
    }
}