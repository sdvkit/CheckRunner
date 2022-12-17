package com.sdv.kit.checkrunner.util;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.sdv.kit.checkrunner.mapper.CheckMapper;
import com.sdv.kit.checkrunner.model.Check;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@SpringBootTest
class FileCheckWriterTest {
    @MockBean
    private CheckMapper checkMapper;
    @MockBean
    private ConsoleCheckWriter consoleCheckWriter;
    private final FileCheckWriter fileCheckWriter;

    @Test
    void testWrite() {
        doNothing().when(consoleCheckWriter).write(any());
        when(checkMapper.fromCheckToString(any())).thenReturn("==Check #1==");
        Check check = new Check();
        fileCheckWriter.write(check);
        verify(consoleCheckWriter).write(any());
        verify(checkMapper).fromCheckToString(any());
    }
}

