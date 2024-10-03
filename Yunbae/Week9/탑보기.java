import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.lang.*;

class Building{
    int index;
    int height;

    public Building(int index, int height) {
        this.index = index;
        this.height = height;
    }
}

public class 탑보기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        Stack<Building> stack = new Stack<>();
        String[] s = br.readLine().split(" ");


        Building[] arr = new Building[n+1];
        int [][] indexAndGap = new int[n+1][2];           //가장 작은 인덱스를 받기위해 배열 생성
        int [] cnt = new int[n+1];                        //cnt 생성
        for(int i=1; i<=n; i++){
            arr[i] = new Building(i, Integer.parseInt(s[i-1]));
            Arrays.fill(indexAndGap[i],100001);    //
        }

        for(int i=1; i<=n; i++){
            while(!stack.isEmpty() && stack.peek().height<=arr[i].height){      //지금 보고있는게 뭔데?..
                stack.pop();
            }

            cnt[i] += stack.size();

            if(!stack.isEmpty()){
                int gap = Math.abs(stack.peek().index-i);
                if(gap<indexAndGap[i][1]){
                    indexAndGap[i][0] = stack.peek().index;
                    indexAndGap[i][1] = gap;
                }
                else if(gap == indexAndGap[i][1] && stack.peek().index<indexAndGap[i][0]){
                    indexAndGap[i][0] = stack.peek().index;
                }
            }

            stack.push(arr[i]);
        }

        stack =new Stack<>();
        for(int i=n; i>=1; i--){
            while(!stack.isEmpty() && stack.peek().height<=arr[i].height){
                stack.pop();
            }

            cnt[i] +=stack.size();

            if(!stack.isEmpty()){
                int gap = Math.abs(stack.peek().index-i);
                if(gap<indexAndGap[i][1]){
                    indexAndGap[i][0] = stack.peek().index;
                    indexAndGap[i][1] = gap;
                }
                else if(gap == indexAndGap[i][1] && stack.peek().index<indexAndGap[i][0]){
                    indexAndGap[i][0] = stack.peek().index;
                }
            }
            stack.push(arr[i]);
        }

        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=n; i++){
            if(cnt[i]==0){
                sb.append(0).append("\n");
            }
            else{
                sb.append(cnt[i]).append(" ").append(indexAndGap[i][0]).append("\n");
            }
        }
        System.out.println(sb.toString());
    }
}
