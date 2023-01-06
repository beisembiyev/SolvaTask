package com.example.solva.Services;

import com.example.solva.Models.DTO.CustomerDTO;
import com.example.solva.Models.DTO.TransactionDTO;
import com.example.solva.Models.Entity.Customer;
import com.example.solva.Models.Entity.Transaction;
import com.example.solva.Models.Enums.Currency;
import com.example.solva.Models.Enums.SpendingsCategory;
import com.example.solva.Repos.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CustomerService {

    private final CustomerRepo customerRepo;

    @Autowired
    public CustomerService(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    public List<CustomerDTO> getAllCustomers(){
        List<Customer> customers =customerRepo.findAll();
        List<CustomerDTO> customerDTOS=new ArrayList<>();
        customers.forEach(customer -> customerDTOS.add(CustomerDTO.builder()
                .id(customer.getId())
                .accountNum(customer.getAccountNum())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .balance(customer.getBalance())
                .transactions(customer.getTransactions())
                .limits(customer.getLimits())
                .build()

        ));
        return customerDTOS;
    }



    public CustomerDTO getCustomer(long id){
        Customer customer=findCustomerById(id);
        return CustomerDTO.builder()
                .id(customer.getId())
                .accountNum(customer.getAccountNum())
                .balance(customer.getBalance())
                .email(customer.getEmail())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .transactions(customer.getTransactions())
                .limits(customer.getLimits())
                .build();
    }

    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer=Customer.builder()
                .accountNum(customerDTO.getAccountNum())
                .balance(customerDTO.getBalance())
                .firstName(customerDTO.getFirstName())
                .lastName(customerDTO.getLastName())
                .email(customerDTO.getEmail())
                .limits(customerDTO.getLimits())
                .transactions(customerDTO.getTransactions())
                .build();
        customerRepo.save(customer);
        return customerDTO;
    }
    public CustomerDTO updateLimits(long id, SpendingsCategory spendingsCategory, double amount){
        Customer customer=findCustomerById(id);
        customer.getLimits().get(spendingsCategory).setAmount(customer.getLimits().get(spendingsCategory).getAmount()+amount);
        customer.getLimits().get(spendingsCategory).setDate(LocalDateTime.now());
        customerRepo.save(customer);
        CustomerDTO customerDTO=getCustomer(id);
        return customerDTO;
    }

    public CustomerDTO makeTransaction(long id,int forWho, SpendingsCategory spendingsCategory, double amount){
        Customer customer=findCustomerById(id);
        boolean limitExceeded=limitChecker(customer, spendingsCategory, amount);
        customer.getTransactions().add(new Transaction(customer.getAccountNum(), forWho,
                Currency.KZT, amount, spendingsCategory, limitExceeded, LocalDateTime.now()));
        customer.setBalance(customer.getBalance()-amount);
        customer.getLimits().get(spendingsCategory).setAmount(customer.getLimits().get(spendingsCategory).getAmount()-amount);
        customerRepo.save(customer);
        CustomerDTO customerDTO =getCustomer(id);
        return  customerDTO;
    }

    public boolean limitChecker(Customer customer, SpendingsCategory spendingsCategory, double amount){
        if (customer.getLimits().get(spendingsCategory).getAmount()>=amount){
            return false;
        }else{
            return true;
        }
    }

    public List<TransactionDTO> exceededTransactions(long id){
        Customer customer=findCustomerById(id);
        List<Transaction> list=customer.getTransactions();
        List<TransactionDTO> dtoList=new ArrayList<>();
        list.forEach(transaction -> {
            if (transaction.isLimit_exceeded()){
                dtoList.add(TransactionDTO.builder()
                        .id(transaction.getId())
                        .account_from(transaction.getAccount_from())
                        .account_to(transaction.getAccount_to())
                        .currency_shortname(transaction.getCurrency_shortname())
                        .sum(transaction.getSum())
                        .expense_category(transaction.getExpense_category())
                        .limit_exceeded(transaction.isLimit_exceeded())
                        .datetime(transaction.getDatetime()).build()
                );
            }
        });
        return dtoList;
    }
    public CustomerDTO updateCustomer(CustomerDTO customerDTO){
        Customer customerFromRepo=findCustomerById(customerDTO.getId());
        customerFromRepo.setAccountNum(customerDTO.getAccountNum());
        customerFromRepo.setBalance(customerDTO.getBalance());
        customerFromRepo.setEmail(customerDTO.getEmail());
        customerFromRepo.setFirstName(customerDTO.getFirstName());
        customerFromRepo.setLastName(customerDTO.getLastName());
        //customerFromRepo.setTransactions(customerDTO.getTransactions());
        customerFromRepo.getTransactions().addAll(customerDTO.getTransactions());
        customerFromRepo.getLimits().putAll(customerDTO.getLimits());
        customerDTO.setLimits(customerFromRepo.getLimits());
        customerDTO.setTransactions(customerFromRepo.getTransactions());
        //customerFromRepo.setLimits(customerDTO.getLimits());
        customerRepo.save(customerFromRepo);
        return customerDTO;
    }

    public void deleteCustomer(long id){
        Customer customer=findCustomerById(id);
        customerRepo.delete(customer);
    }

    public Customer findCustomerById(long id){
        Customer customer=customerRepo.findCustomerById(id);
        if (Objects.isNull(customer)){
            throw new RuntimeException("customer not found!");
        }
        return customer;
    }

}
