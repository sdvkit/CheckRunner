package com.sdv.kit.checkrunner.controller;

import com.sdv.kit.checkrunner.mapper.CheckMapper;
import com.sdv.kit.checkrunner.model.Check;
import com.sdv.kit.checkrunner.service.impl.CheckServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/")
public class CheckRestController {

    private final CheckServiceImpl checkService;
    private final CheckMapper checkMapper;

    @GetMapping(value = "/check/text", produces = MediaType.TEXT_MARKDOWN_VALUE)
    public String getCheck(@RequestParam List<String> items,
                           @RequestParam(value = "card", required = false) Long discountCardNumber) {
        return checkMapper.fromCheckToString(checkService
                .build(items, discountCardNumber));
    }

    @GetMapping(value = "/check/json")
    public ResponseEntity<Check> getJsonCheck(@RequestParam List<String> items,
                                              @RequestParam(value = "card", required = false) Long discountCardNumber) {
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(checkService.build(items, discountCardNumber));
    }
}
