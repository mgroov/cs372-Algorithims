package cs372;

import java.util.*;
//Matthew Groover 
//lab1 fibbonacci numbers 
//9/2/2020

public class lab1 {
	
	public static void main(String[]args) {
		new lab1();
	}//of main

	public lab1() {
		// TODO Auto-generated constructor stub
		System.out.println("enter 1.for exponential");
		System.out.println("enter 2.for polynomial");
		System.out.println("enter 3.for test cases");
		Scanner input = new Scanner(System.in);
		int in =  input.nextInt();
		
		switch(in) {
		case 1:{
			System.out.println("Please enter a number");
			long n = input.nextInt();
			
			long startTime = System.nanoTime();
			
			long fibb = exponential(n);
			
			long endTime = System.nanoTime();

			long duration = (endTime - startTime)/1000000 ;
			
			System.out.println("The program calculated "+ n + " places");
			System.out.println("It took  "+  duration + " ms to calculate");
			System.out.println("The answer is " + fibb);
			break;
			
		}//of case exponential
		case 2:{
			System.out.println("Please enter a number");
			long n = input.nextLong();
			
			long startTime = System.nanoTime();
			
			long fibb = polynomial(n);
			
			long endTime = System.nanoTime();

			long duration = (endTime - startTime)/1000000 ;
			
			System.out.println("The program calculated "+ n + " places");
			System.out.println("It took  "+  duration + " ms to calculate");
			System.out.println("The answer is " + fibb);
			break;
		}
		case 3:{
			testExponential();
			testPolynomial();
			break;
		}
		}//of switch case
	}//of lab1 

	long exponential(long n) {
		if(n==0) {return 0;}
		if(n==1) {return 1;}
		long i = exponential(n-1);
		long j = exponential(n-2);
		i = i + j;
	    return (i);
	}//of exponential
	
	long polynomial(long n) {
		if (n ==0) {return 0;}
		long f[] = new long[(int)n + 1];
		f[0]=0;
		f[1]=1;
		for(int i=2;i<=n;i++) {
			f[i]=f[i-1]+f[i-2];
		}//of i 
		return f[(int)n];
	}//of polynomial
	
	void testExponential() {
		int[] Answer = {0,1,1,2,3,5,8,13,21,34,55};
		for(int i=0;i<11;i++) {
			if(Answer[i] != exponential(i)) {
				System.out.println("ERROR, exponential test failed");
				return;
			}
		}//of for check
		System.out.println("Exponential test PASSED!!!!");
		return;
	}//of testExponential
	
	void testPolynomial() {
		int[] Answer = {0,1,1,2,3,5,8,13,21,34,55};
		for(int i=0;i<11;i++) {
			if(Answer[i] != polynomial(i)) {
				System.out.println("ERROR, polynomial test failed");
				return;
			}
		}//of for check
		System.out.println("Polynomial test PASSED!!!!");
		return;
	}//of testExponential
}
