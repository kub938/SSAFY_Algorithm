import java.util.*;

public class Main {
    public static int N;
    public static int M;
    public static int V;
    public static int[][] arr;
    public static List<List<Integer>> list;

    public static int start;
    public static int destination;

    public static int[] visited;

    public static Queue<Number> q;


    // DFS 구현
    public static void DFS(int V){

        // 방문 했으면 그냥 돌아가라
        if(visited[V] == 1){
            return;
        }

        // 찍음.
        System.out.print(V + 1 + " ");

        // 방문했음.
        visited[V] = 1;


//        for(int v = 0; v < arr[V].length; v++){
//            if(arr[V][v] == 1){
//                DFS(v);
//            }
//        }

        for(int g : list.get(V)){
            // 0이 아니고 방문 안했으면,
            if(visited[g] == 0)  {
                DFS(g);
            }
        }
    }

    public static void BFS(int V){

        visited[V] = 1;
        q.add(V);

        while (!q.isEmpty()){
            int Vertex = (int) q.poll();
            System.out.print(Vertex + 1 + " ");
//            for(int v = 0; v < arr[Vertex].length; v++){
//                if(arr[Vertex][v] == 1 && visited[v] != 1){
//                    q.add(v);
//                    visited[v] = 1;
//                }
//            }
            for(int g : list.get(Vertex)){
                if(visited[g] != 1){
                    q.add(g);
                    visited[g] = 1;
                }
            }
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 정점의 개수
        N = sc.nextInt();
        // 간선의 개수
        M = sc.nextInt();
        // 시작 정점
        V = sc.nextInt();

        arr = new int[N][N];
        list = new ArrayList<>();

        visited = new int[N];

        // BFS 에서 사용할 큐
        q = new LinkedList<>();


        // 인접 행렬에 노드 정보 삽입
//        for(int i = 0; i < M; i++){
//            start = sc.nextInt();
//            destination = sc.nextInt();
//
//            arr[start-1][destination-1] = 1;
//            arr[destination-1][start-1] = 1;
//        }

        // 리스트 초기화
        for (int i = 0; i < N; i++) {
            list.add(new ArrayList<>());
        }

        // 인접 리스트 방식으로 정보 삽입
        for(int i = 0; i < M; i++){
            start = sc.nextInt();
            destination = sc.nextInt();

            list.get(start-1).add(destination-1);
            list.get(destination-1).add(start-1);
        }

        for(int i = 0; i < list.size(); i++){
            Collections.sort(list.get(i));
        }

//        System.out.println(list);


        DFS(V-1);

        // 방문 배열 초기화 해줘야함
        visited = new int[N];

        System.out.println();

        BFS(V-1);

    }
}
