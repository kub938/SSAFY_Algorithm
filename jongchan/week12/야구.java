import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    /**
     * start: 15:03 
     * 완전 탐색
     * max를 구하는 거니까 백트래킹 안됨
     * 1. Permutation을 사용해서 순서를 정함 O(9!) = O(362,880)
     * 2. 이닝별로 진행  O(27)
     * 필요 변수: int[] perm, int max, int n, int[][] arr;
     * 
     * 느낀점: 1번 선수가 4번 고정인걸 늦게 봄 ㅎㅎ
     *
     */

    static int[] perm = {0, 1, 2, 3, 4, 5, 6, 7, 8};
    static int max;
    static int n;
    static int[][] arr;
    static int index;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());

        arr = new int[n][9];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        do {
            index = 0;
            int sum = 0;

            if (perm[3] == 0) {
                for (int i = 0; i < n; i++) {
                    sum += getResult(i);
                }
                max = Math.max(sum, max);
            }
        } while(nextPermutation(perm));
        System.out.println(max);
    }

    private static int getResult(int inning) {

        int outCount = 0;
        int score = 0;
        int[] base = new int[3]; //1루 , 2루, 3루

        while (outCount != 3) {

            switch(arr[inning][perm[index % 9]]) {

                case 0:
                    outCount++;
                    break;
                case 1:
                    if (base[2] == 1) {
                        score++;
                    }
                    for (int i = 2; i > 0; i--) {
                        base[i] = base[i-1];
                    }
                    base[0] = 1;
                    break;
                case 2:
                    if (base[2] == 1) {
                        score++;
                    }
                    if (base[1] == 1) {
                        score++;
                    }
                    base[2] = base[0];
                    base[1] = 1;
                    base[0] = 0;
                    break;
                case 3:
                    if (base[2] == 1) {
                        score++;
                    }
                    if (base[1] == 1) {
                        score++;
                    }
                    if (base[0] == 1) {
                        score++;
                    }

                    base[0] = 0;
                    base[1] = 0;
                    base[2] = 1;
                    break;
                case 4:
                    for (int i = 0; i < 3; i++) {
                        if (base[i] == 1) {
                            score++;
                        }
                    }
                    score++;
                    Arrays.fill(base,0 );
                    break;
            }
            index++;
        }
        return score;
    }


    private static boolean nextPermutation(int[] perm) {

        int i = 8;
        int j = 8;
        int k = 8;


        while (i > 0 && perm[i - 1] > perm[i]) {
            i--;
        }
        if (i == 0) {
            return false;
        }

        while (perm[i - 1] > perm[j]) {
            j--;
        }
        swap(perm, i - 1, j);


        while (i < k) {
            swap(perm, i, k);
            i++;
            k--;
        }
        return true;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
