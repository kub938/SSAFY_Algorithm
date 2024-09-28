package boj.gold.b13913;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * start 14:02
 * 수빈이가 동생을 찾아야 함
 * x-1, x+1, 2 * x로 이동 가능
 * 가장 빠른 시간이 몇 초 후인지? -> BFS
 * 시간이 얼마나 걸리는지 출력, 어떻게 이동해야 하는지 출력
 * 필요 클래스 Point {Integer before, int time}
 * 필요 변수 Point[] points, int n, int k, LinkedList<Integer>
 * 필요 메서드 isRange()
 */

public class Main {

    static class Point {
        Integer before;
        int num;

        public Point(int num, Integer before) {
            this.num = num;
            this.before = before;
        }
    }

    static int n;
    static int k;
    static List<Integer> result = new LinkedList<>();
    static Point[] points = new Point[100_001];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        if (n >= k) {
            System.out.println(n - k);
            for (int i = n; i >=k; i--) {
                System.out.print(i + " ");
            }
            return;
        }

        bfs();
        setResult();
        printResult();
    }

    private static void bfs() {

        points[n] = new Point(n, null);
        Queue<Point> queue = new LinkedList<>();
        queue.offer(points[n]);

        int[] add = {-1, 1, 0};
        int[] multi = {1, 1, 2};

        while (!queue.isEmpty()) {

            Point point = queue.poll();
            if (point.num == k) {
                break;
            }
            for (int i = 0; i < 3; i++) {
                int next = point.num * multi[i] + add[i];
                if (isRange(next) && points[next] == null) {
                    points[next] = new Point(next, point.num);
                    queue.offer(points[next]);
                }
            }
        }
    }

    private static boolean isRange(int n) {
        return 0 <= n && n <= 100_000;
    }

    private static void setResult() {
        Point current = points[k];
        while (current.before != null) {
            result.add(0, current.num);
            current = points[current.before];
        }
        result.add(0, n);
    }

    private static void printResult() {
        System.out.println(result.size() - 1);

        for (Integer integer : result) {
            System.out.print(integer + " ");
        }

    }
}
