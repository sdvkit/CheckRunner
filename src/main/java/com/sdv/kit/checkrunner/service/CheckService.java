package com.sdv.kit.checkrunner.service;

import com.sdv.kit.checkrunner.model.Check;

import java.util.List;

public interface CheckService {
    Check build(String[] args);
    Check build(List<String> items, Long discountCardNumber);
    Check getPricedCheck(Check check);
}
