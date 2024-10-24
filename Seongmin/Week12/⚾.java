import java.util.*;
import java.io.*;

/*
git commit -m "김성민 / 241024 / [BOJ_⚾_17281] / 588ms"

### 풀이 사항
풀이 일자: 2024.10.24
풀이 시간: 45분
채점 결과: 정답
예상 문제 유형: np, 구현
시간: 588 ms
메모리: 21824 kb

### 풀이방법
1. 데이터를 입력받고 순열을 저장할 배열(0번제외)과 타석 순서를 저장할 리스트를 생성한다.
2. 생성된 순열을 리스트에 넣고 0을 3인덱스(4번 타자)에 삽입한다.
3. 리스트 순서대로 이닝을 진행하여 점수를 구하고 최댓값을 갱신한다.
4. next permutation으로 다음 순열을 구하고 2번으로 돌아간다.

### 느낀점
np에 구현에 미숙한 점이 있다는 걸 알아서 좋았다.
이닝이 끝나면 모든 주자를 초기화해야한다는 걸 잊어서 잠깐 시간이 지연되었다.


특이사항)
1번선수가 4번타자로 정해져있다.
8명의 순열을 돈다

순열 결과 값을 바탕으로 n번의 이닝을 진행

이닝
초기 1번선수부터 3아웃이면 이닝 종료
1루 2루 3루에 대한 정보 저장
1루타함수 1->2 2->3 3X score ++; 1true
2루타함수 2true 1-> 3   23X score ++; 2true
3루타함수  123 XX score++; 3true
홈런 함수 각각 구현 123전부 false true개수만큼 score++

0번이 3위치에 넣기



 */

public class Main {
    //bfs
    static int[][] baseball; // [이닝] [선수] 가 성적
    static List<Integer> taOrder;
    static int[] perm;
    static int max = 0;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        baseball = new int[n][9];
        taOrder = new ArrayList<>();
        perm = new int[8];

        for (int i = 0; i < 8; i++) {
            perm[i] = i + 1;
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                baseball[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        np();
        sb.append(max);
        System.out.print(sb);
    }

    private static void np() {

        do {
            taOrder.clear();
            for (int i = 0; i < 8; i++) {
                taOrder.add(perm[i]);
            }
            taOrder.add(3, 0);
            //점수 계산
            int score = getScore();
            max = Math.max(max, score);

        }
        while (nextPermutation());

    }


    private static boolean nextPermutation() {
        int summit = 7;
        int change = 7;
        int end = 7;

        //꼭대기 찾고
        while (summit > 0 && perm[summit] <= perm[summit - 1]) summit--;
        if (summit == 0) return false;

        //꼭대기랑 바꿀 위치 찾고
        while (perm[summit - 1] >= perm[change]) change--;
        swap(summit - 1, change, perm);

        //꼭대기부터 정렬
        while (summit < end) swap(summit++, end--, perm);
        return true;
    }

    private static void swap(int a, int b, int[] arr) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    private static int getScore() {
        int score = 0;
        int curr_order = 0;
        boolean base1 = false;
        boolean base2 = false;
        boolean base3 = false;

        for (int i = 0; i < n; i++) {
            int out = 0;
            base1 = false;
            base2 = false;
            base3 = false;

            while (out<3){
                int player = taOrder.get(curr_order++);
                curr_order%=9;
                switch (baseball[i][player]){
                    case 0:
                        out++;
                        break;
                    case 1:
                        if(base3){
                            base3=false;
                            score++;
                        }
                        if(base2){
                            base3 = true;
                            base2 = false;
                        }
                        if(base1){
                            base2 = true;
                        }
                        base1 = true;
                        break;
                    case 2:
                        if(base3){
                            base3=false;
                            score++;
                        }
                        if(base2){
                            score++;
                        }
                        if(base1){
                            base3 = true;
                            base1 = false;
                        }
                        base2 = true;
                        break;
                    case 3:
                        if(base3){
                            score++;
                        }
                        if(base2){
                            base2 = false;
                            score++;
                        }
                        if(base1){
                            base1 = false;
                            score++;
                        }
                        base3 = true;
                        break;
                    case 4:
                        if(base3){
                            base3=false;
                            score++;
                        }
                        if(base2){
                            base2 = false;
                            score++;
                        }
                        if(base1){
                            base1 = false;
                            score++;
                        }
                        score++;
                        break;
                }
            }
        }

        return score;
    }


}