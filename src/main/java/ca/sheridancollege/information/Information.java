package ca.sheridancollege.information;

import java.util.ArrayList;
import lombok.*;
@Data

public class Information {

	public ArrayList<String> name() {
		ArrayList<String> name = new ArrayList<String>();
		name.add("Jonathan");
		name.add("Harpreet");
		name.add("Harshit");
		name.add("Love");
		name.add("Jonathan P");
		return name;
		
	}
	
	public ArrayList<String> number() {
		ArrayList<String> number = new ArrayList<String>();
		number.add("777777777");
		number.add("999999999");
		number.add("888888888");
		number.add("444444444");
		number.add("555555555");
		return number;
	}
	
	public ArrayList<String> address() {
		ArrayList<String> address = new ArrayList<String>();
		address.add("Brampton");
		address.add("Brampton");
		address.add("Toronto");
		address.add("London");
		address.add("London");
		return address;
	}
	
	public ArrayList<String> email() {
		ArrayList<String> email = new ArrayList<String>();
		email.add("Hello@Gmail.com");
		email.add("Yellow@Gmail.com");
		email.add("Green@Gmail.com");
		email.add("Blue@Gmail.com");
		email.add("Yel@Gmail.com");
		return email;
	}
	
	
	public ArrayList<String> repairInfo() {
		ArrayList<String> repairInfo = new ArrayList<String>();
		repairInfo.add("Phone Repair");
		repairInfo.add("Laptop Repair, Damaged screen");
		repairInfo.add("Whater Damage");
		repairInfo.add("Screen Tablet");
		repairInfo.add("My phone have no problem");
		return repairInfo;
	}
}
