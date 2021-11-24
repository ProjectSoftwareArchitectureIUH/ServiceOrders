package com.example.orderservice.Service;

import com.example.orderservice.Entity.Orders;
import com.example.orderservice.Repository.OrderRepository;
import com.example.orderservice.VO.ResponseTemplateVO;
import com.example.orderservice.VO.Shipping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author MPHuy on 23/11/2021
 */
@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private RestTemplate restTemplate;
    public Orders save(Orders orders){
        return orderRepository.save(orders);
    }
    public Orders getById(long id){
        return orderRepository.findById(id).get();
    }
    public List<Orders> getAll(){
        return orderRepository.findAll();
    }
    public ResponseTemplateVO getOrderWithShipping(long id){
        ResponseTemplateVO responseTemplateVO = new ResponseTemplateVO();
        Orders orders = orderRepository.findById(id).get();
        Shipping shipping =restTemplate.getForObject("http://localhost:9001/shipping/"+orders.getShippingId(),Shipping.class);
        responseTemplateVO.setOrders(orders);
        responseTemplateVO.setShipping(shipping);
        return responseTemplateVO;
    }
}
