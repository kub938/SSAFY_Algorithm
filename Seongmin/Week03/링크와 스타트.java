import java.io.*;
import java.util.*;

/*
시작 시간: 1816 - 1902

git commit -m "김성민 / 240810 / [BOJ_링크와 스타트_15661] / 176ms"

풀이 사항
풀이 일자: 2024.08.10
풀이 시간: 45분
채점 결과: 정답
예상 문제 유형: 백트래킹

풀이방법)
1. 능력치 값을 모두 더한다. (link팀의 값으로 활용)
2. 백트래킹(조합)으로 경우의 수를 모두 탐색한다
    2.1 방문하지 않았으면 방문배열을 갱신한다.(true면 start팀)
    2.2 start팀의 값은 더하고 link팀은 값은 빼준다
    2.3 능력치 차이를 최솟값(min)과 비교해서 갱신한다.
3. min을 출력한다.

피드백)
조합이 아닌 순열 방식을 사용해서 시간초과가 발생하였다.
문제를 읽고 단순 백트래킹을 하는 것이 아닌 조합인지 순열인지를 잘 파악해야할 거 같다.
 */
public class Main {
    static int[][] arr;
    static int n;
    static boolean[] visited;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        visited = new boolean[n];
        //link의 능력치 합을 더할 변수
        int sum=0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                sum+= arr[i][j];
            }
        }
        //조합 백트래킹으로 경우의 수 모두 탐색
        back(0, 0,sum,0);

        System.out.println(min);
    }
    
    private static void back(int d, int start, int link, int num) {
        if(d>=n-1){
            return;
        }else{
            for (int i = num+1; i <n; i++) {
                if(!visited[i]){
                    visited[i] = true;
                    //방문배열을 탐색하면서 start팀에 능력치를 더하고 link팀은 빼준다.
                    int s = 0;
                    int l = 0;
                    for (int j = 0; j < n; j++) {
                        if(visited[j]){
                            s+= arr[i][j];
                            s+= arr[j][i];
                        }else{
                            l+= arr[i][j];
                            l+= arr[j][i];
                        }
                    }
                    //차이 최솟값 갱신
                    min = Math.min(min, Math.abs(start+s - (link-l)));
                    back(d+1, start+s,link-l,i);

                    visited[i] =false;
                }
            }
        }
    }
}
