import java.util.*;
import java.io.*;

/*
git commit -m "김성민 / 241021 / [BOJ_불!_4179] / 552ms"

### 풀이 사항
풀이 일자: 2024.10.21
풀이 시간: 30분
채점 결과: 정답
예상 문제 유형: bfs
시간: 552 ms
메모리: 54132 kb

### 풀이방법
1. 데이터를 입력받으면서 사람과 불을 큐에 넣는다.
2. bfs를 돌리면서 불은 4방향 사람은 그중 한방향으로 이동한다.
3. 끝에 도착하면 리턴

### 느낀점

 */

public class Main {
    //bfs
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        char[][] map = new char[r][c];

        int[] dy = {-1, 1, 0, 0};
        int[] dx = {0, 0, 1, -1};
        Deque<int[]> queue = new ArrayDeque<>();

        for (int i = 0; i < r; i++) {
            String s = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = s.charAt(j);

                if (map[i][j] == 'J') queue.addFirst(new int[]{i, j,1});
                else if (map[i][j] == 'F') queue.addLast(new int[]{i, j,0});
            }
        }

        boolean[][] visited = new boolean[r][c];
        int result = -1;
        int cnt = 0;

        loop:while (!queue.isEmpty()) {
            int size = queue.size();
            for(int a=0;a < size;a++){
                int[] node = queue.pollFirst();
                int y = node[0];
                int x = node[1];
                int who= node[2];

                if(who==1 && map[y][x] == 'F') continue;
                if(who==1 && (y==0 || y==r-1 || x==0 || x==c-1)){
                    cnt++;
                    result = 0;
                    break loop;
                }

                for(int i=0;i<4;i++){
                    int hy = y+dy[i];
                    int hx = x+dx[i];

                    if(hy>=0 && hy<r && hx>=0 && hx<c){
                        if(who == 0 && map[hy][hx] != 'F' && map[hy][hx]!='#'){
                            map[hy][hx] = 'F';
                            queue.addLast(new int[]{hy,hx,0});
                        }else{
                            if(!visited[hy][hx] && map[hy][hx] != 'F' && map[hy][hx]!='#'){
                                visited[hy][hx] = true;
                                queue.addLast(new int[]{hy,hx,1});
                            }
                        }
                    }
                }
            }
            cnt++;
        }

        if (result == -1) {
            sb.append("IMPOSSIBLE");
        } else {
            sb.append(cnt);
        }
        System.out.print(sb);
    }

}
