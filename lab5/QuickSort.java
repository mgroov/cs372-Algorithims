/*
 * 	Quicksort algorithm 
 * 
 * 	from https://www.geeksforgeeks.org/quick-sort/
 */
// Java program for implementation of QuickSort 
class QuickSort 
{ 
    /* This function takes last element as pivot, 
       places the pivot element at its correct 
       position in sorted array, and places all 
       smaller (smaller than pivot) to left of 
       pivot and all greater elements to right 
       of pivot */
    int partition(int arr[], int low, int high) 
    { 
        int pivot = arr[high];  
        int i = (low-1); // index of smaller element 
        for (int j=low; j<high; j++) 
        { 
            // If current element is smaller than the pivot 
            if (arr[j] < pivot) 
            { 
                i++; 
  
                // swap arr[i] and arr[j] 
                int temp = arr[i]; 
                arr[i] = arr[j]; 
                arr[j] = temp; 
            } 
        } 
  
        // swap arr[i+1] and arr[high] (or pivot) 
        int temp = arr[i+1]; 
        arr[i+1] = arr[high]; 
        arr[high] = temp; 
  
        return i+1; 
    } 
  
  
    /* The main function that implements QuickSort() 
      arr[] --> Array to be sorted, 
      low  --> Starting index, 
      high  --> Ending index */
    void sort(int arr[], int low, int high) 
    { 
        if (low < high) 
        { 
            /* pi is partitioning index, arr[pi] is  
              now at right place */
            int pi = partition(arr, low, high); 
  
            // Recursively sort elements before 
            // partition and after partition 
            sort(arr, low, pi-1); 
            sort(arr, pi+1, high); 
        } 
    } 
  
    /* A utility function to print array of size n */
    static void printArray(int arr[]) 
    { 
        int n = arr.length; 
        for (int i=0; i<n; ++i) 
            System.out.print(arr[i]+" "); 
        System.out.println(); 
    } 
    
    /*
     * 	sortByRatio
     * 	sortRatioRecurse
     * 	partitionRatio
     * 
     * 	Added by Zachary Holt
     * 
     * 	Precon:	2 int arrays, item profit and item weight
     * 	Postcon: an int array of the new order of indexes sorted by profit/weight
     */
    
    public int[] sortByRatio(int[] profit, int[] weight) {
    	//load up the order array
    	int indOrder[] = new int[profit.length]; 
    	for(int i=0; i < profit.length; i++) indOrder[i] = i;
    	
    	sortRatioRecurse(indOrder, profit, weight, 0, profit.length - 1);
    	
    	return indOrder;
    }//end sortByRatio
    
    
    /* The main function that implements QuickSort() 
    arr[] --> Array to be sorted, 
    low  --> Starting index, 
    high  --> Ending index */
	void sortRatioRecurse(int[] pos, int[] profit, int[] weight, int low, int high) { 
		if (low < high) { 
		/* pi is partitioning index, arr[pi] is  
		now at right place */
		int pi = partitionRatio(pos, profit, weight, low, high); 
		
		// Recursively sort elements before 
		// partition and after partition 
		sortRatioRecurse(pos, profit, weight, low, pi-1); 
		sortRatioRecurse(pos, profit, weight, pi+1, high); 
	    }//end if 
	}//end sortRatioRecurse 
    
	/* This function takes last element as pivot, 
	places the pivot element at its correct 
	position in sorted array, and places all 
	smaller (smaller than pivot) to left of 
	pivot and all greater elements to right 
	of pivot */
	int partitionRatio(int[] pos, int[] profit, int[] weight, int low, int high) { 
		float pivot = (float)profit[pos[high]] / weight[pos[high]];  
		int i = (low-1); // index of smaller element 
		for (int j=low; j<high; j++) { 
			float curRatio = ((float)profit[pos[j]] / weight[pos[j]]);
			// If current element is smaller than the pivot 
			// if they are equal, choose the one with higher profit
			if (curRatio > pivot ||
				(curRatio == pivot && 
				profit[pos[j]] > profit[pos[high]])) { 
				i++; 
				// swap arr[i] and arr[j] 
				int temp = pos[i]; 
				pos[i] = pos[j]; 
				pos[j] = temp; 
	       }//end if 
		}//end for
	
		// swap arr[i+1] and arr[high] (or pivot) 
		int temp = pos[i+1]; 
		pos[i+1] = pos[high]; 
		pos[high] = temp; 
	
		return i+1; 
	}//end partitionRatio

    // Driver program 
    public static void main(String args[]) 
    { 
        int arr[] = {10, 7, 8, 9, 1, 5}; 
        int n = arr.length; 
  
        QuickSort ob = new QuickSort(); 
        ob.sort(arr, 0, n-1); 
  
        System.out.println("sorted array"); 
        printArray(arr); 
    } 
} 
/*This code is contributed by Rajat Mishra */
