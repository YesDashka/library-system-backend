package org.example.service;

public interface BookOrderService {

    int buy(long bookId, int count);

    int cancelPurchase(long purchaseId);

}
