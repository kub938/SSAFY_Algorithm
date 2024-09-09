import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 벽돌_깨기 {
    static int T, n, w, h, cntBrick, result;
    static int[][] board;
    static ArrayList<Integer> tmp;
    static ArrayList<int[]> permArr;
    static int[] selectCol, permTmp;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void perm(int depth) {
        if (depth == n) {
            permArr.add(permTmp.clone());
            return;
        }

        for (int i = 0; i < w; i++) {
            permTmp[depth] = selectCol[i];
            perm(depth + 1);
        }
    }

    public static boolean inRange(int x, int y) {
        return 0 <= x && x < h && 0 <= y && y < w;
    }

    public static void boom(int X, int Y, int targetNum) {

        for (int i = 0; i < 4; i++) {
            board[X][Y] = 0;
            int nx = X;
            int ny = Y;
            for (int j = 0; j < targetNum - 1; j++) {
                nx += dx[i];
                ny += dy[i];
                if (inRange(nx, ny)) {
                    if (board[nx][ny] > 1) {
                        boom(nx, ny, board[nx][ny]);
                    }
                    board[nx][ny] = 0;
                } else break;
            }
        }
    }

    public static void sortBoard() {
        for (int i = 0; i < w; i++) {
            tmp = new ArrayList<>();
            int[] tmp = new int[h];
            int s = 0;
            for (int j = h - 1; j > -1; j--) {
                if (board[j][i] != 0) {
                    tmp[s] = board[j][i];
                    s++;
                }
            }
            for (int j = 0; j < h; j++) {
                board[h - j - 1][i] = tmp[j];
            }
        }
    }

    public static void countBrick() {
        cntBrick = 0;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (board[i][j] != 0) {
                    cntBrick += 1;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());

        for (int t = 1; t < T + 1; t++) {

            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            board = new int[h][w];
            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            selectCol = new int[w];
            for (int i = 0; i < w; i++) {
                selectCol[i] = i;
            }

          
            permTmp = new int[n];
            permArr = new ArrayList<>();
            int[][] resetBoard = new int[h][w];

            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    resetBoard[i][j] = board[i][j];
                }
            }
            perm(0);

            //ex) [0,0,1,1,3] => x가 0일때 각각의 공의 y 좌표값
            result = Integer.MAX_VALUE;
            for (int i = 0; i < permArr.size(); i++) {      //중복순열로 선택한 n개의 숫자공을 하나씩 떨궈봄
//                if (permArr.get(i)[0] == 2 && permArr.get(i)[1] == 2 && permArr.get(i)[2] == 6) {     //테케 정답 케이스 디버깅
                    for (int j = 0; j < n; j++) {    //젤 위 숫자부터 시작
                        int y = permArr.get(i)[j];
                        for (int k = 0; k < h; k++) {
                            if (board[k][y] > 0) {
                                boom(k, y, board[k][y]);
                                break;
                            }
                        }
                        sortBoard();
                    }
                    countBrick();
                    if (cntBrick < result) {
                        result = cntBrick;
                    }
//                }
                for (int j = 0; j < h; j++) {
                    for (int k = 0; k < w; k++) {
                        board[j][k] = resetBoard[j][k];
                    }
                }
            }
            System.out.println("#"+t+" "+result);
        }
    }
}
