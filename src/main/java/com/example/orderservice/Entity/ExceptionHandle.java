package com.example.orderservice.Entity;

import lombok.*;

/**
 * @author MPHuy on 24/11/2021
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExceptionHandle {
    private String status;
    private String err;
}
