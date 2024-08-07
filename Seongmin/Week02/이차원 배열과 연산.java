import java.io.*;
import java.util.*;

//0806 17:12 start
//순수 풀이 시간 2시간
//0807 17:40 end

/*
git commit -m "김성민 / 240806 / [BOJ_이차원 배열과 연산_17140] / 188ms "
풀이 사항
풀이 일자: 2024.08.06 - 07
풀이 시간: 2시간
채점 결과: 정답
예상 문제 유형: 구현

풀이방법
1. 세로(y)100 가로(x)100 크기의 배열을 생성한다.
2. 사용되는 y크기(ysize) 사용되는 x크기(xsize)를 저장한다.
3. 배열[r][c] == k 인지 확인하고 될때까지 RC연산 수행
    3-1 가로(R) 세로(C) 한 줄을 탐색(해시맵을 사용해서 숫자 빈도수 파악)
    3-2 해시맵의 키를 추출해서 빈도수에 따라 키 정렬
    3-3 탐색한 줄을 초기화
    3-4 숫자와 빈도수를 배열에 넣음 (사용되는 y/x 크기 업데이트)
4. 걸린 시간을 리턴(100내로 못하면 -1)

피드백
List를 사용해서 문제를 해결하려고 하니 너무 복잡해서 시간이 너무 지연되었고 배열로 변경해서 문제 해결
탐색한 줄을 초기화하지 않아서 숫자가 계속 남아있게되어 오류가 발생하였다. 이부분도 고쳐야할 거 같다.

*/
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken()) - 1;
        int c = Integer.parseInt(st.nextToken()) - 1;
        int k = Integer.parseInt(st.nextToken());

        int t = 0;
        int[][] arr = new int[100][100];



        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ysize = 3;
        int xsize = 3;

        HashMap<Integer,Integer> hm = new HashMap<>();

        for (t = 0; t <= 100; t++) {
            //시간값 리턴
            if(arr[r][c]==k) break;

            int max = 0;

            if(ysize >= xsize){
                //R
                for (int i = 0; i < ysize; i++) {
                    hm.clear();
                    //한줄 해시맵에 넣고
                    for (int j = 0; j < xsize; j++) {
                        if(arr[i][j]==0) continue;
                        if(hm.containsKey(arr[i][j])){
                            hm.replace(arr[i][j], hm.get(arr[i][j])+1);
                        }else{
                            hm.put(arr[i][j],1);
                        }
                    }
                    //해시맵 정렬하고
                    List<Integer> keySet = new ArrayList<>(hm.keySet());
                    keySet.sort((o1,o2)->{
                        int a = hm.get(o1).compareTo(hm.get(o2));
                        return (a!=0) ? a : o1.compareTo(o2);
                    });
                    //한줄 초기화하고
                    arr[i] = new int[100];
                    //넣기
                    int size = 0;
                    for (int key:keySet){
                        arr[i][size++] = key;
                        if(size>=100) break;
                        arr[i][size++] = hm.get(key);
                        if(size>=100) break;
                    }
                    max = Math.max(max,size);
                }
                xsize = max;
            }else{
                //C
                for (int i = 0; i < xsize; i++) {
                    hm.clear();
                    //열을 살펴봄
                    for (int j = 0; j < ysize; j++) {
                        if(arr[j][i]==0) continue;
                        if(hm.containsKey(arr[j][i])){
                            hm.replace(arr[j][i], hm.get(arr[j][i])+1);
                        }else{
                            hm.put(arr[j][i],1);
                        }
                    }
                    //해시맵 정렬하고
                    List<Integer> keySet = new ArrayList<>(hm.keySet());
                    keySet.sort((o1,o2)->{
                        int a = hm.get(o1).compareTo(hm.get(o2));
                        return (a!=0) ? a : o1.compareTo(o2);
                    });
                    //열 초기화 해야하네
                    for (int j = 0; j < 100; j++) {
                        arr[j][i] = 0;
                    }
                    //넣기
                    int size = 0;
                    for (int key:keySet){
                        arr[size++][i] = key;
                        if(size>=100) break;
                        arr[size++][i] = hm.get(key);
                        if(size>=100) break;
                    }
                    max = Math.max(max,size);
                }
                ysize = max;
            }
            
        }

        int result = t;

        if (result > 100) {
            result = -1;
        }

        System.out.println(result);
    }
}

