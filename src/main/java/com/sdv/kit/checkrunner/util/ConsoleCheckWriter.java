package com.sdv.kit.checkrunner.util;

import com.sdv.kit.checkrunner.mapper.CheckMapper;
import com.sdv.kit.checkrunner.model.Check;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ConsoleCheckWriter implements CheckWriter{

    private final CheckMapper checkMapper;

    @Override
    public void write(Check check) {
        System.out.println(checkMapper.fromCheckToString(check));
    }
}
