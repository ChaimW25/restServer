package com.example.restserver.service;

import com.example.restserver.entities.Customer;
import com.example.restserver.model.Greeting;
import com.example.restserver.repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class LogicService {

    private final ApplicationContext ac;
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private String value;
    @Autowired
    CustomerRepository repository;
    public LogicService(ApplicationContext ac){
        this.ac=ac;
    }
    public Greeting getGreeting() {
        Customer byId = repository.findById(1);
        return new Greeting(counter.incrementAndGet(), byId.getFirstName());
    }
//    @Autowired
//    RestTemplate restTemplate;
//    public String getFirstName(String str){
//        Example myMap=restTemplate.getForObject("https://data.gov.il/api/3/action/datastore_search?resource_id=9ad3862c-8391-4b2f-84a4-2d4c68625f4b&q="+str, Example.class);
//        System.out.println(myMap);
//        System.out.println(myMap.getResult());
//        Map result = myMap.getResult();
//        List n = (List)result.get("records");
//        String res=(String)((Map)n.get(0)).get("שם_ישוב");
//
//        return res;
//    }

    public void saveCustomer(Long id, Customer customer){
        customer.setId(id);
        CustomerRepository repository=ac.getBean(CustomerRepository.class);
        repository.save(customer);
    }
}
