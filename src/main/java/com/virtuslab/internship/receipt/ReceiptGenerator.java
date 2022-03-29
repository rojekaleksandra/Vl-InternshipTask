package com.virtuslab.internship.receipt;

import com.virtuslab.internship.basket.Basket;
import com.virtuslab.internship.product.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ReceiptGenerator {

    public Receipt generate(Basket basket) {
        List<ReceiptEntry> receiptEntries = new ArrayList<>();

        Map<Product, Long> productsMap = basket.getProducts()
                .stream()
                .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));

        for(Product product: productsMap.keySet()){
            receiptEntries.add( new ReceiptEntry(product, productsMap.get(product).intValue()) );
        }

        return new Receipt(receiptEntries);
    }
}
