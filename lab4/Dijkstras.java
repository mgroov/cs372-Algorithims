

import java.util.Scanner;
//Matthew Groover
//11/6/20
//this program creates then searches for the shortest path to the largest node
public class Dijkstras {
	Dijkstras con;
	Dijkstras con1;
	Dijkstras con2;
	Dijkstras con3;
	 int num;
	 public static boolean visited [];
	

	public static void main(String args[]) {
		
		Scanner scnr = new Scanner(System.in);
		
		int n,m;
		
		System.out.println("please enter the number of vertices then edges: ");
		
		n = scnr.nextInt();
		m = scnr.nextInt();
		
		Dijkstras A= new Dijkstras(-1);
		A.con = new Dijkstras(1);
		Dijkstras temp = A.con;
		Dijkstras temp2 = A.con;
		for(int i=1;i<n+1;i++) {
			//System.out.print("i am here ");
			temp.con = new Dijkstras(i);
			temp2.con = new Dijkstras(i);
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
			while(temp.num != h) { temp = temp.con;}
			while(temp2.num != g) {  temp2 = temp2.con;}
			
			if(temp.con1 == null) {temp.con1 = temp2;}
			else if(temp.con2 == null) {temp.con2 = temp2;}
			else if(temp.con3 == null) {temp.con3 = temp2;}
			else {System.out.println("i only accounted for 3 edges per node sorry");}
			
			temp = A.con;
			temp2 = A.con;
		}//for edges setup
		 System.out.println("Graph set up complete :)...");
		 visited= new boolean[n+1]; //sets the visit array for bool
		int a = dijkstras(A.con,0,n);//passing the length and the first node
		 System.out.println("Output: " + a);
	}//of main
	
	public Dijkstras(int no){
		 this.num = no;
		 
	}//of no
	
	public static int dijkstras(Dijkstras A,int count,int target) {
		if(A == null) {return 0;}
		if(A.num == target) {return count;}
		count++;
		int counts[] = new int[3];
		 counts[0] =  dijkstras(A.con1,count,target);
		 counts[1] =  dijkstras(A.con2,count,target);
		 counts[2] =  dijkstras(A.con3,count,target);
		 int minval =100;
		 for(int i=0;i<counts.length;i++){ 
		      if(counts[i] < minval && counts[i]>0){ 
		        minval = counts[i]; 
		      } 
		    }//of find minval
		 return minval;
	}//of dijkstras 
}//of dijkstras
