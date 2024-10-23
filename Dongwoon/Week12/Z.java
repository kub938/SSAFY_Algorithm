import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static StringTokenizer st;

	static long N, R, C, ans = 0;

	public static void main(String[] args) throws IOException {

	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    
	    N = Integer.parseInt(st.nextToken());
	    R = Integer.parseInt(st.nextToken());
	    C = Integer.parseInt(st.nextToken());
	    
	    z(N, R, C);
	    
	    System.out.println(ans);  // 결과 출력
	}

	private static void z(long N, long R, long C) {
		if(N == 0) {
			return;
		}
		
		long X = (long) Math.pow(2, N);
	//	System.out.print(X + " ");
		if(R < X / 2 && C < X / 2) { // 1번째
		} else if(R < X / 2 && C >= X / 2) { // 2번째
			C -= X / 2;
			ans += ((X/2) * (X/2));
		}else if(R >= X / 2 && C < X / 2) { // 3번쨰
			R -= X / 2;
			ans += ((X/2) * (X/2)) * 2;
		}else { // 4번째
			R -= X / 2;
			C -= X / 2;
			ans += ((X/2) * (X/2)) * 3;
		}
		
	//	System.out.println(ans);
		
		z(N-1, R, C);
	}
}
