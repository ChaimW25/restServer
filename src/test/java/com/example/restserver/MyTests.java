package com.example.restserver;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.restserver.entities.Customer;
import com.example.restserver.repo.CustomerRepository;
import com.example.restserver.service.Example;
import com.example.restserver.service.LogicService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@AutoConfigureMockMvc
public class MyTests {
        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private CustomerRepository rep;

        @Test
        public void greetingShouldReturnMessageFromService() throws Exception {
            Customer c=new Customer("john","smith");
            when(rep.findById(1)).thenReturn(c);
            this.mockMvc.perform(get("/greeting")).andDo(print()).andExpect(status().isOk())
                    .andExpect(content().string(containsString("john")));
        }
    @MockBean
    RestTemplate restMock;
    @Test
    public void getFirstNameTest() throws Exception {
//        Customer c=new Customer("john","smith");
//        RestTemplate restTemplate = new RestTemplate();
        Example ex=new Example();
        Map hm=new HashMap();
        List rcd=new ArrayList<>();
        Map map=new HashMap();
        map.put("שם_ישוב","מודיעין-מכבים-רעות");
        rcd.add(map);
//                "{\"_id\":33346,\"סמל_ישוב\":\"1200 \",\"שם_ישוב\":\"מודיעין-מכבים-רעות \",\"סמל_רחוב\":\"656 \",\"שם_רחוב\":\"ירח-אב\",\"rank\":0.0573088}");
        hm.put("records",rcd);
        ex.setResult(hm);
        when(restMock.getForObject("https://data.gov.il/api/3/action/datastore_search?resource_id=9ad3862c-8391-4b2f-84a4-2d4c68625f4b&q=אב", Example.class)).thenReturn(ex);
//        this.mockMvc.perform(get("/haim?name=דב")).andDo(print()).andExpect(status().isOk())
//                .andExpect(content().string(containsString("משגב דב")));
        this.mockMvc.perform(get("/haim?name=אב")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("מודיעין-מכבים-רעות")));
    }

    }

