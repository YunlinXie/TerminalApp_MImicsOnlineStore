/**
 * Project Name: College Supplies
 * 
 * Group Members:
 * Sarthak Agarwal
 * Bhargavi Kumar Sundaresan
 * Le Minh Long Nguyen
 * Yunlin Xie
 * 
 * Defines List.java
 * @author From Lab
 */

import java.util.NoSuchElementException;

public class List<T extends Comparable<T>> {
    private class Node {
        private T data;
        private Node next;
        private Node prev;

        public Node(T data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    private int length;
    private Node first;
    private Node last;
    private Node iterator;

    /****CONSTRUCTOR****/

    /**
     * Instantiates a new List with default values
     * @postcondition first, last pointers are null, length is 0
     */
    public List() {
        first = null;
        last = null;
        iterator = null;
        length = 0;
    }
    
    /**
     * Instantiates a new List by copying another List
     * @param original the List to make a copy of
     * @postcondition a new List object, which is an identical
     * but separate copy of the List original
     */
    public List(List<T> original) {
        if (original.length == 0) {
            length = 0;
            first = null;
            last = null;
            iterator = null;
        } else {
            Node temp = original.first;
            while (temp != null) {
                addLast(temp.data);
                temp = temp.next;
            }
            iterator = null;
        }       
    }

    /****ACCESSORS****/

    /**
     * Returns the value stored in the first node
     * @precondition The list should be non-empty
     * @return the integer value stored at node first
     * @throws NoSuchElementException when precondition is violated
     */
    public T getFirst() throws NoSuchElementException{
        if (length == 0) {
            throw new NoSuchElementException("getFirst(): List is Empty. No data to access!");
        }
        return first.data;
    }

    /**
     * Returns the value stored in the last node
     * @precondition The list should be non-empty
     * @return the integer value stored in the node last
     * @throws NoSuchElementException when precondition is violated
     */
    public T getLast() throws NoSuchElementException{
        if (length == 0) {
            throw new NoSuchElementException("getLast(): List is Empty. No data to access!");
        }
        return last.data;
    }

    /**
     * Returns the current length of the list
     * @return the length of the list from 0 to n
     */
    public int getLength() {
        return length;
    }

    /**
     * Returns whether the list is currently empty
     * @return whether the list is empty
     */
    public boolean isEmpty() {
        return length == 0;
    }

    /**
     * Returns the element currently pointed
     * at by the iterator
     * @precondition the iterator cannot be null
     * @return the value pointed at by the iterator
     * @throws NullPointerException when precondition is violated
     */
    public T getIterator() throws NullPointerException{
    	if (iterator == null) {
    		throw new NullPointerException("getIterator(): "
    				+ "the iterator is off end. Cannot return data.");
    	}
    	return iterator.data;
    }
    
    /**
     * returns whether or not the iterator
     * is off the end of the list, i.e. null
     * @return whether the iterator is null
     */
    public boolean offEnd() {
    	return iterator == null;
    }
    
    /**
     * Determines whether two Lists have the same data
     * in the same order
     * @param L the List to compare to this List
     * @return whether the two Lists are equal
     */
    @Override public boolean equals(Object o) {
        if(o == this) {
            return true;
        } else if (!(o instanceof List)) {
            return false;
        } else {
            List L = (List) o; 
            if (this.length != L.length) {
                return false;
            } else {
                Node temp1 = this.first;
                Node temp2 = L.first;
                while (temp1 != null) { //Lists are same length
                    if (temp1.data != temp2.data) {
                        return false;
                    }
                    temp1 = temp1.next;
                    temp2 = temp2.next;
                }
                return true;
            }
        }
    }
    
    /**
     * Recursively determines whether 
     * a List is sorted in ascending order
     * @return whether this List is sorted
     */
    private boolean isSorted(Node n) {
        if(n == last) {
        	return true;
        }
        else if (n.data.compareTo(n.next.data) > 0){
        	return false;
        }
        else {
        	return isSorted(n.next);
        }
    }
    
    /**
     * Determines whether a List is sorted
     * by calling the recursive helper method
     * isSorted
     * Note: A List that is empty can be
     * considered to be (trivially) sorted
     * @return whether this List is sorted
     */
    public boolean isSorted() {
    	if (length == 0) {
    		return true;
    	}
    	else {
    		return isSorted(first);
        }
    }
    
    /**
     * Returns the index of the iterator
     * from 1 to n. Note that there is 
     * no index 0.
     * @precondition !offEnd()
     * @return the index of the iterator
     * @throws NullPointerException when
     * the precondition is violated
     */
    public int getIndex() throws NullPointerException{
        if(offEnd()) {
        	throw new NullPointerException("getIndex(): iterator "
        									+ "is off end, nothing to return.");
        }
        else if(iterator == first){
        	return 1;
        }
        else if(iterator == last) {
        	return length;
        }
        else {
        	int index = 1;
        	Node temp = first;
        	while(temp != iterator) {
        		temp = temp.next;
        		index++;
        	}
        	return index;
        }    
    }
    
    /****MUTATORS****/

    /**
     * Creates a new first element
     * @param data the data to insert at the 
     * front of the list
     * @postcondition the newly created node with data become the first node of the list,
     * the first and last should each point to first and last node,
     * the length should be number of nodes
     */
    public void addFirst(T data) {
        if (first == null) {
            first = last = new Node(data);
        } else {
            Node N = new Node(data);
            N.next = first;
            first.prev = N;
            first = N;
        }
        length++;
    }

    /**
     * Creates a new last element
     * @param data the data to insert at the 
     * end of the list
     * @postcondition the newly created node with data become the last node of the list,
     * the first and last should each point to first and last node,
     * the length should be number of nodes
     */
    public void addLast(T data) {
        if (first == null) {
            first = last = new Node(data);
        } else {
            Node N = new Node(data);
            N.prev = last;
            last.next = N;
            last = N;
        }
        length++;
    }

    /**
     * removes the element at the front of the list
     * @precondition The list should be non-empty
     * @postcondition The list should have original first node removed,
     * the first and last should each point to first and last node,
     * the length should be number of nodes
     * @throws NoSuchElementException when precondition is violated
     */
    public void removeFirst() throws NoSuchElementException{
        if (length == 0) {
            throw new NoSuchElementException("removeFirst(): Cannot remove from an empty List!");
        } else if (length == 1) {
            first = last = null;
        } else {
            first = first.next;
            first.prev = null;
        }
        length--;
    }

    /**
     * removes the element at the end of the list
     * @precondition The list should be non-empty
     * @postcondition The list should have original last node removed,
     * the first and last should each point to first and last node,
     * the length should be number of nodes
     * @throws NoSuchElementException when precondition is violated
     */
    public void removeLast() throws NoSuchElementException{
        if (length == 0) {
            throw new NoSuchElementException("removeLast(): Cannot remove from an empty List!");
        }
        else if (length == 1) {
            first = last = null;
        } else {
        	last = last.prev;
        	last.next = null;
        }
        length--;
    }
    
    /**
     * Advances the iterator by one node
     * in the list
     * @precondition !offEnd()
     * @throws NullPointerException when precondition is violated
     */
    public void advanceIterator() throws NullPointerException{
    	if (iterator == null) {
    		throw new NullPointerException("advanceIterator(): "
    				+ "iterator is off end. Cannot advance.");
    	}
    	iterator = iterator.next;
    }
    
    /**
     * Inserts new data in the List after the iterator
     * @param data the new data to insert
     * @precondition iterator != null
     * @throws NullPointerException when the precondition
     * is violated
     */
    public void addIterator(T data) throws NullPointerException{
    	//precondition
    	if(offEnd()) {
    		throw new NullPointerException("addIterator(): "
    				+ "Iterator is off end. Cannot insert.");
    	}
    	//edge case
    	else if (iterator == last){
    		addLast(data);
    	}
    	//general case
    	else {
    		Node N = new Node(data);
    		iterator.next.prev = N;
    		N.next = iterator.next;
    		N.prev= iterator;
    		iterator.next = N;
    		length++;
    	}
    }
    
    /**
     * Removes the element currently pointed to by the iterator
     * @precondition Iterator != null 
     * @postcondition Iterator = null
     * @throws NullPointerException when precondition is violated
     */
    public void removeIterator() throws NullPointerException {
    	// Precondition
        if (offEnd()) {
            throw new NullPointerException("removeIterator(): Iterator is off end. " 
                                           + "Nothing to remove!");
        }
        // Edge case #1
        else if (iterator == first) {
            removeFirst();
        }
        // Edge case #2
        else if (iterator == last) {
        	removeLast();
        }        
        // General case
        else {
            iterator.prev.next = iterator.next;
            iterator.next.prev = iterator.prev;
            length--;
        }
        iterator = null;
    }

    /**
     * Moves the iterator to the start of the list
     * @postcondition iterator points to the start of the list
     */
    public void pointIterator()
    {
        iterator = first;
    }
    
    /**
     * Moves the iterator down by one node
     * in the list
     * @precondition !offEnd()
     * @throws NullPointerException when precondition is violated
     */
    public void reverseIterator() throws NullPointerException{
    	if (iterator == null) {
    		throw new NullPointerException("reverseIterator(): "
    				+ "iterator is off end. Cannot move down.");
    	}
    	iterator = iterator.prev;
    }
    
    
    /**
     * Points the iterator at first
     * and then iteratively advances 
     * it to the specified index
     * @param index the index where
     * the iterator should be placed
     * @precondition 1 <= index <= length
     * @throws IndexOutOfBoundsException
     * when precondition is violated
     */
    public void moveToIndex(int index) throws IndexOutOfBoundsException{
        if (index < 1 || index > length) {
        	throw new IndexOutOfBoundsException("moveToIndex(): "
        			  + "the index is invalid.");
        }
        else if (index == 1) {
        	iterator = first;
        }
        else if (index == length) {
        	iterator = last;
        }
        else {
        	pointIterator();
        	int i = 1;
        	while (i < index) {
        		iterator = iterator.next;
        		i++;
        	}
        }
    }
    
    /**
     * Searches the List for the specified
     * value using the iterative linear
     * search algorithm
     * @param value the value to search for
     * @return the location of value in the
     * List or -1 to indicate not found
     * Note that if the List is empty we will
     * consider the element to be not found
     * @postcondition: position of the iterator remains
     * unchanged!
     */
    public int linearSearch(T value) {
    	if (this.getLength() == 0){
    		return -1;
    	}
    	else if (first.data.compareTo(value) == 0) {
    		return 1;
    	}
    	else if(last.data.compareTo(value) == 0) {
    		return length;
    	}
    	else {
    		Node temp = first; 
        	for(int i = 1; i <= length; i++)
            {
                if (temp.data.compareTo(value) == 0)
                {
                	iterator = first;
                    return i;
                }
                temp = temp.next;
            }
            return -1;
    	}
    }
    

    /****ADDITIONAL OPERATIONS****/

    /**
     * List with each value separated by a blank space
     * At the end of the List a new line
     * @return the List as a String for display
     */
    @Override public String toString() {
        String result = "";
        Node temp = first;
        while(temp != null) {
            result += String.valueOf(temp.data);
            if (temp.next != null) {
                result += " ";
            }
            temp = temp.next;
        }
        result += "\n";
        return result;
    }
    
  
 
   
}

