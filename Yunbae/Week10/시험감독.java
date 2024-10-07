package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 시험감독 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        long cnt = 0;
        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            arr[i]-=b;
            cnt+=1;
            if(arr[i]>0) {
                cnt += arr[i] / c;
                arr[i] %= c;
                if (arr[i] > 0) {
                    cnt += 1;
                }
            }
        }
        System.out.println(cnt);
    }
}
