package algorithm;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.StringTokenizer;

class Edge{
	int start;
	int end;
	int weight;
	
	Edge(int start, int end, int weight){
		this.start = start;
		this.end = end;
		this.weight = weight;
	}
}


public class 학교_탐방하기 {
	static int n,m;
	static ArrayList<Edge> edgeList;
	static int[] parent;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		n++;
		m++;
		
		edgeList = new ArrayList<>();
		for (int i = 0; i < m; i++) {             //edge 초기화
				st = new StringTokenizer(br.readLine());
				
				int start = Integer.parseInt(st.nextToken());          
				int end = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				
				edgeList.add(new Edge(start, end, weight));
		}
		
		parent = new int[n];
		set();         
		
		Collections.sort(edgeList, (e1, e2) -> e1.weight - e2.weight);
		int worst = solution();
		set();
		
		Collections.sort(edgeList, (e1,e2) -> e2.weight - e1.weight);
		int best = solution();
		
		int result = worst*worst - best*best;
		System.out.println(result);

	}

  
	public static int solution() {
		int cnt = 0;
		for (int i = 0;i < edgeList.size(); i++) {
			Edge edge = edgeList.get(i);
			
			if(find(edge.start) != find(edge.end)) {
				union(edge.start, edge.end);
				
				if(edge.weight == 0) {
					 cnt++;
				}
			}
		}
		return cnt;
	}
	
	public static void set() {              //
		for (int i = 0; i < parent.length; i++) {
			parent[i] = i;
		}
	}
	
	public static int find(int x) {
		if(parent[x] == x)  return x;
		return parent[x] = find(parent[x]);
	}
	
	public static void union(int x, int y) {
		int aRoot = find(x);
		int bRoot = find(y);
		
		if(aRoot != bRoot) {
			parent[aRoot] = bRoot;
		}
	}
}
