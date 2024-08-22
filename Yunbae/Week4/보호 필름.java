import  java.util.Scanner;
import java.io.BufferedReader;

public class Film {
    static Scanner sc = new Scanner(System.in);


    static final int DSIZE = 13;
    static final int WSIZE = 20;
    static int D,W,K;


    //film : 필름 2차원 저장
    //minChemicalCnt : 화학처리 횟수의 최솟값
    //chemical : 화학처리 기록
    // 0 약품 a, 1 약품 b, 2 약품 x
    static int [][]film = new int[DSIZE][WSIZE];
    static int minChemicalCnt;
    static int[] chemical = new int[DSIZE];



    static void solve(int curD, int ChemicalCnt, int[] preCon, int[] prevMaxCon){


        if (ChemicalCnt >= minChemicalCnt){
            return;
        }

        //필름을 모두 다 보았을때
        if(curD == D){

            //합격기준 확인
            boolean isSatisfied = true;
            for(int i=0; i<W; i++){
                if(prevMaxCon[i]<K){
                    isSatisfied = false;
                    break;
                }
            }

            //합격 기준을 모두 만족, 현재까지의 약품 최소 처리 횟수보다 작은값이면 갱신
            if(isSatisfied && ChemicalCnt < minChemicalCnt){
                minChemicalCnt = ChemicalCnt;
            }return;
        }


        int[] continuum = new int[WSIZE];
        int[] maxContinuum = new int[WSIZE];
        int prev, cur;


        //약품처리 하지않을때, B약품 처리할때, A약품 처리할 때 차례대로 진행
        for (int i = 2; i >= 0 ; i--) {
            // i번째 약품 처리할 때
            chemical[curD] = i;


            //세로탐색
            for(int j=0;j<W;j++){
                cur = chemical[curD] == 2 ? film[curD][j] : chemical[curD];
                prev = chemical[curD -1] == 2 ? film[curD -1][j] : chemical[curD -1];
                continuum[j] = cur == prev ? preCon[j] + 1 : 1;
                maxContinuum[j] = Math.max(continuum[j], prevMaxCon[j]);
            }
            solve(curD+1, ChemicalCnt + (i==2 ?0:1), continuum, maxContinuum);
        }

    }

    public static void main(String args[]){
        int test_case = sc.nextInt();

        for (int tc = 1; tc <= test_case; tc++) {
            D = sc.nextInt();
            W = sc.nextInt();
            K = sc.nextInt();

            for (int i = 0; i < D; i++) {
                for (int j = 0; j < W; j++) {
                    film[i][j] = sc.nextInt();
                }
            }
            minChemicalCnt = K;

            //continnum : 지금 현재까지 세로방향으로 같은 특성이 존재하는 셀 수
            //maxContinnum
            int[] continuum = new int[WSIZE];
            int[] maxContinuum = new int[WSIZE];
            for (int i = 0; i < W; i++) {
                continuum[i] = maxContinuum[i] = 1;
            }

            //0번째 약품처리 x
            chemical[0] = 2;
            solve(1,0,continuum,maxContinuum);

            //0번째 A처리
            chemical[0] = 0;
            solve(1,1,continuum,maxContinuum);

            //0번째 B처리
            chemical[0] = 1;
            solve(1,1,continuum,maxContinuum);

            System.out.println("#" + tc + " "+minChemicalCnt);
        }


    }

}
