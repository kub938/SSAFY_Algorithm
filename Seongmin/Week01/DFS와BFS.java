import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Integer>[] arr; //그래프 정보를 담는 리스트
    static boolean visited[]; //재방문 체크

    public static void main(String[] args) throws IOException {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        //n:정점 수 m:간선 수 v:탐색 시작 정점
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());
        
        //1 - n까지이므로 n+1로 구성 (입력 받는 정점 수를 -1씩해도 될듯)
        arr = new ArrayList[n+1];
        visited = new boolean[n+1];
        //arrlist 배열 요소 초기화
        for(int i=1;i<=n;i++) {
            arr[i] = new ArrayList<>();
        }
        
        //그래프 간선 정보 저장
        for(int i=0;i<m;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            arr[a].add(b);
            arr[b].add(a);
        }
        //작은것부터 방문하기 위해 정렬
        for(int i=1;i<=n;i++) {
            Collections.sort(arr[i]);
        }

        //DFS (스택 , 재귀구조 활용)
        visited[v] = true;
        dfs(v);

        //방문 배열 다시 초기화
        visited = new boolean[n+1];
        System.out.println();

        //BFS (큐 자료구조 활용)
        bfs(v);
    }
    //DFS (스택 , 재귀구조 활용)
    static void dfs(int v) {
        System.out.print(v+" ");
        visited[v] = true;

        for(int i:arr[v]) {
            if(!visited[i]) {
                dfs(i);
            }
        }
    }
    //BFS (큐 자료구조 활용)
    static void bfs(int v) {
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(v);
        visited[v] = true;

        while(!q.isEmpty()) {
            int node = q.poll();

            System.out.print(node+" ");

            for(int i:arr[node]) {
                if(!visited[i]) {
                    visited[i] = true;
                    q.add(i);
                }
            }
        }

    }
}