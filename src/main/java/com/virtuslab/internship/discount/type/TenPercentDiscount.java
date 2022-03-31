package com.virtuslab.internship.discount.type;

import com.virtuslab.internship.discount.Discount;
import com.virtuslab.internship.receipt.Receipt;

import java.math.BigDecimal;

public final class TenPercentDiscount extends Discount {

    public TenPercentDiscount() {
        super(DiscountType.TEN_PERCENT);
    }

    @Override
    protected boolean shouldApply(Receipt receipt) {
        return receipt.totalPrice().compareTo(BigDecimal.valueOf(50)) >= 0;
    }
}
