package com.example.orderservice.Service;

import com.example.orderservice.Entity.ExceptionHandle;
import com.example.orderservice.Entity.Orders;
import com.example.orderservice.Repository.OrderRepository;
import com.example.orderservice.VO.ResponseTemplateVO;
import com.example.orderservice.VO.Shipping;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author MPHuy on 23/11/2021
 */
@Service
public class OrderService {
    private int flag = 0;
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
    @RateLimiter(name = "basicExample",fallbackMethod = "fallBackRatelimiter")
    @Retry(name = "basic",fallbackMethod = "fallBackRetry")
    public ResponseEntity<ResponseTemplateVO> getOrderWithShipping(long id){
        ResponseTemplateVO responseTemplateVO = new ResponseTemplateVO();
        Orders orders = orderRepository.findById(id).get();
        Shipping shipping =restTemplate.getForObject("http://localhost:9001/shipping/"+orders.getShippingId(),Shipping.class);
        responseTemplateVO.setOrders(orders);
        responseTemplateVO.setShipping(shipping);
        return new ResponseEntity<ResponseTemplateVO>(responseTemplateVO, HttpStatus.OK);
    }
    public ResponseEntity<ExceptionHandle> fallBackRetry(RuntimeException e){
        flag = 0;
        System.out.println("Fall Back Retry: "+e.getMessage());
        ResponseTemplateVO vo = new ResponseTemplateVO();
        ExceptionHandle exceptionCustom = new ExceptionHandle(
                "Fall Back Retry Service is Down",e.getMessage());
        return new ResponseEntity<ExceptionHandle>(exceptionCustom, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<ExceptionHandle> fallBackRatelimiter(RuntimeException e){
        flag = 0;
        System.out.println("Fall Back Ratelimiter: "+e.getMessage());
        ResponseTemplateVO vo = new ResponseTemplateVO();
        ExceptionHandle exceptionCustom = new ExceptionHandle(
                "Fall Back Ratelimiter Service is Down",e.getMessage());
        return new ResponseEntity<ExceptionHandle>(exceptionCustom, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
