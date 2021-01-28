/**
 * Project Name: College Supplies
 * 
 * Group Members:
 * Sarthak Agarwal
 * Bhargavi Kumar Sundaresan
 * Le Minh Long Nguyen
 * Yunlin Xie
 * 
 * Defines Product.java
 * @author Yunlin Xie
 */






public class Product implements Comparable<Product> {
	
		private String name;
		private int id;
		private String category; 
		private String brand;
		private double price;
		private double shipcharge;
		
		public Product() {
	    	name = "";
	    	id = 0;
	    	category = "";
	    	brand = "";
	    	price = 0;
	    	shipcharge = 0;
	    }
		
		
		public Product(String n, String c, String b, double p, int d, double s) {
			
			name=n;
			category= c;
			brand=b;
			price=p;
			id=d;
			shipcharge= s;
			
		}
		
		public String getName()
		{
			return name;
		}
		
		public String getCat()
		{
			return category;
		}
		
		public String getBrand()
		{
			return brand;
		}
		
		public double getPrice()
		{
			return price;
		}
		
		public int getId()
		{
			return id;
		} 
		
		public double getShip()
		{
			return shipcharge;
		}
		
		public void setName(String n)
		{
			name= n;
		}
		
		public void setCat(String c)
		{
			category= c;
		}
		public void setBrand(String b)
		{
			brand= b;
		}
		public void setPrice(double d)
		{
			price= d; 
		}
		
		public void setId(int iden)
		{
			id=iden;
		}
		
		public void setCharge(double sc)
		{
			shipcharge=sc;
		}
		
		@Override public String toString() {
	        String result = "Product Name: " + name
	                + "\nIdentification number " + id
	                + "\nPrice: $" + price
	                + "\nShipping Charges: $"+ shipcharge
	                + "\nCategory: " + category
	                + "\nBrand: " + brand+ "\n";
	    
	        return result;
	    }
	
		
		 public int compareToP(Product o) {
		       
		    	if(this.name.equals(o.name)==true)
		    		return 0;
		    	
		    	else if((this.name).compareTo(o.name)>0)
		    		return 1;
		    		
		    	else
		    		return -1;
		    }
		 
		 
		 public int compareToS(Product o) {
		       
			 	if(this.id==o.id)
			 		return 0;
		    		 
		    	else if(this.id>o.id)
		    		return 1;
		    		
		    	else
		    		return -1;
		    }

		  /**
		     * Compares two Product objects to determine ordering
		     * Returns 0 if the two items are equal
		     * Return -1 if this Product's name comes alphabetically
		     * before the other Product's name.
		     * Returns 1 if the other Product's name comes
		     * alphabetically before this Product's name
		     * If the two Product's names are the same, will
		     * differentiate by ID number
		     * @param the other Product object to compare to this
		     * @return 0 (same Product), -1 (this Product ordered first)
		     * or 1 (the other Product ordered first) 
		     */
		    public int compareTo(Product o) {
		       
		    	if(this.name.equals(o.name)==true)
		    	{
		    		 if((this.id)>(o.id))  
		    			 return 1; 
		    		 else if(this.id==o.id)
		    			 return 0;
		    		 else
		    			 return -1;
		    		
		    	}
		    	
		    	else if((this.name).compareTo(o.name)>0)
		    		return 1;
		    		
		    	else
		    		return -1;
		    }
		    
}
