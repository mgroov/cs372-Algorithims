

//Matthew Groover 
//10/23/20
//This program implements a graph via a tree 
//it then pre forms dfs and returns pre and post numbers 


public class Graph {
	
	String Name;
	Graph con1,con2,con3;
	boolean visited;
	int pre;
	int pos;
	static int printcount =0;
	
	public Graph(String s){
		
		Name = s;
		con1 = con2 = con3 = null;  //These are the nodes and con1 2 and 3 represents the connections
		visited=false;
		pre =0;
		pos=0;
	}//of new Graph node
	
	public static void main(String[] args) {
		//==================================================================================
		Graph A= new Graph("A");
		 
		A.con1 = new Graph("B");
		A.con2 = new Graph("C");
		
		A.con1.con1 = A.con2;
		A.con1.con2 = new Graph("E");   //these implement the tree and their connections
		A.con1.con3 = A;
		
		A.con2.con1= A.con1;
		A.con2.con2 = new Graph("F");
		A.con2.con3 = A;
		
		A.con1.con2.con1 = A.con2.con2;
		A.con1.con2.con2 = A.con1;
		
		A.con2.con2.con1 = A.con1.con2;
		A.con2.con2.con2 = new Graph("I");
		//==================================================================================
		DFS(A,0);  //runs the dfs algorithim on the graph
		//==================================================================================
		print(A);
		print(A.con1);
		print(A.con2);
		print(A.con1.con2);  //prints the nodes and their data post dfs 
		print(A.con2.con2);
		print(A.con2.con2.con2);
        //==================================================================================
	}//of main
	
	//-----------------------------DFS implementation --------------------------------------    
	static int DFS(Graph a,int visitcount) {
		if(a==null) {return visitcount;} // due to the connection3 being null in some cases 
		if(a.visited) {return visitcount;} // we dont count repeat visits 
		
		visitcount +=1;
		a.pre =visitcount;  //sets the pre after the first visit to the node 
		a.visited = true;
		visitcount = DFS(a.con1,visitcount);
		visitcount = DFS(a.con2,visitcount); //traverses all connections 
		visitcount = DFS(a.con3,visitcount);
		a.pos = visitcount +1;  //sets post after travising all the nodes connections
		return(a.pos);//returns the updated number during each call
	}//of DFS
	//-------------------------------------------------------------------------------------
	static void print(Graph A) {
		if(printcount == 0) {
			System.out.println("Vert Pre Pos");   //helper print function 
			printcount+=1;
		}//of first print
		
		System.out.println(A.Name + "     " + A.pre + "    "+ A.pos);
		
	}
}//of Graph

