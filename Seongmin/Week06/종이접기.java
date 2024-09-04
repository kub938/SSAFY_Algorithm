import java.io.*;
import java.util.*;

/*
start:1500-1600

git commit -m "김성민 / 240903 / [BOJ_종이접기_20187] / 660ms"
풀이 사항
풀이 일자: 2024.09.03
풀이 시간: 60분
채점 결과: 정답
예상 문제 유형: 구현

풀이방법
1. 데이터를 입력받는다
2. 구멍의 정보를 저장할 2차원 리스트를 생성하고 처음 값을 저장한다.
3. 명령을 역순으로 실행한다
    3-1 위와 왼쪽은 앞에 데이터를 추가 아래와 오른쪽은 데이터를 뒤에 넣는다.
    3-2 가로인지 세로인지에 따라 숫자를 바꾸는 함수를 실행한다.
4. 완성된 리스트를 출력한다.

느낀점)
완전 구현을 했다. 함수화를 진행한다면 좀 더 원활한 디버깅이 되었을 거 같다.
*/
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int k = Integer.parseInt(br.readLine());
        int sizeY = 1;
        int sizeX = 1;

        String[] arr = br.readLine().split(" ");
        int n = Integer.parseInt(br.readLine());
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();

        list.add(new ArrayList<>());
        list.get(0).add(n);

        for (int i = arr.length - 1; i >= 0; i--) {
            char c = arr[i].charAt(0);

            ArrayList<ArrayList<Integer>> tmp = new ArrayList<>();
            switch (c) {
                case 'D':
                    //위에 뒤집어진 값을 추가
                    for(int y=0; y<sizeY; y++) {
                        ArrayList<Integer> temp = new ArrayList<>();
                        for(int x=0; x<sizeX; x++) {
                            temp.add(swapUD(list.get(y).get(x)));
                        }
                        tmp.add(0, temp);
                    }
                    list.addAll(0,tmp);
                    //sizeY 2배
                    sizeY*=2;
                    break;
                case 'U':
                    //아래에 뒤집어진 값을 추가
                    for(int y=0; y<sizeY; y++) {
                        ArrayList<Integer> temp = new ArrayList<>();
                        for(int x=0; x<sizeX; x++) {
                            temp.add(swapUD(list.get(y).get(x)));
                        }
                        tmp.add(0,temp);
                    }
                    list.addAll(tmp);
                    sizeY*=2;
                    break;
                case 'R':
                    //왼쪽에 뒤집어진 값을 추가
                    for(int y=0; y<sizeY; y++) {
                        ArrayList<Integer> temp = new ArrayList<>();
                        for(int x=0; x<sizeX; x++) {
                            temp.add(0,swapRL(list.get(y).get(x)));
                        }
                        list.get(y).addAll(0,temp);
                    }
                    sizeX*=2;
                    break;
                case 'L':
                    //오른쪽에 뒤집어진 값을 추가
                    for(int y=0; y<sizeY; y++) {
                        ArrayList<Integer> temp = new ArrayList<>();
                        for(int x=0; x<sizeX; x++) {
                            temp.add(0,swapRL(list.get(y).get(x)));
                        }
                        list.get(y).addAll(temp);
                    }
                    sizeX*=2;
                    break;
            }
        }
        /*
         * D: 윗 면이 아랫면을 덮음
         * U: 아랫면이 윗 면을 덮음
         * R: 왼쪽이 오른쪽을 덮음
         * L: 오른쪽이 왼쪽을 덮음
         */
        for (ArrayList<Integer> list1 : list) {
            for (int a : list1) {
                System.out.print(a + " ");
            }
            System.out.println();
        }
    }

    //상하로 뒤집을때 0->2 2->0 1->3 3->1
    //좌우로 뒤집을때 0->1 1->0 2->3 3->2
    static int swapUD(int a){
        return (a+2)%4;
    }
    static int swapRL(int a){
        int i = 0;
        if(a>=2) {
            a-=2;
            i=2;
        }
        return (a+1)%2+i;
    }
}
