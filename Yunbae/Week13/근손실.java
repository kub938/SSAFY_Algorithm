import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main{
    static int n,k,cnt;
    static int[] arr;
    static int[] visited;
    static int[] w;
    static int nowW = 0;

    public static boolean solution(int[] arr) {
        for(int i: arr) {
            nowW-=k;
            nowW+= w[i];
            if(nowW<500){
                return false;
            }
        }
        return true;
    }

    public static void perm(int depth) {
        if(depth==n) {
            nowW = 500;
            if(solution(arr)){
                cnt+=1;
            }
            return;
        }


        for (int i = 0; i < n; i++) {
            if(visited[i] == 0) {
                arr[depth] = i;
                visited[i] = 1;
                perm(depth+1);
                arr[depth] =0;
                visited[i] = 0;
            }

        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        w = new int[n];
        visited = new int[n];
        arr = new int[n];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < w.length; i++) {
            w[i] = Integer.parseInt(st.nextToken());
        }

        perm(0);
        System.out.println(cnt);
    }
}
