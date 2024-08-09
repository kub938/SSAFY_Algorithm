import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
풀이 사항
풀이 일자: 2024.08.09
풀이 시간: 20분
채점 결과: 정답
예상 문제 유형: 백트래킹

풀이방법)
1.백트래킹을 돈다
2.방문하지 않고 부등호에 조건에 맞으면 계속 호출
3.n+1만큼 백트래킹이 돌면 min max 갱신
4.앞에 0이 생략되면 안되니 String으로 변환하고 길이를 계산해서 0을 붙혀줬다.
*/
class Main
{ //204ms
	static int n;
	static char[] arr;
	static long max = Long.MIN_VALUE;
	static long min = Long.MAX_VALUE;
	static boolean[] visited;
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		arr = new char[n];
		visited = new boolean[10];
		
		st =new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] =st.nextToken().charAt(0);
		}
		
		back(0,"");
		String maxs = String.valueOf(max);
		if(maxs.length()==n) maxs= '0'+maxs;
		
		String mins = String.valueOf(min);
		if(mins.length()==n) mins= '0'+mins;
		System.out.println(maxs);
		System.out.println(mins);
	}

	private static void back(int d, String str) {
		// TODO Auto-generated method stub
		if(d>n) {
			long a = Long.parseLong(str);
			max = Math.max(max, a);
			min = Math.min(min, a);
		}else {
			
			
			for(int i=0;i<10;i++) {
				if(!visited[i]) {
					if(d!=0) {
						int num = str.charAt(d-1)-'0';
						if(arr[d-1]=='>') {
							if(num <= i) continue;
						}else {
							if(num >= i) continue;
						}
					}
					
					visited[i] = true;
					back(d+1, str+i);
					visited[i] = false;
				}
			}
		}
	}
}