/**
 * Project Name: College Supplies
 * 
 * Group Members:
 * Sarthak Agarwal
 * Bhargavi Kumar Sundaresan
 * Le Minh Long Nguyen
 * Yunlin Xie
 * 
 * Defines Order.java
 * @author Le Minh Long Nguyen
 */

import java.util.*;

public class Order implements Comparable<Order>{
	private String CusFirstName;
	private String CusLastName;
	private String CusAddress;
	private String CusPhone;
    private String id;
    private String date;
    private double subTotal;
    private double shippingTotal;
    private double taxRate;
    private double tax;
    private double Total;
    private int shippingType;
    private int priority;
    private List<Product> cart;
    private boolean shipped;
    
    public Order(){
    	CusFirstName = "";
    	CusLastName = "";
    	CusAddress = "";
    	CusPhone = "";
    	id = "";
    	date = "";
    	shippingType = 0;
    	priority = 0;
    	subTotal = 0;
    	shippingTotal = 0;
    	taxRate = 0.0925 ;
    	shipped = false;
    	cart = new List<Product>();
    }
    
    public Order(String CusFN, String CusLN, String CusAdd,String CusP,String I,String D,int ship,boolean Ship,List<Product> Ca) {
    	CusFirstName = CusFN;
    	CusLastName = CusLN;
    	CusAddress = CusAdd;
    	CusPhone = CusP;
    	id = I;
    	date = D;
    	shippingType = ship;
    	shippingTotal = 0;
    	subTotal = 0;
    	priority = 0;
    	shipped = Ship;
    	taxRate = 0.0925 ;
    	cart = new List<Product>(Ca);
    	CalSubTotal();
    	CalShippingTotal();
    	CalTax();
    	CalTotal();
    	hashPriority();
    }
    
    public Order(Order o) {
    	CusFirstName = o.CusFirstName;
    	CusLastName = o.CusLastName;
    	CusAddress = o.CusAddress;
    	CusPhone = o.CusPhone;
    	cart = new List(o.cart);
    	id = o.getId();
    	date = o.getDate();
    	shippingType = o.getShippingType();
    	subTotal = o.getSubTotal();
    	taxRate = 0.0925 ;
    	shipped = o.shipped;
    	CalSubTotal();
    	CalShippingTotal();
    	CalTax();
    	CalTotal();
    	hashPriority();
    }
   /**Accessor**/  
    /*
     * get the subTotal of the cart
     */
    public String getCusFirstName() {
    	return CusFirstName;
    }
    
    public String getCusLastName() {
    	return CusLastName;
    }
    
    public String getCusAddress() {
    	return CusAddress;
    }
    
    public String getCusPhone() {
    	return CusPhone;
    }
    
    public double getSubTotal() {
    	return subTotal;
    }
    
    public double getTax() {
    	return tax;
    }
    
    public double getTotal() {
    	return Total;
    }
    
    public int getShippingType() {
    	return shippingType;
    }
    public int getPriority() {
    	return priority;
    }
    public String getId() {
    	return id;
    }
    
    public String getDate() {
    	return date;
    }
    
    public String getCusInfo() {
    	String temp = "";
    	temp = "Name: " + CusFirstName + " " + CusLastName + 
    			"\nAdress: " + CusAddress + 
    			"\nPhone: " + CusPhone;
    	return temp;
    }
    
    public double getShippingTotal() {
    	return shippingTotal;
    }
    
    public boolean getShipStt() {
    	return shipped;
    }
    public String getShipStatus() {
   	 String shipStt = "";
   	 if(shipped) {
   		 shipStt = "Shipped";
   		 
   	 } else {
   		 shipStt = "UnShipped";
   	 } 
   	 return shipStt;
    }
    
   public List<Product> getCart(){
	   return cart;
   }
    public int compareTo(Order o) {
    	if(this.priority > o.priority) {
    		return 1;
    	} else if(this.priority < o.priority) {
    		return -1;
    	} else {
    		return 0;
    	}
	}
    /**Mutator**/
    public void Calculate() {
    	CalSubTotal();
    	CalShippingTotal();
    	CalTax();
    	CalTotal();
    	hashPriority();
    }
    /*
     * Set the subTotal of the cart
     */
    
    public void CalSubTotal(){
    	double sum = 0;
    	cart.pointIterator();
    	sum += cart.getIterator().getPrice();
    	for(int i = 1;i < cart.getLength();i++) {
    		cart.advanceIterator();
    		sum += cart.getIterator().getPrice();		
    	}
    	sum = Math.round(sum * 100);
    	subTotal = sum / 100;
    }
    public void CalShippingTotal() {
    	double sum = 0;
    	double ShipType = Math.round(0 * 100) / 100;
    	if(shippingType == 1) {
    		ShipType = Math.round(10 * 100) / 100;
    	} 
    	if(shippingType == 3) {
    		ShipType = Math.round(5 * 100) / 100;
    	}
    	
    	cart.pointIterator();
    	sum += cart.getIterator().getShip();
    	for(int i = 1;i < cart.getLength();i++) {
    		cart.advanceIterator();
    		sum += cart.getIterator().getShip();		
    	}
    	sum += ShipType;
    	sum = (Math.round(sum * 100));
    	shippingTotal = sum / 100 ;
     }
    
    public void CalTax() {
    	double sum = 0;
    	sum = subTotal * taxRate;
    	sum = Math.round(sum * 100);
    	tax = sum / 100;
    }
	
    public void CalTotal() {
    	Double sum = subTotal + tax + shippingTotal;
    	sum = (double) Math.round(sum * 100);
    	Total = sum /100;
    }
    
    public void setShipping(int ship) {
    	shippingType = ship;
    }
    
    public void SetId(String I) {
    	id = I;
    }
    public void setDate(String D) {
    	date = D;
    }
    
    public void setCusFirstName(String CusFN) {
    	CusFirstName = CusFN;
    }
    
    public void setCusLastName(String CusLN) {
    	CusLastName = CusLN;
    }
    
    public void setCusAddress(String CusAdd) {
    	CusAddress = CusAdd;
    }
    
    public void setCusPhone(String CusP) {
    	CusPhone = CusP;
    }

    public void setShipped(boolean ship) {
    	shipped = ship;
    }
    
    
    public void shipOrder() {
    	shipped = true;
    	hashPriority();
    }
    
    public void setCart(List<Product> C) {
    	cart = new List<Product>(C);
    }
    public void hashPriority() {
    	int pri = 0;
    	int shipped = 408;
    	if(getShipStt()) {
    		shipped = 0;
    	}
    	String[] nDate = date.split("/") ;
    	int month = Integer.parseInt(nDate[0]);
    	int day = Integer.parseInt(nDate[1]);
    	pri = 408 - (month * 31 + day + shippingType) + shipped; 
    	priority = pri;
    }
    
    
	/*AdditionMethod***/

 @Override public String toString() {
	 String temp = "";
	 temp = "\nOrder ID#" + id + 
			 "\n" + getCusInfo() + 
			 "\n\nList of product in order: " + ProductsList() + 
			 "\nSubtotal:           $" + getSubTotal() + 
			 "\nTax:                $" + getTax() + 
			 "\nTotal Shipping fee: $" + getShippingTotal() + 
			 "\nTotal:              $" + getTotal() + 
			 "\nDate: " + getDate() +
			 "\nShip Status: " + getShipStatus();
	 return temp;
 	}
 
	 public String ProductsList() {
		 String temp = "";
		 cart.pointIterator();
		 temp += "\n\nProduct's Name: " + cart.getIterator().getName() + 
				 "\nProduct's ID: " + cart.getIterator().getId() +
				 "\nPrice: $" + Double.toString(cart.getIterator().getPrice()) + 
				 "\nShipping fee: $" + Double.toString(cart.getIterator().getShip()) + "\n" ;
		 for(int i = 1;i < cart.getLength();i++) {
	 		cart.advanceIterator();
	 		temp += "\nProduct's Name: " + cart.getIterator().getName() + 
	 				"\nProduct's ID: " + cart.getIterator().getId() +
	 				"\nPrice: $" + Double.toString(cart.getIterator().getPrice()) + 
	 				"\nShipping fee: $" + Double.toString(cart.getIterator().getShip()) + "$\n";		
	 	}
		 return temp;
	 }
	public String toStringOut() {
		String sum ="";
		String sumPro = "";
		cart.pointIterator();
		sumPro = Integer.toString(cart.getIterator().getId()) + " ";
		 for(int i = 1;i < cart.getLength();i++) {
			 cart.advanceIterator();
			 sumPro += Integer.toString(cart.getIterator().getId()) + " ";
		 }
		sum = getCusFirstName() + "\n"
			+ getCusLastName() + "\n"
			+ getCusAddress() + "\n" 
			+ getCusPhone() + "\n" 
			+ getId() + "\n"
			+ getDate()  + "\n"
			+ getShippingType() + "\n"
			+ Boolean.toString(getShipStt()) + "\n"
			+ sumPro;
		return sum;
	}
 
 
 }