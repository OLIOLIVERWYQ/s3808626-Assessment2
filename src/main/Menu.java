package main;

import mibay.Address;
import mibay.Customer;
import mibay.MiBaySystem;
import mibay.Product;
import mibay.exceptions.MiBayException;
import mibay.packages.PlatinumPackage;
import mibay.packages.RegularPackage;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;

public class Menu{
    static int index = 0;
    public MiBaySystem MBS = new MiBaySystem();
    public RegularPackage regularpackage;
    public PlatinumPackage platinumpackage;    
    
    public void run(){

    	System.out.println("*** MiBaySystem Menu ***");
        System.out.println("Add Customer                         AC");
        System.out.println("Add Product                          AP");
        System.out.println("Prepare Package                      PP");
        System.out.println("Display all Customers                DC");
        System.out.println("Display all Products                 DP");
        System.out.println("Display all Packages                 DA");
        System.out.println("Seed Data                            SD");
        System.out.println("Exit Program                         EP");
        System.out.println();
        System.out.printf("Select an option: ");
    	
        Scanner input = new Scanner(System.in);
        String order = input.nextLine();
        
        if(order.equalsIgnoreCase("ac")){
        while(true){    	        	
        	int id = MBS.customerList.length + 1;
            String firstname;
            String lastname;
            String street;
            String suburb;
            String postcode;
            
       	    System.out.println("- Add Customer (Press enter to return to the menu) -");                
            System.out.println("ID:               " + id);
    
            System.out.printf("Enter first name: ");
            firstname = input.nextLine();   
            if(firstname.isEmpty()) 
               break;   
       		    
            System.out.printf("Enter last name:  ");
            lastname = input.nextLine();
            if(lastname.isEmpty())
               break;  
              
            System.out.printf("Enter street:     ");
            street = input.nextLine();
            if(street.isEmpty())
               break;    
              
            System.out.printf("Enter suburb:     ");
            suburb = input.nextLine();
            if(suburb.isEmpty())
               break;    
              
            System.out.printf("Enter postcode:   ");
            postcode = input.nextLine();
            if(postcode.isEmpty())
               break;   
            if(Integer.parseInt(postcode)<1 || Integer.parseInt(postcode)>9999) {
            	System.err.println("Invalid postcode, please try again.");
                break;
            }
  
            Address address = new Address(street,suburb,Integer.parseInt(postcode));
            Customer customer = new Customer(id,firstname,lastname,address);
            MBS.customerList = Arrays.copyOf(MBS.customerList, MBS.customerList.length+1);
            MBS.customerList [MBS.customerList.length-1] = customer;
            System.out.println(firstname + " " + lastname + " was successfully add to the system.");
               break;             
        }
          System.out.println();
         	     run();
   
       }else if(order.equalsIgnoreCase("ap")){
    	   while(true) {    		   
    		   String productname;
               String weight;
               String cost;

               System.out.println("- Add Product (Press enter to return to the menu) -");
               System.out.printf("Enter name:     ");
               productname = input.nextLine();
               if(productname.isEmpty())
                break;              
               System.out.printf("Enter weight:   ");
               weight = input.nextLine();    
               if(weight.isEmpty())
                break;                 
               System.out.printf("Enter cost:     ");
               cost = input.nextLine();
               if(cost.isEmpty())
                break;                 
               Product product = new Product(productname,Integer.parseInt(weight),Double.parseDouble(cost));               
               if(addProduct(product)) {
            	   System.out.println(MBS.productList[MBS.productList.length - 1].getName() + " was successfully added to the system.");
               }else {
            	   System.err.println("Unable to add " + product.getName() + " to the system.");
               }          
               break;               
    	       }System.out.println();
     	         run();
    	  
       }else if(order.equalsIgnoreCase("pp")){
    	   while(true) {    		   
    		   String customerChoice = null;
    		   String productChoice = null;
    		   String deliverDay;
    		   String deliverMonth;
    		   String deliverYear;
    		   String IsPlatinumPackage;
    		   String IsAddProduct; 
    		   int day = 1;
    		   int month = 1;
    		   int year = 1;
    		   
    		   System.out.println("- Perpare Package (press enter to return to the menu) -");
    		   if(MBS.customerList.length == 0) {
    			   System.err.println("Sorry, there are no customers in the system.");
    			   break;
    		   }else {
    			   for(int i=0;i<MBS.customerList.length;i++) {
        			   System.out.println(MBS.customerList[i].getId()+". "+MBS.customerList[i].getFullName());
        		   }
    			   System.out.printf("Select a customer from the list: ");
    			   customerChoice = input.nextLine();
    			   if(customerChoice.isEmpty())
    				   break;    				    			   
    			   if(Integer.parseInt(customerChoice) <= 0 || Integer.parseInt(customerChoice) > MBS.customerList.length) {
  			        	System.err.println("Invalid customer id, please try again.");
  			        	break;       			     
  			        }
    		   }
    		   
    		   if(MBS.productList.length == 0) {
    			   System.err.println("Sorry, there are no products in the system.");
    			   break;
    		   }else {
    			   for(int i=0;i<MBS.productList.length;i++) {
        			   System.out.println(i+1 +". "+MBS.productList[i].getName());
        		   }
    			   System.out.printf("Select a product from the list:  ");
    			   productChoice = input.nextLine();
    			   if(productChoice.isEmpty())
    				   break;   			   
    			   if(Integer.parseInt(productChoice) <= 0 || Integer.parseInt(productChoice) > MBS.productList.length) {
  			        	System.err.println("Invalid product id, please try again..");
  			        	break;       			     
  			        }    			   
    		   }
    		   
    		   System.out.println("Enter the delivery date.");    		   
    		   System.out.printf("Enter day:     ");
    		   deliverDay = input.nextLine();
    		   if(deliverDay.isEmpty())
				   break;
    		   day = Integer.parseInt(deliverDay);    		   
    		   System.out.printf("Enter month:   ");
    		   deliverMonth = input.nextLine();
    		   if(deliverMonth.isEmpty())
				   break;
    		   month = Integer.parseInt(deliverMonth);   
    		   System.out.printf("Enter year:    ");
    		   deliverYear = input.nextLine();
    		   if(deliverYear.isEmpty())
				   break;
    		   year = Integer.parseInt(deliverYear);    		   
    		   LocalDate date = LocalDate.of(year, month, day);     		   
    		   if(date.isBefore(LocalDate.now()) || date.isEqual(LocalDate.now())) {
    			   System.err.println("Invalid deliver date, please try again.");
   			       break;
    		   }	   
    		   System.out.printf("Is this a platinum package?(Y/N): ");
    		   IsPlatinumPackage = input.nextLine();
    		   if(IsPlatinumPackage.isEmpty())
				   break;
    		   
//    		   ##Platinum Package
    		   else if(IsPlatinumPackage.equalsIgnoreCase("y")) {
    			   System.out.printf("Enter your member number: ");
    			   platinumpackage.memberNumber = input.nextLine();
    			   if(platinumpackage.memberNumber.isEmpty())
    				   break;    			   
    			   if(PlatinumPackage.isValidMemberNumber(platinumpackage.memberNumber)) {
    				   Customer customer = new Customer(MBS.customerList[Integer.parseInt(customerChoice)-1].getId(),MBS.customerList[Integer.parseInt(customerChoice)-1].getFirstName(),MBS.customerList[Integer.parseInt(customerChoice)-1].getLastName(),MBS.customerList[Integer.parseInt(customerChoice)-1].getAddress());        			   
        			   Product Product = new Product(MBS.productList[Integer.parseInt(productChoice)-1].getName(),MBS.productList[Integer.parseInt(productChoice)-1].getWeight(),MBS.productList[Integer.parseInt(productChoice)-1].getCost()); 			 
        			   PlatinumPackage platinum = new PlatinumPackage(date,customer,Product,platinumpackage.memberNumber);
        			   
        			   MBS.Platinum = new PlatinumPackage[1];
        			   MBS.Platinum[0] = platinum;
        			   
        			   while(true) {
              			    System.out.printf("Would you like to add another product in your package?(Y/N): ");
              			    IsAddProduct = input.nextLine();
              			    if(IsAddProduct.isEmpty())
              			        break;
              			    if(IsAddProduct.equalsIgnoreCase("y")) {
              			        for(int i=0;i<MBS.productList.length;i++) {
              				        System.out.println(i+1 +". "+MBS.productList[i].getName());
              			        }
              			        System.out.printf("Select a product from the list: ");
              			        productChoice = input.nextLine();
              			        if(productChoice.isEmpty())
              				        break;              			        
              			        if(Integer.parseInt(productChoice) <= 0 || Integer.parseInt(productChoice) > MBS.productList.length) {
              			        	System.err.println("Please enter valid number.");
              			        	break;       			     
              			        }              			        
              			        if(platinumpackage.addProduct(MBS.productList[Integer.parseInt(productChoice)-1])) {
              			        	System.out.println("Product "+ MBS.productList[Integer.parseInt(productChoice)-1].getName() +" added to package succcessfully." );              			        	
              			        }else {
              			        	System.err.println("Unable to add product to the package, product "+ MBS.productList[Integer.parseInt(productChoice)-1].getName() +" already exist." );              			        	
              			        }             			               			         			     
              			    }
              			    else if(IsAddProduct.equalsIgnoreCase("n")) {
              			    	break;
              			    }else {
               			    	System.err.println("Invalid, please try again.");                   			    
               			    }  
              			  }	   
             			    System.out.println("Package for "+ MBS.customerList[Integer.parseInt(customerChoice)-1].getFullName() +" was successfully perpared." );      		   
              		        break; 
    			   }else {
    				   System.err.println("Invalid member number, please try again.");
    				   System.out.println();
    			   }
    			   
//    			## Regular Package   
    		   }else if(IsPlatinumPackage.equalsIgnoreCase("n")) {
    			   Customer customer = new Customer(MBS.customerList[Integer.parseInt(customerChoice)-1].getId(),MBS.customerList[Integer.parseInt(customerChoice)-1].getFirstName(),MBS.customerList[Integer.parseInt(customerChoice)-1].getLastName(),MBS.customerList[Integer.parseInt(customerChoice)-1].getAddress());    			   
    			   Product Product = new Product(MBS.productList[Integer.parseInt(productChoice)-1].getName(),MBS.productList[Integer.parseInt(productChoice)-1].getWeight(),MBS.productList[Integer.parseInt(productChoice)-1].getCost()); 			   			   
    			   RegularPackage regular = new RegularPackage(date,customer,Product);  			   
    			   MBS.Regular = new RegularPackage[1];
    			   MBS.Regular[0] = regular;
    			   
    			   while(true) {
    				   index = 1;
       			    System.out.printf("Would you like to add another product in your package?(Y/N): ");
       			    IsAddProduct = input.nextLine();
       			    if(IsAddProduct.isEmpty())
       			        break;

       			    if(IsAddProduct.equalsIgnoreCase("y")) {
       			        for(int i=0;i<MBS.productList.length;i++) {
       				        System.out.println(i+1 +". "+MBS.productList[i].getName());
       			        }
       			        System.out.printf("Select a product from the list: ");
       			        productChoice = input.nextLine();
       			        if(productChoice.isEmpty())
       				        break;
       			        
       			        if(Integer.parseInt(productChoice) <= 0 || Integer.parseInt(productChoice) > MBS.productList.length) {
       			        	System.err.println("Invalid  product number, please try again.");
       			        	break;       			     
       			        }   
       			        if(regularpackage.addProduct(MBS.productList[Integer.parseInt(productChoice)-1])) {
       			        	System.out.println("Product "+ MBS.productList[Integer.parseInt(productChoice)-1].getName() +" added to package succcessfully." );
       			        }else {
       			        	System.err.println("Unable to add product to the package, product "+ MBS.productList[Integer.parseInt(productChoice)-1].getName() +" already exist." );       			        	
       			        }       			               			         			     
       			    }
       			    else if(IsAddProduct.equalsIgnoreCase("n")) {
       			    	break;
       			    }else {
       			    	System.err.println("Invalid, please try again.");       			    
       			    }
       			  }   			   
      			    System.out.println("Package for "+ MBS.customerList[Integer.parseInt(customerChoice)-1].getFullName() +" was successfully perpared." );      		   
       		        break; 
    		   }else {
    			   System.err.println("Invalid, please try again.");
    		   }
    	   }System.out.println();
	         run();
    	   
       }else if(order.equalsIgnoreCase("dc")){
    	   System.out.println("- Displaying all Customers -");
		   MBS.DislayCustomer();
		   System.out.println();
	       run();

       }else if(order.equalsIgnoreCase("dp")){
    	   System.out.println("- Displaying all Products -");
    	   MBS.DislayProduct();
    	   System.out.println();
    	   run();

       }else if(order.equalsIgnoreCase("da")){    	   
    	   System.out.println("- Displaying all packages -");   	   
    	   if(MBS.Regular.length == 0 && MBS.Platinum.length == 0) {
    		   System.err.println("There are no packages in the system"); 
    	   }else {
    		   
    		   System.out.println("------------------------------------");
    		   System.out.println("Regular Package "); 
    		   if(MBS.Regular.length == 0) {
    			   System.err.println("There are no regular packages in the system");
    		   }
    		   for(int i=0;i<MBS.Regular.length;i++) {
    			   LocalDate Rdate = MBS.Regular[i].getDate();
    		       String RdateStr = Rdate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));       
    		       System.out.printf("%-22s%-15s\n","name:",MBS.Regular[i].getCustomer().getFullName());	       
    		       System.out.printf("%-22s%-15s\n","Address:",MBS.Regular[i].getCustomer().getAddress().getStreet());
    		       System.out.printf("%-22s%s%s\n","",MBS.Regular[i].getCustomer().getAddress().getSuburb(),MBS.Regular[i].getCustomer().getAddress().getPostCode());
    		       System.out.printf("%-22s%-15s\n","Delivery Date:",RdateStr);
    		       System.out.printf("%-22s%.2f\n","Total Cost:",MBS.Regular[i].getCost());
    		       System.out.printf("%-22s\n","Products Ordered");
    		       System.out.printf("%-22s%-15s%-15s\n","Name","Weight","Cost");
    		       for(int j=0;j<MBS.Regular[i].getProducts().length;j++) {
    		    	   System.out.printf("%-22s%-15s%-15s\n",(MBS.Regular[i].getProducts())[j].getName(),Integer.toString(MBS.Regular[i].getProducts()[j].getWeight()) + "g", "ก็"+Double.toString(MBS.Regular[i].getProducts()[j].getCost()));
    		       }
    	       }
    		   
    		   System.out.println("------------------------------------");
    		   System.out.println("Platinum Package ");
    		   if(MBS.Platinum.length == 0) {
    			   System.err.println("There are no platinum packages in the system");
    		   }
    		   for(int i=0;i<MBS.Platinum.length;i++) {
    			   LocalDate Pdate = MBS.Platinum[i].getDate();
    		       String PdateStr = Pdate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
   		          		       
    		       System.out.printf("%-22s%-15s\n","name:",MBS.Platinum[i].getCustomer().getFullName());	       
    		       System.out.printf("%-22s%-15s\n","Address:",MBS.Platinum[i].getCustomer().getAddress().getStreet());
    		       System.out.printf("%-22s%s%s\n","",MBS.Platinum[i].getCustomer().getAddress().getSuburb(),MBS.Platinum[i].getCustomer().getAddress().getPostCode());
    		       System.out.printf("%-22s%-15s\n","Delivery Date:",PdateStr);
    		       System.out.printf("%-22s%.2f\n","Total Cost:",MBS.Platinum[i].getCost());
    		       System.out.printf("%-22s\n","Products Ordered");
    		       System.out.printf("%-22s%-15s%-15s\n","Name","Weight","Cost");
    		       for(int j=0;j<MBS.Platinum[i].getProducts().length;j++) {
    		    	   System.out.printf("%-22s%-15s%-15s\n",(MBS.Platinum[i].getProducts())[j].getName(),Integer.toString(MBS.Platinum[i].getProducts()[j].getWeight()) + "g", "ก็"+Double.toString(MBS.Platinum[i].getProducts()[j].getCost()));
    		       }
		       }	   
    	     }System.out.println();
	         run();
       }else if(order.equalsIgnoreCase("sd")){
    	   if(MBS.customerList.length == 0 && MBS.productList.length == 0 && MBS.Regular.length == 0 && MBS.Platinum.length == 0) {    		   
    		   MBS.SeedCustomers();
    		   MBS.SeedProducts();
    		   MBS.SeedRegularPackage();
    		   MBS.SeedPlatinumPackage();
    		   System.out.println("Customer, product and package have been seeded");
    	   }else {
    		   System.err.println("Customers, products or packages already exist, seeding aborted.");
    		   
    	   }System.out.println();
	       run();

       }else if(order.equalsIgnoreCase("ep")){
    	   System.out.println("Program Ending.");
    	   MBS.creatProductFile();
    	   System.exit(-1);

       }else {
    	   System.err.println("Please enter correct order.");
    	   System.out.println();
	       run();
       }

    }     
    
    public boolean addProduct(Product product) {   	
    	    for(int i = 0; i < MBS.productList.length; ++i) {	    	
                if (MBS.productList[i].getName().equals(product.getName())) {
      	            return false;
                }
            }
        MBS.productList = Arrays.copyOf(MBS.productList, MBS.productList.length + 1);
        MBS.productList[MBS.productList.length - 1] = product;
        return true;
    }
    
}


//
//  System.out.printf("Enter first name: ");
//  firstname = input.nextLine();  
//  if(firstname.isEmpty() || firstname == "") 
//     throw new MiBayException("First name is null or empty.");
//
//  System.out.printf("Enter last name:  ");   
//  lastname = input.nextLine();
//  if(lastname.isEmpty() || lastname == "") 
//     throw new MiBayException("Last name is null or empty.");
//   
//  System.out.printf("Enter street:     ");
//  street = input.nextLine();
//  if(street.isEmpty() || street == "") 
//     throw new MiBayException("street is null or empty.");  
//   
//  System.out.printf("Enter suburb:     ");
//  suburb = input.nextLine();    
//  if(suburb.isEmpty() || suburb == "") 
//	   throw new MiBayException("suburb is null or empty."); 
//   
//  try {
//     postcode = input.nextLine();
//     if(Integer.parseInt(postcode)<1 || Integer.parseInt(postcode)>9999) {
//	      throw new MiBayException("Invalid postcode, please try again.");
//     }
//  }catch(MiBayException e) {
//     e.getMessage();
//     System.out.println();
//     run();
//  }

