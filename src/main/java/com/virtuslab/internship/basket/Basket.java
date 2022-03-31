package com.virtuslab.internship.basket;

import com.virtuslab.internship.product.Product;

import java.util.ArrayList;
import java.util.List;

public class Basket {

    private final List<Product> productsBasket;

    public Basket() {
        productsBasket = new ArrayList<>();
    }

    public void addProduct(Product product) {
        productsBasket.add(product);
    }

    public void addProducts(final Product... products) {
        addProducts(List.of(products));
    }

    public void addProducts(final List<Product> products) { productsBasket.addAll(products); }

    public List<Product> getProductsBasket() {
        return productsBasket;
    }
}
