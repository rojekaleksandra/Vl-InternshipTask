package com.virtuslab.internship.product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ProductTest {
    private static final String TEST_NAME = "test_name";
    private static final Product.Type TEST_TYPE = Product.Type.DAIRY;
    private static final BigDecimal TEST_PRICE = new BigDecimal("20.20");

    @Test
    @DisplayName("Product should be initialized successfully")
    void productInit_whenInitializedWithCorrectArguments_shouldInitializeSuccessfully() {
        new Product(TEST_NAME, TEST_TYPE, TEST_PRICE);
    }

    @Test
    @DisplayName("Product initialization should fail when name is null")
    void productInit_whenInitializedWithNullName_shouldThrowProductCreationRuntimeException() {
        assertThrows(ProductCreationRuntimeException.class, () -> new Product(null, TEST_TYPE, TEST_PRICE));
    }

    @Test
    @DisplayName("Product initialization should fail when name is empty")
    void productInit_whenInitializedWithEmptyName_shouldThrowProductCreationRuntimeException() {
        assertThrows(ProductCreationRuntimeException.class, () -> new Product("     ", TEST_TYPE, TEST_PRICE));
    }

    @Test
    @DisplayName("Product initialization should fail when price is null")
    void productInit_whenInitializedWithNullPrice_shouldThrowProductCreationRuntimeException() {
        assertThrows(ProductCreationRuntimeException.class, () -> new Product(TEST_NAME, TEST_TYPE, null));
    }

    @Test
    @DisplayName("Product initialization should fail when price is negative number")
    void productInit_whenInitializedWithNegativePrice_shouldThrowProductCreationRuntimeException() {
        assertThrows(ProductCreationRuntimeException.class, () -> new Product(TEST_NAME, TEST_TYPE, new BigDecimal("-20.20")));
    }
}
