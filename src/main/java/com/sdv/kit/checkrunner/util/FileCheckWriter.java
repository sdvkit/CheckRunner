package com.sdv.kit.checkrunner.util;

import com.sdv.kit.checkrunner.mapper.CheckMapper;
import com.sdv.kit.checkrunner.model.Check;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Класс, предназначенный для вывода чека в файл
 */
@RequiredArgsConstructor
@Component
public class FileCheckWriter implements CheckWriter{

    private final ConsoleCheckWriter consoleCheckWriter;
    private final CheckMapper checkMapper;

    @Override
    public void write(Check check) {
        try(FileWriter fileWriter = new FileWriter("check.txt")) {
            consoleCheckWriter.write(check);
            fileWriter.write(checkMapper.fromCheckToString(check));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
