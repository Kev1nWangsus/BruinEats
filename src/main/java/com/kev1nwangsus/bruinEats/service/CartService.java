package com.kev1nwangsus.bruinEats.service;

import com.kev1nwangsus.bruinEats.dao.CartDao;
import com.kev1nwangsus.bruinEats.entity.Cart;
import com.kev1nwangsus.bruinEats.entity.Customer;
import com.kev1nwangsus.bruinEats.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CartService {

  @Autowired
  private CustomerService customerService;

  @Autowired
  private CartDao cartDao;

  public Cart getCart() {
    Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
    String username = loggedInUser.getName();
    Customer customer = customerService.getCustomer(username);

    if (customer != null) {
      Cart cart = customer.getCart();
      double totalPrice = 0;
      for (OrderItem item : cart.getOrderItemList()) {
        totalPrice += item.getPrice() * item.getQuantity();
      }
      cart.setTotalPrice(totalPrice);
      return cart;
    }
    return new Cart();
  }

  public void cleanCart() {
    Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
    String username = loggedInUser.getName();
    Customer customer = customerService.getCustomer(username);
    if (customer != null) cartDao.removeAllCartItems(getCart());
  }
}

