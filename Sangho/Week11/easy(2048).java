import java.io.*;
import java.util.*;

public class Main {

    static StringTokenizer st;
    static int N;
    static Block[][] map;
    static int[] plans;
    static int answer = Integer.MIN_VALUE;

    public static class Block {
        int size;
        boolean merged;

        public Block(int size) {
            this.size = size;
            this.merged = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        map = new Block[N][N];
        plans = new int[5];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                int value = Integer.parseInt(st.nextToken());
                if (value != 0) {
                    map[r][c] = new Block(value);
                }
            }
        }

        DFS(0);
        System.out.println(answer);
    }

    public static void DFS(int depth) {
        if (depth == 5) {
            Block[][] originalMap = copyMap(map);
            for (int i = 0; i < 5; i++) {
                moveBlock(plans[i]);
                resetMerged();
            }
            answer = Math.max(answer, findMaxBlock());
            map = originalMap;
            return;
        }

        for (int i = 0; i < 4; i++) {
            plans[depth] = i;
            DFS(depth + 1);
        }
    }

    private static Block[][] copyMap(Block[][] original) {
        Block[][] copy = new Block[N][N];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (original[r][c] != null) {
                    copy[r][c] = new Block(original[r][c].size);
                }
            }
        }
        return copy;
    }

    public static int findMaxBlock() {
        int maxValue = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (map[r][c] != null) {
                    maxValue = Math.max(maxValue, map[r][c].size);
                }
            }
        }
        return maxValue;
    }

    public static void moveBlock(int direction) {
        switch (direction) {
            case 0: moveDown(); break;
            case 1: moveUp(); break;
            case 2: moveLeft(); break;
            case 3: moveRight(); break;
        }
    }

    private static void moveDown() {
        for (int c = 0; c < N; c++) {
            int count = N - 1;
            for (int r = N - 2; r >= 0; r--) {
                if (map[r][c] != null) {
                    if (map[count][c] == null) {
                        map[count][c] = new Block(map[r][c].size);
                        map[r][c] = null;
                    } else if (map[count][c].size == map[r][c].size && !map[count][c].merged) {
                        map[count][c].size *= 2;
                        map[count][c].merged = true;
                        map[r][c] = null;
                        count--;
                    } else {
                        count--;
                        map[count][c] = new Block(map[r][c].size);
                        if (count != r) {
                            map[r][c] = null;
                        }
                    }
                }
            }
        }
    }

    private static void moveUp() {
        for (int c = 0; c < N; c++) {
            int count = 0;
            for (int r = 1; r < N; r++) {
                if (map[r][c] != null) {
                    if (map[count][c] == null) {
                        map[count][c] = new Block(map[r][c].size);
                        map[r][c] = null;
                    } else if (map[count][c].size == map[r][c].size && !map[count][c].merged) {
                        map[count][c].size *= 2;
                        map[count][c].merged = true;
                        map[r][c] = null;
                        count++;
                    } else {
                        count++;
                        map[count][c] = new Block(map[r][c].size);
                        if (count != r) {
                            map[r][c] = null;
                        }
                    }
                }
            }
        }
    }

    private static void moveLeft() {
        for (int r = 0; r < N; r++) {
            int count = 0;
            for (int c = 1; c < N; c++) {
                if (map[r][c] != null) {
                    if (map[r][count] == null) {
                        map[r][count] = new Block(map[r][c].size);
                        map[r][c] = null;
                    } else if (map[r][count].size == map[r][c].size && !map[r][count].merged) {
                        map[r][count].size *= 2;
                        map[r][count].merged = true;
                        map[r][c] = null;
                        count++;
                    } else {
                        count++;
                        map[r][count] = new Block(map[r][c].size);
                        if (count != c) {
                            map[r][c] = null;
                        }
                    }
                }
            }
        }
    }

    private static void moveRight() {
        for (int r = 0; r < N; r++) {
            int count = N - 1;
            for (int c = N - 2; c >= 0; c--) {
                if (map[r][c] != null) {
                    if (map[r][count] == null) {
                        map[r][count] = new Block(map[r][c].size);
                        map[r][c] = null;
                    } else if (map[r][count].size == map[r][c].size && !map[r][count].merged) {
                        map[r][count].size *= 2;
                        map[r][count].merged = true;
                        map[r][c] = null;
                        count--;
                    } else {
                        count--;
                        map[r][count] = new Block(map[r][c].size);
                        if (count != c) {
                            map[r][c] = null;
                        }
                    }
                }
            }
        }
    }

    private static void resetMerged() {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (map[r][c] != null) {
                    map[r][c].merged = false;
                }
            }
        }
    }
}
