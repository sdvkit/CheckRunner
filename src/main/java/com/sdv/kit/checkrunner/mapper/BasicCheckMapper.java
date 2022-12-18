package com.sdv.kit.checkrunner.mapper;

import com.sdv.kit.checkrunner.model.Check;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

/**
 * Класс, предназначенный для преобразования чека в строку
 */
@Component
public class BasicCheckMapper implements CheckMapper{

    @Override
    public String fromCheckToString(Check check) {
        StringBuilder checkBody = new StringBuilder();

        checkBody.append(String.format("\n" + "=".repeat(40) + "\n%26s\n%28s\n%33s\n%28s\n\n%36s\n%34s" + "\n" + "-".repeat(40) + "\n%4s%18s%8s%8s\n",
                "CASH RECEIPT",
                "SUPERMARKET 1234",
                "12. MILKYWAY Galaxy/ Earth",
                "Tel: 123-456-789",
                "Cashier: Ivanov DATE:" + check.getDateTime().toLocalDate(),
                "TIME:" + DateTimeFormatter.ofPattern("HH:mm:ss").format(check.getDateTime()),
                "QTY", "DESCRIPTION", "PRICE", "TOTAL"
        ));

        check.getProducts().forEach((product, count) -> {
            checkBody.append(String.format("%4d%18s%8.2f%8.2f\n",
                    count,
                    product.getName(),
                    product.getPrice(),
                    product.getPrice() * count));
        });

        checkBody.append(String.format("-".repeat(40) + "\n PRODUCT DISCOUNT: %19.2f\n DISCOUNT CARD: %22.2f\n\n TOTAL: %30.2f\n" + "=".repeat(40),
                check.getProductDiscount(),
                check.getDiscountCardPrice(),
                check.getTotalPrice()
        ));

        return checkBody.toString();
    }
}
