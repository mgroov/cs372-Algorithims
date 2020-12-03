
//Matthew Groover
//12/2/20
//this program implements both the greedy and dynamic of the knapsack problem

import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;

class KnapSack {
	
	public static void main(String[] args) {
		new KnapSack();
	}
	
	KnapSack(){
		
		Scanner scnr = new Scanner(System.in);
		System.out.println("Enter 1:for Dynamic approach will full var entry");
		System.out.println("Enter 2:for Greedy approach will full var entry");
		System.out.println("Enter 3:for test case function");
		System.out.println("Enter 4:for rand time calc function");
		
		int input = scnr.nextInt();
		
		switch(input) {
		case 1:{
			System.out.println("enter the length of W:");
			input = scnr.nextInt();
			int [] tempw  = new int[input];
			
			for(int i=0;i<tempw.length;i++) {
			System.out.println("enter an item of W:");
			input = scnr.nextInt();
			tempw[i] =  input;
			}//of fill w
			
			System.out.println("enter the length of V:");
			input = scnr.nextInt();
			int [] tempv  = new int[input];
			
			for(int i=0;i<tempv.length;i++) {
			System.out.println("enter an item of V:");
			input = scnr.nextInt();
			tempv[i] =  input;
			}//of fill v
			
			System.out.println("enter M:");
			input = scnr.nextInt();
			
			knapsackdp(tempw,tempv,input);
			break;
		} //manual dp call
		case 2:{
			
			System.out.println("enter the length of W:");
			input = scnr.nextInt();
			int [] tempw  = new int[input];
			
			for(int i=0;i<tempw.length;i++) {
			System.out.println("enter an item of W:");
			input = scnr.nextInt();
			tempw[i] =  input;
			}//of fill w
			
			System.out.println("enter the length of V:");
			input = scnr.nextInt();
			int [] tempv  = new int[input];
			
			for(int i=0;i<tempv.length;i++) {
			System.out.println("enter an item of V:");
			input = scnr.nextInt();
			tempv[i] =  input;
			}//of fill v
			
			System.out.println("enter M:");
			input = scnr.nextInt();
			
			knapsackga(tempw,tempv,input);
			break;
			
		}// manual ga call 
		case 3:{
			testcases();
			break;
		}
		case 4:{
			System.out.println("enter n please: Be careful large numbers will run a veery long time");
			int n = scnr.nextInt();
			runtime(n);
			break;
		}
		default:{
			System.out.println("ERR inproper selection exiting....");
		}
		}//of input cases
		
	}//of knapSack start up

	
//================knapsackdp==================================================================================================
	public static void knapsackdp(int[] W, int[] V, int M) {
		
		boolean[] itemsUsed = new boolean[W.length];//prevents from visiting used sub probs
		for(int i=0; i < itemsUsed.length; i++) {
			itemsUsed[i] = false;
		}//setting used array to false as default 
		boolean possible = true;
		int numItems = V.length, itemNum, cappacity, itemcount = numItems - 1, size = M;
		
		int table[][] = new int[numItems][M + 1];
		
		for(itemNum=0; itemNum < numItems; itemNum++) {
			table[itemNum][0] = 0;
		}//initializing table as zero
		for(cappacity=0; cappacity <= M; cappacity++) {
			if(cappacity >= W[0]) {
				table[0][cappacity] = V[0];
			}//if the location isnt filled
		}//filling first row
		
		
		for(itemNum=1; itemNum < numItems; itemNum++) {
			for(cappacity=1; cappacity <= M; cappacity++) {
				
				int cost = cappacity-W[itemNum];
				if(cost >= 0) { //if valid item
						
					int curitemval = V[itemNum] + table[itemNum-1][cost]; // the current items val
					
					int pastitemval = table[itemNum-1][cappacity]; // the prev item val
					
					//fill element appropriately
					if(pastitemval >= curitemval) {
						table[itemNum][cappacity] = table[itemNum-1][cappacity];
					}//shift over
					else {
						table[itemNum][cappacity] = V[itemNum] + table[itemNum-1][cost];
					}//new val
					} else {
					table[itemNum][cappacity] = table[itemNum-1][cappacity];
					}//of if not enough capacity
			}//end itterate through table loop
		}//end iiterate through item
		
		//itterate back to find what was used
		cappacity = size; //get initial capacity to go back through
		for(itemNum=itemcount; itemNum >= 1; itemNum--) {
			if(table[itemNum][cappacity] != table[itemNum-1][cappacity]) {
				itemsUsed[itemNum] = true;
				cappacity -= W[itemNum];
			}//of if item is no longer the same 
		}//end item backwards loop
		if(table[0][cappacity] != 0) {
			itemsUsed[0] = true;
		}//the last item not reached in the for 
		
		
		//print out the combinations
		System.out.print("(W = {");
		for(int i=0; i < W.length; i++) { 
			System.out.print(W[i] + ", ");
		}//of W print
		System.out.print("}, V = {");
		for(int i=0; i < V.length; i++) {
			System.out.print(V[i] + ", ");
		}///of v print
		System.out.println("}, M = " + M);
		for(int i=0; i < itemsUsed.length; i++) {
			if(itemsUsed[i]) {
				possible = false;
				System.out.printf("Item %d:\tW = %d\tV = %d\n", i + 1, W[i], V[i]);
			}//end item used check
		}//end print usedItems loop
		if(possible) {
			System.out.println("No combinations possibe");
		}//if no items used no combinations are possible
		
		System.out.printf("max profit: %d\n\n", table[itemcount][size]);//print maximum profit
		
	}//end knacksapdp
	
//==========================knapsac greedy==========================================================	
	public static void knapsackga(int[] W, int[] V, int M) {
		
		int Greed = M; // the current lowest used or what we are greeding towards
		boolean[] itemsUsed = new boolean[W.length];
		for(int i=0; i < itemsUsed.length; i++) {
			itemsUsed[i] = false;
		}//initialized used as false
		boolean possible = true;
		int profit = 0;
		
		//sort provided to me by Zack Holt / Geek for Geeks
		QuickSort sortTool = new QuickSort();
		int[] sortedArray = sortTool.sortByRatio(V, W);
		
		for(int i=0; i < sortedArray.length; i++) {
			if(W[sortedArray[i]] <= Greed) {
				Greed -= W[sortedArray[i]];
				itemsUsed[sortedArray[i]] = true; // we mark the item as used so we do not repeat item usage 
			}//if it fits in our greed
		}//end of sorted array teaversal
		
		//print out the item combinations and the params
		System.out.print("knapsackga(W = {");
		for(int i=0; i < W.length; i++) { 
			System.out.print(W[i] + ", ");
		}//of W print
		System.out.print("}, V = {");
		for(int i=0; i < V.length; i++) {
			System.out.print(V[i] + ", ");
		}//of V print 
		System.out.println("}, M = " + M);
		for(int i=0; i < itemsUsed.length; i++) {
			if(itemsUsed[i]) {
				possible = false;
				profit += V[i];
				System.out.printf("Item %d:\tW = %d\tV = %d\n", i, W[i], V[i]);
			}//if item used no Items remains false 
		}//for loop that prints used items
		if(possible) {
			System.out.println("No combinations possible");
		}//of if no combo exists 
		System.out.printf("Total profit: %d\n\n", profit);//prints the final profit 
		
	}//end knacksapga
	
//===================testcase=================================================	
	public static void testcases(){
	
		int[] tempw = {4,5,7};
		int[] tempv = {2,3,4};
		int M = 10;
		
		System.out.println("DP test 1:");
		knapsackdp(tempw,tempv,M);
	
		System.out.println("GA test 1:");
		knapsackga(tempw,tempv,M);
		
		int[] tempw1 = {6,5,7,3,1};
		int[] tempv1 = {7,3,4,4,3};
		 M = 13;
		
		System.out.println("DP test 2:");
		knapsackdp(tempw1,tempv1,M);
	
		System.out.println("GA test 2:");
		knapsackga(tempw1,tempv1,M);
		
		int[] tempw2 = {2,3,5,5,3,7};
		int[] tempv2 = {3,4,10,9,6,13};
		 M = 15;
		
		System.out.println("DP test 3:");
		knapsackdp(tempw2,tempv2,M);
	
		System.out.println("GA test 3:");
		knapsackga(tempw2,tempv2,M);
		
		int[] tempw3 ={10,13,17,15};
		int[] tempv3 ={21,17,30,23};
		 M = 30;
		
		System.out.println("DP test 4:");
		knapsackdp(tempw3,tempv3,M);
	
		System.out.println("GA test 4:");
		knapsackga(tempw3,tempv3,M);
		
		int[] tempw4 ={5,4,7,6,3,4,2,1,7,6};
		int[] tempv4 ={3,1,3,2,1,3,2,3,1,4};
		 M = 30;
		
		System.out.println("DP test 5:");
		knapsackdp(tempw4,tempv4,M);
	
		System.out.println("GA test 4:");
		knapsackga(tempw4,tempv4,M);
		
	}//of test function
//===========================runtime====================================	
	public static void runtime(int n) {
		
		Random rand = new Random();
		long duration[] = new long[20];
		int [] tempw = new int[n];
		int [] tempv  = new int[n];
		long startime,endtime;
		
		
		
		int M  = n*10;
		int loc =0;
		
		for(int i=0;i<10;i++) {
			for(int j=0;j<n;j++) {
				tempw[j] = rand.nextInt(20)+10;
				tempv[j] = rand.nextInt(40)+10;
				//System.out.println(tempw[j] + ", " +tempv[j]);
			}//of fill arrays per run
			
			 startime = System.nanoTime(); //starting the timer
			 knapsackdp(tempw,tempv,M);
			 endtime = System.nanoTime();
			
			 duration[loc]= endtime -startime;
			 loc++;
			 
			 startime = System.nanoTime(); //starting the timer
			 knapsackga(tempw,tempv,M);
			 endtime = System.nanoTime();
			
			 duration[loc]= endtime-startime;
			 
			 
			 loc++;
			
		}//of the ten iterations 
		
		System.out.println("      DP    Ga");
		
		for(int i=0;i<duration.length;i++) {
			System.out.println(i+1+" : "+ duration[i]+"  "+duration[i+1]);
			i++;
		}//of print duration table
		
		
	}//of runtime
//=======================================================================	

}//of knapsack
