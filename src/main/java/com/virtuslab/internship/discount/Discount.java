package com.virtuslab.internship.discount;

import com.virtuslab.internship.discount.type.DiscountType;
import com.virtuslab.internship.receipt.Receipt;

public abstract class Discount implements IDiscount {
    private final DiscountType discountType;

    public Discount(final DiscountType discountType) {
        this.discountType = discountType;
    }

    public final Receipt apply(final Receipt receipt) {
        if (shouldApply(receipt)) {
            var totalPrice = discountType.applyDiscount(receipt.totalPrice());
            var discounts = receipt.discounts();
            discounts.add(discountType.getName());
            return new Receipt(receipt.entries(), discounts, totalPrice);
        }
        return receipt;
    }

    protected abstract boolean shouldApply(Receipt receipt);

}
