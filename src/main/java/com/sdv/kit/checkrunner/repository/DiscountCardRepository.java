package com.sdv.kit.checkrunner.repository;

import com.sdv.kit.checkrunner.model.DiscountCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DiscountCardRepository extends JpaRepository<DiscountCard, Long> {
    Optional<DiscountCard> findByNumber(Long number);
}