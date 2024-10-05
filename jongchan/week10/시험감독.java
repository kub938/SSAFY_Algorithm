import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            arr[i] -= b;
        }
        long s = n;

        for (int i = 0; i < n; i++) {
            if (arr[i] <= 0) {
                continue;
            }
            if (arr[i] % c == 0) {
                s += arr[i] / c;
            } else {
                s += arr[i] / c + 1;
            }
        }
        System.out.println(s);

    }
}