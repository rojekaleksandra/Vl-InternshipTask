package com.virtuslab.internship.product;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

public record Product(
        String name,
        Type type,
        BigDecimal price
) {
    public Product {
        if (StringUtils.trimToNull(name) == null)
            throw new ProductCreationRuntimeException("Product name cannot be null");

        if (price == null || price.longValue() < 0)
            throw new ProductCreationRuntimeException("Price cannot be null or a negative number");
    }
    public enum Type {
        DAIRY, FRUITS, VEGETABLES, MEAT, GRAINS
    }
}
