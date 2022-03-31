package com.virtuslab.internship.discount.type;

import com.virtuslab.internship.discount.Discount;
import com.virtuslab.internship.product.Product;
import com.virtuslab.internship.receipt.Receipt;
import com.virtuslab.internship.receipt.ReceiptEntry;

public final class FifteenPercentDiscount extends Discount {

    public FifteenPercentDiscount() {
        super(DiscountType.FIFTEEN_PERCENT);
    }

    @Override
    protected boolean shouldApply(final Receipt receipt) {
        return receipt.entries()
                .stream()
                .filter(this::doesReceiptContainGainsProduct)
                .mapToInt(ReceiptEntry::quantity)
                .sum() >= 3;
    }

    private boolean doesReceiptContainGainsProduct(final ReceiptEntry re) {
        return re.product().type() == Product.Type.GRAINS;
    }
}