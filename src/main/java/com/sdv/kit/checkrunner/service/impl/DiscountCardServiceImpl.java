package com.sdv.kit.checkrunner.service.impl;

import com.sdv.kit.checkrunner.model.DiscountCard;
import com.sdv.kit.checkrunner.repository.DiscountCardRepository;
import com.sdv.kit.checkrunner.service.DiscountCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class DiscountCardServiceImpl implements DiscountCardService {

    private final DiscountCardRepository discountCardRepository;

    @Override
    public Optional<DiscountCard> findByNumber(Long number) {
        return discountCardRepository.findByNumber(number);
    }

    @Override
    public Optional<DiscountCard> findById(Long id) {
        return discountCardRepository.findById(id);
    }

    @Override
    public List<DiscountCard> findAll() {
        return discountCardRepository.findAll();
    }

    @Transactional
    @Override
    public DiscountCard save(DiscountCard discountCard) {
        return discountCardRepository.save(discountCard);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        discountCardRepository.deleteById(id);
    }

    @Transactional
    @Override
    public DiscountCard update(Long id, DiscountCard discountCardParam) {
        return discountCardRepository.findById(id)
                .map(discountCard -> {
                    discountCard.setNumber(discountCardParam.getNumber());
                    discountCard.setPercent(discountCardParam.getPercent());
                    return discountCard;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
