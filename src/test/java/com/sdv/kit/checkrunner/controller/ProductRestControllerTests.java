package com.sdv.kit.checkrunner.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sdv.kit.checkrunner.model.Product;
import com.sdv.kit.checkrunner.service.ProductService;
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
@WebMvcTest(ProductRestController.class)
class ProductRestControllerTests {

    private final MockMvc mockMvc;
    @MockBean
    private ProductService productService;
    private final ObjectMapper objectMapper;
    private final List<Product> productList = List.of(
            new Product(1L, "Product 1", 1f, true),
            new Product(2L, "Product 2", 2f, false),
            new Product(3L, "Product 3", 3f, true));

    @SneakyThrows
    @Test
    void getAllProductsTest() {
        when(productService.findAll()).thenReturn(productList);
        mockMvc.perform(get("/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andDo(print());
    }

    @SneakyThrows
    @Test
    void getProductTest() {
        when(productService.findById(any())).thenReturn(Optional.ofNullable(productList.get(1)));
        mockMvc.perform(get("/api/v1/products/1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Product 2")))
                .andDo(print());
    }

    @SneakyThrows
    @Test
    void saveProductTest() {
        when(productService.save(any())).thenReturn(productList.get(1));
        String jsonProduct = objectMapper.writeValueAsString(productList.get(1));
        mockMvc.perform(post("/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                        .content(jsonProduct))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Product 2")))
                .andDo(print());
    }

    @SneakyThrows
    @Test
    void updateProductTest() {
        when(productService.update(any(), any())).thenReturn(productList.get(1));
        String jsonProduct = objectMapper.writeValueAsString(productList.get(1));
        mockMvc.perform(put("/api/v1/products/2")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                        .content(jsonProduct))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Product 2")))
                .andDo(print());
    }

    @SneakyThrows
    @Test
    void deleteProductTest() {
        doNothing().when(productService).deleteById(any());
        mockMvc.perform(delete("/api/v1/products/2")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andDo(print());
    }
}
