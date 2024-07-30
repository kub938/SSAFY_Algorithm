import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class 연구소 {

    public static int N;
    public static int M;
    public static int[][] board;

    public static int[] dx = {1,-1,0,0};
    public static int[] dy = {0,0,1,-1};

    public static int[][] visited;

    public static List<List<Integer>> list;

    public static int max_value = Integer.MIN_VALUE;

    // 안전구역 설치 하는 경우의 수 따지는 DFS
    public static void DFS(int wall){
        if(wall == 3){
            BFS();
            return;
        }

        for(int r = 0; r < N; r++ ){
            for(int c = 0; c < M; c++ ){
                if(board[r][c] == 0){
                    board[r][c] = 1;
                    DFS(wall + 1);
                    board[r][c] = 0;
                }
            }
        }
    }

    public static void BFS() {
        Queue<int[]> q = new LinkedList<>();

        int[][] map = new int[N][M];

        for(int r = 0; r < N; r++ ){
            for(int c = 0; c < M; c++ ){
                map[r][c] = board[r][c];
                if(map[r][c] == 2){
                    q.add(new int[]{r,c});
                }
            }
        }

        while (!q.isEmpty()) {
            int[] temp = q.poll();
            int x = temp[0];
            int y = temp[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
                    if (map[nx][ny] == 0) {
                        map[nx][ny] = 2;
                        q.add(new int[]{nx, ny});
                    }
                }
            }
        }
        int count = 0;

        for(int r = 0; r < N; r++){
            for(int c = 0; c < M; c++){
                if(map[r][c] == 0){
                    count++;
                }
            }
        }

        // 최대값 초기화
        max_value = Math.max(max_value,count);

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        // 방문 여부 체크 dfs에서 쓸거임
        visited = new int[N][M];

        // 맵임 걍
        board = new int[N][M];

        // 맵 입력 받기
        for(int r = 0; r < N; r++){
            for(int c = 0; c < M; c++){
                board[r][c] = sc.nextInt();
            }
        }


        // 모든 0에 1을 3개 세워 보기. DFS
        // -> 전체 맵 반복하면서 0만나면 그거 기준으로 DFS탐색?
        // dfs안에 bfs가 있어야함
        // 그 맵을 복사해서 바이러스 퍼뜨리기 BFS
        // 최종 안전구역 개수 세서 최댓값과 비교. 검증

        DFS(0);

        System.out.println(max_value);



    }
}
