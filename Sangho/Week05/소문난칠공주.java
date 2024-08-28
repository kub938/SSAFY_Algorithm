import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static StringTokenizer st;

    static char[][] map = new char[5][5];

    static boolean[] visited;
    static int[] selected;

    static int answer = 0;

    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int r = 0; r < 5; r++){
            String str = br.readLine();
            for(int c = 0; c < 5; c++){
                map[r][c] = str.charAt(c);
            }
        }

        selected = new int[25];

        DFS(0,0,0);

        // DFS로 깊이 7까지 탐색하셈
        // 탐색하면서, 이다솜파가 4명 이상인지 찾으셈
        // 근데 임도연파가 4명이 잡히면 이다솜파가 4명이 될 수가 없으니 더 탐색할 가치가 없잖아. stop
        // N이 작으니까 중복되는 탐색을 걱정하진 말자, 시작점만 고정하고
        // 백트래킹으로
        // 근데 탐색은 BFS로 할거고

        System.out.println(answer);

    }

    public static void DFS(int start, int depth, int Y){
        // 임도연파가 4명이 되면 더 이상 탐색할 가치가 없음
        if(Y > 3){
            return;
        }
        if(depth == 7){
            // 방문 여부 체크 함수
            // 이건 이제 BFS 탐색 할건데 그거에서 쓰는 방문 배열임
            visited = new boolean[7];
            BFS();
            return;
        }

        // 인접 여부를 무시하고 25개의 자리중에 7개를 select 이게 가능한 이유는
        // N이 작기 때문에
        for(int i = start; i < 25; i++){
            selected[depth] = i;
            if(map[i/5][i%5] == 'Y'){
                // 임도연파 개수 넘기는 차이가 있음
                DFS(i + 1,depth + 1, Y + 1);
            } else {
                DFS(i + 1, depth + 1, Y);
            }
        }


    }

    public static void BFS(){
        Queue<int[]> q = new LinkedList<>();

        q.add(new int[]{selected[0]/5,selected[0]%5});
        // selected 된 좌표 끼리 연결 되어있는지만 확인하면 되는데
        visited[0] = true;

        int count = 1;

        while (!q.isEmpty()){
            int[] temp = q.poll();
            int x = temp[0];
            int y = temp[1];

            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                int nextIndex = 5*nx + ny;

                if(nx >= 0 && ny >= 0 && nx < 5 && ny < 5){
                    for(int j = 0; j < 7; j++){
                        if(!visited[j] && selected[j] == nextIndex){
                            visited[j] = true;
                            q.add(new int[]{nx,ny});
                            count++;
                        }
                    }
                }
            }
        }

        if(count == 7){
            answer++;
        }




    }
}
