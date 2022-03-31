package com.virtuslab.internship.receipt;

import com.virtuslab.internship.basket.Basket;
import com.virtuslab.internship.product.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class ReceiptGenerator {
    private final IReceiptDiscounter receiptDiscounter;

    public ReceiptGenerator(final IReceiptDiscounter receiptDiscounter) {
        this.receiptDiscounter = receiptDiscounter;
    }

    public Receipt generate(final Basket basket) {
        final List<ReceiptEntry> receiptEntries = new ArrayList<>();

        final Map<Product, Long> products = basket.getProductsBasket()
                .stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        for (final Product product : products.keySet()) {
            receiptEntries.add(new ReceiptEntry(product, products.get(product).intValue()));
        }

        return receiptDiscounter.generateReceiptWithDiscounts(new Receipt(receiptEntries));
    }

}
