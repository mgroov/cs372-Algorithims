

import java.util.*;


//Matthew Groover
//10/23/20
//This program attempts to implement dfs 
//in order so solve the water jug problem

public class WaterJug {

	static int[][][] H = new int[11][8][5];
	static int[] initial = new int[3];
	static int[] endstate = new int[3];
	
	
	public static void main(String[] args) {
	
		Scanner scnr = new Scanner(System.in);
		
		System.out.println("Enter the three variables to represent the initial state: ");
		
		initial[0] = scnr.nextInt();
		initial[1] = scnr.nextInt();
		initial[2] = scnr.nextInt();
		
		System.out.println("Enter the three variables to represent end state: ");
		
		endstate[0] = scnr.nextInt();
		endstate[1] = scnr.nextInt();
		endstate[2] = scnr.nextInt();
		
		boolean a =Waterjug(initial[0],initial[1],initial[2],endstate[0],endstate[1],endstate[2]);
		
		if(!a) {
			System.out.print("no pour sequence from (" + initial[0]+ ", " + initial[1] + ", "+ initial[2]+") to");
			System.out.println(" (" + endstate[0]+ ", " + endstate[1] + "," + endstate[2]+")" );
		}//if the pour sequence is impossible 
		
	}//of main
	
	public static boolean Waterjug(int initial1, int initial2, int initial3, int endstate1, int endstate2, int endstate3) {
		
		if(H[initial1][initial2][initial3]==1) { 
			return false;
		}//if node has already been visited end check
		
		System.out.println("("+initial1+", "+initial2+", "+initial3+")"); //prints visited node 
		H[initial1][initial2][initial3] = 1; //says that node has been visited 
		
		if(initial1==endstate1 && initial2==endstate2 && initial3==endstate3) {
			System.out.println("finished at state: ("+initial1+", "+initial2+", "+initial3+")"); //if the endstate has been found
			return true;
		}//if at end state 
		else {
		
		//pour from 1 to 2
		int temp = initial1;
		int temp2 = initial2;
		while(temp2<7 && temp>0) {
			temp--;
			temp2++;
		}//of pour from cup 1 to 2
		if(Waterjug(temp,temp2,initial3,endstate1,endstate2,endstate3)) { //these statments calculate the edges that the dfs is able to go along 
			return true;
		}
		
		
		//pour from 1 to 3 cup 
		 temp = initial1;
		 temp2 = initial3;
		while(temp2<4 && temp>0) {
			temp--;
			temp2++;
		}//of pour from cup 1 to 3
		if(Waterjug(temp,initial2,temp2,endstate1,endstate2,endstate3)) {
			return true;
		}
		
		
		//pour from 2 to 1
		 temp = initial2;
		 temp2 = initial1;
		while(temp2<10 && temp>0) {
			temp--;
			temp2++;
		}//of pour from cup 2 to 1
		if(Waterjug(temp2,temp,initial3,endstate1,endstate2,endstate3)) {
			return true;
		}
		
		//pour from 2 to 3
		 temp = initial2;
		 temp2 = initial3;
		while(temp2<4 &&temp>0) {
			temp--;
			temp2++;
		}//of pour from cup 2 to 3
		if(Waterjug(initial1,temp,temp2,endstate1,endstate2,endstate3)) {
			return true;
		}
		
		//pour from 3 to 1
		 temp = initial3;
		 temp2 = initial1;
		while(temp2<10 &&temp>0) {
			temp--;
			temp2++;
		}//of pour from cup 3 to 1
		if(Waterjug(temp2,initial2,temp,endstate1,endstate2,endstate3)) {
			return true;
		}
		
		
		//pour from 3 to 2
		 temp = initial3;
		 temp2 = initial2;
		while(temp2<7 &&temp>0) {
			temp--;
			temp2++;
		}//of pour from cup 3 to 2
		if(Waterjug(initial1,temp2,temp,endstate1,endstate2,endstate3)) {
		  return true;	
		}
		
		}//of if disired hasn't been reached 
		return false;//if all branches are false 
		
	}//of waterjug
	
}//WaterJug 
