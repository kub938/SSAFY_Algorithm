import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

//0816 1355 start
//0816 1430 end

/*
git commit -m "김성민 / 240816 / [SWEA_프로세서 연결하기_1767] / 1122ms"
풀이 사항
풀이 일자: 2024.08.16
풀이 시간: 40분
채점 결과: 정답
예상 문제 유형: 부분 집합

풀이 방법)
1.데이터 입력받기
2. 부분집합으로 코어 집합을 추출
    2-1. 추출한 집합에 대해서 각 값을 전선 방향에 대해서 DFS 실행
        2-1-0
        2-1-1. 코어 개수가 크면 coremax와 lineMin 갱신
        2-1-2. 코어 개수가 같다면 전선길이가 더 짧으면 lineMin 갱신

피드백)
다른 코드들을 보니 내 코드에 비해서 실행시간이 굉장히 짧게 나왔다.(1122 >>>>>> 250)
아예 부분집합으로 집합을 추출할때 선을 긋고 체크를 하면 시간을 확 줄일 수 있는 거 같다.
다음에는 이러한 부분도 신경쓰면서 시간을 최대한 짧게 해야할 거 같다.
또한 문제 풀기전 시간복잡도를 계산하는 것도 필요해보인다.
*/

/**
 * 4억,
 *
 * 가로세로 N사이즈
 * 가장자리 전원(이미 끝에 있으면 연결된거임)
 * 전선 교차X
 * core 최대한 많이 -> 전선 길이의 합 (단 여러방법이 있으면 최소값)
 *
 * 1.데이터 입력받기
 *  1.1 배열사이즈
 *  1.2 배열 정보
 * 2. 부분집합으로 코어 집합을 추출
 * 3. 집합에 대해서 각 값을 전선 방향에 대해서 DFS 실행
 * 4. 코어 개수가 크면 coremax와 lineMin 갱신
 * 5. 코어 개수가 같다면 전선길이가 더 짧으면 lineMin 갱신
 */

public class Solution {
    static int size;
    static int[][] arr;
    static int coreMax;
    static int lineMin;
    static boolean[] selected;
    static ArrayList<int[]> coreArr;
    //선택된 코어 리스트
    static ArrayList<int[]> selectedCore = new ArrayList<>();
    static int[][] dd = {{0,1},{0,-1},{1,0},{-1,0}};

     public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            sb.append("#" + tc + " ");
            size = Integer.parseInt(br.readLine().trim());
            arr = new int[size][size];
            coreMax = 0;
            lineMin = 0;

            coreArr = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                st = new StringTokenizer(br.readLine().trim());
                for (int j = 0; j < size; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                    if(arr[i][j]==1 && i!=0 && j!=0) coreArr.add(new int[]{i,j});
                }
            }
            selected = new boolean[coreArr.size()];
            coreBack(0);

            sb.append(lineMin+"\n");
        }
        System.out.print(sb);
    }


    //* 2. 부분집합으로 코어 집합을 추출
    private static void coreBack(int d) {
        if(d >= coreArr.size()){
            selectedCore.clear();
            for (int i = 0; i < d; i++) {
                if(selected[i])  selectedCore.add(coreArr.get(i));
            }
            drawLine(0,0);
            return;
        }
        coreBack(d+1);
        selected[d] = true;
        coreBack(d+1);
        selected[d] = false;
    }
    //     * 3. 집합에 대해서 각 값을 전선 방향에 대해서 또 되는 부분 집합을 뽑아낸다?
    //     * 4. 코어 개수가 크면 coremax와 lineMin 갱신
    //     * 5. 코어 개수가 같다면 전선길이가 더 짧으면 lineMin 갱신
    private static void drawLine(int d,int line) {
        if(d >= selectedCore.size()){
            if(coreMax<d){
                coreMax = d;
                lineMin = line;
            }else if(coreMax == d) lineMin = Math.min(lineMin,line);
            return;
        }

        int[] node = selectedCore.get(d);
        int y = node[0];
        int x = node[1];

        loop1:
        for(int[] dir: dd){
            int hy = y+dir[0];
            int hx = x+dir[1];

            boolean connect = true;
            int cnt = 0;
            
            //선긋기
            while(hy>= 0 && hy<size && hx>=0 && hx<size){
                if(arr[hy][hx] != 0){
                    connect = false;
                    break;
                }
                arr[hy][hx] = 2;
                cnt++;
                hy+= dir[0];
                hx+= dir[1];
            }
            if(connect) drawLine(d+1,line+cnt);
            hy-= dir[0];
            hx-= dir[1];

            //되돌리기
            while(hy != y || hx != x){
                if(arr[hy][hx]==2){
                    arr[hy][hx] = 0;
                    hy-= dir[0];
                    hx-= dir[1];
                }
            }

        }

    }
}