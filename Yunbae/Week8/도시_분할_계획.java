import java.util.*;
 
public class Main {    
 
    static int n, m;
    static PriorityQueue<Node> q;
    static int[] parent;
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
 
        n = scan.nextInt();
        m = scan.nextInt();
        
        q = new PriorityQueue<>();
        for(int i = 0; i < m; i++) {
            int s = scan.nextInt();
            int e = scan.nextInt();
            int cost = scan.nextInt();
            q.offer(new Node(s, e, cost));
        }
        
        parent = new int[n + 1];
        System.out.println(kruskal());
    }
    
    public static int kruskal() {
        for(int i = 1; i <= n; i++) {
            parent[i] = i;
        }
        
        int count = 0;
        int dist = 0; //현재 까지의 최소 간선 경로 값의 합
        while(count < n - 2) { // n - 2개의 간선을 선택한다.
            Node node = q.poll();
            int p1 = find(node.s);
            int p2 = find(node.e);
            
            if(p1 != p2) {
                union(p1, p2);
                dist += node.cost;
                count++; // 싸이클이 발생되지 않아 최소 간선으로 선택된 경우에만 count++를 해준다.
            }
        }
        return dist;
    }
    
    public static void union(int a, int b) {
        parent[a] = b;
    }
    
    public static int find(int a) {
        if(parent[a] == a) return a;
        else return parent[a] = find(parent[a]);
    }
    
    public static class Node implements Comparable<Node> {
        int s;
        int e;
        int cost;
        
        public Node(int s, int e, int cost) {
            this.s = s;
            this.e = e;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Node n1) {
            return this.cost - n1.cost;
        }
    }
}
