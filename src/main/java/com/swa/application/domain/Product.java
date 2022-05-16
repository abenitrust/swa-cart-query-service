package com.swa.application.domain;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {
    private String productNumber;
    private int quantity;
    private double price;
}
