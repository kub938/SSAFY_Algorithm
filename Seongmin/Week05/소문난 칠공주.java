import java.io.*;
import java.util.*;
/*

git commit -m "김성민 / 240828 / [BOJ_소문난 칠공주_1941] / 324ms"
풀이 사항
풀이 일자: 2024.08.28
풀이 시간: 1시간 30분
채점 결과: 정답
예상 문제 유형: 조합, bfs

풀이방법
1. 데이터를 입력받는다.
2. 조합을 사용하여 좌표 7개를 선택한다
    2-1 원활한 조합을 위해 2차원좌표를 0-24로 표시
    2-2 중간에 Y의 개수가 4개를 넘어가면 백트래킹
3. 선택된 좌표가 인접해있는지 BFS를 사용하여 검사한다.

피드백)
처음 로직을 파악하기에 시간이 오래걸렸고 중간에 오타가 발생해서 디버깅하느라 시간이 너무 오래걸렸다.
*/


public class Main {
    static char[][] arr = new char[5][5];
    static int cnt = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < 5; i++) {
            arr[i] = br.readLine().toCharArray();
        }

        back(0, 0, 0);

        System.out.println(cnt);
    }

    static ArrayList<Integer> alist = new ArrayList<>();


    private static void back(int d, int ycnt, int start) {
        if (ycnt >= 4) return;
        if (d >= 7) {
            //인접확인
            if (check()) cnt++;
            return;
        }
        for (int i = start; i < 25; i++) {
            alist.add(i);
            int y = i / 5;
            int x = i % 5;
            int a = 0;
            if (arr[y][x] == 'Y') a++;
            back(d + 1, ycnt + a, i + 1);
            alist.remove(alist.size() - 1);
        }
    }

    static int[][] dir = {{1, 0}, {0, 1}, {0, -1}, {-1, 0}};

    //인접확인
    static boolean check() {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[7];
        visited[0] = true;
        q.add(alist.get(0));

        int count = 1;
        while (!q.isEmpty()) {
            int node = q.poll();
            int y = node / 5;
            int x = node % 5;

            for (int[] d : dir) {
                int hy = y + d[0];
                int hx = x + d[1];
                int num = hy * 5 + hx;

                int index = alist.indexOf(num);
                if (hy>=0 && hx>=0 && hy<5 && hx<5 && index >= 0 && !visited[index]) {
                    visited[index] = true;
                    count++;
                    q.add(num);
                }
            }
        }
        //if(alist.get(0)==5)System.out.println(count);

        if (count == 7) return true;
        else return false;
    }
}
