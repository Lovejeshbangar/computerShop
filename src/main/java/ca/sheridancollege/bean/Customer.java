package ca.sheridancollege.bean;

import java.io.Serializable;
import java.util.ArrayList;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6258237332781941831L;
	private int id;	
	private String name;
	private String number;
	private String address;
	private String email;
	private int customerId;
	private String repairInfo;
	private int price;
	private int laborCharges;
	private int hardwareCharges;
	private int shippingCharges;
	private String timeRequired;

	
} 

