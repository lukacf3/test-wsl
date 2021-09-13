package com.example.demo1.controller;

import com.example.demo1.db.service.api.CustomerAccountService;
import com.example.demo1.db.service.api.CustomerService;
import com.example.demo1.domain.Customer;
import com.example.demo1.domain.CustomerAccount;
import com.example.demo1.domain.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/customer")
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerAccountService customerAccountService;
    public CustomerController(CustomerService customerService, CustomerAccountService customerAccountService) {
        this.customerService = customerService;
        this.customerAccountService = customerAccountService;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping
    public ResponseEntity add(@RequestBody Customer customer){
        Integer id = customerService.add(customer);
        if(id !=null){
            return new ResponseEntity<>(id,  HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("{id}")
    public ResponseEntity get(@PathVariable("id") int id){
       Customer customer = customerService.get(id);
       if(customer == null){
           return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
       }
       return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping()
    public ResponseEntity getAll(){
        List<Customer> customers = customerService.getCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @PostMapping("/account")
    public ResponseEntity addAcount(@RequestBody CustomerAccount customerAccount){
        customerAccountService.addCustomerAccount(customerAccount);
        return new ResponseEntity<>(null,HttpStatus.CREATED );
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable int id){
        Customer customer = customerService.get(id);
        if (customer != null){
            customerService.delete(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Product with id: "+id+" does not exist");
    }

}
