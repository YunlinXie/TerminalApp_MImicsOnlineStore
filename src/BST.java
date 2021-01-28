/**
 * Project Name: College Supplies
 * 
 * Group Members:
 * Sarthak Agarwal
 * Bhargavi Kumar Sundaresan
 * Le Minh Long Nguyen
 * Yunlin Xie
 * 
 * Defines BST.java
 * @author Yunlin Xie
 */





import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;

public class BST {
    private class Node {
        private Product data;
        private Node left;
        private Node right;
        
        public Node(Product data) {
            this.data = data;
            left = null;
            right = null;
        }
    }
    
    
    private Node root;
    
    /***CONSTRUCTORS***/
    
    /**
     * Default constructor for BST
     * sets root to null
     * @postcondition a new BST object with root set to null
     */
    public BST() {
    	
        root=null;
       
    }
    
  
    /***ACCESSORS***/
   
    
    /**
     * Returns the current size of the 
     * tree (number of nodes)
     * @return the size of the tree
     */
    public int getSize() {
    	
    	if(root==null)
    		return 0;
    	else
    		return getSize(root);
    
    }
    
    /**
     * Helper method for the getSize method
     * @param node the current node to count
     * @return the size of the tree
     */
    private int getSize(Node node) {
    	
    	if(node==null)
    		return 0;
    	else
    		return 1+ getSize(node.left)+ getSize(node.right);
    	
    }

    /**
     * wrapper method
     * Searches for a specific ID
     * in the tree
     * @param ID the ID to search for
     * @return boolean whether the value is stored
     * in the tree
     * Note: has to be called from the BST sorted according to secondary key 
     * because of the nature of BST property
     */
    public boolean search(int ID) {
    	
    	if(root==null)
    		return false;
    	else
    	{ 
    		Node temp= search(ID,root);
    		if(temp==null)
    			{System.out.println("There is no such element with ID as "+ ID);
    			 return false; }
    		  else 
    		  {  System.out.println("The Product you are searching for is ready below!\n"+ temp.data.toString());
    			  return true; }
    		
    	}
    }
    
    /**
     * Helper method for the search method in the secondary BST
     * @param ID the ID to search for
     * @param node the current node to check
     * @return the node that is equal to the ID
     * SEARCHING HAPPENS ONLY IN THE SECONDAEY BST
     */
    private Node search(int ID, Node node) {
    	
    	if(node==null)
    		return null;
       
    	else if(node.data.getId()==ID)
    		return node;
    	
    	else if(node.data.getId()>ID)
    		return search(ID,node.left);
    	
    	else
    		return search(ID,node.right);
    	
    	
    }

    
    /**
     * wrapper method
     * Searches for a specific product name
     * in the tree
     * @param name the product name to search for
     * @return boolean whether the value is stored
     * in the tree
     * Note: has to be called from the BST sorted according to secondary key 
     * because of the nature of BST property
     */
    
    public boolean search(String name) {
    	
    	if(root==null)
    		return false;
    	else
    	{ 
    		Node temp= search(name,root);
    		if(temp==null)
    			{System.out.println("There is no such element with product name as "+ name);
    			 return false; }
    		  else 
    		  {  System.out.println("The Product you are searching for is ready below!\n"+ temp.data.toString());
    			  return true; }
    		
    	}
    }
    
    /**
     * Helper method for the search method
     * @param name the product name to search for 
     * @param node the current node to check
     * @return the node that contains the same
     * product name as name
     * returns null if product name not found
     */
	private Node search(String name, Node node) {
	
	if(node==null)
		return null;
   
	else if(node.data.getName().equals(name))
		return node;
	
	else if(node.data.getName().compareTo(name)>0)
		return search(name,node.left);
	
	else
		return search(name,node.right);
	
	
}
    
	/**
     * FOR PRIMARY BST
     * wrapper method 
     * has to be called from the respective classes
     * Returns the smallest value in the tree
     * @precondition !isEmpty()
     * @return the Product that has a product name
     * that is the smallest in alphabetical order   
     * @throws NoSuchElementException when the
     * precondition is violated
     */
    public Product findMinP() throws NoSuchElementException{
    	if(root==null)
    			throw new NoSuchElementException("findMin(): There is no element in the BST, and hence, no minimum element!");
        
    	else
    		return findMinP(root);
    }
    
    /**
     * Helper method to findMinP method
     * @param node the current node to check
     * if it is the smallest
     * @return the Product that has a product name
     * that is the smallest in alphabetical order  
     */
    private Product findMinP(Node node) {
    	
        if(node.left==null)
        	return node.data;
        else
        	return findMinP(node.left);
        	
    }
    
    /**
     * FOR SECONDARY BST
     * wrapper method 
     * has to be called from the respective classes
     * Returns the smallest value in the tree
     * @precondition !isEmpty()
     * @return the Product that has the smallest ID
     * @throws NoSuchElementException when the
     * precondition is violated
     */
    
    public Product findMinS() throws NoSuchElementException{
    	if(root==null)
    			throw new NoSuchElementException("findMin(): There is no element in the BST, and hence, no minimum element!");
        
    	else
    		return findMinS(root);
    }
    
    /**
     * Helper method to findMinS method
     * @param node the current node to check
     * if it is the smallest
     * @return the Product that has the smallest ID 
     */
    
    private Product findMinS(Node node) {
    	
        if(node.left==null)
        	return node.data;
        else
        	return findMinS(node.left);
        	
    }
  

    /**
     * getProduct(): get the product method
     * with ID i
     * @param i the id to search for
     * @return the Product that has the same ID as i
     */
    
    public Product getProduct(int i)
    {
    	Node n= search(i, root);
    	return n.data;
    }
    
    

    /***MUTATORS***/
    
    /**
     * This is the wrapper method to call insert for the primary and secondary BSTs
     * Inserts a new node of Product info into the BST
     * sorted according to primary key- name
     * sorted according to secondary key- id
     * @param data the data to insert
     * @param s the BST in which data is sorted according to ID
     */
    public void insert(Product data, BST s) {
    	
    	if(root==null || s.root==null)
    		{root=new Node(data);
    	    s.root= new Node(data); }
    	else
    		{insertP(data,root);
    			insertS(data,s.root); }
    
    }
    
    /**
     * Helper method to insert into BST sorted according to primary key
     * Inserts a new value in the tree
     * @param data the data to insert
     * @param node the current node in the
     * search for the correct location
     * in which to insert
     */
    private void insertP(Product data, Node node) {
    	
    	if(node.data.compareToP(data)>0)     //go left
    	{
    		if(node.left==null)
    			node.left=new Node(data);
    		
    		else
    			insertP(data,node.left);
    	}
    	
    	else    							//go right
    	{
    		if(node.right==null)
    			node.right= new Node(data);
    		
    		else
    			insertP(data,node.right);
    	}
    	
    
    }
    
   
    /**
     * Helper method to insert into BST sorted according to secondary key
     * Inserts a new value in the tree
     * @param data the data to insert
     * @param node the current node in the
     * search for the correct location
     * in which to insert
     */
    private void insertS(Product data, Node node) {
    	
    	if(node.data.compareToS(data)>0)     //go left
    	{
    		if(node.left==null)
    			node.left=new Node(data);
    		
    		else
    			insertS(data,node.left);
    	}
    	
    	else    							//go right
    	{
    		if(node.right==null)
    			node.right= new Node(data);
    		
    		else
    			insertS(data,node.right);
    	}
    	
    
    }
    
    /**
     * wrapper method for primary and secondary BSTs
     * has to be called from primary BST
     * Removes the product from both the BSTs 
     * @param data the value to remove
     * @param s the secondary BST 
     * @precondition !isEmpty()
     * @precondition the data is located in the tree
     * @throws NoSuchElementException when the
     * precondition is violated
     */
    
    public void remove(Product data, BST s) throws NoSuchElementException{
    	
        if(root==null)
        	throw new NoSuchElementException("remove(): There is no such element in the BST or the BST is empty!");
        
        else
        	{ root=removeP(data,root);
        	  s.root=removeS(data,s.root);
        	}
        
        
    }
    
    /**
     * Helper method to the remove product in primary BST
     * @param data the data to remove
     * @param node the current node
     * @return an updated reference variable for the root of the primary BST
     */
    private Node removeP(Product data, Node node) {   
    	
    	if(node==null)
    		return node;
    	
    	else if(data.compareToP(node.data)<0)  
    		node.left=removeP(data,node.left);
    	
    	else if(data.compareToP(node.data)>0)
    		node.right=removeP(data,node.right);
    	
    	else
    	{
    		if(node.right==null && node.left==null)
    			node=null;
    		
    		else if(node.right==null && node.left!=null)
    			node=node.left;
    		
    		else if(node.right!=null && node.left==null)
    			node=node.right;
    		else
    		{	Product temp= findMinP(node.right);
    			node.data= temp;
    			node.right=removeP(temp,node.right);
    			
    		}
    	}
    	return node;
    }
    
    /**
     * Helper method to the remove product in secondary BST
     * @param data the data to remove
     * @param node the current node
     * @return an updated reference variable for the root of the secondary BST
     */      
    private Node removeS(Product data, Node node) {   
    	
    	if(node==null)
    		return node;
    	
    	else if( data.compareToS(node.data)<0)  
    		node.left=removeS(data,node.left);
    	
    	else if(data.compareToS(node.data)>0)
    		node.right=removeS(data,node.right);
    	
    	else
    	{
    		if(node.right==null && node.left==null)
    			node=null;
    		
    		else if(node.right==null && node.left!=null)
    			node=node.left;
    		
    		else if(node.right!=null && node.left==null)
    			node=node.right;
    		else
    		{	Product temp= findMinS(node.right);
    			node.data= temp;
    			node.right=removeS(temp,node.right);
    			
    		}
    	}
    	return node;
    }
    
    
   

    /***ADDITIONAL OPERATIONS***/
    
    /**
     * Prints the data in pre order
     * to the console
     */
    public void preOrderPrint() {
    	preOrderPrint(root);
    	System.out.println();
    }
    
    /**
     * Helper method to preOrderPrint method
     * Prints the data in pre order
     * to the console
     */
    private void preOrderPrint(Node node) {
    	if(node==null)
			return;
		
		else 
		{
			System.out.print(node.data+" ");
			preOrderPrint(node.left);
			preOrderPrint(node.right);  
		}
    		
       
    }
    
    /**
     * Prints the data in sorted order 
     * to the console
     */
    public void inOrderPrint() {
    	inOrderPrint(root);
    	System.out.println();
    }
    
    /**
     * Helper method to inOrderPrint method
     * Prints the data in sorted order
     * to the console
     */
    private void inOrderPrint(Node node) {
    	
    	if(node==null)
			return;
		
		else 
		{
			inOrderPrint(node.left);
			
			System.out.println(node.data+" ");
			inOrderPrint(node.right);  
		}
    }
    
    /**
     * Prints the data in post order
     * to the console
     */
    public void postOrderPrint() {
    	postOrderPrint(root);
    	System.out.println();
    }
    
    /**
     * Helper method to postOrderPrint method
     * Prints the data in post order
     * to the console
     */
    private void postOrderPrint(Node node) {
    	if(node==null)
			return;
		
		else 
		{
			postOrderPrint(node.left);
			postOrderPrint(node.right); 
			System.out.print(node.data+" ");
		}
    }
    
    
    /**
     * 
     * @param l the existing list that contains the items(if any)
     * @param ID according to ID, we search and add to cart(list)
     * @return the list that has the cart items
     * SEARCHING HAPPENS ONLY IN THE SECONDAEY BST sorted accoridng to ID
     * has to be called from secondary BST s
     */
    
    public List<Product> addToCart(List<Product> l, int ID) 
    {
    	
    	boolean info= search(ID);
    	Node n= search(ID, root);
    	
    	if(info!=false)
    	{Product p= n.data;
    	l.addLast(p);
    	System.out.println("The product with ID number "+ID+ " has been added to your cart!");
    	}
    	
    	else
    		System.out.println("The product with ID number "+ID+ " does not exist in our store!");
    	
    	return l;
    }
    
    
    /**update()
     * helper method
     * writes into the existing textfile the new products
     * employee can remove and add
     * thus function is a helper to write into 
     * the same file, the altered products
     */
    public void update() throws IOException
   {

		File file= new File("productInfo.txt");
		FileWriter writer= new FileWriter(file);
		update(writer,root);
		writer.close();
   }
    
    /**update()
     * wrapper method 
     * writes into the existing textfile the new products
     * employee can remove and add
     * thus function is a helper to write into 
     * the same file, the altered products
     */

    private void update(FileWriter writer, Node node) throws IOException{
    	
    	if(node==null)
			return;
		
		else 
		{
			update(writer,node.left);
			writer.write(node.data.getName() +" "+ node.data.getCat() +" "+ node.data.getBrand() +" "+ node.data.getPrice()
			+" "+node.data.getId() +" "+node.data.getShip()+"\n");
			update(writer,node.right);  
		}
    
    }
    
    
    
    
      
    
    
    
}
