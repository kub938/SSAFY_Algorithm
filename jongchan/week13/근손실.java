import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    /**
     * start 09:39
     * end 09:52
     * 순열, np
     */

    private static int n;
    private static int k;
    private static int[] perm;
    private static int[] arr;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        perm = new int[n];
        arr = new int[n];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            perm[i] = i;
        }

        int cnt = 0;

        do {

            if (canExercise()) {
                cnt++;
            }

        } while (nextPermutation(perm));

        System.out.println(cnt);
    }

    private static boolean canExercise() {

        int s = 0;

        for (int i = 0; i < n; i++) {
            s += arr[perm[i]];
            s -= k;
            if (s < 0) {
                return false;
            }
        }

        return true;

    }


    private static boolean nextPermutation(int[] perm) {
        int i = n - 1;

        while (i > 0 && perm[i - 1] >= perm[i]) {
            i--;
        }
        if (i <= 0) {
            return false;
        }
        int j = n - 1;

        while (perm[j] <= perm[i - 1]) {
            j--;
        }

        int temp = perm[i - 1];
        perm[i - 1] = perm[j];
        perm[j] = temp;

        j = n - 1;
        while (i < j) {
            temp = perm[i];
            perm[i] = perm[j];
            perm[j] = temp;
            i++;
            j--;
        }
        return true;


    }

}
