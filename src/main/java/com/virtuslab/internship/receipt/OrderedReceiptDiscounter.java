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
    public OrderedReceiptDiscounter addDiscountToBeginning(final IDiscount discount) {
        var newDiscountsQueue = new LinkedHashSet<IDiscount>();
        newDiscountsQueue.add(discount);
        newDiscountsQueue.addAll(discounts);
        return new OrderedReceiptDiscounter(newDiscountsQueue);
    }

    @Override
    public OrderedReceiptDiscounter addDiscount(final IDiscount discount) {
        var copiedDiscountsQueued = new LinkedHashSet<>(discounts);
        copiedDiscountsQueued.add(discount);
        return new OrderedReceiptDiscounter(copiedDiscountsQueued);
    }

    @Override
    public Receipt generateReceiptWithDiscounts(final Receipt receipt) {
        if (discounts.size() == 0) return receipt;

        final Iterator<IDiscount> iterator = discounts.iterator();

        Receipt discountedReceipt = iterator.next().apply(receipt);

        while (iterator.hasNext())
            discountedReceipt = iterator.next().apply(discountedReceipt);

        return discountedReceipt;
    }

    @Override
    public LinkedHashSet<IDiscount> getDiscounts() {
        return new LinkedHashSet<>(discounts);
    }
}
