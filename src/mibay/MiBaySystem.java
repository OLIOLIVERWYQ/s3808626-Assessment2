package mibay;

import java.io.PrintStream;
import java.time.LocalDate;
import java.util.Arrays;
import mibay.MiBaySystem;
import mibay.packages.PlatinumPackage;
import mibay.packages.RegularPackage;

public class MiBaySystem {
	
	public Customer[] customerList;
    public Product[] productList;
    public RegularPackage[] Regular;
    public PlatinumPackage[] Platinum;	
    
    public MiBaySystem() {
    	customerList = new Customer[0];
    	productList = new Product[0];
    	Regular = new RegularPackage[0];
    	Platinum = new PlatinumPackage[0];
    }
    
    public void DislayCustomer() {
    	if(customerList.length == 0) {
			System.err.println("There are no customers in the system.");
		}else {
			System.out.printf("%-5s%-30s%-35s\n","ID","Name","Address");
			for(int i=0;i<customerList.length;i++) {
				System.out.printf("%-5s%-30s%s%s%s\n",Integer.toString(customerList[i].getId()),customerList[i].getFullName(), customerList[i].getAddress().getStreet()+" ",customerList[i].getAddress().getSuburb()+" ", customerList[i].getAddress().getPostCode()+" ");
			}
		}   
    }
    
    public void DislayProduct() {
    	if(productList.length == 0) {
			System.err.println("There are no products in the system.");
		}else {
			System.out.printf("%-20s%-15s%-15s\n","Name","Weight","Cost");
			for(int i=0;i<productList.length;i++) {
				System.out.printf("%-20s%-15s%-15s\n",productList[i].getName(),Integer.toString(productList[i].getWeight()) + "g", "ก็"+Double.toString(productList[i].getCost()));
			}
		}	   
    }
    
    public void SeedCustomers(){
    	Address address1 = new Address("83 Dalgliesh street","South Yarra",3141);
		Address address2 = new Address("42 Prode Avenue","Elwood",3184);
		   
	    Customer customer1 = new Customer(1,"Matthew","Bolger",address1);  
	    Customer customer2 = new Customer(2,"Gayan","Wijesinghe",address2); 
	       
	    customerList = Arrays.copyOf(customerList, customerList.length+1);
		customerList [customerList.length-1] = customer1;
		   
		customerList = Arrays.copyOf(customerList, customerList.length+1);
		customerList [customerList.length-1] = customer2;      
    }
    
    public void SeedProducts(){
    	Product product1 = new Product("The Lion King",320,17.99);
    	Product product2 = new Product("Man of Steel",300,24.99);
    	Product product3 = new Product("Avengers",500,30.00);
    	Product product4 = new Product("Inception",500,20.05);
	       
	    productList = Arrays.copyOf(productList, productList.length+1);
	    productList [productList.length-1] = product1;
	    
	    productList = Arrays.copyOf(productList, productList.length+1);
	    productList [productList.length-1] = product2;
	    
	    productList = Arrays.copyOf(productList, productList.length+1);
	    productList [productList.length-1] = product3;
	    
	    productList = Arrays.copyOf(productList, productList.length+1);
	    productList [productList.length-1] = product4;
    }
    
    public void SeedRegularPackage(){
    	LocalDate date1 = LocalDate.of(2021, 07, 15);   
    	Address address1 = new Address("83 Dalgliesh street","South Yarra ",3141);   
	    Customer customer1 = new Customer(1,"Matthew","Bolger",address1);  
	    Product product1 = new Product("The Lion King",320,17.99);
	    
    	RegularPackage regular1 = new RegularPackage(date1,customer1,product1);
    	
    	Regular = new RegularPackage[1];
		Regular[0] = regular1;
    }
    
    public void SeedPlatinumPackage(){
    	LocalDate date2 = LocalDate.of(2021, 07, 15);
    	Address address2 = new Address("42 Prode Avenue","Elwood ",3184);
    	Customer customer2 = new Customer(2,"Gayan","Wijesinghe",address2);
    	Product product2 = new Product("Man of Steel",300,24.99);
    	Product product3 = new Product("Avengers",500,30.00);
    	
    	PlatinumPackage platinum1 = new PlatinumPackage(date2,customer2,product2,"M1234");
    	
    	Platinum = new PlatinumPackage[1];
		Platinum[0] = platinum1;
		Platinum[0].addProduct(product3);
    }
    
    public void creatProductFile(){
    	try {
    		PrintStream ps = new PrintStream("./product.txt");
            System.setOut(ps);
            System.out.printf("%-20s%-15s%-15s\n","Name","Weight","Cost");
		    for(int i=0;i<productList.length;i++) {
			    System.out.printf("%-20s%-15s%-15s\n",productList[i].getName(),Integer.toString(productList[i].getWeight()) + "g", "ก็"+Double.toString(productList[i].getCost()));
		    }
    	}catch(Exception e) {}
        
    }
    
    

    
    
    
}
