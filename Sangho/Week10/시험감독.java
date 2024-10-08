import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static StringTokenizer st;

    static int N,B,C;

    static long answer = 0;

    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());

        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        // 총 감독관 수
        answer += N;

        for(int i = 0; i < N; i++){
            // 총 감독관이 볼 수 있는 만큼 뺌
            arr[i] -= B;
            // 총 감독관이 다 볼 수 있으면 넘어감
            if(arr[i] <= 0) continue;
            // 안되면 부 감독관 쓰셈
            answer += arr[i] / C;
            // 부 감독관이 봤는데 0으로 안나누어 떨어지면 부감독관 한명 더 필요함
            if(arr[i] % C != 0){
                answer++;
            }
        }

        System.out.println(answer);

    }
}
