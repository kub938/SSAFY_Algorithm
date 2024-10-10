

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Shark {
    int r, c, speed, dir, size;

    public Shark(int r, int c, int speed, int dir, int size) {
        this.r = r;
        this.c = c;
        this.speed = speed;
        this.dir = dir;
        this.size = size;
    }

    @Override
    public String toString() {
        return "Shark{" +
                "r=" + r +
                ", c=" + c +
                ", speed=" + speed +
                ", dir=" + dir +
                ", size=" + size +
                '}';
    }
}

public class Main {
    static int row, col, m, score, kingPos;
    static int[][] delta = {{0, 0}, {-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    static ArrayList<Shark> sList = new ArrayList<>();

    public static void fishing() {
        kingPos += 1;
        ArrayList<Shark> fList = new ArrayList<>();

        for (int i = 0; i < sList.size(); i++) {         //잡을수 있는 상어 체크
            Shark target = sList.get(i);
            if (kingPos == target.c) {
                fList.add(target);
            }
        }

        if (fList.isEmpty()) return;                    //잡을 수 있는게 없으면 탈출

        Shark nearShark = fList.get(0);
        for (int i = 0; i < fList.size(); i++) {  //그중 젤 위에꺼 잡음
            if (nearShark.r > fList.get(i).r) {
                nearShark = fList.get(i);
            }
        }

        score += nearShark.size;
        sList.remove(nearShark);
    }

    public static void moveShark() {
        for (int i = 0; i < sList.size(); i++) {
            Shark a = sList.get(i);
            for (int j = 0; j < a.speed; j++) {
                if (a.dir == 1 || a.dir == 2) {  //상 하 일때
                    if (a.r == 1 && a.dir == 1 || a.r == row && a.dir == 2) {
                        a.dir = a.dir == 1 ? 2 : 1;
                    }
                    a.r = a.r + delta[a.dir][0];
                } else {          //좌, 우 일때

                    if (a.c == 1 && a.dir == 4 || a.c == col && a.dir == 3) {
                        a.dir = a.dir == 3 ? 4 : 3;
                    }
                    a.c = a.c + delta[a.dir][1];
                }
            }
        }
    }

    public static void samePos() {        //같은위치 찾고 먹음
        ArrayList<Shark> arr = new ArrayList<>();

        for (int i = 0; i < sList.size() - 1; i++) {                //10000 * 10000 
            for (int j = i+1; j < sList.size(); j++) {
                Shark a = sList.get(i);
                Shark b = sList.get(j);
                if (a.r == b.r && a.c == b.c) { //같은 좌표면
                    if (a.size > b.size) {
                        arr.add(b);
                    } else {
                        arr.add(a);
                    }
                }
            }
        }
        for (Shark shark : arr) {
            sList.remove(shark);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int speed = Integer.parseInt(st.nextToken());   //속도
            int dir = Integer.parseInt(st.nextToken());   //이동 방향
            int size = Integer.parseInt(st.nextToken());   //크기  -> 크기가
            Shark shark = new Shark(r, c, speed, dir, size);
            sList.add(shark);
        }

        kingPos = 0;
        while (kingPos != col+1) {
            fishing();
            moveShark();
            samePos();
        }
        System.out.println(score);
    }
}

