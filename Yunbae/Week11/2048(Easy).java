package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2048_easy {
    static int n;
    static int[][] realMap;
    static int[][] map;
    static int result;
    static int[] pushDir;
    static int[] arr = new int[5];
    static int[] visited = new int[4];
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        realMap = new int[n][n];
        pushDir = new int[]{0, 1, 2, 3};

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                realMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        result = 0;
        perm(0);
        System.out.println(result);
    }


    public static void perm(int depth) {
        // 깊이가 5에 도달했을 때
        if (depth == 5) {
            map = new int[n][n];
            for (int i = 0; i < n; i++) {        //map 초기화
                for (int j = 0; j < n; j++) {
                    map[i][j] = realMap[i][j];
                }
            }

            for (int i = 0; i < arr.length; i++) {
                move(arr[i]);
            }

            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (result < map[j][k]) {
                        result = map[j][k];
                    }
                }
            }
            return;
        }

        // 0부터 3까지의 방향을 반복
        for (int i = 0; i < 4; i++) {
            arr[depth] = pushDir[i]; // 현재 깊이에 방향 저장
            visited[i] = 1; // 방문 처리
            perm(depth + 1); // 다음 깊이로 재귀 호출
            visited[i] = 0; // 백트래킹: 방문 처리 해제
        }
    }

    public static void move(int dir) {
        //0상 1하 2좌 3우
        //위쪽으로 밀기
        if (dir == 0) {
            for (int i = 0; i < n; i++) {
                Stack<Integer> stack = new Stack<>();
                boolean canMix = true;  //합쳐졌는지 체크
                for (int j = 0; j < n; j++) {
                    if (map[j][i] == 0) continue;

                    if (stack.isEmpty()) {
                        stack.push(map[j][i]);
                        canMix = true;
                    } else {
                        if (stack.peek() == map[j][i]) { // 똑같으면
                            if (canMix) {
                                int value = stack.pop();
                                stack.push(value * 2);
                                canMix = false;      //합쳐짐
                            } else {
                                stack.push(map[j][i]);
                                canMix = true;
                            }
                        } else {
                            stack.push(map[j][i]);
                            canMix = true;
                        }
                    }
                }
                for (int j = 0; j < n; j++) {
                    map[j][i] = 0;
                }
                for (int j = 0; j < stack.size(); j++) {
                    map[j][i] = stack.get(j);
                }
            }

        } else if (dir == 1) {  //아래쪽
            for (int i = 0; i < n; i++) {
                Stack<Integer> stack = new Stack<>();
                boolean canMix = true;  //합쳐졌는지 체크
                for (int j = n - 1; j > -1; j--) {
                    if (map[j][i] == 0) continue;

                    if (stack.isEmpty()) {
                        stack.push(map[j][i]);
                        canMix = true;
                    } else {
                        if (stack.peek() == map[j][i]) { // 똑같으면
                            if (canMix) {
                                int value = stack.pop();
                                stack.push(value * 2);
                                canMix = false;      //합쳐짐
                            } else {
                                stack.push(map[j][i]);
                                canMix = true;
                            }
                        } else {
                            stack.push(map[j][i]);
                            canMix = true;
                        }
                    }
                }
                for (int j = 0; j < n; j++) {
                    map[j][i] = 0;
                }
                for (int j = 0; j < stack.size(); j++) {
                    map[n - j - 1][i] = stack.get(j);
                }
            }
        } else if (dir == 2) {  //왼쪽
            for (int i = 0; i < n; i++) {
                Stack<Integer> stack = new Stack<>();
                boolean canMix = true;  //합쳐졌는지 체크
                for (int j = 0; j < n; j++) {
                    if (map[i][j] == 0) continue;
                    if (stack.isEmpty()) {
                        stack.push(map[i][j]);
                        canMix = true;
                    } else {
                        if (stack.peek() == map[i][j]) { // 똑같으면
                            if (canMix) {
                                int value = stack.pop();
                                stack.push(value * 2);
                                canMix = false;      //합쳐짐
                            } else {
                                stack.push(map[i][j]);
                                canMix = true;
                            }
                        } else {
                            stack.push(map[i][j]);
                            canMix = true;
                        }
                    }
                }
                for (int j = 0; j < n; j++) {
                    map[i][j] = 0;
                }
                for (int j = 0; j < stack.size(); j++) {
                    map[i][j] = stack.get(j);
                }
            }


        } else if (dir == 3) {  // 오른쪽
            for (int i = 0; i < n; i++) {
                Stack<Integer> stack = new Stack<>();
                boolean canMix = true;  //합쳐졌는지 체크
                for (int j = n - 1; j > -1; j--) {
                    if (map[i][j] == 0) continue;

                    if (stack.isEmpty()) {
                        stack.push(map[i][j]);
                        canMix = true;
                    } else {
                        if (stack.peek() == map[i][j]) { // 똑같으면
                            if (canMix) {
                                int value = stack.pop();
                                stack.push(value * 2);
                                canMix = false;      //합쳐짐
                            } else {
                                stack.push(map[i][j]);
                                canMix = true;
                            }
                        } else {
                            stack.push(map[i][j]);
                            canMix = true;
                        }
                    }
                }

                for (int j = 0; j < n; j++) {
                    map[i][j] = 0;
                }

                for (int j = 0; j < stack.size(); j++) {
                    map[i][n - j - 1] = stack.get(j);
                }
            }
        }
    }
}

