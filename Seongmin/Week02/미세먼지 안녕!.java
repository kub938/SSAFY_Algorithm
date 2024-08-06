import java.io.*;
import java.util.*;

//0806 15:15 start
//0806  16:30 end

/*
git commit -m "김성민 / 240806 / [BOJ_미세먼지 안녕!_17144] / 444ms "
풀이 사항
풀이 일자: 2024.08.06
풀이 시간: 1시간 15분
채점 결과: 정답
예상 문제 유형: 구현

풀이 방법
모든 먼지의 분산이 동시에 일어나기 때문에 배열 하나만으로는 동시 처리가 불가능하다.
먼지 개수의 변화량을 담는 배열을 생성하고 모든 분산이후 배열 값을 합하는 방식을 사용하였다.

공기 청정기에 경우,
조건대로 앞에서 밀기보다는 역순으로 당기는 식으로 구현했다.
역순으로 생각하니 생각보다 시간이 많이 지연되었다.앞으로 미는 방식으로 로직을 짜는게 더 편할거 같다.
*/
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        int[][] arr = new int[n][m];
        int[] air = new int[2];
        int a = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == -1) {
                    air[a] = i;
                    a++;
                }
            }
        }
        //공기청정기 윗 방향
        int[][] up = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
        //공기청정기 아랫방향
        int[][] down = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};


        //분산으로 인한 변화량을 저장하는 배열
        int[][] push;

        for (int tc = 0; tc < t; tc++) {
            push = new int[n][m];
            
            //먼지 분산
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (arr[i][j] > 0) {
                        //4방향을 체크
                        for (int[] d : up) {
                            int y = i + d[0];
                            int x = j + d[1];
                            //분산에 따른 값 변화 저장
                            if (y >= 0 && y < n && x >= 0 && x < m && arr[y][x] != -1) {
                                //분산을 받은 친구는 +
                                //분산 하는 친구는 -
                                push[y][x] += arr[i][j] / 5;
                                push[i][j] -= arr[i][j] / 5;
                            }
                        }
                    }
                }
            }
            //분산 값 적용
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    arr[i][j] += push[i][j];
                }
            }
            
            //공기 청정기
            int dir = 3;
            int y = air[0];
            int x = 0;

            while (dir >= 0) {
                int hy = y + up[dir][0];
                int hx = x + up[dir][1];

                if (hy <= air[0] && hy >= 0 && hx >= 0 && hx < m) {
                    if (arr[y][x] == -1) {}
                    else if (arr[hy][hx] == -1) {
                        arr[y][x] = 0;
                        break;
                    } else {
                        arr[y][x] = arr[hy][hx];
                    }
                    y= hy;
                    x=hx;
                }else{
                    dir--;
                }
            }
            dir = 3;
            y = air[1];
            x = 0;

            while (dir >= 0) {
                int hy = y + down[dir][0];
                int hx = x + down[dir][1];

                if (hy >= air[1] && hy < n && hx >= 0 && hx < m) {
                    //교환
                    if (arr[y][x] == -1) {
                    } else if (arr[hy][hx] == -1) {
                        arr[y][x] = 0;
                        break;
                    } else {
                        arr[y][x] = arr[hy][hx];
                    }
                    y= hy;
                    x=hx;
                }else{
                    dir--;
                }
            }

        }
        int sum = 0;
        //총 먼지 개수 세기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] > 0) sum += arr[i][j];
            }
        }
        System.out.println(sum);
    }
}

