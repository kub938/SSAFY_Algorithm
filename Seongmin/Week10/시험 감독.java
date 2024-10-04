import java.util.*;
import java.io.*;

/*
git commit -m "김성민 / 241002 / [BOJ_시험 감독_13458] / 412ms"


 */

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int roomCnt = Integer.parseInt(br.readLine());
        int[] people = new int[roomCnt];
        
        st= new StringTokenizer(br.readLine());
        for (int i = 0; i < roomCnt; i++) {
            people[i] = Integer.parseInt(st.nextToken());
        }
        
        st = new StringTokenizer(br.readLine());
        int main = Integer.parseInt(st.nextToken());
        int sub = Integer.parseInt(st.nextToken());
        
        long result =0;

        for (int i = 0; i < roomCnt; i++) {
            people[i] -= main;
            result++;

            if(people[i]>0){
                result+= people[i]/sub;
                if(people[i]%sub !=0){
                    result++;
                }
            }
        }
        
        sb.append(result);
        
        System.out.print(sb);
    }
}