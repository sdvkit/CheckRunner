package com.sdv.kit.checkrunner.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Check {

    private LocalDateTime dateTime;
    @Builder.Default
    private Map<Product, Integer> products = new HashMap<>();
    private DiscountCard discountCard;
}
