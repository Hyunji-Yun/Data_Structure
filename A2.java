public class A2 {

	 /**
	 * @param a is the array that is input and needs to be sorted
	 * 
	 * This method sorts an array of integers iteratively
	 * 
	 */
	public static void mergeSortIterative(int[] a) {
	        int size = a.length;	//'size' is created as the number of integers in the array
        
	        for (int p = 1; p < size; p *= 2) {							//for loop, 'p' doubles every loop as long as it is smaller than size of array
	            for (int low = 0; low <= size - p; low += 2 * p) {		//for loop, goes through each of the sub arrays of integers 
	                int high = Math.min(low + 2 * p - 1, size - 1);		//'high' is created and set as the index of the last integer being merged in the sub array
	                int mid = low + p - 1;								//'mid' is created and set as the index of the middle integer being merged in the sub array
	                mergeI(a, low, mid, high);							//merges the sub array using the mergeI function
	            }
	        }
	    }

    /**
     * @param a is the array that is being merged and sorted
     * @param low is the the first index of the sub array
     * @param mid is the middle index of the sub array 
     * @param high is the middle index of the sub array
     * 
     * This method merges and sorts the sub arrays and then sets it to the original array
     * 
     */
    public static void mergeI(int[] a, int low, int mid, int high) {
        int i = low;
        int j = mid + 1;
        int k = low;
        			// index i,j,k are created to loop over them and sort the sub arrays
        			// 'i' goes over the left half of the array, 'j' goes over the right half and 'k' goes over 'tempArray'
        					 
        
        int[] tempArray = new int[high - low + 1];		//new temporary array where the sorted integers are stored.

        while (i <= mid && j <= high) {			//loops while index i is on the left half of the array AND index j is in the right half
            if (a[i] <= a[j]) {					//checks if integer on the left side is smaller than the integer on the right side
                tempArray[k - low] = a[i];		//if conditions are met, tempArray's current index is set to index i from the sub array
                i++;							//index of the left side increments by 1
            } else {							//if conditions are not met, the integer on the ride sight is smaller 
                tempArray[k - low] = a[j];		//the integer on the right side is set to tempArray's current index
                j++;							//index of the right side increments by 1
            }
            k++;								//index of the tempArray increments by 1
        }
       
        while (i <= mid) {						//loops while index i is on the left half of the array 
            tempArray[k - low] = a[i];			//current index of tempArray is set to the index i of the array
            i++;						
            k++;
            									//i and k increment by 1
        }
        
        while (j <= high) {						//loops while index i is on the right half of the array 
            tempArray[k - low] = a[j];			//current index of tempArray is set to the index j of the array
            j++;
            k++;
            									//j and k increment by 1
        }

        for (int m = 0; m < tempArray.length; m++) {	//for loop that sets the original arrays indexes to the sorted tempArray. 
            a[low + m] = tempArray[m];
        }
    }
   
  
    /**
     * @param a is the array that is input and needs to be sorted
     * 
     * This method sorts an array of integers recursively
     * 
     */
    public static void mergeSortRecursive(int[] a) {
		int size = a.length;		//creates variable 'size' as the number of integers in the array
		if (size <= 1) {				//checks if the size of the array is 1 or less
			return;						//if condition is true, that means array is already sorted
		}
		else {
			int mid = size/2;							//creates variable 'mid' as the middle in the array
			int[] leftArray = new int[mid];				//creates a new array, 'leftArray' that is the left half of the array,a	
			int[] rightArray = new int[size - mid];	//creates a new array, 'rightArray' that is the right half of the array,a

			int i = 0;					//index i initialized to 0
			int j = 0;					//index j initialized to 0
			
			for (; i < size; i++) {		//for loop goes through each integer in the array, a
				if (i < mid) {				//if index i is in the left half of the array,
					leftArray[i] = a[i];	//that integer is added to the leftArray in the same index
				}
				else {
					rightArray[j] = a[i];	//if index i is not in the left half of the array, it is in the right half
					j++;					//that integer is added to the rightArray starting from the 0 index (j is 0 initially) then incrementing by 1
				}
			}
			mergeSortRecursive(leftArray);		//the function is called again on leftArray
			mergeSortRecursive(rightArray); 	//the function is called again on rightArray 
			mergeR(leftArray, rightArray, a);	//the leftArray and rightArray are merged using the mergeR function
		}

	}

	/**
	 * @param leftArray is the first half of the array, a
	 * @param rightArray is the other half of the array, a
	 * @param a is the array that is sorted in the function
	 * 
	 * This method merges and sorts the sub arrays and sets it to the original array
	 * 
	 */
	public static void mergeR(int[] leftArray, int[] rightArray, int[] a) {
		int leftSize = leftArray.length;	//creates variable leftSize as the size of leftArray
		int rightSize = rightArray.length;	//creates variable rightSize as the size of RightArray
		int i = 0, l = 0, r = 0;			//index i,l,r are initialized to 0
	
		while (l < leftSize && r < rightSize) {		//loops while leftArray has more integers than index l AND rightArray has more elements than index r
			if (leftArray[l] < rightArray[r]) {		//checks if integer in leftArray is smaller than rightArray 
				a[i] = leftArray[l];				//if conditions are met, array a's index i is set to the integer in index l in the leftArray 
				i++;
				l++;
													//index i and l are incremented so the next integers can be compared and set to the next index in the array, a
			}
			else {							//if conditions are not met, rightArray's integer is smaller
				a[i] = rightArray[r];		//array a's index i is set to the integer in index r in the rightArray
				i++;					
				r++;
											//index i and r are incremented so the next integers can be compared and set to the next index in the array, a
			}
		}
		while (l < leftSize) {			//loops while leftArray has more integers than index l
			a[i] = leftArray[l];		//array a's index i is set to the integer in index l in the leftArray
			i++;
			l++;
										//index i and l are incremented
		}
		while (r < rightSize) {			//loops while rightArray has more integers than index r
			a[i] = rightArray[r];		//array a's index i is set to the integer in index r in the rightArray
			i++;
			r++;
										//index i and r are incremented
		}
	}
}