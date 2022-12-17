package com.sdv.kit.checkrunner.service.impl;

import com.sdv.kit.checkrunner.model.Check;
import com.sdv.kit.checkrunner.model.DiscountCard;
import com.sdv.kit.checkrunner.model.Product;
import com.sdv.kit.checkrunner.repository.DiscountCardRepository;
import com.sdv.kit.checkrunner.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@SpringBootTest
public class CheckServiceImplTests {

    @MockBean
    private ProductRepository productRepository;
    @MockBean
    private DiscountCardRepository discountCardRepository;
    private final CheckServiceImpl checkService;

    @BeforeEach
     void setResources() {
        Product product = new Product(1L, "Name", 10f, true);
        DiscountCard discountCard = new DiscountCard(1L, 1234L, 10);

        when(productRepository.findById(any())).thenReturn(Optional.of(product));
        when(discountCardRepository.findByNumber(any())).thenReturn(Optional.of(discountCard));
    }

    @Test
    void buildWithArgsTest() {
        String[] args = {"1-2" , "card-1234"};
        Check check = checkService.build(args);

        assertEquals(1, check.getProducts().size());
        assertEquals(10, check.getDiscountCard().getPercent());
    }

    @Test
    void buildWithRestTest() {
        List<String> items = List.of("1-2");
        Long discountCardNUmber = 1234L;
        Check check = checkService.build(items, discountCardNUmber);

        assertEquals(1, check.getProducts().size());
        assertEquals(10, check.getDiscountCard().getPercent());
    }
}
