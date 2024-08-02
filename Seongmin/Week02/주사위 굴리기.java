import java.io.*;
import java.util.*;

//31일 2시50분 시작 -  5시06분
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        //주사위 각 칸에 저장된 숫자를 담는 배열(처음은 전부 0)
        int[] dice = new int[6];
        //위, 동쪽, 북쪽이 가리키는 숫자를 담는 배열
        //6-a-1 = 맞은편 숫자
            //(ex: m3[0] = 3 -> 현재 위를 가리키는 숫자값은 dice[3])
            //위의 방향을 알면 아래 방향을 알 수 있음 (m3[0] = 3 -> 현재 위를 가리키는 방향은 3 -> 아래는 6-1-3 = 2 -> 아래를 가리키는 방향은 2)
        int[] m3 = new int[3];

        //위는0, 동쪽은 1, 북쪽은 2
        m3[0] = 0;
        m3[1] = 1;
        m3[2] = 2;

        //마지막 줄에는 이동하는 명령이 순서대로 주어진다. 동쪽은 1, 서쪽은 2, 북쪽은 3, 남쪽은 4로 주어진다.
        int[][] dd = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}}; //동서북남

        st = new StringTokenizer(br.readLine());

        for (int a = 0; a < k; a++) {
            int command = Integer.parseInt(st.nextToken()) - 1;
            int hy = y + dd[command][0];
            int hx = x + dd[command][1];

            if (hy >= 0 && hy < n && hx >= 0 && hx < m) {
                //이동후 맨위에 값을 출력
                y = hy;
                x = hx;
                int tmp;
                //명령에 따라 m3배열 갱신
                switch (command) {
                    //동쪽으로 주사위 굴리기
                        //위에는 서쪽에 있는 값이 오게되고 동쪽에는 위에 있는 값이 오게됨 
                    case 0:
                        tmp = m3[0];
                        m3[0] = 5 - m3[2]; //위에 서쪽값을 넣음
                        m3[2] = tmp;        //동쪽에 위에 값을 넣음
                        break;
                    //서쪽
                    case 1:
                        tmp = m3[2];
                        m3[2] = 5 - m3[0];
                        m3[0] = tmp;
                        break;
                    //븍쪽
                    case 2:
                        tmp = m3[0];
                        m3[0] = 5 - m3[1];
                        m3[1] = tmp;
                        break;
                    //남쪽
                    case 3:
                        tmp = m3[1];
                        m3[1] = 5 - m3[0];
                        m3[0] = tmp;
                        break;

                }
                //현재 위가 가지고 있는 숫자값 출력
                sb.append(dice[m3[0]]+"\n");
                
                //아래 확인
                //발판이 0이면 주사위 값 복사
                //0이 아니라면 발판의 값을 주사위에 넣고 발판값이 0
                int bottom = 5 - m3[0];

                if (arr[y][x] == 0) {
                    arr[y][x] = dice[bottom];
                } else {
                    dice[bottom] = arr[y][x];
                    arr[y][x] = 0;
                }


            }
        }
        System.out.print(sb);
    }
}

