import java.util.*;
import java.io.*;

/*
git commit -m "김성민 / 240923 / [BOJ_도시 분할 계획_1647] / 1160ms"
### 풀이 사항
풀이 일자: 2024.09.23
풀이 시간: 50분
채점 결과: 정답
예상 문제 유형: 최소신장트리

### 풀이방법
 1. 데이터를 입력받는다.
 2. mst 크루스칼을 사용하여 n-2개의 간선을 추출하고 모두 더한다,

### 느낀점
처음에 문제에 2개 그룹으로 나눈다에 정신이 팔려서
시간초과를 나지 않고 어떻게 조합을 넣을까라고 생각한 것이 안좋았던거 같다.
함정에 빠지지 않게 잘 문제를 고려해야 할 거 같다.

*/

/**
 n개 마을 길 m개 양방향 그래프
마을을 2개로 분리
 분리할때 모두 연결되어 있어야함
 최소 신장 트리를 제외하고는 모두 길을 삭제

 프림
 1. 데이터를 입력받는다.
 n과 m (10만 100만)
 m개를 걸쳐서
 v1 v2 weight로 주어진다. (음수는 없음) 무조건 두집사이에 경로가 존재함

 2. mst 크루스칼을 사용하여 n-2개의
 */
public class Main
{
    static int n;
    static int m;
    static PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
        return o1[2]-o2[2];
    });
    static int[] parents;
    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            int w = Integer.parseInt(st.nextToken());

            pq.add(new int[]{a,b,w});
        }
        int result =0;
        parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }
        //크루스칼
        int cnt = 0;
        for (int i = 0; cnt<n-2; i++) {
            //최솟값을 뽑고
            int[] edge = pq.poll();
            //사이클 만드는지 확인하고
            if(find(edge[0]) == find(edge[1])) continue;
            union(edge[0], edge[1]);
            cnt++;
            result+= edge[2];
        }
        System.out.println(result);
    }

    static void union(int a, int b){
        int rootA = find(a);
        int rootB = find(b);
        parents[rootB] = rootA;
    }
    static int find(int a){
        if(a == parents[a]) return a;
        return parents[a] = find(parents[a]);
    }
}