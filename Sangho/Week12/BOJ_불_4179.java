import java.util.*;
import java.io.*;

public class Main {

    static StringTokenizer st;

    static int R,C;

    static char[][] map;

    static Queue<int[]> peopleQ;
    static Queue<int[]> fireQ;

    static boolean[][] peopleVisited;
    static boolean[][] fireVisited;

    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};

    static int distance;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];

        peopleQ = new LinkedList<>();
        fireQ = new LinkedList<>();

        peopleVisited = new boolean[R][C];
        fireVisited = new boolean[R][C];

        for(int r = 0; r < R; r++){
            String line = br.readLine();
            for(int c = 0; c < C; c++){
                map[r][c] = line.charAt(c);
                if(map[r][c] == 'J'){
                    peopleQ.add(new int[]{r,c,0});
                    peopleVisited[r][c] = true;
                    map[r][c] = '.';
                } else if (map[r][c] == 'F'){
                    fireQ.add(new int[]{r,c});
                    fireVisited[r][c] = true;
                }
            }
        }
        distance = BFS();

        if(distance == -1) {
            System.out.println("IMPOSSIBLE");
        } else {
            System.out.println(distance);
        }
    }

    public static int BFS() {

        while (!peopleQ.isEmpty()) {

            int fireSize = fireQ.size();

            for(int i = 0; i < fireSize; i++) {
                int[] tempFire = fireQ.poll();
                int fx = tempFire[0];
                int fy = tempFire[1];

                for(int d = 0; d < 4; d++) {
                    int nx = fx + dx[d];
                    int ny = fy + dy[d];

                    if(nx < 0 || ny < 0 || nx >= R || ny >= C) continue;

                    if(fireVisited[nx][ny] || map[nx][ny] == '#') continue;

                    fireVisited[nx][ny] = true;

                    fireQ.add(new int[]{nx, ny});
                }
            }

            int peopleSize = peopleQ.size();

            for(int i = 0; i < peopleSize; i++) {
                int[] tempPeople = peopleQ.poll();
                int px = tempPeople[0];
                int py = tempPeople[1];
                int pd = tempPeople[2];

                if(px == 0 || py == 0 || px == R-1 || py == C-1) return pd + 1;

                for(int d = 0; d < 4; d++){
                    int nx = px + dx[d];
                    int ny = py + dy[d];

                    // 이건 필요없긴 할듯
                    if(nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
                    if(map[nx][ny] == '#' || fireVisited[nx][ny] || peopleVisited[nx][ny]) continue;

                    if(map[nx][ny] == '.') {
                        peopleVisited[nx][ny] = true;
                        peopleQ.add(new int[]{nx, ny, pd + 1});
                    }
                }
            }
        }
        return -1;
    }
}
