package com.sdv.kit.checkrunner.service;

import com.sdv.kit.checkrunner.model.DiscountCard;

import java.util.List;
import java.util.Optional;

public interface DiscountCardService {
    Optional<DiscountCard> findByNumber(Long number);
    List<DiscountCard> findAll();
    DiscountCard save(DiscountCard discountCard);
    void deleteById(Long id);
    DiscountCard update(Long id, DiscountCard discountCard);
    Optional<DiscountCard> findById(Long id);
}
