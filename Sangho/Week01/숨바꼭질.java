import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 숨바꼭질 {
    public static int x;
    public static int y;
    public static int[] visited = new int[100001];

    public static Queue<Integer> q;

    public static void BFS(int x){
        // 큐에 넣는거임
        q.add(x);

        // 방문처리
        visited[x] = 1;


        while (!q.isEmpty()){
            int temp = q.poll();

          // - + *2 에 대해 다 탐색
            for(int i = 0; i < 3; i++){
                int next;

                if(i == 0){
                    next = temp + 1;
                } else if (i == 1){
                    next = temp - 1;
                } else {
                    next = temp * 2;
                }

              // 동생 찾으면 거기까지간 거리 바로 출력
                if(next == y){
                    System.out.println(visited[temp]);
                    return;
                }

              // 동생 못찾았으면 큐에 넣으셈
                if (next >= 0 && next < visited.length && visited[next] == 0){
                    q.add(next);
                    // 현재 값에서 한번 더 이동한 수치 기록
                    visited[next] = visited[temp] + 1;
                }
            }
            
        }






    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        x = sc.nextInt();
        y = sc.nextInt();

        q = new LinkedList<>();

        if(x == y) {
            System.out.println(0);
        } else {
            BFS(x);
        }


    }
}
