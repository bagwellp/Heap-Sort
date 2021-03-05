/*
 * Patty Bagwell
 * Data Structures
 * December, 2017
 */
package dsheapsort;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

public class DSHeapSort {

   static Scanner scn; //create scanner to accept input from file
   public void sort(String arr[])//methord for sorting
    {
        int n = arr.length;//initialize variable n to size of array
 
        // Rearrange array(heap) by recursively calling heapify
        for (int i = n / 2 - 1; i >= 0; i--)//this will be the parent 
                                            //of the last leaf in the tree
            heapify(arr, n, i); //calls heapify to move the node up if needed
 
        // Once max is at top of heap, Extract the element from the heap, move
        //it to the end of the array, and heapify on the reduced heap. 
        //This sorts the array in ascending order
        for (int i=n-1; i>=0; i--)
        {
            // Move current root to end
            String temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
 
            // call max heapify on the reduced heap
            heapify(arr, i, 0);
        }
    }
 
    // To heapify a subtree rooted with node i which is
    // an index in arr[] n is size of heap
    void heapify(String arr[], int n, int i)
    {
        int largest = i;  // Initialize largest as root
        int l = 2*i + 1;  // left child of root node
        int r = 2*i + 2;  // right child of root node
 
        // If left child is larger than root set largest to l
        if (l < n && arr[l].compareTo(arr[largest])>0)
            largest = l;
 
        // If right child is larger than largest so far set largest to r
        if (r < n && arr[r].compareTo(arr[largest])>0)
            largest = r;
 
        // If largest is not root then swap
        if (largest != i)
        {
            String swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
 
            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest);
        }
    }
   
    public static void main(String[] args)//main method
    {
    long startTime = System.currentTimeMillis();//start timer
    try //creat scanner to load file
    {
        scn = new Scanner(new File("test/words.txt"));
    }
    catch(FileNotFoundException ex)//catch exception if file not found
    {
        System.out.println(ex.getMessage());
    }
    System.out.println("Items loaded into the Array: ");
    
    String[] array = scn.next().split(",");//load items in array
    System.out.println(array.length);//print number of itesm in array
        
        for(int i = 0; i<array.length; i++)//print items in the array
        {
            System.out.print(array[i] + ", ");
        }
        //create new heap array the length of the original array
        String [] heap = new String[array.length]; 
        System.arraycopy(array, 0, heap, 0, array.length);
         
         //create new heap sort calling DSHeapSort method       
        DSHeapSort ob = new DSHeapSort();
        ob.sort(heap);//sort the heap array
        
        
        System.out.print("\n");
        System.out.println("Unique Items in the Heap Sorted Array Order:");
        String s = heap[0];
        System.out.print(s);
        for (int i = 1; i < heap.length; i++)
        {
            if (!heap[i].equals(s))
            {
                s = heap[i];
                System.out.print(", " + heap[i]);
            }
            
        }
        long endTime = System.currentTimeMillis();
        System.out.print("\n");
        System.out.print("\n");
        System.out.println("Heap Sort Took " + (endTime - startTime) 
                + " milliseconds");
    }
    
}
