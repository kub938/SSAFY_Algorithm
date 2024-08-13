package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class LinkStart {
    static StringTokenizer st;

    // 사람의 수
    static int N;

    // 점수를 담을 배열
    static int[][] arr = new int[20][20];

    // 점수 차이 담는 변수
    static int answer = Integer.MAX_VALUE;

    // 팀을 구분하기 위해 DFS로 조합 구할건데 그거 할 때 방문배열 써야되잖음
    static boolean[] visited = new boolean[20];

    public static void DFS(int start, int depth, int teamNum){
        // 팀이 짜여지면 점수 계산
        if(depth == teamNum){
            CaculateScore();
            // 이 순간에 visited 에 담기면 스타트팀임

            return;
            // 여기서 팀 점수 계산
        }

        for(int i = start; i < N; i++){
            if(!visited[i]){
                visited[i] = true;
                DFS(i + 1,depth + 1, teamNum);
                visited[i] = false;
            }
        }
    }

    public static void CaculateScore(){
        int redTeam = 0;
        int blueTeam = 0;
        int diff;

        // 얘 시간 초과 나나본데
        for(int r = 0; r < N; r++){
           for(int c = 0; c < N; c++){
               if(visited[r] && visited[c]){
                   redTeam += arr[r][c];
               } else if (!visited[r] && !visited[c]) {
                   blueTeam += arr[r][c];
               }
           }
        }

        diff = Math.abs(redTeam - blueTeam);

        if(diff < answer){
            answer = diff;
        }

    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 1. 팀 조합 i vs N - i nCi (i < n//2 + 1)

        // i == 팀의개수임
        for(int i = 1; i <= N/2; i++){
            DFS(0,0,i);
        }

        System.out.println(answer);

    }
}
