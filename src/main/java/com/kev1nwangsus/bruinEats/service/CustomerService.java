package com.kev1nwangsus.bruinEats.service;

import com.kev1nwangsus.bruinEats.dao.CustomerDao;
import com.kev1nwangsus.bruinEats.entity.Customer;
import com.kev1nwangsus.bruinEats.entity.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

  private CustomerDao customerDao;

  @Autowired
  public CustomerService(CustomerDao customerDao) {
    this.customerDao = customerDao;
  }

  public void signUp(Customer customer) {
    Cart cart = new Cart();
    customer.setCart(cart);
    customer.setEnabled(true);
    customerDao.signUp(customer);
  }

  public Customer getCustomer(String email) {
    return customerDao.getCustomer(email);
  }
}

