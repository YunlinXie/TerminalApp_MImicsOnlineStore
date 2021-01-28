/**
 * Project Name: College Supplies
 * 
 * Group Members:
 * Sarthak Agarwal
 * Bhargavi Kumar Sundaresan
 * Le Minh Long Nguyen
 * Yunlin Xie
 * 
 * Defines Customer.java
 * @author Le Minh Long Nguyen
 */

import java.util.*;

public class Heap <T extends Comparable<T>>{

    private int heap_size;
    private ArrayList<Order> heap;


    /* Heapify-build the heap with 
    * max-priority
    * build a max-heapify
    * @param index i
    */
    
    
    
    private void heapify(int i) {
	int l = 2 * i;
	int r = 2 * i + 1;
	int index_of_max = i;
	if(l <= heap_size && heap.get(l).compareTo(heap.get(i)) > 0){
	index_of_max = l;
	} 
	if(r <= heap_size && heap.get(r).compareTo(heap.get(i)) > 0){
	index_of_max = r;
	} 
	if(i != index_of_max){
	Order temp = heap.get(i);
	heap.set(i,heap.get(index_of_max));
	heap.set(index_of_max,temp);
	}
	
    } 


    /**Constructors*/
    /*
    *Create a default heap
    *the size of heap equals 0
    */

    public Heap(int n){
    heap = new ArrayList<Order>(n);
    for(int i = 0;i <= n;i++) {
    	Order O = new Order();
    	heap.add(O);
    }
	heap_size = 0;
    }    

    public Heap(Heap H) {
    	this.heap_size = H.get_size();
    	this.heap = new ArrayList<Order>(H.heap);
    }
    
    /**Mutators*/
    /*
    * build the heap
    * from an arraylist
    */
    public void build_heap(){
    	int n = heap_size;
    	for(int i = n/2;i >= 1;i--){
		heapify(i);
    	}	
    }    
	    
    /*
    *insert a new order
    * into the heap 
    */	
    public void insert(Order o){
	heap_size++;
	Order dummy = new Order();
	heap.set(heap_size,dummy);
	heap_increase_key(heap_size, o);
    }   

    /*
    * The insert healper
    * @param index i
    * @param key is the insert
    */
    private void heap_increase_key(int i, Order key){
	if(key.compareTo(heap.get(i)) > 0){
		heap.set(i,key);
		while( i > 1 && get_parent(i).compareTo(heap.get(i)) > 0){
			Order temp = heap.get(i);
			heap.set(i,get_parent(i));
			heap.set(i / 2,temp);
			i = i / 2;
		}
	}
    }
    
    public void remove(int index) throws NoSuchElementException{
    	if(heap_size == 0 ) {
    		throw new NoSuchElementException("Remove(): The heap is empty. Cannot Remove");
    	}
    	if(index <= 0 || index > heap_size) {
    		throw new IndexOutOfBoundsException("Remove(): The Index is out of bound. Cannot remove");
    	} else {
    	Order temp = new Order(heap.get(index));
    	heap.set(index, heap.get(heap_size));
    	heap.set(heap_size, temp);
    	heap_size--;
    	heapify(index);
    	}
    }    

    public ArrayList<Order> sort() {
    	ArrayList<Order> ar = new ArrayList<Order>(heap_size);
    	for(int i = 0; i<= heap_size;i++) {
    		Order o = new Order();
    		ar.add(o);
    	}
    	int n = heap_size;
    	for(int i = n; i >= 2; i--) {
    		Order temp = new Order(heap.get(i));
    		heap.set(i, heap.get(1));
    		heap.set(1, temp);
    		heap_size--;
    		heapify(1);
    		ar.set(heap_size,temp);
    	}
    	heap_size = n;
    	return ar;
    }
    
    /**Accessors*/
    /*
    * Return the biggest value
    */
    public Order get_max(){
	return heap.get(1);
    }
    
    /*
    * return the value of parent of 
    * the index i
    * @param index i
    * @precondition 1 < i <= heap_size 
    * throws IndexOutOfBoundsException 
    * when the precondition is violated
    */
    public Order get_parent(int i) throws IndexOutOfBoundsException {
	if(i <= 1 || i > heap_size){
		throw new IndexOutOfBoundsException("get_parent(): the index is out of bound. Cannot access!");
	} else {
		return heap.get(i / 2);
		}
	}


    /*
    * return the value of the 
    * left child of the index i
    * @param index i
    * @precondition 1 <= i and 2i <= heapsize
    * throws IndexOutOfBoundsException 
    * when the precondition is violated	
    */
    public Order get_left(int i) {
    	if(i <= 1 || i > heap_size){
    		throw new IndexOutOfBoundsException("get_parent(): the index is out of bound. Cannot access!");
    	} else {
    		return heap.get(i * 2);
    		}
	}
    /*
    * return the value of the 
    * right child of the index i
    * @param index i
    * @precondition 1 <= i 
    * and 2i + 1 <= heapsize
    * throws IndexOutOfBoundsException 
    * when the precondition is violated	
    */
    public Order get_right(int i) {
    	if(i <= 1 || i > heap_size){
    		throw new IndexOutOfBoundsException("get_parent(): the index is out of bound. Cannot access!");
    	} else {
    		return heap.get(i * 2 + 1);
    		}
    }  
    

    /*
    *return the size of the heap
    */
    public int get_size() {
	return heap_size; 
    }
    /*
    * return the value of 
    * the index i
    * @param index i
    * @precondition 1 <= i <= heap_size 
    * throws IndexOutOfBoundsException 
    * when the precondition is violated
    */
    public Order get_element(int i) {
    	if(i <= 0 || i > heap_size){
    		throw new IndexOutOfBoundsException("get_parent(): the index is out of bound. Cannot access!");
    	} else {
    		return heap.get(i);
    		}
    }
    
   
    
    public int searchId(int id) {
    	int result = -1;
    	for(int i = 1;i <= heap_size;i++) {
    		if(Integer.toString(id).compareTo(heap.get(i).getId()) == 0){
    			result = i;
    		} 
    	}
    	return result;
    }
    /**Additional Operations*/

    @Override public String toString(){
    	String temp = "";
    	for(int i = 1;i <= heap_size;i++) {
    		temp += heap.get(i).toString() + "/n";
    		
    	}
    	return temp;
    }  

    public String toStringOut(){
    	String temp = "";
    	for(int i = 1;i <= heap_size;i++) {
    		temp += heap.get(i).toStringOut() + "\n";
    		
    	}
    	return temp;
    }  
    
    public void displayArray(){
    	for(int i = 1;i <= heap_size;i++) {
    		System.out.println(heap.get(i).toString());
    	}
    }  
}