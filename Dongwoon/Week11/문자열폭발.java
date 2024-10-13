import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static int bLength;
	public static String str, bomb;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		// 1. 입력받기
		str = br.readLine();
		bomb = br.readLine();
		bLength = bomb.length();
		
		// 2. 스택에 하나씩 넣으며 폭발 문자열 비교하기
		Stack<Character> st = new Stack<Character>();
		for(int i=0;i<str.length();i++) {
			st.push(str.charAt(i));
			
			// 2-1. 폭발 문자열 길이보다 커졌다면
			if(st.size() >= bLength) {
				boolean chk = false;
				
				// 3. st.size - bLength 부터 st.size까지 탐색
				for(int j = 0;j<bLength;j++) {
					if(st.get(st.size()-bLength+j) != bomb.charAt(j)) {
						chk = true;
						break;
					}
				}
				
				// 3-1. 문자열이 전부 일치한다면 제거
				if(!chk) {
					for(int j = 0;j<bLength; j++) {
						st.pop();
					}
				}
			}
		}

		// 4. 출력하기
		if (st.size() == 0) {
			sb.append("FRULA");
		} else {
			for(Character c : st) {
				sb.append(c);
			}
		}
		System.out.println(sb);
	}
}
