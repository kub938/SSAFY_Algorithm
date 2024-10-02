import java.util.*;
import java.io.*;

/*
git commit -m "김성민 / 241002 / [BOJ_탑 보기_22866] / 2684ms"
1시간
10만
숫자 10만
무조건 뒤에 있는게 커야함 같아도 가려짐

완탐)
건물을 기준으로 양옆으로 while문을 실행 자기보다 n*n

dp)
왼쪽 ->
3 7 1 6 3 5 1 7

1 3-> 7 -> 1
2 7-> X -> 0
3 1-> 6,7-> 2
4 6-> 7
5 3-> 5 7
6 5-> 7
7 1-> 7
8 7-> X

max 값이면 그냥 0
1의 값보다 2의 값이 크거나 같으면 영향을 줌 (-1)
1의 값보다 2의 값이 작으면 영향 안줌 다시 구해야함

좌쪽 기준으로 구하고 우측 기준으로 구하고 이를 더함 and 가장 가까운 번호 출력
 */

public class Main {
    static int[] arr;
    static int n;
    static int[] index;
    static int[] left;
    static int[] right;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        int max = 0;

        arr = new int[n];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max,arr[i]);
        }
        //가장 가까운 건물 번호
        index = new int[n];
        Arrays.fill(index,Integer.MAX_VALUE);
        //canSee
        left = new int[n];
        right = new int[n];

        //왼쪽에서 우측으로
        for (int i = 0; i < n; i++) {
            left[i] = check(i, max,n,1, left);
            right[i] = check(i, max,-1,-1, right);
        }


        for (int i = 0; i < n; i++) {
            int result = (left[i] + right[i]);
            sb.append(result).append(" ");
            if(result != 0){
                sb.append(index[i]);
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }

    private static int check(int build, int max, int end, int d, int[] dir) {
        if(max == arr[build]) return 0;
        if(dir[build] != 0){
            return dir[build];
        }
        //다음으로 큰 숫자를 찾고 리턴
        for (int i = build+d; i != end; i+=d) {

            if(arr[build]< arr[i]){
                int diff1 = Math.abs(build - (index[build]-1));
                int diff2 = Math.abs(build - i);

                if(diff1 == diff2){
                    index[build] = Math.min(i+1,index[build]);
                }else if(diff1 > diff2){
                    index[build] = i+1;
                }

                return dir[build] = check(i,max, end,d,dir)+1;
            }
        }
        //없으면 그냥 리턴
        return 0;
    }
}