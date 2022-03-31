package com.virtuslab.internship.basket;

import com.virtuslab.internship.product.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;

class BasketTest {
    private static final Product TEST_PRODUCT_BANANA = new Product("Banana", Product.Type.FRUITS, new BigDecimal(2));
    private static final Product TEST_PRODUCT_APPLE = new Product("Apple", Product.Type.FRUITS, new BigDecimal(4));

    @Test
    @DisplayName("Should addProduct add product to basket correctly")
    void addProduct_whenProductIsAdded_shouldAddProductSuccessfully() {
        final var basket = new Basket();

        basket.addProduct(TEST_PRODUCT_BANANA);
        final var basketAfterAddingProduct = basket.getProductsBasket();

        assertThat(basketAfterAddingProduct.size(), is(1));
        assertThat(basketAfterAddingProduct, containsInAnyOrder(TEST_PRODUCT_BANANA));
    }

    @Test
    @DisplayName("Should addProducts varargs add products to basket correctly")
    void addProducts_whenProductsAreAddedToVarargsMethod_shouldAddProductSuccessfully() {
        final var basket = new Basket();

        basket.addProducts(TEST_PRODUCT_BANANA);
        basket.addProducts(TEST_PRODUCT_APPLE, TEST_PRODUCT_BANANA);
        final var basketAfterAddingProducts = basket.getProductsBasket();

        assertThat(basketAfterAddingProducts.size(), is(3));
        assertThat(basketAfterAddingProducts, containsInAnyOrder(TEST_PRODUCT_BANANA, TEST_PRODUCT_APPLE, TEST_PRODUCT_BANANA));
    }

    @Test
    @DisplayName("Should addProducts list add products to basket correctly")
    void addProducts_whenProductsAreAddedToListMethod_shouldAddProductSuccessfully() {
        final var basket = new Basket();

        basket.addProducts(List.of(TEST_PRODUCT_BANANA, TEST_PRODUCT_BANANA));
        final var basketAfterAddingProducts = basket.getProductsBasket();

        assertThat(basketAfterAddingProducts.size(), is(2));
        assertThat(basketAfterAddingProducts, containsInAnyOrder(TEST_PRODUCT_BANANA, TEST_PRODUCT_BANANA));
    }

    @Test
    @DisplayName("Should getProductsBasket return copy of collection")
    void getProductsBasket_whenRetrievingBasket_shouldReturnCopyOfBasket() {
        final var basket = new Basket();

        final var basketBeforeAddingProduct = basket.getProductsBasket();
        basket.addProducts(List.of(TEST_PRODUCT_BANANA));
        final var basketAfterAddingProduct = basket.getProductsBasket();

        assertThat(basketBeforeAddingProduct.size(), is(0));
        assertThat(basketAfterAddingProduct.size(), is(1));
        assertThat(basketAfterAddingProduct, containsInAnyOrder(TEST_PRODUCT_BANANA));
    }
}
