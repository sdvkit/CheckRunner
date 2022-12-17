package com.sdv.kit.checkrunner.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sdv.kit.checkrunner.model.DiscountCard;
import com.sdv.kit.checkrunner.service.DiscountCardService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@WebMvcTest(DiscountCardRestController.class)
class DiscountCardRestControllerTests {

    private final MockMvc mockMvc;
    @MockBean
    private DiscountCardService discountCardService;
    private final ObjectMapper objectMapper;
    private final List<DiscountCard> cardList = List.of(
            new DiscountCard(1L, 1234L, 10),
            new DiscountCard(2L, 5678L, 20),
            new DiscountCard(3L, 9009L, 30));

    @SneakyThrows
    @Test
    void getAllCardsTest() {
        when(discountCardService.findAll()).thenReturn(cardList);
        mockMvc.perform(get("/api/v1/cards")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andDo(print());
    }

    @SneakyThrows
    @Test
    void getCardTest() {
        when(discountCardService.findById(any())).thenReturn(Optional.ofNullable(cardList.get(1)));
        mockMvc.perform(get("/api/v1/cards/1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number", is(5678)))
                .andDo(print());
    }

    @SneakyThrows
    @Test
    void saveCardTest() {
        when(discountCardService.save(any())).thenReturn(cardList.get(1));
        String jsonProduct = objectMapper.writeValueAsString(cardList.get(1));
        mockMvc.perform(post("/api/v1/cards")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                        .content(jsonProduct))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number", is(5678)))
                .andDo(print());
    }

    @SneakyThrows
    @Test
    void updateCardTest() {
        when(discountCardService.update(any(), any())).thenReturn(cardList.get(1));
        String jsonProduct = objectMapper.writeValueAsString(cardList.get(1));
        mockMvc.perform(put("/api/v1/cards/2")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                        .content(jsonProduct))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number", is(5678)))
                .andDo(print());
    }

    @SneakyThrows
    @Test
    void deleteProductTest() {
        doNothing().when(discountCardService).deleteById(any());
        mockMvc.perform(delete("/api/v1/cards/2")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andDo(print());
    }
}
