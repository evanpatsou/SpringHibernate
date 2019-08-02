package SpringMVC_HB_CRUD.springdemo.dao;

import SpringMVC_HB_CRUD.springdemo.entity.Customer;

import java.util.List;

public interface CustomerDAO {

	public List<Customer> getCustomers();

	public void saveCustomer(Customer theCustomer);

	public Customer getCustomer(int theId);

	public void deleteCustomer(int theId);

}