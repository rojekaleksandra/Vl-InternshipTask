package com.virtuslab.internship.receipt;

import com.virtuslab.internship.discount.IDiscount;
import com.virtuslab.internship.discount.type.DiscountType;
import com.virtuslab.internship.discount.type.FifteenPercentDiscount;
import com.virtuslab.internship.discount.type.TenPercentDiscount;
import com.virtuslab.internship.product.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class OrderedReceiptDiscounterTest {
    private final static IDiscount TEN_PERCENT_DISCOUNT = new TenPercentDiscount();
    private final static IDiscount FIFTEEN_PERCENT_DISCOUNT = new FifteenPercentDiscount();

    @Test
    @DisplayName("Should generateReceiptWithDiscounts be executed correctly")
    void generateReceiptWithDiscounts_isInitWithProducts_shouldExecuteCorrectly() {
        var orderedReceiptDiscounter = OrderedReceiptDiscounter.withDiscounts(TEN_PERCENT_DISCOUNT, FIFTEEN_PERCENT_DISCOUNT);
        var testReceipt = new Receipt(
                List.of(
                        new ReceiptEntry(new Product("Milk", Product.Type.DAIRY, new BigDecimal("2.7")), 30),
                        new ReceiptEntry(new Product("Cereals", Product.Type.GRAINS, new BigDecimal(8)), 30)
                )
        );
        var resultReceipt = orderedReceiptDiscounter.generateReceiptWithDiscounts(testReceipt);

        assertThat(orderedReceiptDiscounter.getDiscounts().size(), is(2));
        assertThat(resultReceipt.totalPrice(), comparesEqualTo(new BigDecimal("245.565")));
        assertThat(resultReceipt.discounts(), contains(DiscountType.TEN_PERCENT.getName(), DiscountType.FIFTEEN_PERCENT.getName()));
    }

    @Test
    @DisplayName("Should addDiscountToBeginning add another discount to the beginning of set")
    void addDiscountToBeginning_isExecutedWithDiscount_shouldThatDiscountBeAddedToTheBeginningOfSet() {
        var orderedReceiptDiscounter = OrderedReceiptDiscounter.withDiscounts(TEN_PERCENT_DISCOUNT);

        var resultReceipt = orderedReceiptDiscounter.addDiscountToBeginning(FIFTEEN_PERCENT_DISCOUNT);

        assertThat(resultReceipt.getDiscounts().size(), is(2));
        assertThat(resultReceipt.getDiscounts(), contains(FIFTEEN_PERCENT_DISCOUNT, TEN_PERCENT_DISCOUNT));
    }

    @Test
    @DisplayName("Should addDiscount add another discount to the end of set")
    void addDiscount_isExecutedWithDiscount_shouldThatDiscountBeAddedToEndOfSet() {
        var orderedReceiptDiscounter = OrderedReceiptDiscounter.withDiscounts(TEN_PERCENT_DISCOUNT);

        var resultReceipt = orderedReceiptDiscounter.addDiscount(FIFTEEN_PERCENT_DISCOUNT);

        assertThat(resultReceipt.getDiscounts().size(), is(2));
        assertThat(resultReceipt.getDiscounts(), contains(TEN_PERCENT_DISCOUNT, FIFTEEN_PERCENT_DISCOUNT));
    }
}
