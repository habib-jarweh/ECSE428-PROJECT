package ecse428.backend.model;

import ecse428.backend.dto.CustomerDto;
import ecse428.backend.model.SmartEats.DietaryRestriction;

import java.util.Set;
import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
public class Customer extends Account {

    private String billingAddress;
    private Integer points;
    private Set<DietaryRestriction> dietaryRestrictions;
    private Set<WeightDate> weightHistory;

    // Default constructor for JPA
    protected Customer() {
    }

    public Customer(String email) {
        this.setEmail(email);
    }

    public Customer(String email, String password, Set<DietaryRestriction> dietaryRestrictions) {
        this.setEmail(email);
        this.setPassword(password);
        this.setDietaryRestrictions(dietaryRestrictions);
    }

    public String getBillingAddress() {
        return this.billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public Integer getPoints() {
        return this.points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Set<DietaryRestriction> getDietaryRestrictions() {
        return this.dietaryRestrictions;
    }

    public void setDietaryRestrictions(Set<DietaryRestriction> dietaryRestrictions) {
        this.dietaryRestrictions = dietaryRestrictions;
    }

    public Set<WeightDate> getWeightHistory() {
        return this.weightHistory;
    }

    public void setWeightHistory(Set<WeightDate> weightHistory) {
        this.weightHistory = weightHistory;
    }

    public CustomerDto convertToDto() {
        return new CustomerDto(this.getEmail(), this.getName(), this.getDietaryRestrictions());
    }

}