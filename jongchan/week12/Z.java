/**
 * start 16:52
 * 예상 유형: 수학, 재귀
 * int형으로 계산 가능할 듯
 * 사분면을 구하는 방법으로 계속 들어가면 되지 않을까 싶음 ㅇ.ㅇ
 * r과 c에 해당하는 배열의 값은 정해져 있음
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n, r, c;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        solve((int) Math.pow(2, n), 0, 0);
        System.out.println(result);
    }


    static void solve(int size, int sr, int sc) {

        if (size == 1) {
            return;
        }

        int newSize = size / 2;
        
        // 2사분면
        if (r < sr + newSize && c < sc + newSize) {
            solve(newSize, sr, sc);
        }
        // 1사분면
        else if (r < sr + newSize && c >= sc + newSize) {
            result += newSize * newSize;
            solve(newSize, sr, sc + newSize);
        }
        // 3사분면
        else if (r >= sr + newSize && c < sc + newSize) {
            result += 2 * newSize * newSize;
            solve(newSize, sr + newSize, sc);
        }
        // 4사분면
        else {
            result += 3 * newSize * newSize;
            solve(newSize, sr + newSize, sc + newSize);
        }
    }
}
