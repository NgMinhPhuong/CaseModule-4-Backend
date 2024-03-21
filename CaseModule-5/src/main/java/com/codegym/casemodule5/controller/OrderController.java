package com.codegym.casemodule5.controller;

import com.codegym.casemodule5.dto.order.OrderDtoRepsonse;
import com.codegym.casemodule5.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/orders")
public class OrderController {
    @Autowired
    private IOrderService iOrderService;
    @GetMapping()
    public ResponseEntity<List<OrderDtoRepsonse>> getAll(){
        return new ResponseEntity<>(iOrderService.findAll() ,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDtoRepsonse> get(@PathVariable Long id){
        OrderDtoRepsonse orderDto = iOrderService.findById(id);
        if(orderDto == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orderDto, HttpStatus.OK);
    }


}
