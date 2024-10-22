import java.util.*;
import java.io.*;

public class Main {

    static StringTokenizer st;

    static int N;

    static int[] players;  // 선수 번호 (1~9)

    static int[] battingOrder;  // 정해진 타순

    static int[][] inningResult;  // 각 타자의 이닝 내 결과

    static int answer = Integer.MIN_VALUE;  // 최대 득점

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());  // 이닝 수
        inningResult = new int[N][9];  // 각 이닝과 선수 결과

        players = new int[9];  // 1~9 타순 배열

        // 선수 번호 넣어두기
        for (int i = 0; i < 9; i++) {
            players[i] = i + 1;
        }

        // [이닝][선수번호] = 결과 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                inningResult[i][j] = Integer.parseInt(st.nextToken());
            }
        }

//        // 1번 선수를 4번 타자로 고정
//        players[3] = 1;  // 4번 타자에 1번 선수 고정
        Arrays.sort(players, 1, 9);  // 2~9번 선수 정렬

        do {
            if(players[3] == 1){
                simulate();
            }
        } while (nextPermutation(players));

        System.out.println(answer);  // 결과 출력
    }

    // nextPermutation을 구현
    private static boolean nextPermutation(int[] arr) {
        int n = arr.length;
        int i = n - 1;

        while (i > 0 && arr[i - 1] >= arr[i]) {
            i--;
        }

        if (i <= 0) {
            return false;
        }

        int j = n - 1;
        while (arr[j] <= arr[i - 1]) {
            j--;
        }

        int temp = arr[i - 1];
        arr[i - 1] = arr[j];
        arr[j] = temp;

        j = n - 1;
        while (i < j) {
            temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
        return true;
    }

    // 타순이 정해졌을 때 시뮬레이션
    public static void simulate() {
        int score = 0;
        int outCount = 0;
        int inning = 0;
        int hitterIndex = 0;  // 타자 인덱스
        boolean[] onBase = new boolean[3];  // 1루, 2루, 3루에 주자 존재 여부

        while (true) {
            if (inning == N) {
                answer = Math.max(answer, score);
                break;
            }

            while (outCount < 3) {
                int result = inningResult[inning][players[hitterIndex] - 1];  // 타자의 결과
                hitterIndex = (hitterIndex + 1) % 9;  // 다음 타자로 이동

                switch (result) {
                    case 0:
                        outCount++;  // 아웃
                        break;
                    case 1:
                        score += pushBase(onBase, 1);  // 1루타
                        break;
                    case 2:
                        score += pushBase(onBase, 2);  // 2루타
                        break;
                    case 3:
                        score += pushBase(onBase, 3);  // 3루타
                        break;
                    case 4:
                        score += pushBase(onBase, 4);  // 홈런
                        break;
                }
            }

            inning++;
            outCount = 0;
            Arrays.fill(onBase,false);  // 주자 상태 초기화
        }
    }

    // 주자를 진루시키고 득점 확인
    public static int pushBase(boolean[] onBase, int n) {
        int score = 0;
        switch (n) {
            case 1: // 1루타
                if (onBase[2]) score++; // 3루 주자 득점
                onBase[2] = onBase[1]; // 2루 주자 3루로
                onBase[1] = onBase[0]; // 1루 주자 2루로
                onBase[0] = true; // 타자가 1루로
                break;
            case 2: // 2루타
                if (onBase[2]) score++; // 3루 주자 득점
                if (onBase[1]) score++; // 2루 주자 득점
                onBase[2] = onBase[0]; // 1루 주자 3루로
                onBase[1] = true; // 타자 주자 2루로
                onBase[0] = false; // 타자가 2루로
                break;
            case 3: // 3루타
                score += countBase(onBase); // 주자 모두 득점
                onBase[0] = false; // 1루 주자 없음
                onBase[1] = false; // 2루 주자 없음
                onBase[2] = true; // 타자가 3루로
                break;
            case 4: // 홈런
                score += countBase(onBase) + 1; // 주자 모두 득점 + 타자 득점
                Arrays.fill(onBase, false); // 모든 주자 출루 상태 초기화
                break;
        }
        return score;
    }

    // 루상에 주자 수 세기
    public static int countBase(boolean[] onBase) {
        int count = 0;
        for (boolean check : onBase) {
            if (check) {
                count++;
            }
        }
        return count;
    }
}
