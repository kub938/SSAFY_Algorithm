import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution{

    static StringTokenizer st;
    static int T;
    static int N;
    static int[][] board;
    static List<int[]> wormHoleList; // 웜홀 리스트
    static List<int[]> blackHoleList;
    
    // 상 하 좌 우
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());

        for (int tc = 1; tc <= T; tc++) {
            answer = Integer.MIN_VALUE;
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());

            board = new int[N][N];
            wormHoleList = new ArrayList<>(); // 웜홀 리스트 초기화
            blackHoleList = new ArrayList<>(); // 블랙홀 리스트 초기화

            for (int r = 0; r < N; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < N; c++) {
                    board[r][c] = Integer.parseInt(st.nextToken());
                    if (board[r][c] == -1) {
                        blackHoleList.add(new int[]{r, c});
                    } else if (board[r][c] >= 6 && board[r][c] <= 10) {
                        wormHoleList.add(new int[]{r, c, board[r][c]});
                    }
                }
            }

            // 0인 지점에서 상하좌우 핀볼 다 쏴봄
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (board[r][c] == 0) {
                        for (int i = 0; i < 4; i++) {
                            simulation(r, c, i);
                        }
                    }
                }
            }

            System.out.println("#" + tc + " " + answer);
        }
    }

    public static void simulation(int x, int y, int direction) {
        int nx = x;
        int ny = y;
        int score = 0;

        while (true) {
            nx += dx[direction];
            ny += dy[direction];

            if (isBlackHole(nx, ny)) break; // 블랙홀이면 종료
            if (nx == x && ny == y) break; // 초기 위치로 돌아오면 종료

            // 벽에 튕기는 경우
            if (ny < 0 || ny >= N || nx < 0 || nx >= N) {
                score++;
                direction = getNewDirectionAfterWall(nx, ny, direction);
                continue;
            }

            // 블록에 튕기는 경우
            if (board[nx][ny] >= 1 && board[nx][ny] <= 5) {
                score++;
                direction = getNewDirectionAfterBlock(board[nx][ny], direction);
                continue;
            }

            // 웜홀에 빠지는 경우
            if (board[nx][ny] >= 6 && board[nx][ny] <= 10) {
                int[] wormHole = findWormHole(board[nx][ny], nx, ny);
                nx = wormHole[0];
                ny = wormHole[1];
            }
        }

        answer = Math.max(answer, score);
    }

    private static boolean isBlackHole(int x, int y) {
        for (int[] blackHole : blackHoleList) {
            if (blackHole[0] == x && blackHole[1] == y) {
                return true;
            }
        }
        return false;
    }

    private static int getNewDirectionAfterWall(int x, int y, int direction) {
        if (y < 0) return 3; // 왼쪽 벽
        if (y >= N) return 2; // 오른쪽 벽
        if (x < 0) return 1; // 위쪽 벽
        if (x >= N) return 0; // 아래쪽 벽
        return direction;
    }

    private static int getNewDirectionAfterBlock(int blockType, int direction) {
        switch (blockType) {
            case 1:
                return (direction == 0) ? 1 : (direction == 1) ? 3 : (direction == 2) ? 0 : 2;
            case 2:
                return (direction == 0) ? 3 : (direction == 1) ? 0 : (direction == 2) ? 1 : 2;
            case 3:
                return (direction == 0) ? 2 : (direction == 1) ? 0 : (direction == 2) ? 3 : 1;
            case 4:
                return (direction == 0) ? 1 : (direction == 1) ? 2 : (direction == 2) ? 3 : 0;
            case 5:
                return (direction == 0) ? 1 : (direction == 1) ? 0 : (direction == 2) ? 3 : 2;
            default:
                return direction;
        }
    }

    private static int[] findWormHole(int wormHoleNumber, int x, int y) {
        for (int[] wormHole : wormHoleList) {
            if (wormHole[2] == wormHoleNumber && (x != wormHole[0] || y != wormHole[1])) {
                return new int[]{wormHole[0], wormHole[1]};
            }
        }
        return new int[]{x, y}; // Should never reach here
    }
}
