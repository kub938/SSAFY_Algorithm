import java.util.*;
class Solution {
    public static int N, h, cnt = 0;
    public static int[] dr = {-1, 0, 1, 0};
    public static int[] dc = {0, 1, 0 ,-1};
    public static int[][] visited;
    public static PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[2], o2[2]));
    
    public int solution(int[][] land, int height) {
        int answer = 0;
        N = land[0].length;
        h = height;
        visited = new int[N][N];
        
        pq.offer(new int[] {0, 0, 0});
        
        while(cnt < N * N){
            int[] cur = pq.poll();
            int cr = cur[0];
            int cc = cur[1];
            if(visited[cr][cc] == 1) continue;
            cnt += 1;
            answer += cur[2];
            dfs(land, cr, cc);
        }
        
        return answer;
    }
    
    public static void dfs(int[][] land, int sr, int sc){
        visited[sr][sc] = 1;
        for(int i = 0;i<4;i++){
            int nr = sr + dr[i];
            int nc = sc + dc[i];
            if(nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
            if(visited[nr][nc] == 0){
                int cost = Math.abs(land[nr][nc] - land[sr][sc]);
                if(cost <= h){
                    cnt += 1;
                    dfs(land, nr, nc);
                } else{
                    pq.offer(new int[] {nr, nc, cost});
                }
            }
        }
    }
}
