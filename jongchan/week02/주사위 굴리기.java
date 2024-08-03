import java.util.Scanner;

public class Main {

    static int n;
    static int m;
    static int k;

    static int[][] dice;
    static int[] point;
    static int[][] board;

    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};




    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        n = scanner.nextInt();
        m = scanner.nextInt();

        point = new int[2];

        point[0] = scanner.nextInt();
        point[1] = scanner.nextInt();

        k = scanner.nextInt();

        board = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                board[i][j] = scanner.nextInt();
            }
        }

        dice = new int[4][3];


        for (int i = 0; i < k; i++) {

            int dir = scanner.nextInt();

            if (roll(dir)) {
                System.out.println(dice[1][1]);
            }

        }


        scanner.close();

    }

    private static boolean roll(int dir) {

        int nextX = point[0] + dx[dir - 1];
        int nextY = point[1] + dy[dir - 1];

        if (!range(nextX, nextY)) {
            return false;
        }


        switch(dir) {
            case 1:
                int temp = dice[1][2];
                dice[1][2] = dice[1][1];
                dice[1][1] = dice[1][0];
                dice[1][0] = dice[3][1];
                dice[3][1] = temp;
                break;

            case 2:
                int temp1 = dice[1][0];
                dice[1][0] = dice[1][1];
                dice[1][1] = dice[1][2];
                dice[1][2] = dice[3][1];
                dice[3][1] = temp1;
                break;
            case 3:
                int temp2 = dice[0][1];
                dice[0][1] = dice[1][1];
                dice[1][1] = dice[2][1];
                dice[2][1] = dice[3][1];
                dice[3][1] = temp2;
                break;
            case 4:
                int temp3 = dice[3][1];
                dice[3][1] = dice[2][1];
                dice[2][1] = dice[1][1];
                dice[1][1] = dice[0][1];
                dice[0][1] = temp3;
                break;

        }

        if (board[nextX][nextY] != 0) {

            dice[3][1] = board[nextX][nextY];
            board[nextX][nextY] = 0;
        } else {
            board[nextX][nextY] = dice[3][1];
        }
        point = new int[] {nextX, nextY};


        return true;
    }

    private static boolean range(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }

}
