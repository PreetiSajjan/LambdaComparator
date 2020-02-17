import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * Implements the recursive merge sort algorithm
 * using instance of comparator in lambda expression
 */
public class LambdaCompator<T>{

	//Creating List and Comparator of generic data type
	private List<T> array;
	private Comparator<T> comparator;
	
	/**
	 * Creating a generic method sort which assigns the user input to array
	 * @param strarray is the user input which needs to be sorted
	 * @param C is the comparator instance created in main
	 * @return the sorted list
	 */
	public List<T> sort(List<T> strarray, Comparator<T> C) { // array length must be a power of 2
		comparator = C;
		array = strarray;
		sort(0, array.size(), comparator);
		return array;
	} 
	 
	/**
	 * Creating sort method which recursively calls itself dividing the array into halves for sorting
	 * and then merging them
	 * @param low is the lowest index of array
	 * @param n is the length i.e highest index of array
	 * @param C is the comparator instance
	 */
	private void sort(int low, int n, Comparator<T> C) {
		if (n > 1) {
			//Split the array in halves
			int mid = n >> 1;
			
			//Sort the halves
			sort(low, mid, C);
			sort(low + mid, mid, C);
			
			//Merge the halves
			combine(low, n, 1, C);
		}
	} 
	 
	/**
	 * Creating combine method which recursively calls itself merging the divided array after comparing and sorting
	 * @param low is the lowest index of divided array
	 * @param n is the highest index of divided array
	 * @param st is number of left shifts to be done to merge the array
	 * @param C is the comparator instance
	 */
	private void combine(int low, int n, int st, Comparator<T> C) {
	    int m = st << 1;
		if (m < n) {
			combine(low, n, m, C);
			combine(low + st, n, m, C);
			for (int i = low + st; i + st < low + n; i += m)
				compareAndSwap(i, i + st, C);
		} else
			compareAndSwap(low, low + st, C);    
	} 
	 
	/**
	 * Creating compareAndSwap method which compares the elements and further calls swap method to swap them
	 * @param i is lower index of the pair
	 * @param j is higher index of the pair
	 * @param C is the comparator instance
	 */
	private void compareAndSwap(int i, int j, Comparator<T> C) {
	    if (C.compare(array.get(i), array.get(j)) > 0)
			swap(i, j);
	} 
	 
	/**
	 * Creating swap method to swap the elements
	 * @param i is lower index of the pair
	 * @param j is higher index of the pair
	 */
	private void swap(int i, int j) {
	    T h = array.get(i);
	    array.set(i, array.get(j)); 
	    array.set(j, h); 			
	}  
}

class Main 
{ 
    public static void main (String[] args) {
    	
    	//Creating an object of the class LambdaCompator for type String and Double
    	LambdaCompator<String> string_obj = new LambdaCompator<String>();
        LambdaCompator<Double> double_obj = new LambdaCompator<Double>();
        
        List SortedArray = null;
        
        //Creating an comparator instance for type String as CString using lambda expression
    	Comparator<String> CString = (o1, o2) -> o1.compareTo(o2);
    	
    	//Creating an comparator instance for type Double as CDouble using lambda expression
    	Comparator<Double> CDouble = (o1, o2) -> o1.compareTo(o2);
                
    	System.out.println("\nSize of the array should be in powers of 2 with atleast 16 elements\n");
		Scanner out = new Scanner(System.in);		
		System.out.println("Enter the length of string array: ");
		int n = out.nextInt();
		
		//checking if the length of the String array is in powers of 2 and with atleast 16 elements in it
		if((n >= 16 ) && ((n&(n-1)) == 0)) { 			
			
			//Reading the input from user
			List<String> strArray = new ArrayList<String>();
			System.out.println("Please enter String Array:");
			for(int i=0; i<n; i++) {
				String str = out.next();
				strArray.add(i, str); 				
			}				
			
			System.out.println("Original Array of type String: " + strArray);
			//passing the input array for sorting
			SortedArray = string_obj.sort(strArray, CString);
			System.out.println("Sorted Array of type String: " + SortedArray);
		}	
		else
			System.out.println("Array length restrictions: Size of array should be in powers of 2 with atleast 16 elements in it.");
		
		System.out.println("\n\nEnter the length of double array: ");
		int n1 = out.nextInt();
		
		//checking if the length of the Double array is in powers of 2 and with atleast 16 elements in it
		if((n1 >= 16 ) && ((n1&(n1-1)) == 0)) { 			
			
			//Reading the input from user
			List<Double> doubleArray = new ArrayList<Double>();
	        System.out.println("Please enter Double Array:");
			for(int i=0; i<n1; i++) {
				Double d = out.nextDouble();
				doubleArray.add(i, d);; 				
			}	
			
			System.out.println("Original Array of type Double: "+doubleArray);
			//passing the input array for sorting
			SortedArray = double_obj.sort(doubleArray, CDouble);
			System.out.println("Sorted Array of type String: "+SortedArray);
		}
		else
			System.out.println("Array length restrictions: Size of array should be in powers of 2 with atleast 16 elements in it.");
		
		//Closing the scanner to avoid resource leakage
		out.close();
    }
}