package com.virtuslab.internship.configuration;

import com.virtuslab.internship.discount.type.FifteenPercentDiscount;
import com.virtuslab.internship.discount.type.TenPercentDiscount;
import com.virtuslab.internship.receipt.IReceiptDiscounter;
import com.virtuslab.internship.receipt.OrderedReceiptDiscounter;
import com.virtuslab.internship.receipt.ReceiptGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReceiptConfiguration {

    @Bean
    public IReceiptDiscounter getReceiptDiscounter() {
        return OrderedReceiptDiscounter.withDiscounts(new FifteenPercentDiscount(), new TenPercentDiscount());
    }

    @Bean
    public ReceiptGenerator getReceiptGenerator() {
        return new ReceiptGenerator(getReceiptDiscounter());
    }
}
