package mibay.packages;

import java.time.LocalDate;
import mibay.Customer;
import mibay.Product;

public class PlatinumPackage extends RegularPackage{
	public String memberNumber;
	
	public PlatinumPackage(LocalDate date, Customer customer,Product product, String memberNumber){
		super(date,customer,product);
		this.memberNumber = memberNumber;
	}

	@Override
	public double getCost() {
		return super.getCost() * 0.9;
	}

	public static boolean isValidMemberNumber(String memberNumber) {
		if (memberNumber.length() != 5) {
			return false;
		} else {
			if (Character.isUpperCase(memberNumber.charAt(0))) {
				for (int i = 1; i < 5; i++) {
					if (Character.isDigit(memberNumber.charAt(i))) {
						return true;
					}
				}
			}
			return false;
		}
	}	
}




