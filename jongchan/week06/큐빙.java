import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * start 19:15
 * 예상: 빡구현
 */

public class Main {

    static char[][][] cube;
    static char[] color = {'w', 'r', 'b', 'o', 'g', 'y'};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            init();
            int n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < n; i++) {
                String string = st.nextToken();
                char cmd = string.charAt(0);
                char direction = string.charAt(1);
                solve(cmd, direction);

            }

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    sb.append(cube[0][i][j]);
                }
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }

    private static void init() {
        cube = new char[6][3][3];
        for (int i = 0; i < 6; i++) {
            fill(i, color[i]);
        }
    }

    private static void fill(int index, char ch) {
        for (int i = 0; i < 3; i++) {
            Arrays.fill(cube[index][i], ch);
        }
    }

    private static void solve(char cmd, char direction) {

        switch (cmd) {
            case 'U':
                if (direction == '+') {
                    u();
                } else {
                    u();
                    u();
                    u();
                }
                break;
            case 'D':
                if (direction == '+') {
                    d();
                } else {
                    d();
                    d();
                    d();
                }
                break;
            case 'L':
                if (direction == '+') {
                    l();
                } else{
                    l();
                    l();
                    l();
                }
                break;
            case 'R':
                if (direction == '+') {
                    r();
                } else {
                    r();
                    r();
                    r();
                }
                break;
            case 'F':
                if (direction == '+') {
                    f();
                } else {
                    f();
                    f();
                    f();
                }
                break;
            case 'B':
                if (direction == '+') {
                    b();
                } else {
                    b();
                    b();
                    b();
                }
                break;
        }

    }

    private static void u() {
        rotate(0);
        char[] temp = new char[3];
        System.arraycopy(cube[1][0], 0, temp, 0, 3);

        for (int i = 0; i < 3; i++) {
            cube[1][0][i] = cube[2][2 - i][0];
        }
        for (int i = 0; i < 3; i++) {
            cube[2][i][0] = cube[3][2][i];
        }
        for (int i = 0; i < 3; i++) {
            cube[3][2][i] = cube[4][2 - i][2];
        }
        for (int i = 0; i < 3; i++) {
            cube[4][i][2] = temp[i];
        }

    }

    private static void d() {
        rotate(5);
        char[] temp = new char[3];
        System.arraycopy(cube[3][0], 0, temp, 0, 3);

        for (int i = 0; i < 3; i++) {
            cube[3][0][i] = cube[2][i][2];
        }
        for (int i = 0; i < 3; i++) {
            cube[2][i][2] = cube[1][2][2-i];
        }
        for (int i = 0; i < 3; i++) {
            cube[1][2][i] = cube[4][i][0];
        }
        for (int i = 0; i < 3; i++) {
            cube[4][i][0] = temp[2-i];
        }
    }

    private static void l() {
        rotate(4);
        char[] temp = new char[3];
        for (int i = 0; i < 3; i++) {
            temp[i] = cube[0][i][0];
        }
        for (int i = 0; i < 3; i++) {
            cube[0][i][0] = cube[3][i][0];
        }
        for (int i = 0; i < 3; i++) {
            cube[3][i][0] = cube[5][i][0];
        }
        for (int i = 0; i < 3; i++) {
            cube[5][i][0] = cube[1][i][0];
        }
        for (int i = 0; i < 3; i++) {
            cube[1][i][0] = temp[i];
        }
    }

    private static void r() {
        rotate(2);

        char[] temp = new char[3];
        for (int i = 0; i < 3; i++) {
            temp[i] = cube[0][i][2];
        }
        for (int i = 0; i < 3; i++) {
            cube[0][i][2] = cube[1][i][2];
        }
        for (int i = 0; i < 3; i++) {
            cube[1][i][2] = cube[5][i][2];
        }
        for (int i = 0; i < 3; i++) {
            cube[5][i][2] = cube[3][i][2];
        }
        for (int i = 0; i < 3; i++) {
            cube[3][i][2] = temp[i];
        }

    }

    private static void f() {
        rotate(1);
        char[] temp = new char[3];
        System.arraycopy(cube[5][0], 0, temp, 0, 3);
        for (int i = 0; i < 3; i++) {
            cube[5][0][i] = cube[2][2][2-i];
        }
        System.arraycopy(cube[0][2], 0, cube[2][2], 0, 3);
        System.arraycopy(cube[4][2], 0, cube[0][2], 0, 3);
        for (int i = 0; i < 3; i++) {
            cube[4][2][i] = temp[2-i];
        }
    }

    private static void b() {
        rotate(3);
        char[] temp = new char[3];
        System.arraycopy(cube[0][0], 0, temp, 0, 3);
        System.arraycopy(cube[2][0], 0, cube[0][0], 0, 3);
        for (int i = 0; i < 3; i++) {
            cube[2][0][i] = cube[5][2][2-i];
        }
        for (int i = 0; i < 3; i++) {
            cube[5][2][i] = cube[4][0][2-i];
        }
        System.arraycopy(temp, 0, cube[4][0], 0, 3);
    }


    private static void rotate(int index) {
        char[][] temp = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                temp[j][2 - i] = cube[index][i][j];
            }
        }

        for (int i = 0; i < 3; i++) {
            System.arraycopy(temp[i], 0, cube[index][i], 0, 3);
        }
    }

}
