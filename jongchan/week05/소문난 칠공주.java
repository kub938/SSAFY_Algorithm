import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static char[][] map = new char[5][5];
    static int count;
    static int result = 0;

    static int[] combination = new int[7];
    static boolean[] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 5; i++) {
            String str = br.readLine();

            for (int j = 0; j < 5; j++) {
                map[i][j] = str.charAt(j);
            }

        }

        dfs(0, 0);
        System.out.println(result);
    }

    static void dfs(int start, int depth) {

        if (depth == 7) {
            count = 1;
            visited = new boolean[7];
            visited[0] = true;

            int countS = 0;

            for (int i = 0; i < 7; i++) {
                if (map[combination[i] / 5][combination[i] % 5] == 'S') {
                    countS++;
                }
            }

            if (countS < 4) {
                return;
            }

            dfs(0);

            if (count == 7) {
                result++;
            }

            return;
        }

        for (int i = start; i < 25; i++) {
            combination[depth] = i;
            dfs(i + 1, depth + 1);
        }


    }


    static void dfs(int current) {

        for (int i = 0; i < 7; i++) {

            if (visited[i]) {
                continue;
            }

            if (isAdjacent(current, i)) {
                visited[i] = true;
                count++;
                dfs(i);
            }
        }


    }

    static boolean isAdjacent(int p1, int p2) {
        p1 = combination[p1];
        p2 = combination[p2];

        int x1 = p1 / 5;
        int y1 = p1 % 5;

        int x2 = p2 / 5;
        int y2 = p2 % 5;

        return Math.abs(x1 - x2) + Math.abs(y1 - y2) == 1;
    }


}
