package ca.sheridancollege.databases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.bean.Customer;
import ca.sheridancollege.information.Information;

@Repository
public class DatabaseAccess {

	@Autowired
	public NamedParameterJdbcTemplate jdbc;

	// add everything into the customer table
	public void addCustomerInTable(Customer customer) {
		MapSqlParameterSource pr = new MapSqlParameterSource();
		String query = "Insert into customer(name, number, address, email, repairInfo) values (:name, :number, :address, :email, :repairInfo)";
		pr.addValue("name", customer.getName());
		pr.addValue("number", customer.getNumber());
		pr.addValue("address", customer.getAddress());
		pr.addValue("email", customer.getEmail());
		pr.addValue("repairInfo", customer.getRepairInfo());
		jdbc.update(query, pr);
	}

	// displaying everything from customer table
	public ArrayList<Customer> getCustomerRecords() {
		String q = "select * from customer ";
		ArrayList<Customer> customer = (ArrayList<Customer>) jdbc.query(q,
				new BeanPropertyRowMapper<Customer>(Customer.class));
		return customer;
	}

	// get edit records for the customer
	public Customer editCustomerRecord(int id) {
		String q = "select * from customer where customerId=:id ";
		MapSqlParameterSource pr = new MapSqlParameterSource();
		pr.addValue("id", id);
		ArrayList<Customer> customer = (ArrayList<Customer>) jdbc.query(q, pr,
				new BeanPropertyRowMapper<Customer>(Customer.class));
		if (customer.size() > 0) {
			return customer.get(0);
		} else {
			return null;
		}
	}

	// edit records for the customer
	public void editCustRecord(Customer customer) {
		String q = "update customer set name=:name, number=:number, address=:address, email=:email, repairInfo=:repairInfo where customerId=:id";
		MapSqlParameterSource pr = new MapSqlParameterSource();
		pr.addValue("id", customer.getCustomerId());
		pr.addValue("name", customer.getName());
		pr.addValue("number", customer.getNumber());
		pr.addValue("address", customer.getAddress());
		pr.addValue("email", customer.getEmail());
		pr.addValue("repairInfo", customer.getRepairInfo());
		jdbc.update(q, pr);
	}

	// delete records for the customer
	public void deleteCustomerRecord(int id) {
		MapSqlParameterSource pr = new MapSqlParameterSource();
		String q = "delete from customer where customerId=:id";
		pr.addValue("id", id);
		jdbc.update(q, pr);
	}

	// adding to admin
	public Customer addRecordsAdmin(int id) {
		String q = "select * from customer where customerId=:id ";
		MapSqlParameterSource pr = new MapSqlParameterSource();
		pr.addValue("id", id);
		ArrayList<Customer> customer = (ArrayList<Customer>) jdbc.query(q, pr,
				new BeanPropertyRowMapper<Customer>(Customer.class));
		if (customer.size() > 0) {
			return customer.get(0);
		} else {
			return null;
		}
	}

	// adding into admin table
	public void addIntoAdmin(Customer c) {
		MapSqlParameterSource pr = new MapSqlParameterSource();
		String q = "insert into customerrepairinfo (customerId,laborCharges, hardwareCharges, shippingCharges, timeRequired) values (:customerId,:laborCharges, :hardwareCharges, :shippingCharges, :timeRequired)";
		
		pr.addValue("customerId", c.getCustomerId());
		pr.addValue("laborCharges", c.getLaborCharges());
		pr.addValue("hardwareCharges", c.getHardwareCharges());
		pr.addValue("shippingCharges", c.getShippingCharges());
		pr.addValue("timeRequired", c.getTimeRequired());
		jdbc.update(q,pr);
	}

	public Object searchCustbyId(int parseInt) {
		MapSqlParameterSource pr = new MapSqlParameterSource();
		String query="Select * from customer where customerId=:id";
		pr.addValue("id", parseInt);
		ArrayList<Customer> customer = (ArrayList<Customer>) jdbc.query(query, pr,
				new BeanPropertyRowMapper<Customer>(Customer.class));
		if (customer.size() > 0) {
			return customer.get(0);
		} else {
			return null;
		}

	}

	public Object searchCustbyName(String search) {
		MapSqlParameterSource pr = new MapSqlParameterSource();
		String query="Select * from customer where name=:name";
		pr.addValue("name", search);
		ArrayList<Customer> customer = (ArrayList<Customer>) jdbc.query(query, pr,
				new BeanPropertyRowMapper<Customer>(Customer.class));
		if (customer.size() > 0) {
			return customer.get(0);
		} else {
			return null;
		}

	}

	public Object searchCustbyNumber(String search) {
		MapSqlParameterSource pr = new MapSqlParameterSource();
		String query="Select * from customer where number=:number";
		pr.addValue("number",search);
		ArrayList<Customer> customer = (ArrayList<Customer>) jdbc.query(query, pr,
				new BeanPropertyRowMapper<Customer>(Customer.class));
		if (customer.size() > 0) {
			return customer.get(0);
		} else {
			return null;
		}
	}

	public Object searchCustbyAddress(String search) {
		MapSqlParameterSource pr = new MapSqlParameterSource();
		String query="Select * from customer where address=:address";
		pr.addValue("address", search);
		ArrayList<Customer> customer = (ArrayList<Customer>) jdbc.query(query, pr,
				new BeanPropertyRowMapper<Customer>(Customer.class));
		if (customer.size() > 0) {
			return customer.get(0);
		} else {
			return null;
		}

	}

	public Object searchCustbyEmail(String search) {
		MapSqlParameterSource pr = new MapSqlParameterSource();
		String query="Select * from customer where email=:email";
		pr.addValue("email", search);
		ArrayList<Customer> customer = (ArrayList<Customer>) jdbc.query(query, pr,
				new BeanPropertyRowMapper<Customer>(Customer.class));
		if (customer.size() > 0) {
			return customer.get(0);
		} else {
			return null;
		}

	}

	public Customer findUserAccount(String username) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT * FROM sec_user WHERE userName=:userName";
		parameters.addValue("userName", username);
		ArrayList<Customer> users = (ArrayList<Customer>)jdbc.query(query, parameters, new BeanPropertyRowMapper<Customer>(Customer.class));
		if(users.size()>0)
			return users.get(0);
		else
			return null;
	}
	
	public List<String> getRoleById(long userId) {
		ArrayList<String> roles = new ArrayList<String>();
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "SELECT user_role.userId, sec_role.roleName FROM user_role, sec_role "
				+ "WHERE user_role.roleId=sec_role.roleId AND userId=:userId";
		parameters.addValue("userId", userId);
		List<Map<String, Object>> rows = jdbc.queryForList(query, parameters);
		for (Map<String, Object> row : rows) {
			roles.add((String)row.get("roleName"));
		}
		return roles;
	}
	

	
	public void addUser(String username, String password) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "INSERT INTO SEC_User (userName, encryptedPassword, ENABLED)" + 
				"VALUES (:userName, :password, 1)";
		parameters.addValue("userName", username);
		parameters.addValue("password", password);
		jdbc.update(query, parameters);
	}
	
	public void addRole(long userId, long roleId) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		String query = "INSERT INTO user_role (userId, roleId)" + 
				"VALUES (:userId, :roleId)";
		parameters.addValue("userId", userId);
		parameters.addValue("roleId", roleId);
		jdbc.update(query, parameters);
	}
		public void generateDummyRecordCustomer() {
		Information info = new Information();
		String q = "insert into customer(name,number,address,email,repairInfo) values( :name, :number, :address, :email, :repairInfo)";
		MapSqlParameterSource pr = new MapSqlParameterSource();
		ArrayList<String> name = info.name();
		ArrayList<String> number = info.number();
		ArrayList<String> address  = info.address();
		ArrayList<String> email = info.email();
		ArrayList<String> repairInfo = info.repairInfo();
		for(int sub =0; sub < 5; sub++) {
			pr.addValue("name", name.get(sub));
			pr.addValue("number", number.get(sub));
			pr.addValue("address", address.get(sub));
			pr.addValue("email", email.get(sub));
			pr.addValue("repairInfo", repairInfo.get(sub));
			jdbc.update(q, pr);
		}
		
	}

		public ArrayList<Customer> getCustomerRates() {
			String query="Select * from customerrepairinfo";
			ArrayList<Customer> customer = (ArrayList<Customer>) jdbc.query(query,
					new BeanPropertyRowMapper<Customer>(Customer.class));
			return customer;
			
			
		}
}
