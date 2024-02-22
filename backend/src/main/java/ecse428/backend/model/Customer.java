package ecse428.backend.model;

import ecse428.backend.dto.CustomerDto;
import ecse428.backend.model.SmartEats.DietaryRestriction;

import java.util.Set;

import jakarta.persistence.*;

@Entity
public class Customer extends Account{

    private String billingAddress;
    private Integer points;
    private Set<DietaryRestriction> dietaryRestrictions;
    private String pfpImageLink;
    private Double weightGoal;


    // Default constructor for JPA
    protected Customer() {}

    public Customer(String email) {
        this.setEmail(email);
    }

    public Customer(String email, String password, Set<DietaryRestriction> dietaryRestrictions, Double weightGoal) {
        this.setEmail(email);
        this.setPassword(password);
        this.setDietaryRestrictions(dietaryRestrictions);
        this.setWeightGoal(weightGoal);
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

    public Double getWeightGoal() {
        return this.weightGoal;
    }

    public void setWeightGoal(Double weightGoal) {
        this.weightGoal = weightGoal;
    }


    public String getPfpImageLink() {
        return this.pfpImageLink;
    }

    public void setPfpImageLink(String pfpImageLink) {
        this.pfpImageLink = pfpImageLink;
    }

    public CustomerDto convertToDto() {
        return new CustomerDto(this.getEmail(), this.getName(), this.getDietaryRestrictions(), this.getWeightGoal());
    }
    
}