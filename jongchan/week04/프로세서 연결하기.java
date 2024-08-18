import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Solution {

    static int[][] arr;
    static int n;
    static List<int[]> cores;
    static int result;
    static int maxCount;

    static int[][] delta = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};


    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            n = sc.nextInt();
            arr = new int[n][n];
            cores = new ArrayList<>();
            result = 0;
            maxCount = 0;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    arr[i][j] = sc.nextInt();
                    if (arr[i][j] == 1 && !isMargin(new int[]{i,j})) {
                        cores.add(new int[]{i, j});
                    }
                }
            }

            solve(0, 0, 0);

            System.out.println("#" + test_case + " " + result);

        }
    }

    private static void solve(int start, int sum, int count) {

        if (count > maxCount) {
            maxCount = count;
            result = sum;
        } else if(count == maxCount) {
            result = Math.min(result, sum);
        }

        for (int i = start; i < cores.size(); i++) {

            if (isMargin(cores.get(i))) {
                solve(i + 1, sum, count + 1);
                continue;
            }

            for (int d = 0; d < 4; d++) {
                if (deltaCheck(cores.get(i), delta[d])) {
                    int s = deltaChange(cores.get(i), delta[d], -1);
                    solve(i + 1, sum + s, count + 1);
                    deltaChange(cores.get(i), delta[d], 0);
                }
            }
            solve(i + 1, sum, count); // 선택하지 않은 경우

        }

    }

    private static boolean isMargin(int[] current) {
        return current[0] == 0 || current[0] == n - 1 || current[1] == 0 || current[1] == n - 1;
    }

    private static boolean deltaCheck(int[] current, int[] direction) {

        int nextX = current[0] + direction[0];
        int nextY = current[1] + direction[1];

        while (range(nextX, nextY)) {
            if (arr[nextX][nextY] != 0) {
                return false;
            }
            nextX = nextX + direction[0];
            nextY = nextY + direction[1];
        }
        return true;
    }

    private static int deltaChange(int[] current, int[] direction, int num) {
        int x = current[0] + direction[0];
        int y = current[1] + direction[1];
        int count = 0;

        while (range(x, y)) {
            arr[x][y] = num;
            x += direction[0];
            y += direction[1];
            count++;
        }
        return count;

    }


    private static boolean range(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

}
