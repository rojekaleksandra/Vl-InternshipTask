package com.virtuslab.internship.receipt;

import com.virtuslab.internship.discount.IDiscount;

public interface IReceiptDiscounter {
    IReceiptDiscounter addToBeginning(IDiscount discount);

    IReceiptDiscounter addToEnd(IDiscount discount);

    Receipt generateReceiptWithDiscounts(final Receipt receipt);

    int currentDiscountsQuantity();
}
