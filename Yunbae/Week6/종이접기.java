import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 종이접기 {
    static int k;

    public static void swap(int[] target, int x, int y){
        int tmp = 0;
        tmp = target[x];
        target[x] = target[y];
        target[y] = tmp;
    }

    public static void print(int idx){

        //처음 idx = target[k]
        //idx == 0 이면 0 , 1 , 2 , 3
        //idx == 1 이면 1, 0, 3, 2
        //idx == 2 이면 2, 3, 0, 1
        //idx == 3 이면 3, 2, 1, 0
        // , idx == 2면? 3 , 3이면 2, 1이면 0
        if(idx==0){
            for (int i = 0; i < k; i++) {
                for (int j = 0; j < k; j++) {
                    System.out.print(0+" "+1+" ");
                }
                System.out.println();
                for (int j = 0; j < k; j++) {
                    System.out.print(2+" "+3+" ");
                }
                System.out.println();
            }

        }else if(idx==1){
            for (int i = 0; i < k; i++) {
                for (int j = 0; j < k; j++) {
                    System.out.print(1+" "+0+" ");
                }
                System.out.println();
                for (int j = 0; j < k; j++) {
                    System.out.print(3+" "+2+" ");
                }
                System.out.println();
            }
        }else if(idx==2){
            for (int i = 0; i < k; i++) {
                for (int j = 0; j < k; j++) {
                    System.out.print(2+" "+3+" ");
                }
                System.out.println();
                for (int j = 0; j < k; j++) {
                    System.out.print(0+" "+1+" ");
                }
                System.out.println();
            }
        }else if(idx==3){
            for (int i = 0; i < k; i++) {
                for (int j = 0; j < k; j++) {
                    System.out.print(3+" "+2+" ");
                }
                System.out.println();
                for (int j = 0; j < k; j++) {
                    System.out.print(1+" "+0+" ");
                }
                System.out.println();
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        k = Integer.parseInt(br.readLine());
        String[] order = new String[k*2];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k*2; i++) {
            order[i] = st.nextToken();
        }

        int holl = Integer.parseInt(br.readLine());
        int[] target = new int[] {0,1,2,3};

        String[] pageState = {"A","A"}; //0번 좌우, 1번 위아래
        for(String o : order){
            if(o.equals("D") && !pageState[1].equals("D")){
                pageState[1] = "D";
                swap(target, 0, 2);
                swap(target, 1,3);
            }else if(o.equals("R") && !pageState[0].equals("R")){
                pageState[0] = "R";
                swap(target, 0,1);
                swap(target, 2,3);
            }else if(o.equals("L") && pageState[0].equals("R")){
                pageState[0] = "L";
                swap(target, 0,1 );
                swap(target, 2,3);
            }else if(o.equals("U") && pageState[1].equals("D")){
                pageState[1] = "U";
                swap(target, 0, 2);
                swap(target, 1,3);
            }
        }

        int idx = target[holl];
        print(idx);
    }
}
