package com.shops.customers.rest;

import com.shops.customers.domain.Customer;
import com.shops.customers.service.CustomerService;
import com.shops.customers.service.CustomerServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/customers/")
@RestController
public class CustomerRestController {

    @Autowired
    CustomerServiceInterface customerServiceInterface;

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> getCustomer (@PathVariable("id") Long idOrder){
        if(idOrder==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Customer customer = this.customerServiceInterface.getById(idOrder);

        if(customer ==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> saveCustomer (@RequestBody @Validated Customer customer){
        if (customer ==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.customerServiceInterface.save(customer);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> updateCustomer (@RequestBody @Validated Customer customer){
        if (customer ==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.customerServiceInterface.save(customer);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> deleteCustomer (@PathVariable("id") Long idCustomer){
        if (idCustomer==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        this.customerServiceInterface.delete(idCustomer);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Customer>> getAllCustomers (){
        List<Customer> customers = this.customerServiceInterface.getAll();
        if (customers == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping(value = "/getDetailsAboutOrder/{id}")
    public String getOrder(@PathVariable("id") Long orderId){
        return this.customerServiceInterface.getOrderById(orderId);
    }

    @GetMapping(value = "/getDetailsAboutSupplier/{id}")
    public String getSupplier(@PathVariable("id") Long supplierId){
        return this.customerServiceInterface.getSupplierById(supplierId);
    }
}
