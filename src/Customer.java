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
 * @author Yunlin Xie
 */



public class Customer implements Comparable<Customer> {

    private String first_name;
    private String last_name;
    private String address;
    private String city;
    private String state;
    private String zip; // 5 digits
    private String phone;
    private String email;
    private List<Order> orders;

// CONSTRUCTORS
    public Customer() {
    	first_name = "";
    	last_name = "";
    	address = "";
    	city = "";
    	state = "";
    	zip = "";
    	phone = "";
    	email = "";
    	orders = new List<Order> ();
    }
    
    public Customer(String a1, String a2, String b, String c, String d, String e, String f, String g) {
    	first_name = a1;
    	last_name = a2;
    	address = b;
    	city = c;
    	state = d;
    	zip = e;
    	phone = f;
    	email = g;
    	orders = new List<Order> ();
    }
    
    /**
     * Copy the information of a customer
     * @param original the customer to make a copy of
     * @postcondition a new Customer object, which is an identical
     * but separate copy of the original customer
     */
    public Customer(Customer original) {
    	first_name = original.getFirstName();
    	last_name = original.getLastName();
    	address = original.getAddress();
    	city = original.getCity();
    	state = original.getState();
    	zip = original.getZip();
    	phone = original.getPhone();
    	email = original.getEmail();
    	
    	List<Order> originalOrders = original.getAllOrders();
    	if (originalOrders.getLength() == 0) {
    		orders = new List<Order> ();
        } else {
        	originalOrders.pointIterator();
        	while(!originalOrders.offEnd()) {
        		Order o = originalOrders.getIterator();
        		orders.addLast(o);
        		originalOrders.advanceIterator();
        	}
        }
    	
    }
    
    
// ACCESSORS  ///////////////////////////////////////////////////  
    public String getFirstName() {
    	return first_name.trim();
    }
    
    public String getLastName() {
    	return last_name.trim();
    }
    
    public String getAddress() {
    	return address.trim();
    }
    
    public String getCity() {
    	return city.trim();
    }
    
    public String getState() {
    	return state.trim();
    }
    
    public String getZip() {
    	return zip.trim();
    }
    
    public String getPhone() {
    	return phone.trim();
    }
    
    public String getEmail() {
    	return email.trim();
    }
    
    // for orders
    public List<Order> getAllOrders(){
    	return orders;
    }
    
    /**
     * get a list of unshipped orders
     * @return list of unshipped orders
     */
    public List<Order> getUnshippedOrders(){
    	if (orders.getLength() == 0) {
    		return null;
    	}
    	List<Order> unship = new List<Order> ();
    	orders.pointIterator();		
   		while(!orders.offEnd()) {
   			if (orders.getIterator().getShipStt() == false) {
   				unship.addLast(orders.getIterator());
   			}
    		orders.advanceIterator();
    	}
   		return unship;
    }
    
    /**
     * get a list of shipped orders
     * @return a list of shipped orders
     */
    public List<Order> getShippedOrders(){
    	if (orders.getLength() == 0) {
    		return null;
    	}
    	List<Order> ship = new List<Order> ();
    	orders.pointIterator();    		
   		while(!orders.offEnd()) {
   			if (orders.getIterator().getShipStt() == true) {
   				ship.addLast(orders.getIterator());
   			}
    		orders.advanceIterator();
    	}
   		return ship;
    }
    
    
    
    
 // MUTATORS /////////////////////////////////////////////////////  
    public void changeFirstName(String a11) {
    	first_name = a11;
    }
    
    public void changeLastName(String a22) {
    	last_name = a22;
    }
  
    public void changeAdress(String b1) {
    	address = b1;
    }
    
    public void changeCity(String c1) {
    	city = c1;
    }
    
    public void changeState(String d1) {
    	state = d1;
    }
    
    public void changeZip(String e1) {
    	zip = e1;
    }
    
    public void changePhone(String f1) {
    	phone = f1;
    }
    
    public void changeEmail(String g1) {
    	email = g1;
    }    
    
    // for orders
    public void addOrder(Order o){// after playing an order, call it to add order information as part of customer's information 
    	orders.addLast(o);
    }
    
    public void removeOrder(int index) {// for employee to remove an order to update customer's information if needed
    	orders.moveToIndex(index);
    	orders.removeIterator();
    }    
    
      
    
 // ADDITIONAL METHODS ////////////////////////////////////////////
    /**
     * print all shipped orders 
     * information of a customer
     */
    public void printShippedOrders() {
    	List<Order> shippedOrders = getShippedOrders();
    	if(shippedOrders.getLength() == 0) {
    		System.out.println("You don't have any shipped orders!");
    		System.out.println();
    	}else {
    		System.out.println("\n\nYou have following shipped orders:\n\n");
        	shippedOrders.pointIterator();		
       		while(!shippedOrders.offEnd()) {
       			System.out.println(shippedOrders.getIterator().toString());
       			System.out.println();
       			shippedOrders.advanceIterator();
        	}
    	}
    	
    }
       
    /**
     * print all unchipped orders
     * information of a customer 
     */
    public void printUnshippedOrders() {
    	List<Order> unshippedOrders = getUnshippedOrders();
    	if(unshippedOrders.getLength() == 0) {
    		System.out.println("You don't have any unshipped orders!");
    		System.out.println();
    	} else {
    		System.out.println("\nYou have following unshipped orders:\n\n");
        	unshippedOrders.pointIterator();		
       		while(!unshippedOrders.offEnd()) {
       			System.out.println(unshippedOrders.getIterator().toString());
       			System.out.println();
       			unshippedOrders.advanceIterator();
        	}
    	}
    } 
    
    /**
     * print all order information of a customer
     */
    public void printAllOrders() {
    	printUnshippedOrders();
    	printShippedOrders();
    }
    
    
    /**
     * eg. initial is Y.X., code = ('Y' - 'A') * 26 + ('X' - 'A') 
     */
    @Override public int hashCode() {
    	char first_first = Character.toUpperCase(getFirstName().charAt(0));
    	char first_last = Character.toUpperCase(getLastName().charAt(0));
    	
    	// for this hash algorithm we will have a max of 26*26 array
    	int code = ((int)first_first - 65) * 26 + ((int)first_last - 65);

    	return code;
    }
   

    public int compareTo(Customer c) {
        if (last_name.compareTo(c.last_name) > 0)
            return 1;
        else if  (last_name.compareTo(c.last_name) < 0)
            return -1;
        else if (first_name.compareTo(c.first_name) > 0)
            return 1;
        else if (first_name.compareTo(c.first_name) < 0)
            return -1;
        else if (address.compareTo(c.address) > 0)
            return 1;
        else if (address.compareTo(c.address) < 0)
            return -1;
        else if (zip.compareTo(c.zip) > 0)
            return 1;
        else if (zip.compareTo(c.zip) < 0)
            return -1;
        else
            return 0;
    }
    
    
    

}
