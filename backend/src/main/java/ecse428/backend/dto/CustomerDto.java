package ecse428.backend.dto;


import ecse428.backend.model.Customer;
import ecse428.backend.model.SmartEats;
import ecse428.backend.model.SmartEats.Pair;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import java.util.Set;
import java.time.LocalDate;

public class CustomerDto {

    @NotEmpty
    @Email
    private String email;
    private String name;
    private String password;
    
    private Set<SmartEats.DietaryRestriction> dietaryRestrictions;

    private Set<SmartEats.Pair<LocalDate, Double>> weightHistory;

   

    private Double weightGoal;


    public CustomerDto() {}

    public CustomerDto(String email, String password) {
        this.email = email;
        this.password = password;
    }


    public CustomerDto(String email, String name, Set<SmartEats.DietaryRestriction> dietaryRestrictions, Set<Pair<LocalDate,Double>> weightHistory) {

    public CustomerDto(String email, String name, Set<SmartEats.DietaryRestriction> dietaryRestrictions, Double weightGoal) {

        this.email = email;
        this.password = null;
        this.name = name;
        this.dietaryRestrictions = dietaryRestrictions;

        this.weightHistory = weightHistory;

        this.weightGoal = weightGoal;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<SmartEats.DietaryRestriction> getDietaryRestrictions() {
        return dietaryRestrictions;
    }

    public void setDietaryRestrictions(Set<SmartEats.DietaryRestriction> dietaryRestrictions) {
        this.dietaryRestrictions = dietaryRestrictions;
    }


    public Set<SmartEats.Pair<LocalDate,Double>> getWeightHistory() {
        return this.weightHistory;
    }

    public void setWeightHistory(Set<SmartEats.Pair<LocalDate,Double>> weightHistory) {
        this.weightHistory = weightHistory;
    }

    public Customer convertToEntity() {
        return new Customer(this.getEmail(), this.getPassword(), this.getDietaryRestrictions(), this.getWeightHistory());

    public Double getWeightGoal() {
        return weightGoal;
    }

    public void setWeightGoal(Double weightGoal) {
        this.weightGoal = weightGoal;
    }

    public Customer convertToEntity() {
        return new Customer(this.getEmail(), this.getPassword(), this.getDietaryRestrictions(), this.getWeightGoal());
        

    }

}
