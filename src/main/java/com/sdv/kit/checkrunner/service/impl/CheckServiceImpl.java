package com.sdv.kit.checkrunner.service.impl;

import com.sdv.kit.checkrunner.exception.DiscountCardNotFoundException;
import com.sdv.kit.checkrunner.exception.ProductNotFoundException;
import com.sdv.kit.checkrunner.model.Check;
import com.sdv.kit.checkrunner.model.DiscountCard;
import com.sdv.kit.checkrunner.model.Product;
import com.sdv.kit.checkrunner.service.CheckService;
import com.sdv.kit.checkrunner.service.DiscountCardService;
import com.sdv.kit.checkrunner.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Сервис, предназначенный для создания экземпляра чека на основе получаемых данных.
 */
@RequiredArgsConstructor
@Service
public class CheckServiceImpl implements CheckService {

    private final DiscountCardService discountCardService;
    private final ProductService productService;

    /**
     * Метод предназанчен для создания чека на основе аргументов, переданных при запуске программы
     */
    @Override
    public Check build(String[] args) {
        Map<Product, Integer> products = new HashMap<>();
        DiscountCard discountCard = null;

        for (String arg : args) {
            String[] separatedArg = arg.split("-");
            if (arg.contains("card")) {
                discountCard = discountCardService.findByNumber(Long.parseLong(separatedArg[1]))
                        .orElseThrow(() -> new DiscountCardNotFoundException("Discount card with this number not found"));
            } else {
                Product product = productService.findById(Long.parseLong(separatedArg[0]))
                        .orElseThrow(() -> new ProductNotFoundException("Product with this ID not found"));
                products.put(product, Integer.parseInt(separatedArg[1]));
            }
        }

        return Check.builder()
                .dateTime(LocalDateTime.now())
                .discountCard(discountCard)
                .products(products)
                .build();
    }

    /**
     * Метод предназначен для создания чека на основе параметров, получаемых из REST-котроллера
     */
    @Override
    public Check build(List<String> items, Long discountCardNumber) {
        Map<Product, Integer> products = new HashMap<>();
        DiscountCard discountCard = (discountCardNumber != null)
                ? discountCardService.findByNumber(discountCardNumber)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
                : null;

        items.forEach(item -> {
            String[] separatedArg = item.split("-");
            Product product = productService.findById(Long.parseLong(separatedArg[0]))
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
            products.put(product, Integer.parseInt(separatedArg[1]));
        });

        return Check.builder()
                .dateTime(LocalDateTime.now())
                .discountCard(discountCard)
                .products(products)
                .build();
    }
}
