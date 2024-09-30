import java.util.*;
import java.io.*;

/**

 git commit -m "김성민 / 240930 / [BOJ_팰린드롬?_10942] / 800ms"
 *
 *
 * 특이사항
 * <p>
 * 0.5초 500만
 * 질문 개수가 100만
 * 수열크기가 2000이므로 일반적인 펠린드롬은 불가능
 * <p>
 * 처음에 입력받을때 모두 구한다
 * 개수가 1이야 그럼 무조건 펠린드롬
 * 개수가 2야 2개가 같아야 펠린드롬
 * 개수가 3이야 중앙은 의미가 없고 각 바깥이 같아야함
 * <p>
 * 약간 메모라이징 느낌으로 가면 될거 같음
 * 1 7 을 입력받으면
 * 중앙 4를 기준으로 펠린드롬을 갱신한다.
 * <p>
 * 11 12 13 14 --- 1 2000
 * 2000 +1999 + 1
 * 2001*1000/2 = 500 * 2000 = 1000000
 */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        int[][] isPelin = new int[n][n]; //0이면 방문안함 1이면 펠린드롬 -1 펠린드롬X

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;

            checkPelin(isPelin, arr, start, end);

            int result = isPelin[start][end];
            if (result < 0) result = 0;
            sb.append(result).append('\n');
        }
        System.out.println(sb);
    }
    private static int checkPelin(int[][] isPelin, int[] arr, int start, int end) {
        //isPelin이 이미 true이다 그럼 바로 리턴
        if (isPelin[start][end] != 0) return isPelin[start][end];

        if (start >= end) {
            return isPelin[start][end] = 1;
        }

        if (arr[start] == arr[end])
            isPelin[start][end] = checkPelin(isPelin, arr, start + 1, end - 1);
        else
            isPelin[start][end] = -1;

        return isPelin[start][end];

    }
}