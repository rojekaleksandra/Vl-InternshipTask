package com.virtuslab.internship.receipt;

import com.virtuslab.internship.basket.Basket;
import com.virtuslab.internship.discount.type.FifteenPercentDiscount;
import com.virtuslab.internship.discount.type.TenPercentDiscount;
import com.virtuslab.internship.product.Product;
import com.virtuslab.internship.product.ProductDb;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ReceiptGeneratorTest {

    @Test
    @DisplayName("Should generate receipt for give basket")
    void shouldGenerateReceiptForGivenBasket() {
        // Given
        var productDb = new ProductDb();
        var cart = new Basket();
        var milk = productDb.getProduct("Milk");
        var bread = productDb.getProduct("Bread");
        var apple = productDb.getProduct("Apple");
        var expectedTotalPrice = milk.price().multiply(BigDecimal.valueOf(2)).add(bread.price()).add(apple.price());
        var receiptDiscounter = OrderedReceiptDiscounter.withDiscounts(new FifteenPercentDiscount(), new TenPercentDiscount());

        cart.addProduct(milk);
        cart.addProduct(milk);
        cart.addProduct(bread);
        cart.addProduct(apple);

        var receiptGenerator = new ReceiptGenerator(receiptDiscounter);

        // When
        var receipt = receiptGenerator.generate(cart);

        // Then
        assertNotNull(receipt);
        assertEquals(3, receipt.entries().size());
        assertEquals(expectedTotalPrice, receipt.totalPrice());
        assertEquals(0, receipt.discounts().size());
    }

    @Test
    @DisplayName("ReceiptGenerator initialization should throw NPE when receiptDiscounter is null")
    void ProductDb_whenInitializedWithNullProductsSet_shouldThrowNPE() {
        assertThrows(NullPointerException.class, () -> new ReceiptGenerator(null));
    }

    @Test
    @DisplayName("Should generate receipt correctly")
    void generate_whenExecutedWithExistingProductNameInDb_shouldReturnExpectedProductSuccessfully() {
        final var mockReceiptDiscounter = mock(IReceiptDiscounter.class);
        final var receiptGenerator = new ReceiptGenerator(mockReceiptDiscounter);
        final var basket = new Basket();
        final var testProduct = new Product("Banana", Product.Type.FRUITS, new BigDecimal(2));
        final var testReceipt = new Receipt(List.of(new ReceiptEntry(testProduct, 2, new BigDecimal("4"))));

        when(mockReceiptDiscounter.generateReceiptWithDiscounts(testReceipt))
                .thenReturn(testReceipt);

        basket.addProducts(testProduct, testProduct);
        final var resultReceipt = receiptGenerator.generate(basket);


        verify(mockReceiptDiscounter, times(1))
                .generateReceiptWithDiscounts(testReceipt);

        assertThat(resultReceipt, is(testReceipt));
    }

    @Test
    @DisplayName("ReceiptGenerator initialization should throw NPE when discounter generator is null")
    void ProductDb_whenInitializedWithNullDiscounter_shouldThrowNPE() {
        assertThrows(NullPointerException.class, () -> new ReceiptGenerator(null));
    }

    @Test
    @DisplayName("Should generate throw NPE when basket is null")
    void generate_whenNullArgumentIsPassed_shouldThrowNPE() {
        final var receiptGenerator = new ReceiptGenerator(mock(IReceiptDiscounter.class));

        assertThrows(NullPointerException.class, () -> receiptGenerator.generate(null));
    }

}
