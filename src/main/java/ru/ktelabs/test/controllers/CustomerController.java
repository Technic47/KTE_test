package ru.ktelabs.test.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ktelabs.test.models.Customer;
import ru.ktelabs.test.services.CustomerService;

@Tag(name = "Customers", description = "The Customer API")
@RestController
@RequestMapping("/api/customers")
public class CustomerController extends AbstractController<Customer, CustomerService> {
    protected CustomerController(CustomerService service) {
        super(service);
    }
}
