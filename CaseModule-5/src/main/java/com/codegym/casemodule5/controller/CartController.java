package com.codegym.casemodule5.controller;

import com.codegym.casemodule5.model.Order;
import com.codegym.casemodule5.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SessionAttributes("orders")
@RestController
@RequestMapping("api/carts")
public class CartController {
    @Autowired
    private IOrderService iOrderService;
    @ModelAttribute("orders")
    public List <Order> init(){
        return new ArrayList<>();
    }
    @GetMapping
    public ResponseEntity<List<Order>> getAll(@ModelAttribute("orders") List<Order> orders){
        if (orders.size() == 0){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(orders,HttpStatus.OK);
    }
    @PostMapping ("/add")
    public ResponseEntity<Order> add(@ModelAttribute("orders") List<Order> orders,@RequestBody Order order){
        order.setCategories(iOrderService.getCategory(order.getCategories()));
        order.setId(UUID.randomUUID().toString());
        orders.add(order);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
