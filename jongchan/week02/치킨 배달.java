import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class House {
        int x;
        int y;
        int count;

        House(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }


    private static int m;
    private static int min;

    private static List<int[]> chicken;
    private static List<House> houses;


    public static void main(String[] args) throws IOException {
        input();
        dfs(0, new ArrayList<>(), 0);
        System.out.println(min);
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        min = Integer.MAX_VALUE;

        int[][] board = new int[n][n];

        chicken = new ArrayList<>();
        houses = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 1) {
                    houses.add(new House(i, j, 0));
                } else if (board[i][j] == 2) {
                    chicken.add(new int[]{i, j});
                }
            }
        }
    }

    private static void dfs(int depth, List<int[]> list, int count) {

        if (count >= m) {
            int sum = 0;

            for (House house : houses) {
                int m = Integer.MAX_VALUE;

                for (int[] chi : list) {
                    m = Math.min(m, Math.abs(chi[0] - house.x) + Math.abs(chi[1] - house.y));
                }
                sum += m;
            }
            min = Math.min(sum, min);
            return;
        }

        if (depth >= chicken.size()) {
            return;
        }

        dfs(depth + 1, list, count);

        List<int[]> newList = new ArrayList<>(list);
        newList.add(chicken.get(depth));

        dfs(depth + 1, newList, count + 1);

    }
}
