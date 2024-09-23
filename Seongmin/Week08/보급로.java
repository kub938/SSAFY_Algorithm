import java.io.*;
import java.util.*;

/*
git commit -m "김성민 / 240923 / [SWEA_보급로_1249] / 218ms"
### 풀이 사항
풀이 일자: 2024.09.23
풀이 시간: 20분
채점 결과: 정답
예상 문제 유형: 우선순위큐

### 풀이방법
 1. 데이터를 입력받는다.
 2. 우선순위큐를 사용해서 최단거리를 구한다.
 3. 만약 목적지에 도착했으면 반복을 종료하고 그때 비용을 출력한다.
*/

public class Solution {
    static int[][] map = new int[100][100];
    static int[][] dd = {{0,1},{1,0},{0,-1},{-1,0}};
    static int[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[2], o2[2]));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            int n = Integer.parseInt(br.readLine());
            visited = new int[n][n];

            for (int i = 0; i < n; i++) {
                String s = br.readLine();
                for (int j = 0; j < n; j++) {
                    map[i][j] = s.charAt(j) - '0';
                    visited[i][j] = Integer.MAX_VALUE;
                }
            }

            //int {r, c, w}
            pq.clear();
            pq.offer(new int[]{0,0,0});
            while (!pq.isEmpty()) {
                int[] cur = pq.poll();
                int r = cur[0];
                int c = cur[1];
                int weight = cur[2];

                if(r==n-1&&c==n-1){
                    break;
                }

                for(int[] d:dd){
                    int hr = r+d[0];
                    int hc = c+d[1];

                    if(hr>=0 && hr<n && hc>=0 && hc<n && visited[hr][hc]>map[hr][hc]+weight){
                        visited[hr][hc] = map[hr][hc]+weight;
                        pq.offer(new int[]{hr,hc,visited[hr][hc]});
                    }
                }
            }

            sb.append('#').append(tc).append(' ').append(visited[n-1][n-1]).append('\n');
        }
        System.out.print(sb);
    }
}