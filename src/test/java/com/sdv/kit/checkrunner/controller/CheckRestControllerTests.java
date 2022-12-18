package com.sdv.kit.checkrunner.controller;

import com.sdv.kit.checkrunner.mapper.CheckMapper;
import com.sdv.kit.checkrunner.model.Check;
import com.sdv.kit.checkrunner.service.impl.CheckServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@WebMvcTest(CheckRestController.class)
class CheckRestControllerTests {

    private final MockMvc mockMvc;
    @MockBean
    private CheckServiceImpl checkService;
    @MockBean
    private CheckMapper checkMapper;

    @SneakyThrows
    @Test
    void getCheckTest() {
        Check check = new Check(LocalDateTime.now(), Collections.emptyMap(), null, 0f, 0f, 0f);
        when(checkService.build(any(), any())).thenReturn(check);
        mockMvc.perform(get("/api/v1/check/text?items=1-2")
                        .contentType(MediaType.TEXT_MARKDOWN_VALUE))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @SneakyThrows
    @Test
    void getJsonCheckTest() {
        Check check = new Check(LocalDateTime.now(), Collections.emptyMap(), null, 0f, 0f, 0f);
        when(checkService.build(any(), any())).thenReturn(check);
        mockMvc.perform(get("/api/v1/check/json?items=1-2")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
