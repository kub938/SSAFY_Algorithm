import java.io.*;
import java.util.*;
/*
git commit -m "김성민 / 240910 / [BOJ_색종이 붙이기_17136] / 1072ms"
###풀이 사항
풀이 일자: 2024.09.10
풀이 시간: 60분
채점 결과: 정답
예상 문제 유형: 중복순열, DFS

###풀이방법
1. 데이터를 입력받으면서 fletten하게 만들어 리스트에 저장
2. 중복 순열을 사용하여 dfs돌림
    1) 각 색종이 개수가 5개 넘어가면 백트래킹
    2) 최솟값보다 크면 탐색할 필요 없어서 백트래킹
    3) 크기가 5부터 1까지 색종이를 넣을 수 있는지를 확인하고 다음 dfs 호출

###느낀점
색종이를 넣는지 확인하는 과정이 비효율적이라 시간이 많이 나왔다
다른 방식을 찾아보니 배열 0,0 부터 9,9까지 dfs를 호출하는 방식을 사용하는게 훨씬 효율적인거 같다.
*/

public class Main {
    static ArrayList<Integer> alist = new ArrayList(100);
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < 10; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                if (st.nextToken().equals("1")) {
                    alist.add(i*10+j);
                }
            }
        }
        back(0,new int[5]);

        if(min == Integer.MAX_VALUE) min = -1;
        System.out.print(min);
    }

    private static void back(int d, int[] cnt) {
        if(cnt[0]>5 || cnt[1]>5 || cnt[2]>5 || cnt[3]>5 || cnt[4]>5) return;
        if(d >= min) return;

        if(alist.size() == 0){
            min = Math.min(min, d);
            return;
        }
        ArrayList<Integer> tmp = new ArrayList();
        for (int i = 4; i >= 0; i--) {
            cnt[i]++;
            if(check(i,tmp)) {
                back(d + 1, cnt);
            }
            alist.addAll(tmp);
            alist.sort(Integer::compareTo);
            tmp.clear();
            cnt[i]--;
        }

    }

    private static boolean check(int size, ArrayList<Integer> tmp) {
        int node = alist.get(0);

        for(int i = 0; i <= size; i++) {
            for(int j = 0; j <= size; j++) {
                int y = node/10 +i;
                int x = node%10 +j;

                if(y>=10 || x>=10) return false;
                int a = alist.indexOf(y*10+x);
                if(a==-1) return false;
                tmp.add(alist.remove(a));
            }
        }
        return true;
    }
}