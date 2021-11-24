package com.example.orderservice.Controller;

import com.example.orderservice.Entity.Orders;
import com.example.orderservice.Service.OrderService;
import com.example.orderservice.VO.ResponseTemplateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author MPHuy on 23/11/2021
 */
@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @GetMapping("/shipping/{id}")
    public ResponseTemplateVO getOrderWithShipping(@PathVariable long id){
                return orderService.getOrderWithShipping(id);
    }
    @GetMapping("/all/")
    public List<Orders> getAllOrder(){
        return orderService.getAll();
    }
    @GetMapping("/{id}")
    public Orders getById(@PathVariable long id){
        return orderService.getById(id);
    }
    @PostMapping("/")
    public Orders getById(@RequestBody Orders orders){
        return orderService.save(orders);
    }
}
