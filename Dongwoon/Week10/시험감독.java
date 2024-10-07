import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static int N, B, C;
	public static long ans = 0;
	public static int[] A;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 1. 입력받기
		N = Integer.parseInt(br.readLine());
		A = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i =0;i<N;i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		// 2. 감독관 수 세기
		for(int i =0;i<N;i++) {
			int result = A[i] - B;
			if((result) <= 0) {
				ans += 1;
				continue;
			}
			
			if(result <= C) {
				ans += 2;
			}else if(result % C == 0){
				ans += (result / C) + 1;
			} else {
				ans += (result / C) + 2;
			}
			
		}
		
		// 3. 출력하기
		System.out.println(ans);

	}
}
