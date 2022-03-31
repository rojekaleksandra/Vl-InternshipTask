package com.virtuslab.internship.discount;

import com.virtuslab.internship.receipt.Receipt;

@FunctionalInterface
public interface IDiscount {
    Receipt apply(final Receipt receipt);
}
