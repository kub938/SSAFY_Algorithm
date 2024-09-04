import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 아기_상어 {
    static int n;
    static int[][] board;
    static int nowSize;  //지금 크기
    static int eatCnt;   //먹은만큼
    static int[] babyShark;
    static int[][] moveDis;
    static int[] dx = {-1,0 ,0, 1};
    static int[] dy = {0, -1, 1, 0};
    static int result = 0;
    static int beforeX;
    static int beforeY;
    static ArrayList<Integer> resultArr;


    public static boolean inRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    public static void bfs(int X, int Y) {
        int[][] visited = new int[n][n];
        Queue<int[]> que = new LinkedList<>();
        que.add(new int[]{X, Y});
        visited[X][Y] = 1;

        while (!que.isEmpty()) {
            int size = que.size();
            ArrayList<int[]> sameTime = new ArrayList<>();
            for (int s = 0; s < size; s++) {
                int[] coord = que.poll();
                int x = coord[0];
                int y = coord[1];
                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    if (inRange(nx, ny) && visited[nx][ny] == 0) {
                        if (board[nx][ny] == 0 || board[nx][ny] == nowSize) {
                            moveDis[nx][ny] = moveDis[x][y] + 1;
                            visited[nx][ny] = 1;
                            que.add(new int[]{nx, ny});
                        } else if (board[nx][ny] < nowSize) {
                            moveDis[nx][ny] = moveDis[x][y] + 1;
                            resultArr.add(moveDis[nx][ny]);
                            sameTime.add(new int[]{nx, ny,moveDis[nx][ny]});  //제일 처음 먹을 수 있는걸 만나는 애가 가장 빠른건데 같은 사이클에서 같이 먹는 경우의수 생각
                        }
                    }
                }
            }
            if (!sameTime.isEmpty()) {   //
                sameTime.sort((o1, o2) -> {
                    if (o1[0] != o2[0]) {
                        return Integer.compare(o1[0], o2[0]);
                    } else {
                        return Integer.compare(o1[1], o2[1]);
                    }
                });         //첫번째 기준으로 정렬, 같으면 두번째 기준으로 정렬해서 0번 인덱스인걸로 bfs 다시 돌린다, 다시 돌리고 이 케이스는 이제 필요없으니 그냥 return;
                int sx = sameTime.get(0)[0];
                int sy = sameTime.get(0)[1];
                result = sameTime.get(0)[2];
                board[sx][sy] = 0;
                eatCnt += 1;
                if (eatCnt == nowSize) {
                    nowSize += 1;
                    eatCnt = 0;
                }
                bfs(sameTime.get(0)[0], sameTime.get(0)[1]);      //bfs 다시 보냄 다시보내면 visited 자동 초기화
                return;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
        board = new int[n][n];
        babyShark = new int[2];
        resultArr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 9) {
                    babyShark[0] = i;
                    babyShark[1] = j;
                    board[i][j] = 0;
                }
            }
        }
        moveDis = new int[n][n];      
        nowSize = 2;
        eatCnt = 0;
        bfs(babyShark[0], babyShark[1]);

        System.out.println(result);

    }
}
