/**
 * Project Name: College Supplies
 * 
 * Group Members:
 * Sarthak Agarwal
 * Bhargavi Kumar Sundaresan
 * Le Minh Long Nguyen
 * Yunlin Xie
 * 
 * Defines CustomerHashTable.java
 * @author Yunlin Xie
 */



public class CustomerHashTable {
	private int SIZE = 26 * 26;
    private Hash<Customer> hashTable;

// CONSTRUCTOR /////////////////////////////////////////////////////
    public CustomerHashTable() {
        hashTable = new Hash<Customer>(SIZE);
    }


// ACCESSORS /////////////////////////////////////////////////////
    /**
	 *  get a customer's information by searching name
	 * if firstName is null or empty string, only use lastName, vice versa.
	 * this one single method actually works has three functions by
	 * changing the arguments you pass into: getCustomerByName, getCustomerByFirstName, getCustomerByLastName
	 * @return a list of customers with same name
	 */
    public List<Customer> getCustomerInfoByName(String firstName, String lastName) {
    	   	
    	/*
   	     when both fn, ln are not null,
   	 	compute hash
   	 	find bucket, and search thru the bucket and output every customer with the same fn and ln
   	 	
   	 	if fn != null && ln == null
   	 	fnhash = fn[0] - 'A'
   	 	go thru buckets fnhash * 26 + k;  k=[0, 25]
   	 	search thru every bucket and output every customer with the same fn
   	 	
        if fn == null && ln != null
   	 	lnhash = ln[0] - 'A'
   	 	go thru buckets lnhash + k*26; k=[0, 25]
   	 	search thru every bucket and output every customer with the same ln
   	    	 	
   	    if both null,
   	 	return empty
   	 */
    	List<Customer> searchedCustomer = new List<Customer> ();
    	
    	if(firstName != null && lastName != null) {
    		char first_initial = firstName.trim().charAt(0);
    		char last_initial = lastName.trim().charAt(0);
    		int bucket = ((int)first_initial - 65) * 26 + ((int)last_initial - 65);
    		
    		List<Customer> listCustomer = hashTable.getBucket(bucket);
    		if(listCustomer != null) {
    			listCustomer.pointIterator();
    			while (!listCustomer.offEnd()) {
    				Customer c = listCustomer.getIterator();
    				searchedCustomer.addLast(c);
    				listCustomer.advanceIterator();
    			}
    		}
    		
    	}
    	else if(firstName != null) {
    		int fnhash = (int)(firstName.trim().charAt(0)) - 65;
    		for(int k = 0; k <= 25; k++) {
    			int bucket = fnhash * 26 + k;
    			List<Customer> listCustomer = hashTable.getBucket(bucket);
        		if(listCustomer != null) {
        			listCustomer.pointIterator();
        			while (!listCustomer.offEnd()) {
        				Customer c = listCustomer.getIterator();
        				searchedCustomer.addLast(c);
        				listCustomer.advanceIterator();
        			}
        		}
    		}
    	}
    	else if (lastName != null){
    		int lnhash = (int)(lastName.trim().charAt(0)) - 65;
    		for(int k = 0; k <= 25; k++) {
    			int bucket = lnhash + 26 * k;
    			List<Customer> listCustomer = hashTable.getBucket(bucket);
        		if(listCustomer != null) {
        			listCustomer.pointIterator();
        			while (!listCustomer.offEnd()) {
        				Customer c = listCustomer.getIterator();
        				searchedCustomer.addLast(c);
        				listCustomer.advanceIterator();
        			}
        		}
    		}
    	}
    	  	
    	return searchedCustomer;
 
        
    }
    
    /**
	 * get a customer by given emai as a string
	 * @return a customer of Customer type
	 */
    public Customer getCustomerByEmail (String email) {
    	List<Customer> allCustomer = getAllCustomerInfo();
    	Customer c = new Customer();
    	allCustomer.pointIterator();
		while (!allCustomer.offEnd()) {
			c = allCustomer.getIterator();
			if (c.getEmail().equals(email)) {
				return c;
			}
			allCustomer.advanceIterator();
		}
		return c;
    }
    
    
    /**
     * display all unsorted customer information
     * @return a list of all customers
     */
    public List<Customer> getAllCustomerInfo() {
    	List<Customer> allCustomer = new List<Customer> ();
    	for (int i = 0; i < SIZE; i++) {
    		List<Customer> listCustomer = hashTable.getBucket(i);
    		if(listCustomer != null) {
    			listCustomer.pointIterator();
    			while (!listCustomer.offEnd()) {
    				Customer c = listCustomer.getIterator();
    				allCustomer.addLast(c);
    				listCustomer.advanceIterator();
    			}  			
    		}
    	}
        return allCustomer;
    }
    
// MUTATOR /////////////////////////////////////////////////////////////
    /**
     * insert a customer into hash table, do not insert if already exists
     * @param c the Customer c
     */
    public void insertCustomer(Customer c) {
    	List<Customer> listCustomer = getCustomerInfoByName(c.getFirstName(), c.getLastName()); // return a list of customers with same name
    	boolean same = false;
    	String str1 = c.getEmail().trim();    // put it here instead of the while loop to reduce the time
    	
    	if (listCustomer.getLength() > 0) {
    		listCustomer.pointIterator();
    		
    		// compare the email which is also the username, should be unique
    		while (!listCustomer.offEnd()) {    		
    			String str2 = listCustomer.getIterator().getEmail().trim();
    			if (str1.equalsIgnoreCase(str2)) {
    				same = true;
    				break;      // if we find a same one, we do not need to go over all the rest, just continue to the next command
    			}
    			
    			listCustomer.advanceIterator();
    		}
    	}
    	
    	if (same) {
    		System.out.println("The customer infromation already exist, please register by other information.");
    	}
    	
    	hashTable.insert(c);
    }

    /**
     * add an order for a customer with given names
     */
    public void addOrderForCus(String fname, String lname, Order o) {
    	List<Customer> l = getCustomerInfoByName(fname, lname);
    	l.pointIterator();
    	while(!l.offEnd()) {
    		Customer c = l.getIterator();
    		c.getAllOrders().pointIterator();
    		while(!c.getAllOrders().offEnd()) {
    			Order order = c.getAllOrders().getIterator();
    			if (order.getId().equalsIgnoreCase(o)) {
    				order.
    			}
    		}
    	}
    }
    
    

// ADDITIONAL METHODS /////////////////////////////////////////////////////	
    
    /**
     * print information for a searched customer 
     * @param firstName the first name of customer
     * @param lastName the last name of customer
     */
    public void printCustomerByName(String firstName, String lastName) {
    	List<Customer> custom = getCustomerInfoByName(firstName, lastName);
    	if(custom.getLength() == 0) {
    		System.out.println("Cannot find a customer with given names!");
    	}
    	custom.pointIterator();
    	while (!custom.offEnd()) {    		
			Customer c = custom.getIterator();		
			c.printAllOrders();
			custom.advanceIterator();
		}
    }
 
    /**
     * print information for all customer
     */
    public void printAllCustomers() { 
    	List<Customer> custom = getAllCustomerInfo();
    	if(custom.getLength() == 0) {
    		System.out.println("Do not have any customer's information!");
    	}
    	custom.pointIterator();
    	while (!custom.offEnd()) {    		
			Customer c = custom.getIterator();
			System.out.println("\n\nPrint information for customer: " + c.getFirstName() + " " 
								+ c.getLastName() + "\n");
			c.printAllOrders();
			custom.advanceIterator();
		}
    }
    
    
    
    
}
