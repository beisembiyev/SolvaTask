package com.example.solva.Configs;

import com.example.solva.Models.Entity.Customer;
import com.example.solva.Models.Entity.Limit;
import com.example.solva.Models.Enums.SpendingsCategory;
import com.example.solva.Repos.CustomerRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

@Configuration
public class CustomerConfig {
    @Bean
    CommandLineRunner commandLineRunner(CustomerRepo customerRepo){
        return args -> {
            /*Customer a=new Customer(111,"fn","ln",
                    "email",300.0, new HashMap<>(), new ArrayList<>());
            customerRepo.save(a);*/
        };
    }
}
