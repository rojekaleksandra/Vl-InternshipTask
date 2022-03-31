package com.virtuslab.internship.controller;

import com.virtuslab.internship.basket.Basket;
import com.virtuslab.internship.product.Product;
import com.virtuslab.internship.product.ProductDb;
import com.virtuslab.internship.receipt.Receipt;
import com.virtuslab.internship.receipt.ReceiptGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BasketController {
    private final ReceiptGenerator receiptGenerator;
    private final ProductDb productDb;
    private final Basket basket;

    @Autowired
    public BasketController(final ReceiptGenerator receiptGenerator,
                            final ProductDb productDb,
                            final Basket basket) {
        this.receiptGenerator = receiptGenerator;
        this.productDb = productDb;
        this.basket = basket;
    }

    @GetMapping("/calculate")
    public ResponseEntity<String> calculatePrice(@RequestParam  @Nullable List<String> productNames ) {
        if(productNames==null) return ResponseEntity.ok("Basket is empty");

        final List<Product> products = productNames.stream()
                .map(productDb::getProduct)
                .collect(Collectors.toList());

        basket.addProducts(products);

        final Receipt receipt = receiptGenerator.generate(basket);

        String result = productNames.stream()
                .map( p -> productDb.getProduct(p).name() + " " +  productDb.getProduct(p).price())
                .collect(Collectors.joining(", "));

        if(receipt.discounts().size()>0)
            result += " -> " + receipt.discounts().stream().collect(Collectors.joining(", "));

        result += " = " + receipt.totalPrice();

        return ResponseEntity.ok(result);
    }
}
