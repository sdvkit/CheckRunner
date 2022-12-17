package com.sdv.kit.checkrunner.service.impl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.sdv.kit.checkrunner.model.DiscountCard;
import com.sdv.kit.checkrunner.repository.DiscountCardRepository;

import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@SpringBootTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class DiscountCardServiceImplTests {

    @MockBean
    private DiscountCardRepository discountCardRepository;

    private final DiscountCardServiceImpl discountCardServiceImpl;

    @Test
    void testFindByNumber() {
        when(discountCardRepository.findByNumber(any())).thenThrow(new ResponseStatusException(HttpStatus.CONTINUE));
        assertThrows(ResponseStatusException.class, () -> discountCardServiceImpl.findByNumber(1L));
        verify(discountCardRepository).findByNumber(any());
    }

    @Test
    void testFindById() {
        DiscountCard discountCard = new DiscountCard(123L, 1L, 1);
        Optional<DiscountCard> ofResult = Optional.of(discountCard);
        when(discountCardRepository.findById(any())).thenReturn(ofResult);
        Optional<DiscountCard> actualFindByIdResult = discountCardServiceImpl.findById(123L);
        assertSame(ofResult, actualFindByIdResult);
        assertTrue(actualFindByIdResult.isPresent());
        verify(discountCardRepository).findById(any());
    }

    @Test
    void testFindAll() {
        when(discountCardRepository.findAll()).thenThrow(new ResponseStatusException(HttpStatus.CONTINUE));
        assertThrows(ResponseStatusException.class, discountCardServiceImpl::findAll);
        verify(discountCardRepository).findAll();
    }

    @Test
    void testSave() {
        when(discountCardRepository.save(any())).thenThrow(new ResponseStatusException(HttpStatus.CONTINUE));
        DiscountCard discountCard = new DiscountCard(123L, 1L, 1);
        assertThrows(ResponseStatusException.class, () -> discountCardServiceImpl.save(discountCard));
        verify(discountCardRepository).save(any());
    }

    @Test
    void testDeleteById() {
        doNothing().when(discountCardRepository).deleteById(any());
        discountCardServiceImpl.deleteById(123L);
        verify(discountCardRepository).deleteById(any());
    }

    @Test
    void testUpdate() {
        DiscountCard discountCard = new DiscountCard(123L, 1L, 1);
        when(discountCardRepository.findById(any())).thenReturn(Optional.of(discountCard));
        assertDoesNotThrow(() -> discountCardServiceImpl.update(123L, discountCard));
        verify(discountCardRepository).findById(any());
    }
}

