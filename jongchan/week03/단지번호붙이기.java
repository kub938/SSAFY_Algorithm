import java.util.*;

public class Main {

    static List<Integer> list;
    static int[][] map;
    static boolean[][] visited;
    static int n;
    static int[][] move = {{-1, 0}, {1,0}, {0,1}, {0,-1}};

    public static void main(String[] args) {

        list = new ArrayList<>();
        input();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j]) {
                    continue;
                }
                visited[i][j] = true;
                if (map[i][j] == 1) {
                    list.add(dfs(new int[]{i,j}));
                }
            }
        }
        System.out.println(list.size());
        Collections.sort(list);

        for (Integer i : list) {
            System.out.println(i);
        }

    }

    private static void input() {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        map = new int[n][n];
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            String str = scanner.next();
            for (int j = 0; j < n; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

    }

    private static int dfs(int[] point) {
        int count = 1;

        for (int i = 0; i < 4; i++) {
            int nextX = point[0] + move[i][0];
            int nextY = point[1] + move[i][1];

            if (range(nextX, nextY) && !visited[nextX][nextY] && map[nextX][nextY] == 1) {
                visited[nextX][nextY] = true;
                count += dfs(new int[]{nextX, nextY});
            }
        }
        return count;

    }

    private static boolean range(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }


}
