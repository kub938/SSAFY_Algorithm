import java.io.*;
import java.util.*;

/**
 * 15:00 -
 * 아기상어 크기 2
 * 상하좌우 배열
 * 작으면 먹기 가능, 같으면 이동만 가능, 크면 이동X
 * <p>
 * 더 먹을 수 없으면 끝
 * 먹을 수 있는 물고기가 1마리면 그 물고기
 * 1마리보다 많으면 가장 가까운 물고기 먹으러감
 * <p>
 * 먹으면 자기 크기만큼 먹으면 1증가
 * <p>
 * n 20
 * 2억 연산
 * <p>
 * 1. 데이터를 입력받는다.
 * 배열 사이즈
 * 배열 모습 (상어의 위치 저장) (물고기의 위치를 리스트에 저장 우선순위큐)
 *
 * 2. 상어가 먹을 수 있는 크기의 물고기들과의 최단 거리를 구한다 (BFS)
 * 3. 먹을 수 있는 물고기가 없으면 현재 시간 리턴
 * 4. 먹을 수 있는 물고기 중 가장 가깝거나 위에 있거나 왼쪽에 있는 위치로 이동
 * 5. 이동 시간 만큼 시간에 더함
 */
/*
start:1500-1600

git commit -m "김성민 / 240903 / [BOJ_아기 상어_16236] / 112ms"
풀이 사항
풀이 일자: 2024.09.03
풀이 시간: 60분
채점 결과: 정답
예상 문제 유형: bfs,시뮬레이션

풀이방법
1. 데이터를 입력받는다.
 1-1 상어의 위치와 크기를 따로 전역변수로 저장한다.
2. 상어 현재 위치와 가장 가까우면서도 먹을 수 있는 물고기를 BFS로 탐색한다.
3. 물고기 들중 y가 가장 작고 x가 가장 작은 좌표로 상어를 이동시키고 시간을 더한다.
4. BFS를 성공할때마다 상어가 물고기 먹은 개수를 높이고, 개수에 따라 사이즈를 키운다.
5. BFS함수가 false를 리턴하면 더 이상 먹을게 없으니 현재 시간을 출력한다.


*/


public class Main {
    static int n;
    static int[][] map;
    static ArrayList<Fish> fishList;
    static Fish shark;
    static int levelUp = 0;
    static int time = 0;

    static class Fish {
        int y, x, size;

        public Fish(int y, int x, int size) {
            this.y = y;
            this.x = x;
            this.size = size;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        //데이터 입력 받는다.
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    shark = new Fish(i, j, 2);
                }

            }
        }
        /*
         * 2. 상어가 먹을 수 있는 크기의 물고기들과의 최단 거리를 구한다 (BFS)
         * 3. 먹을 수 있는 물고기가 없으면 현재 시간 리턴
         * 4. 먹을 수 있는 물고기 중 가장 가깝거나 위에 있거나 왼쪽에 있는 위치로 이동
         * 5. 이동 시간 만큼 시간에 더함
         */
        while(bfs()){
            levelUp++;

            if(levelUp == shark.size){
                shark.size++;
                levelUp = 0;
            }
        }

        System.out.println(time);
    }

    static int[][] dd = {{-1,0},{0,-1},{0,1},{1,0}};

    private static boolean bfs() {
        int[][] visited = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                visited[i][j] = Integer.MAX_VALUE;
            }
        }

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{shark.y, shark.x});
        visited[shark.y][shark.x] = 0;
        PriorityQueue<int[]> fishes = new PriorityQueue<>((a,b)->{
            if(a[0] == b[0]){
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });

        while (!q.isEmpty()) {
            for(int i=0,size=q.size();i<size;i++){
                int[] cur = q.poll();
                int y = cur[0];
                int x = cur[1];

                for(int[] d : dd) {
                    int hy = y + d[0];
                    int hx = x + d[1];

                    if(hy>=0 && hx>=0 && hy<n && hx<n && shark.size>=map[hy][hx] && visited[hy][hx]>visited[y][x]+1) {
                        visited[hy][hx] = visited[y][x] + 1;
                        if(map[hy][hx] < shark.size && map[hy][hx] != 0) {
                           fishes.add(new int[]{hy, hx});
                        }
                        q.add(new int[]{hy, hx});
                    }
                }
            }
            if(fishes.size()>0){
                //그중 y
                int[] ha = fishes.poll();
                map[shark.y][shark.x] = 0;
                shark.y = ha[0];
                shark.x = ha[1];
                map[shark.y][shark.x] = 9;
                time+= visited[shark.y][shark.x];
                return true;
            }
        }

        return false;
    }
}
