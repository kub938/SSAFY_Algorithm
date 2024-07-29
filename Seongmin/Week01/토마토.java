import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    //토마토가 익음 정보를 저장하는 배열
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        //값 입력받기
        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        arr = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        //BFS를 통해서 순회
        System.out.println(bfs(n,m));
    }
    //Queue를 사용해서
    private static int bfs(int n, int m) {
        Queue<int[]> q = new LinkedList<>();

        //방향 배열
        int[][] dd = {{1,0},{-1,0},{0,1},{0,-1}};
        int max = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(arr[i][j] == 1) q.add(new int[]{i,j,0});
            }
        }
        
        //지금와서 생각해보니 
        //그냥 arr배열 값에 날짜를 넣는게 메모리적으로 효율적일듯
        
        while(!q.isEmpty()){
            int[] node = q.poll();
            int y = node[0];
            int x = node[1];
            
            //동서남북 방향으로 배열 범위 안이고 0이라면 토마토 익힘
            for (int[] d : dd){
                int hy = y+d[0];
                int hx = x+d[1];

                if(hy>=0 && hy<n && hx>=0 && hx<m && arr[hy][hx]==0){
                    arr[hy][hx] = 1;
                    q.add(new int[]{hy,hx,node[2]+1});
                    max = Math.max(node[2]+1,max);
                }
            }
        }
        //아직 안익은 토마토가 있을 경우 -1을 리턴
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(arr[i][j] == 0) return -1;
            }
        }
        //토마토가 전부 익었으면 max리턴
        return max;
    }
}
