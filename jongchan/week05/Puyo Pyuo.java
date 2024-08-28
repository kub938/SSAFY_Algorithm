import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

    static char[][] map = new char[12][6];
    static int[][] delta = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    static int count;


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 12; i++) {
            String s = br.readLine();
            for (int j = 0; j < 6; j++) {
                map[i][j] = s.charAt(j);
            }
        }
        while(check()) {
            count++;
        }

        System.out.println(count);

    }

    private static boolean bfs(int x, int y, char ch) {

        boolean[][] visited = new boolean[12][6];

        Queue<int[]> queue = new LinkedList<>();
        Queue<int[]> remove = new LinkedList<>();

        queue.add(new int[]{x, y});
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nextX = poll[0] + delta[d][0];
                int nextY = poll[1] + delta[d][1];

                if (isRange(nextX,nextY) && !visited[nextX][nextY] && map[nextX][nextY] == ch) {
                    queue.add(new int[] {nextX, nextY});
                    visited[nextX][nextY] = true;
                }
            }
            remove.add(poll);
        }

        if (remove.size() >= 4) {
            while (!remove.isEmpty()) {
                int[] poll = remove.poll();
                map[poll[0]][poll[1]] = '.';
            }
            return true;
        }
        return false;

    }

    private static boolean isRange(int x, int y) {
        return 0 <= x && x < 12 && 0 <= y && y < 6;
    }


    private static boolean check() {

        boolean result = false;

        for (int i = 11; i >= 0; i--) {
            for (int j = 0; j < 6; j++) {
                if (map[i][j] != '.') {
                    if (bfs(i, j, map[i][j])) {
                        result = true;
                    }

                }
            }
        }

        move();

        return result;
    }

    private static void move() {

        for (int j = 0; j < 6; j++) {
            List<Character> list = new ArrayList<>();
            for (int i = 11; i >= 0; i--) {
                if (map[i][j] != '.') {
                    list.add(map[i][j]);
                }
            }

            for (int i = 0; i < list.size(); i++) {
                map[11 - i][j] = list.get(i);
            }
            for (int i = list.size(); i < 12; i++) {
                map[11 - i][j] = '.';
            }
        }
    }

}
