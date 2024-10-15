import java.util.*;
import java.io.*;

/*
git commit -m "김성민 / 241015 / [BOJ_2024 (easy)_] / 292ms"
### 풀이 사항
풀이 일자: 2024.10.15
풀이 시간: 1시간반
채점 결과: 정답
예상 문제 유형: 중복순열, 구현
시간: 292 ms
메모리: 35492 kb

### 풀이방법
1. 데이터를 입력받는다.
2. 중복 순열로 방향을 5개를 선정한다.
3. 방향에 따라 숫자를 합친다.
4. 한번 합쳐진 숫자는 다시 안합쳐지도록 추가적인 변수를 스택에 넣는다.
5. 배열에 최댓값을 리턴한다.

### 느낀점
하나의 메소드로 4 방향을 처리하도록 구현하느라 시간이 오래걸렸다.


특이사항)
한번 합쳐지면 두번 이상 합쳐지진 않는다
최대 5번 이동한다.

중복순열로 5번 방향 상우하좌

백트래킹
최대값 max<<5 가 구해지면 중복순열을 또 돌필요가 없다.
원소가 1개이면 더이상 구할필요가 없다.

1. 데이터 입력
2. 중복순열로 구하면서 새롭게 변경되는 배열을 전송

 */

public class Main {
    static int n = 4;
    static int[][] board;
    static int result;
    static int max;
    static int limit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        init(br);
        solve(0, max, board);


        sb.append(result);
        System.out.print(sb);
    }

    private static void solve(int d, int num, int[][] arr) {
        if (d >= 5 || num == limit) {
            result = Math.max(result, num);
            return;
        }

        for (int i = 0; i < 4; i++) { //0 상, 1하 , 2좌, 3우
            int[][] tmp = move(i, arr);
            solve(d + 1, max, tmp);
        }
    }

    //상 하 좌 우
    static int[] startR = {0, n - 1, 0, 0};
    static int[] endR = {n, -1, n, n};
    static int[] startC = {0, 0, n - 1, 0};
    static int[] endC = {n, n, -1, n};
    static int[] dirR = {1, -1, 1, 1};
    static int[] dirC = {1, 1, -1, 1};

    private static int[][] move(int dir, int[][] arr) {
        int[][] tmp = new int[n][n];

        for (int i = 0; i < n; i++) {
            moveOne(i, dir, arr, tmp);
        }

        return tmp;
    }

    private static void moveOne(int a, int dir, int[][] arr, int[][] tmp) {
        int sr = a;
        int er = a + 1;
        int dr = 1;

        int sc = startC[dir];
        int ec = endC[dir];
        int dc = dirC[dir];

        if (dir < 2) {
            sr = startR[dir];
            er = endR[dir];
            dr = dirR[dir];

            sc = a;
            ec = a + 1;
            dc = 1;
        }
        Stack<int[]> stack = new Stack<>();

        for (int i = sr; i != er; i += dr) {
            for (int j = sc; j != ec; j += dc) {
                if (arr[i][j] == 0) continue;
                if (stack.isEmpty()) stack.add(new int[]{arr[i][j], 0});
                else {
                    if (stack.peek()[1] == 0 && arr[i][j] == stack.peek()[0]) {
                        stack.add(new int[]{stack.pop()[0] * 2, 1});
                    } else {
                        stack.add(new int[]{arr[i][j], 0});
                    }
                }
            }
        }
        int num = 0;
        int m = 0;
        loop:
        for (int i = sr; i != er; i += dr) {
            for (int j = sc; j != ec; j += dc) {
                if (num == stack.size()) break loop;
                tmp[i][j] = stack.get(num++)[0];
                m=Math.max(m,tmp[i][j]);
            }
        }
        max = Math.max(max,m);
    }

    private static void init(BufferedReader br) throws IOException {
        n = Integer.parseInt(br.readLine());

        startR = new int[]{0, n - 1, 0, 0};
        endR = new int[]{n, -1, n, n};
        startC = new int[]{0, 0, n - 1, 0};
        endC = new int[]{n, n, -1, n};
        dirR = new int[]{1, -1, 1, 1};
        dirC = new int[]{1, 1, -1, 1};

        board = new int[n][n];
        result = 0;
        limit = 0;
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                max = Math.max(max, board[i][j]);
            }
        }
        limit = max << 5;
    }
}
