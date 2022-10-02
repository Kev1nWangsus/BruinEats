package com.kev1nwangsus.bruinEats.controller;

import com.kev1nwangsus.bruinEats.entity.MenuItem;
import com.kev1nwangsus.bruinEats.entity.Restaurant;
import com.kev1nwangsus.bruinEats.service.MenuInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MenuInfoController {

  @Autowired
  private MenuInfoService menuInfoService;

  @RequestMapping(value = "/restaurants", method = RequestMethod.GET)
  @ResponseBody
  public List<Restaurant> getRestaurants() {
    return menuInfoService.getRestaurants();
  }

  @RequestMapping(value = "/restaurant/{restaurantId}/menu", method = RequestMethod.GET)
  @ResponseBody
  public List<MenuItem> getMenus(@PathVariable("restaurantId") int restaurantId) {
    return menuInfoService.getAllMenuItem(restaurantId);
  }
}
