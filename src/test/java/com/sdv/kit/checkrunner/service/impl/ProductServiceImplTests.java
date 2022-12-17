package com.sdv.kit.checkrunner.service.impl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.sdv.kit.checkrunner.model.Product;
import com.sdv.kit.checkrunner.repository.ProductRepository;

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
class ProductServiceImplTests {

    @MockBean
    private ProductRepository productRepository;

    private final ProductServiceImpl productServiceImpl;

    @Test
    void testFindById() {
        when(productRepository.findById(any())).thenThrow(new ResponseStatusException(HttpStatus.CONTINUE));
        assertThrows(ResponseStatusException.class, () -> productServiceImpl.findById(123L));
        verify(productRepository).findById(any());
    }

    @Test
    void testFindAll() {
        when(productRepository.findAll()).thenThrow(new ResponseStatusException(HttpStatus.CONTINUE));
        assertThrows(ResponseStatusException.class, productServiceImpl::findAll);
        verify(productRepository).findAll();
    }

    @Test
    void testSave() {
        when(productRepository.save(any())).thenThrow(new ResponseStatusException(HttpStatus.CONTINUE));
        Product product = new Product(123L, "Name", 10f, true);
        assertThrows(ResponseStatusException.class, () -> productServiceImpl.save(product));
        verify(productRepository).save(any());
    }

    @Test
    void testDeleteById() {
        doNothing().when(productRepository).deleteById(any());
        productServiceImpl.deleteById(123L);
        verify(productRepository).deleteById(any());
    }

    @Test
    void testUpdate() {
        Product product = new Product(123L, "Name", 10f, true);
        when(productRepository.findById(any())).thenReturn(Optional.of(product));
        assertDoesNotThrow(() -> productServiceImpl.update(123L, product));
        verify(productRepository).findById(any());
    }
}

