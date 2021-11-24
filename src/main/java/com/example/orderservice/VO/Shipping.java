package com.example.orderservice.VO;

import lombok.*;

/**
 * @author MPHuy on 24/11/2021
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Shipping {
    private long id;
    private String shippingCompany;
    private String region;
    private String deliveryCost;
}
