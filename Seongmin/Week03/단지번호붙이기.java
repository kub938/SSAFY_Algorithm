import java.io.*;
import java.util.*;

//0808 1713 start
//0808 1725 end

/*
git commit -m "김성민 / 240808 / [BOJ_단지번호붙이기_2667] / 136ms"
풀이 사항
풀이 일자: 2024.08.08
풀이 시간: 12분
채점 결과: 정답
예상 문제 유형: DFS, BFS

풀이방법)
한번 풀었던 문제라 빠르게 로직을 파악했다.

1.맵 정보를 배열에 저장한다.
2.맵을 탐색하면서 값이 '1'이라면 해당 위치를 기반으로 dfs수행
    2-1 DFS를 수행하면서 한번 탐색한 노드가 재탐색되면 안되도록 '0'으로 변경
3. DFS가 수행된 숫자만큼 리스트에 넣음
4. 정렬 후 답 출력

*/
public class Main {
    static char[][] arr;
    static int n;
    static int[][] dd = {{0,-1},{0,1},{-1,0},{1,0}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        //입력 받는다.
        //반복문을돌면서 1이면 dfs나 bfs를 돌면서 0으로 바꿔주고 개수를 Arraylist에 넣는다,
        //정렬하고 출력한다.
        n = Integer.parseInt(br.readLine());
        arr = new char[n][n];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < n; j++) {
                arr[i][j] = s.charAt(j);
            }
        }
        ArrayList<Integer> alist = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(arr[i][j]=='1'){
                   alist.add(dfs(i,j));
                }
            }
        }
        alist.sort((o1,o2)->o1.compareTo(o2));
        System.out.println(alist.size());
        for (int i : alist){
            System.out.println(i);
        }
    }

    private static int dfs(int y, int x) {
        int cnt = 0;
        arr[y][x]='0';
        for (int[] d : dd){
            int hy = y+d[0];
            int hx = x+d[1];

            if(hy>=0 && hy<n && hx>=0 && hx<n && arr[hy][hx]=='1'){
                cnt+=dfs(hy,hx);
            }
        }

        return cnt+1;
    }
}

