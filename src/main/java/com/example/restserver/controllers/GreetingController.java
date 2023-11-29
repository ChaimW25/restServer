//public class EmptyClass {
//
//}
package com.example.restserver.controllers;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.example.restserver.entities.Customer;
import com.example.restserver.model.Greeting;
import com.example.restserver.repo.CustomerRepository;
import com.example.restserver.service.LogicService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private String value;
    private static final Logger log = LoggerFactory.getLogger(GreetingController.class);
    @Autowired
    private LogicService logicService;

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return logicService.getGreeting();
    }
    @GetMapping("/haim")
    public String getFirstCity(@RequestParam(value = "name") String name){
//        return logicService.getFirstName(name);
        return "";
    }



    @RequestMapping (value="/greeting/{value}", method = RequestMethod.POST)
    public void setValue(@PathVariable String value) {
        System.out.println("in post func");
        this.value=value;
    }
     @Autowired
    CustomerRepository repository;

    @GetMapping("/customers")
    public List<Customer>  getAllCustomers(){
        return (List<Customer>) repository.findAll();
    }
    @PutMapping (value="/customers/{id}")
    public void setCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        logicService.saveCustomer(id,customer);
    }





    @GetMapping("/data")
    public void data(){
        repository.save(new Customer("Jack", "Bauer"));
        repository.save(new Customer("Chloe", "O'Brian"));
        repository.save(new Customer("Kim", "Bauer"));
        repository.save(new Customer("David", "Palmer"));
        repository.save(new Customer("Michelle", "Dessler"));

        // fetch all customers
        log.info("Customers found with findAll():");
        log.info("-------------------------------");
        for (Customer customer : repository.findAll()) {
            log.info(customer.toString());
        }
        log.info("");

        // fetch an individual customer by ID
        Customer customer = repository.findById(1L);
        log.info("Customer found with findById(1L):");
        log.info("--------------------------------");
        log.info(customer.toString());
        log.info("");

        // fetch customers by last name
        log.info("Customer found with findByLastName('Bauer'):");
        log.info("--------------------------------------------");
        repository.findByLastName("Bauer").forEach(bauer -> {
            log.info(bauer.toString());
        });
        // for (Customer bauer : repository.findByLastName("Bauer")) {
        //  log.info(bauer.toString());
        // }
        log.info("");
    }

}