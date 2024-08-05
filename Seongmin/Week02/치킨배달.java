import java.io.*;
import java.util.*;

//0805 16:56 start
//0805 17:32 end

/*
풀이 사항
풀이 일자: 2024.08.05
풀이 시간: 35분
채점 결과: 정답
예상 문제 유형: BFS, 백트래킹

풀이 방법
백트래킹(dfs) 조합을 사용하여 m개의 치킨집을 구하고 최소 도시치킨거리를 갱신하여 문제를 해결했다.
*/
public class Main {
    //1이 집, 2가 치킨
    //집의 위치 리스트를 저장
    //치킨의 위치 리스트를 저장
    
    //백트래킹으로 치킨집 m개를 설정하고 1배열과 치킨집 m개 사이의 치킨 거리를 구하고 모두 더한다.
    //해당 값 중 최솟값을 리턴

    //시간 복잡도는 2*50*6* 13C6 대충 백만정도 나와서 1초 안넘김
    static int min = Integer.MAX_VALUE;

    //집의 위치를 저장하는 리스트
    //치킨집 위치를 저장하는 리스트
    static ArrayList<int[]> harr = new ArrayList<>();
    static ArrayList<int[]> carr = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int a = Integer.parseInt(st.nextToken());

                if(a==1){
                    harr.add(new int[]{i,j});
                }else if(a==2){
                    carr.add(new int[]{i,j});
                }
            }
        }

        back(m, new ArrayList<>(),0);
        System.out.println(min);
    }
    //조합을 사용하여 m개의 치킨집을 구하고 도시 치킨거리를 갱신하는 함수
    private static void back(int m, ArrayList<int[]> arr,int start) {
        //m개의 치킨집이 모이면 도시 치킨 거리 구함
        if(arr.size()>=m){
            int sum = 0;
            for (int[] a: harr){
                int hmin = Integer.MAX_VALUE;
                for (int[] b: arr){
                    hmin = Math.min(hmin,getScore(a,b));
                }
                sum+= hmin;
            }
            min = Math.min(min,sum);
        }else{
            //조합 사용
            for (int i = start; i < carr.size(); i++) {
                arr.add(carr.get(i));
                back(m,arr,i+1);
                arr.remove(carr.get(i));
            }

        }
    }
    //치킨 거리를 구하는 함수
    static int getScore(int[] a, int[] b){
        return Math.abs(a[0]-b[0])+Math.abs(a[1]-b[1]);
    }
}

