import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static int n;
    static int[][] arr;
    static int min = Integer.MAX_VALUE;


    public static void main(String[] args) {
        input();
        dfs(0, new ArrayList<>(), new ArrayList<>());
        System.out.println(min);
    }

    private static void input() {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = scanner.nextInt();
            }
        }
    }

    private static void dfs(int depth, List<Integer> team1, List<Integer> team2) {
        if (depth == n) {
            int sum1 = sum(team1);
            int sum2 = sum(team2);
            min = Math.min(min, Math.abs(sum1 - sum2));
            return;
        }

        List<Integer> newTeam1 = new ArrayList<>(team1);
        List<Integer> newTeam2 = new ArrayList<>(team2);

        newTeam1.add(depth);
        dfs(depth + 1, newTeam1, team2);
        newTeam2.add(depth);
        dfs(depth + 1, team1, newTeam2);

    }

    private static int sum(List<Integer> list) {
        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (i == j) {
                    continue;
                }
                sum += arr[list.get(i)][list.get(j)];
            }
        }
        return sum;
    }
}
