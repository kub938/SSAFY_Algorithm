import java.util.*;
import java.io.*;

/*
git commit -m "김성민 / 241024 / [CT_정육면체 한번 더 굴리기] / 123ms"

### 풀이 사항
풀이 일자: 2024.10.24
풀이 시간: 40분
채점 결과: 정답
예상 문제 유형: 구현, bfs
시간: 123 ms
메모리: 10 MB

### 풀이방법
1. 데이터를 입력받는다.
2. 미리 칸에 도착했을때 얻을 수 있는 점수를 계산한다.(인접한 칸을 찾는 BFS + 인접한 칸에 값을 집어넣는 BFS)
3. 주사위 아래 남쪽 동쪽을 배열로 관리한다. (클래스로 빼는게 더 가독성이 좋을 듯)
4. m번 만큼 주사위를 움직이고 점수를 더한다.

### 느낀점
주사위를 굴릴 때마다 점수를 얻기 위해 bfs를 돌리는건 손해라고 생각하여 미리 bfs를 돌아서 전부 구해서 사용했는데
오히려 속도가 차이가 나지 않았고 오히려 더 느리기도 하였다. 왜 그런지 곰곰히 생각해봐야할거 같다.



특이사항)
주사위 (아래 남 동)을 3개 관리 (위 북 서)는 7에서 각각을 뺀것

처음 시작이 (0,0) dir = 0; {0,1,2,3}우 하 좌 상 으로 구현한다.

현재 아래칸 값이 바닥 칸보다 크다면 dir+= 1 하고 %4를 한다.

hy hx가 0보다 작거나 n보다 크거나 같으면 dir+2 %4를 조져주고 y-dy x-dx로 바꿔준다.

int[] dice = [3] 아래 남 동
dy 우 하 좌 상
dx

함수)
check(y,x,n)
m번 만큼 반복(for)
    방향에 맞춰서 이동
    방향 설정
    점수 계산

 */

public class Main {

    static int[] dy = {0,1,0,-1}; // 우 하 좌 상
    static int[] dx = {1,0,-1,0}; // 우 하 좌 상
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int[][] score = getScore(map,n);
        int[] dice = {6,2,3};//아래 남 동
        int dir = 0;
        int y = 0;
        int x = 0;

        int result = 0;

        for (int i = 0; i < m; i++) {
            //move
            int hy = y+dy[dir];
            int hx = x+dx[dir];
            //dir set
            if(check(hy,hx,n)){
                y = hy;
                x = hx;
            }else{
                y -= dy[dir];
                x -= dx[dir];

                dir = (dir+2)%4;
            }
            //roll
            roll(dice, dir);
            if(dice[0]>map[y][x]){
                dir= (dir+1)%4;
            } else if (dice[0] < map[y][x]) {
                dir = (dir+3)%4;
            }

            //score
            result += score[y][x];
        }

        sb.append(result);
        System.out.print(sb);
    }

    private static void roll(int[] dice, int dir) {//// 우 하 좌 상
        int tmp;
        if(dir==0){
            //아래 서 위 동
            tmp = 7-dice[0];
            dice[0] = dice[2];
            dice[2] = tmp;
        }else if(dir == 1){
            tmp = 7-dice[0];
            dice[0] = dice[1];
            dice[1] = tmp;
        }else if(dir == 2){
            tmp = 7-dice[2];
            dice[2] = dice[0];
            dice[0] = tmp;
        }else{
            tmp = 7-dice[1];
            dice[1] = dice[0];
            dice[0] = tmp;
        }
    }

    private static int[][] getScore(int[][] map, int n) {
        int[][] arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(arr[i][j] != 0) continue;
                bfs(map, arr, n, i, j);
            }
        }

        return arr;
    }

    private static void bfs(int[][] map, int[][] arr, int n, int i, int j) {
        boolean[][] visited = new boolean[n][n];
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] {i,j});

        int sum = 0;
        int num = map[i][j];

        while(!queue.isEmpty()){
            int[] node = queue.poll();

            int y = node[0];
            int x = node[1];
            if(visited[y][x]) continue;
            visited[y][x] = true;
            sum+= map[y][x];
            for (int k = 0; k < 4; k++) {
                int hy = y+dy[k];
                int hx = x+dx[k];

                if(check(hy,hx,n) && map[hy][hx] == num && !visited[hy][hx] ){
                    queue.add(new int[] {hy,hx});
                }
            }
        }

        queue.add(new int[] {i,j});

        while(!queue.isEmpty()){
            int[] node = queue.poll();

            int y = node[0];
            int x = node[1];

            visited[y][x] = false;
            arr[y][x] = sum;
            for (int k = 0; k < 4; k++) {
                int hy = y+dy[k];
                int hx = x+dx[k];

                if(check(hy,hx,n) && visited[hy][hx] ){
                    queue.add(new int[] {hy,hx});
                }
            }
        }
    }

    public static boolean check(int y, int x, int n){
        return y>=0 && x>=0 && y<n && x<n;
    }
}