import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

class Shark {
    int speed, dir, size;

    public Shark(int speed, int dir, int size) {
        this.speed = speed;
        this.dir = dir;
        this.size = size;
    }
}

public class Main {
    static int row, col, m, score, kingPos;
    static int[][] delta = {{0, 0}, {-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    static ArrayList<int[]> sList = new ArrayList<>();
    static Shark[][] map;

    public static void fishing() {
        kingPos += 1;
        for (int i = 0; i < row; i++) {
            if (map[i][kingPos] != null) {
                score += map[i][kingPos].size;
                map[i][kingPos] = null;
                return;
            }
        }
    }
    
    public static void moveShark() {
        Shark[][] moveShark = new Shark[row][col];
        
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                int nr = r;
                int nc = c;
                if (map[r][c] != null) {
                    Shark a = map[r][c];
                    for (int k = 0; k < a.speed; k++) {
                        if (a.dir == 1 || a.dir == 2) {  //상 하 일때
                            if (nr == 0 && a.dir == 1 || nr == row - 1 && a.dir == 2) {
                                a.dir = a.dir == 1 ? 2 : 1;
                            }
                            nr = nr + delta[a.dir][0];
                        } else {          //좌, 우 일때
                            if (nc == 0 && a.dir == 4 || nc == col - 1 && a.dir == 3) {
                                a.dir = a.dir == 3 ? 4 : 3;
                            }
                            nc = nc + delta[a.dir][1];
                        }
                    }
                    
                    if (moveShark[nr][nc] != null) {
                        if (a.size > moveShark[nr][nc].size) {
                            moveShark[nr][nc] = a;
                        }
                    } else {
                        moveShark[nr][nc] = a;
                    }

                }

            }
        }
        map = moveShark;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new Shark[row][col];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int speed = Integer.parseInt(st.nextToken());   //속도
            int dir = Integer.parseInt(st.nextToken());   //이동 방향
            int size = Integer.parseInt(st.nextToken());   //크기  -> 크기가
            Shark shark = new Shark(speed, dir, size);
            map[r - 1][c - 1] = shark;
        }
        
        kingPos = -1;
        for (int i = 0; i < col; i++) {
            fishing();
            moveShark();
        }
        System.out.println(score);
    }
}

