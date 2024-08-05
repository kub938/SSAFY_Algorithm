import java.io.*;
import java.util.*;

public class Main {
    static boolean isNotChange;
    static int n;
    static int small;
    static int big;
    static int sum;
    static int num;
    static int[][] arr;
    static int[][] visited;
    static int[][] dd = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int color;
    static Map<Integer,Integer> map = new HashMap<>();
    

    /*
        DFS나 BFS를 활용한 구현문제인듯

        Map없이 DFS함수를 빠져나오면서 sum/num을 넣는식으로 구현했었는데
        값이 일정하게 나오지 않는 문제가 발생함 (dfs특성 상 하나의 분기를 다 끝내고 다른 분기를 확인하기에 문제가 발생)

        Map을 활용하여 DFS가 모두 끝나고 값을 할당하여 해당 문제가 발생하지 않도록 해결 
    */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        //n*n
        //arr[r][n] = 인구수
        //diff>=L && diff<=R  -> true
        //열렸으면 총합/칸 ,bfs
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        small = Integer.parseInt(st.nextToken());
        big = Integer.parseInt(st.nextToken());

        arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //4면 방향에 따라 diff를 측정 , dfs
        int time = 0;

        //인구이동이 발생안할때까지 반복
        while (true) {
            isNotChange = true;
            visited= new int[n][n];
            color = 1;
            map = new HashMap<>();

            //색이 안칠해져있으면 순회하며 색칠
            //색에 따라서 동일한 값 할당
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    sum = 0;
                    num = 0;
                    if(visited[i][j]==0){
                        dfs(i, j,color);
                        map.put(color++,sum/num);
                    }
                    arr[i][j] = map.get(visited[i][j]);
                }
            }
            if (isNotChange) break;
            time++;
        }
        //걸린 시간 리턴
        System.out.println(time);
    }
    //DFS로 순회해서 같은 색으로 색칠
    private static void dfs(int y, int x, int a) {
        sum += arr[y][x];
        num++;
        visited[y][x] = a;

        int value = arr[y][x];

        for (int[] d : dd) {
            int hy = y + d[0];
            int hx = x + d[1];

            if (hy >= 0 && hy < n && hx >= 0 && hx < n && visited[hy][hx]== 0) {
                int diff = Math.abs(value-arr[hy][hx]);

                if(diff>=small && diff<=big){
                    isNotChange = false;
                    dfs(hy,hx,a);
                }
            }
        }
    }
}