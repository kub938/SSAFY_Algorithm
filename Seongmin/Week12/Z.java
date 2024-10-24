import java.util.*;
import java.io.*;

/*
git commit -m "김성민 / 241024 / [BOJ_Z_1074] / 104ms"

### 풀이 사항
풀이 일자: 2024.10.24
풀이 시간: 30분
채점 결과: 정답
예상 문제 유형: 재귀
시간: 104 ms
메모리: 14352 kb

### 풀이방법
1. 데이터를 입력받는다.
2. 예시를 바탕으로 패턴을 파악한다.
//35 3사분면 + 1사분면 + 4사분면
//32 + 0 + 3 = 35
// 40 -> 3사분면(32) + 3사분면(8) + 1사분면(0) = 40
3. 중앙R과 중앙C를 구하고 이를 각각 비교해보며 몇사분면인지 분류한다.
4. 사분면에 따라 크기에 맞는 값을 더해주고 다음 재귀를 호출한다.

### 느낀점
분명히 골드5였는데 다 푸니까 실버1이 되어있다.
 */


public class Main {
    static int n;
    static int r;
    static int c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        int num = (int) Math.pow(4,n-1);
        int result = recurr(n,0,0, num);
        sb.append(result);

        System.out.print(sb);
    }
    static int[] quadrant = {0,1,2,3};
    private static int recurr(int n, int sr, int sc, int num) {
        if(n==0) return 0;

        int size = 1<<n;
        int midR = sr+size/2;
        int midC = sc+size/2;

        int i = 0;
        int nr = sr;
        int nc = sc;
        if(midR>r && midC>c) { //1사분면
            i = 0;
        } else if (midR>r && midC<=c) {//2사분면
            i = 1;
            nc = midC;
        } else if (midR<=r && midC>c) {//3사분면
            i = 2;
            nr = midR;
        } else{//4사분면
            i = 3;
            nr = midR;
            nc = midC;
        }
        
        return num*quadrant[i] + recurr(n-1,nr,nc,num/4);
    }

}