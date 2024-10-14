import java.util.*;
import java.io.*;

/*
git commit -m "김성민 / 241013 / [BOJ_문자열 폭발_9935] / 420ms"
### 풀이 사항
풀이 일자: 2024.10.13
풀이 시간: 1시간 반
채점 결과: 정답
예상 문제 유형: 스택
시간: 420 ms
메모리: 35728 kb

### 풀이방법
1. 데이터를 입력받는다. (문자열, 비교 패턴)
2. 원본 문자열의 문자 하나씩 스택에 넣는다.
3. 스택의 문자 개수가 비교 패턴과 같거나 크면 비교를 시작한다.
4. 비교 패턴과 같다면 스택에서 그만큼 빼낸다.
5. 모두 끝마치고 스택이 비어있으면 실패 남아있으면 출력

### 느낀점
초반에 스택과 어레이리스트가 기능이 비슷하다고 생각하여 ArrayList로 구현했는데 메모리초과가 발생했다.
스택이 메모리를 덜 사용한다는 것을 알 수 있었어서 좋았다.

*/

public class Main
{
    public static void main(String args[]) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        Stack<Character> stack = new Stack<>();

        char[] arr = br.readLine().toCharArray();
        String pattern = br.readLine();
        int pSize = pattern.length();
        loop:for(char c: arr){
            stack.add(c);
            if(stack.size()< pSize){
                continue;
            }
            for (int i = 0; i < pSize; i++) {
                if(pattern.charAt(i) != (char)stack.get(stack.size()-pSize+i)){
                    continue loop;
                }
            }
            for (int i = 0; i < pSize; i++) {
                stack.pop();
            }
        }
        if(stack.size() == 0){
            System.out.println("FRULA");
        }else{
            StringBuilder result = new StringBuilder();
            for(char c: stack){
                result.append(c);
            }
            System.out.println(result);
        }


    }
}