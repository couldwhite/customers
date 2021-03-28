package com.shops.customers.service;

import com.shops.customers.domain.Customer;

import java.util.List;

public interface CustomerServiceInterface {
    Customer getById(Long id);

    void save (Customer customer);

    void delete (Long id);

    List<Customer> getAll();

    String getOrderById(Long id);

    String getSupplierById(Long id);
}
