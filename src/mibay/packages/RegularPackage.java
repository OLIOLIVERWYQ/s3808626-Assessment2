package mibay.packages;

import java.time.LocalDate;
import java.util.Arrays;
import mibay.Customer;
import mibay.Product;

public class RegularPackage {
	public LocalDate date;
	public Customer customer;
	public Product[] productArray;
	
	public RegularPackage(LocalDate date, Customer customer,Product product) {
		this.date = date;
		this.customer = customer;
		productArray = new Product[1];
		this.productArray[0] = product;	
	}
	public LocalDate getDate() {
		return date;
	}
	public Customer getCustomer() {
		return customer;
	}
	public Product[] getProducts() {
		return productArray;
	}
	public boolean addProduct(Product product) {
            for(int i = 0; i < this.productArray.length; i++) {
                if (this.productArray[i].getName().equals(product.getName()) ) {
                    return false;
                }
            }
            this.productArray = (Product[])Arrays.copyOf(this.productArray, this.productArray.length + 1);
            this.productArray[this.productArray.length - 1] = product;
            return true;       
	}
	public double getCost() {
		double sumCost = 0;
		for(int i=0;i<productArray.length;i++) {
			sumCost += productArray[i].getCost();
		}
		return sumCost;
	}
	public String toString() {		
		String RegularPackageInfo = String.format("%-20s"+"%-15s",getCustomer(),getProducts());
		return RegularPackageInfo;
	}
}
