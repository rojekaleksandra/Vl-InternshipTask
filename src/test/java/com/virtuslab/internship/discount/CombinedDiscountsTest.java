package com.virtuslab.internship.discount;

import com.virtuslab.internship.discount.type.FifteenPercentDiscount;
import com.virtuslab.internship.discount.type.TenPercentDiscount;
import com.virtuslab.internship.product.ProductDb;
import com.virtuslab.internship.receipt.Receipt;
import com.virtuslab.internship.receipt.ReceiptEntry;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CombinedDiscountsTest {
    @Test
    void shouldApplyCombinedDiscounts() {
        // Given
        var productDb = new ProductDb();
        var bread = productDb.getProduct("Bread");
        var steak = productDb.getProduct("Steak");
        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        receiptEntries.add(new ReceiptEntry(bread, 3));
        receiptEntries.add(new ReceiptEntry(steak, 1));

        var receipt = new Receipt(receiptEntries);
        var tenPercentDiscount = new TenPercentDiscount();
        var fifteenPercentDiscount = new FifteenPercentDiscount();
        var expectedTotalPrice = bread.price().multiply(BigDecimal.valueOf(3)).add(steak.price()).multiply(BigDecimal.valueOf(0.85)).multiply(BigDecimal.valueOf(0.9));

        // When
        var receiptAfterDiscount = tenPercentDiscount.apply(fifteenPercentDiscount.apply(receipt));

        // Then
        assertEquals(expectedTotalPrice, receiptAfterDiscount.totalPrice());
        assertEquals(2, receiptAfterDiscount.discounts().size());
    }
}
