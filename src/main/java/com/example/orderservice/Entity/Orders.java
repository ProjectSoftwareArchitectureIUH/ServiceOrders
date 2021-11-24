package com.example.orderservice.Entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

/**
 * @author MPHuy on 23/11/2021
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "orders_table")
public class Orders {
    @Id
    @GeneratedValue
    private long id;
    private String productName;
    private Date orderDate;
    private String address;
    private String phoneNumber;
    private long shippingId;
}
