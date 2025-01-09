package com.ad.ecommerceMultivBackend.service;

import com.ad.ecommerceMultivBackend.model.Order;
import com.ad.ecommerceMultivBackend.model.Seller;
import com.ad.ecommerceMultivBackend.model.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction createTransaction(Order order);
    List<Transaction> getTransactionsBySeller(Seller seller);
    List<Transaction> getAllTransactions();
}
