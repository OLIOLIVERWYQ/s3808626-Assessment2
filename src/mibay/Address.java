package mibay;

public class Address {
	private String street;
	private String suburb;
	private int postcode;
	
	public Address(String street, String suburb, int postcode){
		this.street = street;
		this.suburb = suburb;
		this.postcode = postcode;		
	}
	
	public String getStreet(){ return street; }
	public String getSuburb(){ return suburb; }
	public int getPostCode(){ return postcode; }

	public void setStreet(String street) {
        this.street = street;
    }
    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }
    public void setPostCode(int postCode) {
        this.postcode = postCode;
    }
}
