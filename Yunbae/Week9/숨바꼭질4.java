package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class 숨바꼭질4 {
    //bfs 돌리면서 젤 먼저 도착하는걸로 설정
    //현재 위치 계속 체크, 입력
    //배열은 어떻게 선언하지?
    //배열 위치 = que 선언
    static int n, k;
    static int[] move = {-1, 1, 2};
    static int[] check = new int[100001];
    static Stack<Integer> result = new Stack<>();
    static int[] visited = new int[100001];
    public static boolean inRange(int x) {
        return 0 <= x && x < 100001;
    }

    public static void bfs() {
        Queue<Integer> que = new LinkedList<>();    //위치
        que.add(n);
        check[n] = -1;
        visited[n] = 1;

        while (!que.isEmpty()){
            int x = que.poll();
            int nx;

            for (int i = 0; i < 3; i++) {
                if (i == 2) {
                    nx = x * move[i];
                } else {
                    nx = x + move[i];
                }

                if(inRange(nx) && check[nx]==0 && visited[nx]==0) {
                    visited[nx] = 1;
                    check[nx] = x;
                    que.add(nx);
                    if (nx == k) {
                        return;
                    }
                }
            }
        }
    }

    public static void setResult(){
        while(check[k]!= -1){
            result.push(k);
            k = check[k];
        }
        result.push(n);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        bfs();
        setResult();

        System.out.println(result.size()-1);
        while(!result.isEmpty()){
            System.out.print(result.pop() + " ");
        }
    }
}
