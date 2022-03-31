package com.virtuslab.internship.receipt;

import com.virtuslab.internship.discount.IDiscount;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public final class OrderedReceiptDiscounter implements IReceiptDiscounter {
    private final Set<IDiscount> discounts;

    private OrderedReceiptDiscounter(final Set<IDiscount> discounts) {
        this.discounts = discounts;
    }

    public static OrderedReceiptDiscounter withDiscounts(final IDiscount... discounts) {
        return new OrderedReceiptDiscounter(new LinkedHashSet<>(List.of(discounts)));
    }

    @Override
    public IReceiptDiscounter addToBeginning(final IDiscount discount) {
        var newDiscountsQueue = new LinkedHashSet<IDiscount>();
        newDiscountsQueue.add(discount);
        newDiscountsQueue.addAll(discounts);
        return new OrderedReceiptDiscounter(new LinkedHashSet<>(newDiscountsQueue));
    }

    @Override
    public IReceiptDiscounter addToEnd(final IDiscount discount) {
        var copiedDiscountsQueued = new LinkedHashSet<>(discounts);
        copiedDiscountsQueued.add(discount);
        return new OrderedReceiptDiscounter(copiedDiscountsQueued);
    }

    @Override
    public Receipt generateReceiptWithDiscounts(final Receipt receipt) {
        if (currentDiscountsQuantity() == 0) return receipt;

        final Iterator<IDiscount> iterator = discounts.iterator();

        Receipt discountedReceipt = iterator.next().apply(receipt);

        while (iterator.hasNext())
            discountedReceipt = iterator.next().apply(receipt);

        return discountedReceipt;
    }

    @Override
    public int currentDiscountsQuantity() {
        return discounts.size();
    }
}
