package com.example.orderservice.Controller;

import com.example.orderservice.Entity.Orders;
import com.example.orderservice.Service.OrderService;
import com.example.orderservice.VO.ResponseTemplateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author MPHuy on 23/11/2021
 */
@RestController
@RequestMapping("/orders")
@CrossOrigin
public class OrderController {
    @Autowired
    private OrderService orderService;
    @GetMapping("/shipping/{id}")
    public ResponseEntity<ResponseTemplateVO> getOrderWithShipping(@PathVariable long id){
                return orderService.getOrderWithShipping(id);
    }
    @GetMapping("/all/")
    public ResponseEntity<List<Orders>>  getAllOrder(){
        return orderService.getAll();
    }
    @GetMapping("/{id}")
    public Orders getById(@PathVariable long id){
        return orderService.getById(id);
    }
    @PostMapping("/")
    public Orders save(@RequestBody Orders orders){
        return orderService.save(orders);
    }

    @DeleteMapping("/{id}")
    void deleteOrderById(@PathVariable Long id) {
        orderService.deleteById(id);
    }

    @PutMapping("/{id}")
    public Orders updateOrdersById(@PathVariable("id") Long id,@RequestBody Orders orders){

        return orderService.updateOrderById(id, orders);
    }
    @Value("${message}")
    private String name;

    @GetMapping("/name")
    public String getMyName(){
        return name;
    }

}
