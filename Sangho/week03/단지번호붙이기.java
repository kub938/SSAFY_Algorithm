import java.util.*;

public class 단지번호붙이기 {
    static int N;
    static int[][] board;

    static Queue<int[]> q;
    static List<Integer> list;

    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};

    static int size;

    public static int BFS(){
        // 하나의 집만 있어도 단지로 치는거임 그래서 1로 시작
        int sum = 1;

        size++;

        while (!q.isEmpty()){
            int[] temp = q.poll();
            int x = temp[0];
            int y = temp[1];

            board[x][y] = 0;

            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx >= 0 && ny >= 0 && nx < N && ny < N){
                    if(board[nx][ny] == 1){
                        board[nx][ny] = 0;
                        q.add(new int[]{nx,ny});
                        sum = sum + 1;
                    }
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();

        board = new int[N][N];

        q = new LinkedList<>();

        list = new ArrayList<>();

        size = 0;

        for(int r = 0; r < N; r++){
            String str = sc.next();
            for(int c = 0; c < N; c++){
                board[r][c] = str.charAt(c) - '0';
            }
        }

        for(int r = 0; r < N; r++){
            for(int c = 0; c < N; c++){
                if(board[r][c] == 1){
                    q.add(new int[]{r,c});
                    list.add(BFS());
                }
            }
        }

        list.sort(Comparator.naturalOrder());

        System.out.println(size);

        for (Integer integer : list) {
            System.out.println(integer);
        }



    }
}
