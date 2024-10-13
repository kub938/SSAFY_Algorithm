
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;


class Char {
    String s;
    int p;

    public Char(String s, int p) {
        this.s = s;
        this.p = p;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String target = br.readLine();
        int tLen = target.length();
        Stack<Char> stack = new Stack<>();

        int point = 0;

        for (int i = 0; i < str.length(); i++) {
            String s = String.valueOf(str.charAt(i));         //받은 값
            String t = String.valueOf(target.charAt(point));        //pointer가 위치하는 값

            if (t.equals(s)) {
                point += 1;
                Char c = new Char(String.valueOf(str.charAt(i)), point);
                stack.add(c);
                if (point == tLen) { //문자열 길이만큼 똑같으면 pop 진행
                    for (int j = 0; j < tLen; j++) {
                        stack.pop();
                    }
                    if (!stack.isEmpty()) {
                        point = stack.peek().p;
                        continue;
                    }
                    point = 0;
                }
            } else {
                if (!stack.isEmpty()) {
                    if (stack.peek().s.equals(s) && stack.peek().p == 1) { //넣을때 첫번째껄 똑같이 한번 더 넣을 경우 처리
                        point = 1;
                    }else if(s.equals(String.valueOf(target.charAt(0)))){
                        point = 1;
                    }else{
                        point = 0;
                    }
                } else {
                    point = 0;
                }
                Char c = new Char(String.valueOf(str.charAt(i)), point);
                stack.add(c);
            }
        }

        StringBuilder sb = new StringBuilder();
        if (stack.isEmpty()) {
            System.out.println("FRULA");
        } else {
            for (Char aChar : stack) {
                sb.append(aChar.s);
            }
        }
        System.out.print(sb.toString());

    }
}
