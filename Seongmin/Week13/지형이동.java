import java.util.*;
//bfs를 돌면서 높이차가 3이하인 친구들을 한묶음으로 표시 1,2,3이런식으로 새로운 bfs실행될수록 숫자 커짐
//모든 사다리를 돌면서 묶음이 다른 원소가 상하좌우에 있다면 간선리스트에 저장
//최소신장트리(크루스칼)로 간선리스트를 연결한다.


class Solution {
    int[][] color;
    int[][] graph;
    int[][] land;
    int n;
    int colorNum;
    int[] dy = {-1,1,0,0};
    int[] dx = {0,0,1,-1};
    int result;
    PriorityQueue<int[]> pq;
    public int solution(int[][] land, int height) {
        this.land = land; 
        pq = new PriorityQueue<>((o1,o2)->{return o1[2] - o2[2];});
        n = land.length;
        color = new int[n][n];
        colorNum = 1;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(color[i][j]==0){
                    bfs(i,j,colorNum++, height);
                }
            }
        }
        setGraph();
        kruskal();
        return result;
    }
    void kruskal(){
        int[] parents = new int[colorNum];
        for(int i=1;i<colorNum;i++){
            parents[i] = i;
        }
        
        for(int i=0;i<colorNum-2;i++){
            while(!pq.isEmpty()){
                
                //하나를 뺸다
                int[] node = pq.poll();
                int a = node[0];
                int b = node[1];
                int value = node[2];
                //뺀 친구가 사이클을 안만들면
                if(find(a,parents) != find(b,parents)){
                    //graph값을 result에 더하고 break;
                    union(a,b,parents);
                    result+= value;
                    break;
                }
            }
        }
    }
    
    void union(int a, int b,int[] parents){
        int rootA = find(a,parents);
        int rootB = find(b,parents);
        
        parents[rootB] = rootA;
    }
    int find(int a, int[] parents){
        if(a==parents[a]) return a;
        return parents[a] = find(parents[a],parents);
    }
    
    
    void setGraph(){
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                
                for(int k=0;k<4;k++){
                    int hy = i+dy[k];
                    int hx = j+dx[k];
                    int color1 = color[i][j];
                    
                    if(check(hy,hx) && color[i][j] != color[hy][hx]){
                        int color2 = color[hy][hx];
                        pq.add(new int[] {color1, color2, Math.abs(land[i][j]-land[hy][hx])});
                    }
                }
                
            }
        }
    }
    
    public void bfs(int sy, int sx, int num, int height){
        color[sy][sx] = num;
        
        Queue<int[]> que = new ArrayDeque<>();
        que.add(new int[]{sy,sx});
        
        while(!que.isEmpty()){
            int[] node = que.poll();
            int y = node[0];
            int x = node[1];
            for(int i=0;i<4;i++){
                int hy = node[0]+dy[i];
                int hx = node[1]+dx[i];
                
                if(check(hy,hx) && color[hy][hx]==0 && Math.abs(land[hy][hx]-land[y][x])<=height){
                    color[hy][hx] = num;
                    que.add(new int[]{hy,hx});
                }
            }
        }
    }
    boolean check(int y, int x){
        return y>=0 && y<n && x>=0 && x<n;
    }
}
