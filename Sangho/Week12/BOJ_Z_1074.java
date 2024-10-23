import java.util.*;
import java.io.*;

public class Main {

    static int N, r, c;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        
        answer = 0;
        zOrder(N, r, c);
        System.out.println(answer);
    }

    static void zOrder(int n, int row, int col) {
        if (n == 0) return;

        int half = (int)Math.pow(2,n-1);

        if (row < half && col < half) {
            zOrder(n - 1, row, col); // 1st quadrant
        } else if (row < half && col >= half) {
            answer += half * half; // 1st quadrant count
            zOrder(n - 1, row, col - half); // 2nd quadrant
        } else if (row >= half && col < half) {
            answer += 2 * half * half; // 1st + 2nd quadrant count
            zOrder(n - 1, row - half, col); // 3rd quadrant
        } else {
            answer += 3 * half * half; // 1st + 2nd + 3rd quadrant count
            zOrder(n - 1, row - half, col - half); // 4th quadrant
        }
    }
}
