package com.example.solva.Controllers;

import com.example.solva.Models.DTO.CustomerDTO;
import com.example.solva.Models.DTO.TransactionDTO;
import com.example.solva.Models.Entity.Customer;
import com.example.solva.Models.Enums.SpendingsCategory;
import com.example.solva.Services.CustomerService;
import com.example.solva.Services.LimitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/customers")
@Tag(name = "Customers", description = "Customers API")
public class CustomerController {
    private final CustomerService customerService;
    private final LimitService limitService;

    @GetMapping("/all")
    @Operation(summary = "Method to get all customers")
    public ResponseEntity<List<CustomerDTO>> getAllCustomers(){
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/{customerId}")
    @Operation(summary = "Method to get customer by id")
    public ResponseEntity<CustomerDTO> getCustomer(
            @Parameter(description = "Customer id") @PathVariable long customerId){
        return ResponseEntity.ok(customerService.getCustomer(customerId));
    }

    @GetMapping("exceeded/{customerId}")
    @Operation(summary = "Method to get customers exceeded transactions")
    public ResponseEntity<List<TransactionDTO>> getExceededtransactions(
            @Parameter(description = "Customer id") @PathVariable long customerId){
        return ResponseEntity.ok(customerService.exceededTransactions(customerId));
    }

    @PostMapping("/{customerId}")
    @Operation(summary = "Method to set limits to customer by id")
    public ResponseEntity<CustomerDTO> setNewLimitToCustomer(
            @Parameter(description = "Customer id") @PathVariable long customerId,
    @Parameter(description = "SpendingCategory") @RequestParam SpendingsCategory spendingsCategory,
    @Parameter(description = "Amount of limit") @RequestParam double amount){
        return ResponseEntity.ok(customerService.updateLimits(customerId,spendingsCategory,amount));
    }

    @PostMapping("newtransaction/{customerId}")
    @Operation(summary = "Method to make transactions by customers id")
    public ResponseEntity<CustomerDTO> makeNewTransaction(
            @Parameter(description = "Customer id") @PathVariable long customerId,
            @Parameter(description = "addressee id") @RequestParam int addressee,
            @Parameter(description = "SpendingCategory") @RequestParam SpendingsCategory spendingsCategory,
            @Parameter(description = "Transaction amount") @RequestParam double amount){
        return ResponseEntity.ok(customerService.makeTransaction(customerId,addressee,spendingsCategory,amount));
    }

    @PostMapping()
    @Operation(summary = "Method to create customer")
    public ResponseEntity<CustomerDTO> createCustomer(
            @Parameter(description = "Request body of Customer") @RequestBody CustomerDTO customerDTO) {
        customerDTO.getLimits().put(SpendingsCategory.SERVICES,limitService.createInitialServicesLimit());
        customerDTO.getLimits().put(SpendingsCategory.PRODUCTS,limitService.createInitialProductsLimit());
        return ResponseEntity.ok(customerService.createCustomer(customerDTO));
    }

    @PutMapping()
    @Operation(summary = "Method to update customer by id")
    public ResponseEntity<CustomerDTO> updateCustomer(
            @Parameter(description = "Request body of Customer")@RequestBody CustomerDTO customerDTO){
        return ResponseEntity.ok(customerService.updateCustomer(customerDTO));
    }

    @DeleteMapping("/{customerId}")
    @Operation(summary = "Method to delete customer by id")
    public ResponseEntity<?> deleteCustomer(
            @Parameter(description = "Customer id")@PathVariable long customerId){
        customerService.deleteCustomer(customerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
