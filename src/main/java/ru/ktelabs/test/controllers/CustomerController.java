package ru.ktelabs.test.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ktelabs.test.models.Customer;
import ru.ktelabs.test.models.Doctor;
import ru.ktelabs.test.models.TimeSlot;
import ru.ktelabs.test.models.dto.AbstractDto;
import ru.ktelabs.test.models.dto.CustomerDTO;
import ru.ktelabs.test.models.dto.DoctorDTO;
import ru.ktelabs.test.models.dto.TimeSlotDTO;
import ru.ktelabs.test.services.CustomerService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Tag(name = "Customers", description = "The Customer API")
@RestController
@RequestMapping("/api/users/customers")
public class CustomerController extends AbstractController<Customer, CustomerService, CustomerDTO> {
    protected CustomerController(CustomerService service) {
        super(service);
    }

    @Override
    public ResponseEntity<List<CustomerDTO>> index() {
        List<Customer> index = service.index();
        List<CustomerDTO> dtoList = new ArrayList<>();
        index.forEach(item -> dtoList.add(CustomerDTO.createCustomerDTO(item)));
        return ResponseEntity.ok(dtoList);
    }

    @Override
    public ResponseEntity<CustomerDTO> create(@RequestBody CustomerDTO newDTO) {
        Customer saved = service.save(new Customer(newDTO));
        return ResponseEntity.ok(CustomerDTO.createCustomerDTO(saved));
    }

    @Override
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {

        return super.delete(id);
    }

    @Operation(summary = "Get TimeSlots by customerId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "TimeSlots loaded",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Access denied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Customer not found",
                    content = @Content)})
    @GetMapping("/{customerId}/slots")
    public ResponseEntity<List<TimeSlotDTO>> getTimeSlots(@PathVariable Long customerId) {
        return ResponseEntity.ok(service.getSlots(customerId));
    }

    @Operation(summary = "Get TimeSlots by uuid/customerId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "TimeSlots loaded",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid request Body",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Access denied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Customer not found",
                    content = @Content)})
    @GetMapping("/slots")
    public ResponseEntity<List<TimeSlotDTO>> getTimeSlots(
            @RequestParam(name = "uuid", required = false) UUID uuid,
            @RequestParam(name = "customerId", required = false) Long customerId
    ) {
        if (uuid == null) {
            if (customerId == null) {
                throw new IllegalArgumentException("Specify at least 1 argument!");
            }
            return ResponseEntity.ok(service.getSlots(customerId));
        }
        return ResponseEntity.ok(service.getSlots(uuid));
    }
}
