import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int N, M;
	public static int[] S;
	
	public static void main(String[] args) throws NumberFormatException, IOException  {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		S = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for(int i =1;i<=N;i++) {
			S[i] = Integer.parseInt(st.nextToken());
		}
		
		M = Integer.parseInt(br.readLine());
		int start, end, size;
		for(int i =0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());
			size = start + end;
			boolean chk = true;
			
			while(start <= size / 2) {
				if(S[start] != S[end]) {
					chk = false;
					break;
				}
				start++;
				end--;
			}
			
			if(chk) { // 펠린드롬이라면
				sb.append(1 + "\n");
			} else { // 펠린드롬이 아니라면
				sb.append(0 + "\n");
			}
		}
		
		System.out.println(sb);
	}
}
