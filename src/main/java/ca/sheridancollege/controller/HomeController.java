package ca.sheridancollege.controller;

import java.util.ArrayList;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.Email.EmailServiceImpl;
import ca.sheridancollege.bean.Customer;
import ca.sheridancollege.databases.DatabaseAccess;

@Controller
public class HomeController {
	
	@Autowired
	private DatabaseAccess da;
	@Autowired
	EmailServiceImpl esi;

	//return index.html the very first page of the assignment.
	@GetMapping("/")
	public String returnIndex() {
		return "landPage.html";
	}
	@GetMapping("/index")
	public String returnLogin()
	{
		return "index.html";
	}
	//sending the object to the add page to perform the form binding
	@GetMapping("/add")
	public String home(Model model)
	{
		model.addAttribute("customer", new Customer());
		return "AddCustomer.html";
	}
	@GetMapping("/login")
	public String loginPage()
	{
		return "login.html";
	}
	
	//sending the object of the student and reloading it with a new object that can be utilized if necessary
	@GetMapping("/addCustomer")
	public String goAdd(Model model, @ModelAttribute Customer customer)
	{
		da.addCustomerInTable(customer);
		model.addAttribute("customer", new Customer());
		return "AddCustomer.html";
	}
	
	//view all the records
		@GetMapping("/view")
		public String getRecords(Model model){
		ArrayList<Customer>  a= da.getCustomerRecords();
		model.addAttribute("customer", a);
		return "viewCustomer.html";
		}
		@GetMapping("/viewRates")
		public String getRates(Model model){
		ArrayList<Customer> c= da.getCustomerRates();
		model.addAttribute("customer2",c);

		return "viewCustomer.html";
		}
		
	//get edit Customer Records
		@GetMapping("/editLinkCustomer/{id}")
		public String editRecordsCustomer(@PathVariable int id, Model model){
			model.addAttribute("customer", da.editCustomerRecord(id));
			return "editCustomer.html";
		}
		
	//edit record Customer
		@GetMapping("/editCustomer")
		public String modifyRecord(@RequestParam int customerId, @RequestParam String name, @RequestParam String number, @RequestParam String email, @RequestParam String address, @RequestParam String repairInfo){
			Customer c = new Customer();
			c.setCustomerId(customerId);
			c.setName(name);
			c.setNumber(number);
			c.setAddress(address);
			c.setEmail(email);
			c.setRepairInfo(repairInfo);
			da.editCustRecord(c);
			return "redirect:/view";
		}
		
	//delete record for Customer
		@GetMapping("/deleteLinkCustomer/{id}")
		public String deleteRecordCustomer(@PathVariable int id){
			da.deleteCustomerRecord(id);
			return "redirect:/view";
		}
		
	//add admin records
		@GetMapping("/addInfoLink/{id}")
			public String addToAdmin(@PathVariable int id, Model model){
				model.addAttribute("customer", da.addRecordsAdmin(id));
				return "AddAdmin.html";
			}
		
		
		
		
	//adding record to the admin
		@GetMapping("/addAdmin")
		public String inserToAdmin(@RequestParam int id,@RequestParam String name, @RequestParam String email, @RequestParam int laborCharges, @RequestParam int hardwareCharges,
				@RequestParam int shippingCharges, @RequestParam String timeRequired) {
			Customer c = new Customer();
			System.out.println(id);
			c.setCustomerId(id);
			c.setLaborCharges(laborCharges);
			c.setHardwareCharges(hardwareCharges);
			c.setShippingCharges(shippingCharges);
			c.setTimeRequired(timeRequired);
			da.addIntoAdmin(c);
			try {
				esi.sendMailWithInline(name, 
						laborCharges, 
						hardwareCharges,
						shippingCharges,
						email,
						"Charges");
			} catch (MessagingException e) {
				
				System.out.println(e);
			}
			return "redirect:/view";
			
			
			
		}
		@GetMapping("/search")
		public String searchMain(Model model)
		{
			model.addAttribute("customer",new Customer());
			return "Search.html";
		}
		@GetMapping("/searchByCriteria")
		public String searchResult(Model model, @RequestParam String searchOption, @RequestParam  String search)
		{
			if(searchOption.contentEquals("id"))
		{
				model.addAttribute("customer",da.searchCustbyId(Integer.parseInt(search)));
				return "Search.html";
		}
			else if(searchOption.contentEquals("name"))
			{
				model.addAttribute("customer",da.searchCustbyName(search));
				return "Search.html";

		}
			else if(searchOption.contentEquals("number"))
			{
				model.addAttribute("customer",da.searchCustbyNumber(search));

				return "Search.html";

			}
			else if(searchOption.contentEquals("address"))
			{
				model.addAttribute("customer",da.searchCustbyAddress(search));
				return "Search.html";
			}
			else if(searchOption.contentEquals("email"))
			{
				model.addAttribute("customer",da.searchCustbyEmail(search));
				return "Search.html";
			}
		return "redirect:/seach";
		}
		@GetMapping("/deleteLinkCustomerSearch/{id}")
		public String deleteRecordCustomerSearch(@PathVariable int id){
			da.deleteCustomerRecord(id);
			return "redirect:/search";
		}
		
		@GetMapping("/test2")
		public String test2( String email) {

			return "home.html";
		}

		@GetMapping("/registrationPage")
		public String registrationPage() {
			return "registrationPage.html";
		}
		
		@GetMapping("/register")
		public String registerUser(@RequestParam String name, @RequestParam String password) {
		    da.addUser(name, password);
			long userId = da.findUserAccount(name).getId();
			da.addRole(userId, 1);
			da.addRole(userId, 2);
			return "redirect:/";
		}

	@GetMapping("/access-denied")
		public String accessDenied() {
			return "/error/access-denied.html";
		}
	@GetMapping("/dummyRecords")
	public String dummy()
	{
		da.generateDummyRecordCustomer();
		return "redirect:/index";
		
	}
}
	
	
	
	
	

