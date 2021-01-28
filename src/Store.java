/**
 * Project Name: College Supplies
 * 
 * Group Members:
 * Sarthak Agarwal
 * Bhargavi Kumar Sundaresan
 * Le Minh Long Nguyen
 * Yunlin Xie
 * 
 * Defines Store.java
 * This is our main
 * @author Bhargavi Kumar Sundaresan
 */


import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.*;

public class Store {

    private static String Email;
    public static void setEmail(String E) {
        Email = E;
    }
    public static String getEmail() {
        return Email;
    }
    public static void view(int o) throws IOException {

        Scanner sc = new Scanner(System.in);
        Scanner input = new Scanner(System.in);

        File infile= new File("productInfo.txt");
        Scanner tempscanner= new Scanner(infile);

        String a1= new String();
        String a2= new String();
        String a3= new String();
        double pe;
        int idenn;
        double shipcharge;

        //Note: insert using primary BST into both trees
        BST p = new BST();   //tree sorted according to primary key -name
        BST s = new BST();   //tree sorted according to secondary key -ID

        while(tempscanner.hasNext())
        {
            a1= tempscanner.next();
            a2= tempscanner.next();
            a3= tempscanner.next();
            pe= tempscanner.nextDouble();
            idenn= tempscanner.nextInt();
            shipcharge= tempscanner.nextDouble();
            Product product=new Product(a1,a2,a3,pe,idenn,shipcharge);
            p.insert(product, s);


        }

        tempscanner.close();





        //Hash and Heap{
        CustomerHashTable customers = new CustomerHashTable();
   
        Heap OrderList = new Heap(1000);
        int OrderId = 0;

        readingOrderDataBase(OrderList,s,OrderId);
        OrderList.build_heap();
        List<Product> MakeUp = new List<Product>();
 
  This works perfectly, but because we are not able to link heap and hash, we just make up the information        
        /**
         * get customer infromation by reading customer.txt and create a
         * hashtable with existing customer, but there is no order information
         * for all customer yet, need to get information from heap of orders        
         */
                //Construct HashTable for Customer
                CustomerHashTable customerTable = new CustomerHashTable();
                
                String entireFileText = new Scanner(new File("customer.txt")).useDelimiter("\\A").next();
                String[] seperateText = entireFileText.split("\n\n");
                int lengthofAll = seperateText.length;        
                
                List<String> allStrings = new List<String>();
                for(int i = 0; i < lengthofAll; i++) {
                	String temp = seperateText[i];
                	allStrings.addLast(temp);
                }
                	        
                allStrings.pointIterator();
                while(!allStrings.offEnd()){
                	String customer = allStrings.getIterator();
                	String[] variables = customer.split("\n");
                	
                	String firstname = variables[0];
                	String lastname = variables[1];
                	String address = variables[2];
                	String city = variables[3];
                	String state = variables[4];
                	String zip = variables[5];
                	String phone = variables[6];
                	String email = variables[7];     	
                	
                	Customer c = new Customer(firstname.trim(), lastname.trim(), address.trim(), city.trim(), state.trim(), zip.trim(), phone.trim(), email.trim());
                	customerTable.insertCustomer(c);     	
                	allStrings.advanceIterator();
                }
        
        
//        CustomerHashTable customerTable = new CustomerHashTable();
//        Customer c = new Customer("Sarthak", "Agarwal", "2653 Flagstone Drive", "San Jose", "California", "95132", "408-4640727", "sarthak@gmail.com");
//        Customer c2 = new Customer("Dan", "Hoang", "823 saint kitts court", "San Jose", "California", "95132", "408-4640827", "dan@legend.com");
//        Customer c3 = new Customer("Xin","Xin","1762 Gilda Way","San Jose","California","95124","650-8612521","xinxin@gmail.com");
//
//        c.addOrder(OrderList.get_element(2));
//        c2.addOrder(OrderList.get_element(1));
//        c.addOrder(OrderList.get_element(3));
//        customerTable.insertCustomer(c);
//        customerTable.insertCustomer(c2);
    
 // This is for link heap and Hash, but it does not work, so the hashtable has customer information but without orders   
                for(int i = 1; i < OrderList.get_size(); i++) {
        			String fname = OrderList.get_element(i).getCusFirstName();
        			String lname = OrderList.get_element(i).getCusLastName();
        			customerTable.addOrderForCus(fname, lname, OrderList.get_element(i));	
        			
        		}       		
  
//hash and Heap}
       
        int op = 0;

        if (o == 1) {
            Customer c1 = customerTable.getCustomerByEmail(Store.Email);
            System.out.println(c1.getFirstName());
            System.out.println("Welcome Customer!!");
            List<Product> l = new List<Product>();
            do {
                System.out.println("Menu:"
                        + "\n1. List the products in our store using Product name"
                        + "\n2. List the products in our store using ID number"
                        + "\n3. Search for a product using product name"
                        + "\n4. Search for a product using ID number"
                        + "\n5. Add product to my shopping list"
                        + "\n6. Proceed to next step"
                        + "\n7. View my order"
                        + "\n8. Quit!");
                System.out.print("Please enter the option number: ");
                op = sc.nextInt();
                System.out.println();
                if (op == 1) {

                    System.out.println("There are " + p.getSize() + " number of products in our store!\n");
                    p.inOrderPrint();
                } else if (op == 2) {
                    System.out.println("There are " + s.getSize() + " number of products in our store!\n");
                    s.inOrderPrint();
                } else if (op == 3) {
                    p.inOrderPrint();
                    System.out.print("Please enter the product name: ");
                    sc.nextLine();
                    String n = sc.nextLine();
                    p.search(n);
                } else if (op == 4) {
                    s.inOrderPrint();
                    System.out.print("Please enter the product ID: ");
                    int id = sc.nextInt();
                    System.out.println();
                    s.search(id);
                } else if (op == 5) {
                    s.inOrderPrint();
                    System.out.print("Enter the ID number of the product you want to add to cart: ");
                    int i = sc.nextInt();
                    System.out.println();
                    l = s.addToCart(l, i);
                    System.out.println("YOUR SHOPPING CART IS: \n" + l.toString());
                    
                } else if (op == 6) {
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("To Place Your Order");
                    System.out.println("Please choose the shipping type:");
                    System.out.println("1. Overnight Shipping $10 ");
                    System.out.println("2. Rush Shipping      $8 ");
                    System.out.println("3. Standard Shipping  $5");
                    System.out.print("Please enter the option number: ");
                    int shippingtype = scanner.nextInt();
                    Order order = new Order();
                    if(shippingtype == 1) {
                        order.setShipping(1);
                        System.out.println("You chose Overnight shipping");
                    } else if(shippingtype == 2) {
                        order.setShipping(3);
                        System.out.println("You chose Rush shipping");
                    } else if (shippingtype == 3){
                        order.setShipping(5);
                        System.out.println("You chose Standard shipping");
                    } else{
                        System.out.println("Invalid input. Please enter a valid option.");
                    }

                    OrderId++;
                    order.SetId(Integer.toString(OrderId));
                    order.setCusFirstName(c1.getFirstName());
                    order.setCusLastName(c1.getLastName());
                    order.setCusAddress(c1.getAddress() + " , " + c1.getCity() + " , " + c1.getState() + " , " + c1.getZip());
                    order.setCusPhone(c1.getPhone());
                    order.setCart(l);
                    for(int i = 0;i < l.getLength();i++) {
                    	l.removeFirst();
                    }
                    order.setShipped(false);
                    order.setDate(currentTime());
                    order.Calculate();
                    c1.addOrder(order);
                    OrderList.insert(order);
                    System.out.println("This is your Order information: \n" + order);
                } else if (op == 7) {

                    System.out.println("Please choose the order you want to review:");
                    System.out.println("1.View my shipped order");
                    System.out.println("2.View my unshipped order");
                    int orderview = sc.nextInt();
                    if (orderview == 1) {
                       c1.printShippedOrders();


                    } else if (orderview == 2) {
                        // show the unshipped order
                        c1.printUnshippedOrders();

                    } else {
                        System.out.println("Please enter the valid number:");

                    }

                } else if (op == 8) {
                    System.out.println("Thank you for shopping in our online storefront! Goodbye!");
                    writeOrderDataBase(OrderList);
                    System.exit(0);
                } else {
                    System.out.println("Invalid option! Please enter again!");
                }

            } while (true);
        } else if (o == 2) {
            System.out.println("Welcome Employee!!");
            do {
                System.out.println("Menu:"
                        + "\n1. Search a customer"
                        + "\n2. View the customer information"
                        + "\n3. View the orders "
                        + "\n4. Shipp an order"
                        + "\n5. List the products in our store using Product name"
                        + "\n6. List the products in our store using ID number"
                        + "\n7. Add a new product to the store"
                        + "\n8. Remove a product in our store"
                        + "\n9. Quit");
                System.out.print("Please enter the option number: ");
                op = sc.nextInt();
                System.out.println();


                if (op == 1) {
                	System.out.println("\n\nPlease enter the First Name of the customer (uppercase for first letter): ");
                	String f_name = input.nextLine();
                	System.out.println("\n\nPlease enter the Last Name of the customer (uppercase for first letter): ");
                	String l_name = input.nextLine();
                	customerTable.printCustomerByName(f_name, l_name);

                } else if (op == 2) { //display unsorted customer information including first, last names, address and phone number, order history...

                    customerTable.printAllCustomers();

                } else if (op == 3) { // view orders by priority
                  OrderList.build_heap();
                  OrderList.displayArray();
                }else if(op == 4){ //ship an order
                    String FirstName = "";
                    String LastName = "";
                    FirstName =	OrderList.get_element(1).getCusFirstName();
                    LastName = OrderList.get_element(1).getCusLastName();
                    OrderList.get_element(1).shipOrder();
              
                    
                   
                   
                }
                else if (op == 5) {
                    System.out.println("There are " + p.getSize() + " number of products in our store!\n");
                    p.inOrderPrint();
                } else if (op == 6) {
                    System.out.println("There are " + s.getSize() + " number of products in our store!\n");
                    s.inOrderPrint();
                } else if (op == 7) {
                    System.out.println("Enter the details of the product you want to add:");
                    sc.nextLine();
                    System.out.print("Enter product name: ");
                    String t1 = sc.nextLine();
                    System.out.print("Enter Category of the product: ");
                    String t2 = sc.nextLine();
                    System.out.print("Enter Brand of the product: ");
                    String t3 = sc.nextLine();
                    System.out.print("Enter price of the product: ");
                    double t4 = sc.nextDouble();
                    System.out.print("Enter ID number of the product: ");
                    int t5 = sc.nextInt();
                    System.out.print("Enter shipping charge of the product: ");
                    double t6 = sc.nextDouble();
                    Product newproduct = new Product(t1, t2, t3, t4, t5, t6);
                    p.insert(newproduct, s);
                    System.out.println(t1 + " has been added to the store!\n");
                } else if (op == 8) {
                    System.out.println("Enter the product details to be removed: ");
                    sc.nextLine();
                    System.out.print("Enter the product name: ");
                    String n = sc.nextLine();
                    System.out.print("Enter the product ID: ");
                    int id = sc.nextInt();
                    Product t = new Product(n, "", "", 0.0, id, 0.0);
                    p.remove(t, s);
                    System.out.println("\n" + n + " with ID number " + id + " has been removed from our store database!!\n These are the current products in our store!");
                    p.inOrderPrint();
                } else if (op == 9) {
                    System.out.println("Thank you! Goodbye!");
                    System.exit(0);
                } else
                    System.out.println("Invalid option! Please enter again!");


            }
            while (true);
        } else if (o == 3) {
            System.out.println("Welcome Customer!!");
            do {
                System.out.println("Menu: "
                        + "\n1. List the products in our store using Product name "
                        + "\n2. List the products in our store using ID number"
                        + "\n3. Search for a product using product name"
                        + "\n4. Search for a product using ID number"
                        + "\n5. Register for continue shopping"
                        + "\n6. Quit!");
                System.out.print("Please enter the option number: ");
                op = sc.nextInt();
                System.out.println();
                if (op == 1) {
                    System.out.println("There are " + p.getSize() + " number of products in our store!\n");
                    p.inOrderPrint();
                } else if (op == 2) {
                    System.out.println("There are " + s.getSize() + " number of products in our store!\n");
                    s.inOrderPrint();
                } else if (op == 3) {
                    p.inOrderPrint();
                    System.out.print("Please enter the product name: ");
                    sc.nextLine();
                    String n = sc.nextLine();
                    p.search(n);
                } else if (op == 4) {
                    s.inOrderPrint();
                    System.out.print("Please enter the product ID: ");
                    int id = sc.nextInt();
                    System.out.println();
                    s.search(id);
                } else if (op == 5) {
                    Login lg = new Login();
                    sc.nextLine();
                    lg.registerNewUser(sc);

                    // copy the code from op ==1
                    //System.out.println("Welcome Customer!!");
                    //do {
                    //    System.out.println("Menu:\n1. List the products in our store using Product name\n2. List the products in our store using ID number\n"
                    //
                    //

                    //


                } else if(op == 6){
                    System.out.println("Thank you! Goodbye!");
                    System.exit(0);
                } else
                    System.out.println("Invalid option! Please enter again!");


            } while (true);
        }
    }

    public static String currentTime() {
        String temp = "";
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/YY");
        temp = sdf.format(cal.getTime()) ;
        return temp;
    }
    
    public static void writeOrderDataBase(Heap H) throws IOException {
    	 FileWriter fw = null;
//         BufferedWriter bw = null;
         PrintWriter pw = null;
         
 

         FileOutputStream out = new FileOutputStream("orderDatabase.txt");
//         ObjectOutputStream oos = new ObjectOutputStream(out);
         try {
             fw = new FileWriter("orderDatabase.txt", true);
//             bw = new BufferedWriter(fw);
             pw = new PrintWriter(fw);
             String[] str = H.toStringOut().split("\n"); 
             for(int i = 0;i < str.length;i++) {
             pw.println(str[i]);
             }

             System.out.println("Data Successfully added to database");
//             pw.flush();

         } finally {
             try {
                 pw.close();
//                 bw.close();
                 fw.close();
             } catch (IOException io) {
            	 System.out.println("lalalalala");
             }
         }
    }
    
    public static void readingOrderDataBase(Heap H,BST s,int NumO) throws FileNotFoundException {
    	/**
    	 * get order information by reading from a text file
    	 * and contruct a Heap to store order information
    	 */
    	      
    	      String allOrderString = new Scanner(new File("orderDatabase.txt")).useDelimiter("\\A").next();
    	      
    	      String[] oneOrderStrings = allOrderString.split("\n");       
    	      NumO = oneOrderStrings.length / 10;
    	      
    	      for(int i = 0; i < oneOrderStrings.length;i = i + 10 ) {
    	    	 
    	      	
    	      	
    	      		String firstname = oneOrderStrings[i];
    	          	String lastname = oneOrderStrings[i + 1];
    	          	String address = oneOrderStrings[i + 2];
    	          	String phone = oneOrderStrings[i + 3];
    	          	String id = oneOrderStrings[i + 4];
    	          	String date = oneOrderStrings[i + 5];
    	          	
    	          	String tempship = oneOrderStrings[i + 6];
    	          	int ship = Integer.parseInt(tempship.trim());
    	          	
    	          	String tempboolean = oneOrderStrings[i + 7];
    	          	
    	          	boolean isShipped = Boolean.parseBoolean(tempboolean);
    	      		String ProList1 = oneOrderStrings[i + 8];
    	      	
    	      		String ProList = ProList1.trim();
    	      		
    	      		String[] proList = ProList.split(" ");
    	      		List<Product> Li = new List<Product>();
    	      		for(int k = 0;k < proList.length;k++) {
    	      			String ID = proList[k];
    	      			
    	      			int idPro = Integer.parseInt(ID);
    	      			Product p = new Product();
    	      			p = s.getProduct(idPro);
    	      			Li.addFirst(p);
    	      			
    	      		}
    	      		Order o = new Order(firstname, lastname, address,phone,id,date,ship,isShipped, Li);
    	      		
    	      		H.insert(o);
    	      	
    	      	}
    	      	
    	      
    }
    
}
    







