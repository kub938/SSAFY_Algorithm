import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PaperFolding {

    static int k; // 종이 접기 횟수
    static int holePosition; // 2x2 종이에서 구멍 위치
    static char lastHorizontalFold, lastVerticalFold; // 마지막 접기 방향

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine()); // 종이 접기 횟수 읽기
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        // 마지막 접기 방향 읽기
        for (int i = 0; i < 2 * k; i++) {
            char foldDirection = st.nextToken().charAt(0);
            if (foldDirection == 'D' || foldDirection == 'U') {
                lastHorizontalFold = foldDirection;
            } else {
                lastVerticalFold = foldDirection;
            }
        }

        holePosition = Integer.parseInt(br.readLine()); // 구멍의 위치 읽기

        // 2x2 종이에서 구멍 위치 결정
        if (lastVerticalFold == 'L' && lastHorizontalFold == 'U') {
            holePosition = holePosition;
        } else if (lastVerticalFold == 'R' && lastHorizontalFold == 'U') {
            holePosition = getVerticalFoldPosition(holePosition);
        } else if (lastVerticalFold == 'L' && lastHorizontalFold == 'D') {
            holePosition = getHorizontalFoldPosition(holePosition);
        } else if (lastVerticalFold == 'R' && lastHorizontalFold == 'D') {
            holePosition = getHorizontalFoldPosition(getVerticalFoldPosition(holePosition));
        }

        // 2x2 종이의 각 구멍 위치 계산
        int topLeft = holePosition;
        int topRight = getVerticalFoldPosition(holePosition);
        int bottomLeft = getHorizontalFoldPosition(holePosition);
        int bottomRight = getHorizontalFoldPosition(getVerticalFoldPosition(holePosition));

        // 최종 결과 출력
        printPaper(topLeft, topRight, bottomLeft, bottomRight);
    }

    // 세로 방향 대칭 구멍 위치
    private static int getVerticalFoldPosition(int position) {
        switch (position) {
            case 0: return 1;
            case 1: return 0;
            case 2: return 3;
            case 3: return 2;
            default: return position;
        }
    }

    // 가로 방향 대칭 구멍 위치
    private static int getHorizontalFoldPosition(int position) {
        switch (position) {
            case 0: return 2;
            case 1: return 3;
            case 2: return 0;
            case 3: return 1;
            default: return position;
        }
    }

    // 2x2 종이의 구멍 위치를 출력
    private static void printPaper(int topLeft, int topRight, int bottomLeft, int bottomRight) {
        int size = (int) Math.pow(2, k);
        for (int i = 0; i < size; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < size / 2; j++) {
                    System.out.print(topLeft + " ");
                    System.out.print(topRight + " ");
                }
            } else {
                for (int j = 0; j < size / 2; j++) {
                    System.out.print(bottomLeft + " ");
                    System.out.print(bottomRight + " ");
                }
            }
            System.out.println();
        }
    }
}
