package com.example.orderservice.VO;

import com.example.orderservice.Entity.Orders;
import lombok.*;

/**
 * @author MPHuy on 24/11/2021
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponseTemplateVO {
    private Orders orders;
    private Shipping shipping;
}
