package com.shops.customers.service;

import com.shops.customers.domain.Customer;
import com.shops.customers.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CustomerService implements CustomerServiceInterface{

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Customer getById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void delete(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @Override
    public String getOrderById(Long id) {
        String response = restTemplate.exchange("http://orders-service/api/orders/{id}", HttpMethod.GET, null, new ParameterizedTypeReference<String>(){},id).getBody();
        return " response = "+ response;
    }

    @Override
    public String getSupplierById(Long id) {
        String response = restTemplate.exchange("http://suppliers-service/api/suppliers/{id}", HttpMethod.GET, null, new ParameterizedTypeReference<String>(){},id).getBody();
        return " response = "+ response;
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
