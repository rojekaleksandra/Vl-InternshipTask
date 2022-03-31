package com.virtuslab.internship.discount.type;

import java.math.BigDecimal;
import java.util.function.UnaryOperator;

public enum DiscountType {
    TEN_PERCENT("TenPercentDiscount", p -> p.multiply(BigDecimal.valueOf(0.9))),
    FIFTEEN_PERCENT("FifteenPercentDiscount", p -> p.multiply(BigDecimal.valueOf(0.85)));

    private final String name;
    private final UnaryOperator<BigDecimal> discountApplier;

    DiscountType(final String name, UnaryOperator<BigDecimal> discountApplier) {
        this.name = name;
        this.discountApplier = discountApplier;
    }

    public String getName() {
        return name;
    }

    public BigDecimal applyDiscount(final BigDecimal before) {
        return discountApplier.apply(before);
    }
}
