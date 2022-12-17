package com.sdv.kit.checkrunner.controller;

import com.sdv.kit.checkrunner.model.DiscountCard;
import com.sdv.kit.checkrunner.service.DiscountCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class DiscountCardRestController {

    private final DiscountCardService discountCardService;

    @GetMapping(value = "/cards")
    public ResponseEntity<List<DiscountCard>> getAllCards() {
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(discountCardService.findAll());
    }

    @GetMapping("/cards/{id}")
    public ResponseEntity<DiscountCard> getCard(@PathVariable Long id) {
        return discountCardService.findById(id)
                .map(discountCard -> ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .body(discountCard)
                ).orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT));
    }

    @PostMapping(value = "/cards", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DiscountCard> saveCard(@RequestBody DiscountCard discountCard) {
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(discountCardService.save(discountCard));
    }

    @PutMapping(value = "/cards/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DiscountCard> updateCard(@PathVariable Long id,
                                                 @RequestBody DiscountCard discountCard) {
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(discountCardService.update(id, discountCard));
    }

    @DeleteMapping("/cards/{id}")
    public ResponseEntity<HttpStatus> deleteCard(@PathVariable Long id) {
        discountCardService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
