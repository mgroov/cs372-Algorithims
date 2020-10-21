package lab3;

public class Graph {
	
	String Name;
	Graph con1,con2,con3;
    static int visitcount;
	boolean visited;
	int pre;
	int pos;
	static int printcount =0;
	
	public Graph(String s){
		
		Name = s;
		con1 = con2 = con3 = null;
		visited=false;
		pre =0;
		pos=0;
	}//of new Graph node
	
	public static void main(String[] args) {
		
		Graph A= new Graph("A");
		 
		A.con1 = new Graph("B");
		A.con2 = new Graph("E");
		
		A.con1.con1 = A.con2;
		A.con1.con2 = new Graph("C");
		A.con1.con3 = A;
		
		A.con2.con1= A.con1;
		A.con2.con2 = new Graph("F");
		A.con2.con3 = A;
		
		A.con1.con2.con1 = A.con2.con2;
		A.con1.con2.con2 = A.con1;
		
		A.con2.con2.con1 = A.con1.con2;
		A.con2.con2.con2 = new Graph("I");
		
		DFS(A);
		print(A);
		print(A.con1);
		print(A.con2);
		print(A.con1.con2);
		print(A.con2.con2);
		print(A.con2.con2.con2);

	}//of main
	
	    
	static void DFS(Graph a) {
		if(a==null) {return;}
		visitcount +=1;
		if(a.visited) {a.pos = visitcount; return;}
		else {
		a.pre =visitcount;
		a.visited = true;
		}
		DFS(a.con1);
		DFS(a.con2);
		DFS(a.con3);
	}//of DFS
	
	static void print(Graph A) {
		if(printcount == 0) {
			System.out.println("Vert Pre Pos");
			printcount+=1;
		}//of first print
		
		System.out.println(A.Name + "     " + A.pre + "    "+ A.pos);
		
	}
}//of Graph
