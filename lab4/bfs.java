
import java.util.Scanner;

public class bfs {
	
	 bfs con;
	 bfs con1;
	 bfs con2;
	 bfs con3;
	 int num;
	 public static boolean visited [];
	

	public static void main(String args[]) {
		
		Scanner scnr = new Scanner(System.in);
		
		int n,m;
		
		System.out.println("please enter the number of vertices then edges: ");
		
		n = scnr.nextInt();
		m = scnr.nextInt();
		
		bfs A= new bfs(-1);
		A.con = new bfs(0);
		bfs temp = A.con;
		bfs temp2 = A.con;
		for(int i=0;i<n;i++) {
			//System.out.print("i am here ");
			temp.con = new bfs(i);
			temp2 = new bfs(i);
			//System.out.println(i);
			temp = temp.con;
			temp2 = temp2.con;
		}//of for till all vertices have been created
		
		temp = A.con;
		temp2 = A.con;
		for(int j=0;j<m;j++) {

			System.out.println("please enter orig node then target for the edge ");
			int h = scnr.nextInt();
			int g = scnr.nextInt();
			
			//System.out.println("hi");
			while(temp.num != h ) { temp = temp.con;}
			while(temp2.num != g) {  temp2 = temp2.con;}
			
			if(temp.con1 == null) {temp.con1 = temp2;}
			else if(temp.con2 == null) {temp.con2 = temp2;}
			else if(temp.con3 == null) {temp.con3 = temp2;}
			else {System.out.println("i only accounted for 3 edges per node sorry");}
			
			temp = A.con;
			temp2 = A.con;
		}//for edges setup
		 System.out.println("Graph set up complete :)...");
		 visited= new boolean[n]; //sets the visit array for bool
		 System.out.println("Ouutput: ");
		 BFS(A.con);//passing the length and the first node
		 
	}//of main
	
	public bfs(int no){
		 this.num = no;
		 
	}//of no
	
	public static void BFS(bfs A) {
		if(A == null) {return;}
		if(visited[A.num]) {
			return;
		}
		else {
			System.out.println(A.num);
			visited[A.num] = true;
			BFS(A.con1);
			BFS(A.con2);
			BFS(A.con3);
		}
	}//of BFS method()
	
}//of bfs
