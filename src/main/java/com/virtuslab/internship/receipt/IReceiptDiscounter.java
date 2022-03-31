package com.virtuslab.internship.receipt;

import com.virtuslab.internship.discount.IDiscount;

import java.util.LinkedHashSet;

public interface IReceiptDiscounter {
    IReceiptDiscounter addDiscountToBeginning(IDiscount discount);

    IReceiptDiscounter addDiscount(IDiscount discount);

    Receipt generateReceiptWithDiscounts(final Receipt receipt);

    LinkedHashSet<IDiscount> getDiscounts();
}
