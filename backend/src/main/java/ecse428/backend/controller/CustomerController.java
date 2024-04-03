package ecse428.backend.controller;

import ecse428.backend.dto.CustomerDto;
import ecse428.backend.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/customers")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody CustomerDto customerDto, BindingResult result) {
        if (result.hasErrors()) {
            String errors = result.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            return ResponseEntity.badRequest().body(errors);
        }
        try {
            return ResponseEntity.ok(customerService.registerCustomer(customerDto));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> checkUserCredentials(@Valid @RequestBody CustomerDto customerDto, BindingResult result) {
        if (result.hasErrors()) {
            String errors = result.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            return ResponseEntity.badRequest().body(errors);
        }
        try {
            return ResponseEntity.ok(customerService.checkCustomerCredentials(customerDto));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping("/dietary-restrictions")
    public ResponseEntity<?> updateDietaryRestrictions(@Valid @RequestBody CustomerDto customerDto, BindingResult result) {
        if (result.hasErrors()) {
            String errors = result.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            return ResponseEntity.badRequest().body(errors);
        }
        try {            
            // Convert Set<DietaryRestriction> to String[] for the service method
            String[] dietaryRestrictions = customerDto.getDietaryRestrictions().stream()
                    .map(Enum::name) // Assuming DietaryRestriction is an enum and we want its name
                    .toArray(String[]::new);
            
            // Call the service method with the email and converted dietary restrictions
            boolean success = customerService.setDietaryRestriction(customerDto.getEmail(),  dietaryRestrictions);
            
            return success
                    ? ResponseEntity.ok().build()
                    : ResponseEntity.internalServerError().build();
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }


    @GetMapping("/dietary-restrictions")
    public ResponseEntity<?> getDietaryRestrictions(@Valid @RequestBody CustomerDto customerDto, BindingResult result) {
        if (result.hasErrors()) {
            String errors = result.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            return ResponseEntity.badRequest().body(errors);
        }
        try {
            String email = customerDto.getEmail();
            String[] dietaryRestrictions = customerService.getDietaryRestriction(email);
            return ResponseEntity.ok(dietaryRestrictions);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping("/weightGoal")
    public ResponseEntity<?> updateWeightGoal(@Valid @RequestBody CustomerDto customerDto, @RequestParam Double weightGoal, BindingResult result) {
        if (result.hasErrors()) {
            String errors = result.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            return ResponseEntity.badRequest().body(errors);
        }
        try {
            return ResponseEntity.ok(customerService.addUpdateWeightGoal(customerDto, weightGoal));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/weightGoal")
    public ResponseEntity<?> getWeightGoal(@Valid @RequestBody CustomerDto customerDto, BindingResult result) {
        if (result.hasErrors()) {
            String errors = result.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            return ResponseEntity.badRequest().body(errors);
        }
        try {
            String email = customerDto.getEmail();
            double weightGoal = customerService.getWeightGoal(email);
            return ResponseEntity.ok(weightGoal);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping("/weightHistory")
    public ResponseEntity<?>updateUserWeightHistory(@Valid @RequestBody CustomerDto customerDto,@RequestParam Double weight, BindingResult result){
        if (result.hasErrors()) {
            String errors = result.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            return ResponseEntity.badRequest().body(errors);
        }
        try{
            return ResponseEntity.ok(customerService.addUpdateWeightHistory(customerDto,weight));
        }catch(IllegalArgumentException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/view_all")
    public ResponseEntity<?> viewAllCustomers() {
        try {
            return ResponseEntity.ok(customerService.getAllCustomers());
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestBody CustomerDto customerDto, BindingResult result) {
        String email = customerDto.getEmail();
        try {
            if (email == null || email.trim().isEmpty()) {
                throw new IllegalArgumentException("Email must not be empty");
            }
            String message = customerService.deleteCustomer(email);
            return ResponseEntity.ok().body(message);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("An error occurred while deleting the customer.");
        }
    }

    @GetMapping("/userInfo")
    public ResponseEntity<?>getUserInfo(@Valid @RequestBody CustomerDto customerDto, BindingResult result){
        if (result.hasErrors()) {
            String errors = result.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            return ResponseEntity.badRequest().body(errors);
        }
        try{
             return ResponseEntity.ok(customerService.getUserInfo(customerDto.getEmail()));
        }catch(IllegalArgumentException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/userInfo/{email}")
    public ResponseEntity<?> getUserInfo(@PathVariable String email) {

        System.out.println("entered get info");

        try {
            
            return ResponseEntity.ok(customerService.getUserInfo(email));
        } catch (IllegalArgumentException ex) {
            
        System.out.println("illegal exception");
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping("/userInfo")
    public ResponseEntity<?>editUserInfo(@Valid @RequestBody CustomerDto customerDto, BindingResult result){


        System.out.println("entered get info");

        if (result.hasErrors()) {
            System.out.println("result has error");
            String errors = result.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            return ResponseEntity.badRequest().body(errors);
        }
        try{
            System.out.println("makes a service call");
             return ResponseEntity.ok(customerService.editUserInfo(customerDto));
        }catch(IllegalArgumentException ex){
            
            System.out.println("illegalException");
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }


}