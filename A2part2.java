import java.util.Arrays;
import java.util.Random;

/**
 * 
 * This class investigates and compares the running time complexity of the implemented sorting methods with that of quick sort 
 * It does so by measuring the running time for different array sizes ranging from 10 to 1,000,000 (or higher)
 */
public class A2part2 {
    public static void main(String[] args) {
        int[] sizes = {10, 100, 1000, 10000, 100000, 1000000}; // Array sizes to test
        long startTime, endTime;

        System.out.println("\t\t\tmergeSortIter\tmergeSortRecur\t\tsort(int)");

        for (int n : sizes) {
        	
            String sizeString = formatSize(n);				//Convert the size to a formatted string
            int[] array1 = generateRandomArray(n);			//Generate a random array of size n
            int[] array2 = Arrays.copyOf(array1, n);		//Make a copy of array1 using Arrays.copyOf to ensure both merge sort methods
            int[] array3 = Arrays.copyOf(array1, n);

            startTime = System.currentTimeMillis();			//Start measuring the execution time
            A2.mergeSortIterative(array1);					//Call the mergeSortIterative method from class A2 to sort array1
            endTime = System.currentTimeMillis();			//End measuring the execution time
            long mergeSortIterTime = endTime - startTime;	//Calculate the difference

            startTime = System.currentTimeMillis();			//Start measuring the execution time
            A2.mergeSortRecursive(array2);					//Call the mergeSortRecursive method from class A2 to sort array2
            endTime = System.currentTimeMillis();			//End measuring the execution time
            long mergeSortRecurTime = endTime - startTime;	//Calculate the difference

            startTime = System.currentTimeMillis();			//Start measuring the execution time
            Arrays.sort(array3);							//Sort array3 with using Arrays.sort
            endTime = System.currentTimeMillis();			//End measuring the execution time 
            long sortTime = endTime - startTime;			//Calculate the difference

            //Print the results for the current size
            System.out.printf("n = %-6s\t%10d ms\t%14d ms\t%9d ms%n", sizeString, mergeSortIterTime, mergeSortRecurTime, sortTime);
        }
    }
    
    /**
     *
     * @param size the size of the array
     * @return the random array
     * 
     * It generates a random array of the given size
     */
    private static int[] generateRandomArray(int size) {
        int[] array = new int[size];			//Create an array named "array" with the specified size
        Random random = new Random();			//Create a new instance named "random"

        for (int i = 0; i < size; i++) { 	
            array[i] = random.nextInt();		//Use the random.nextInt() for generating a random integer 
        }

        return array;							//Returns the generated array
    }
    
    /**
     * 
     * @param size the given size
     * @return the formatted size of string
     * 
     * It formats the given array to a string
     */
    private static String formatSize(int size) {
    	
        if (size >= 1000000)					//Check if the size is greater than or equal to 1000000
            return (size / 1000000) + "M";		//If true, it divides the size by 1000000 and concatenates "M" for representing millions
        			
        if (size >= 1000)						//Check if the size is greater than or equal to 1000
            return (size / 1000) + "k";			//If true, it divides the size by 1000 and concatenates "K" for representing thousands
        
        return String.valueOf(size);			//If the size is less than 1000, it converts the size to a string and returns the string representation of the size
    }
}
