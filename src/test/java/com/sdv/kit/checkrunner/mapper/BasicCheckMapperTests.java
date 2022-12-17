package com.sdv.kit.checkrunner.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.sdv.kit.checkrunner.model.Check;

import java.time.LocalDateTime;
import java.time.Month;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@SpringBootTest
class BasicCheckMapperTests {

    private final BasicCheckMapper basicCheckMapper;

    @Test
    void testFromCheckToString() {
        Check check = new Check();
        check.setDateTime(LocalDateTime.of(1, Month.JANUARY, 1, 1, 1));
        String actualFromCheckToStringResult = basicCheckMapper.fromCheckToString(check);
        assertEquals("\n"
                        + "========================================\n"
                        + "              CASH RECEIPT\n"
                        + "            SUPERMARKET 1234\n"
                        + "       12. MILKYWAY Galaxy/ Earth\n"
                        + "            Tel: 123-456-789\n" + "\n"
                        + "     Cashier: Ivanov DATE:0001-01-01\n"
                        + "                     TIME:01:01:00\n"
                        + "----------------------------------------\n"
                        + " QTY       DESCRIPTION   PRICE   TOTAL\n"
                        + "----------------------------------------\n"
                        + " PRODUCT DISCOUNT:                0,00\n"
                        + " DISCOUNT CARD:                   0,00\n"
                        + "\n"
                        + " TOTAL:                           0,00\n"
                        + "========================================",
                actualFromCheckToStringResult);
    }
}

