package ecse428.backend.model;

import ecse428.backend.dto.CustomerDto;
import ecse428.backend.model.SmartEats.DietaryRestriction;

import java.util.Set;

import jakarta.persistence.*;

@Entity
public class Customer extends Account {

    private String billingAddress;
    private Integer points;
    private Set<DietaryRestriction> dietaryRestrictions;
    private String pfpImageLink;
    private Double weightGoal;
    private Set<WeightDate> weightHistory;

    // Default constructor for JPA
    protected Customer() {
    }

    public Customer(String email) {
        this.setEmail(email);
    }

    public Customer(String email, String password, Set<DietaryRestriction> dietaryRestrictions, Double weightGoal,
            Set<WeightDate> weightHistory) {
        this.setEmail(email);
        this.setPassword(password);

        this.setDietaryRestrictions(dietaryRestrictions);
        this.setWeightGoal(weightGoal);
    }

    public Customer(String email, String name, String phoneNumber, String password, String address,
            String billingAddress, String pfpImageLink, Set<DietaryRestriction> dietaryRestrictions,
            Double weightGoal,
            Set<WeightDate> weightHistory) {
        this.setEmail(email);
        this.setName(name);
        this.setPhoneNumber(phoneNumber);
        this.setPassword(password);
        this.setAddress(address);
        this.setBillingAddress(billingAddress);
        this.setPfpImageLink(pfpImageLink);
        this.setDietaryRestrictions(dietaryRestrictions);
        this.setWeightGoal(weightGoal);
        this.setWeightHistory(weightHistory);
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

        return new CustomerDto(this.getEmail(), this.getName(), this.getDietaryRestrictions(), this.getWeightGoal(),
                this.getWeightHistory());
    }

    @OneToMany(cascade = { CascadeType.ALL })
    public Set<WeightDate> getWeightHistory() {
        return this.weightHistory;
    }

    public void setWeightHistory(Set<WeightDate> weightHistory) {
        this.weightHistory = weightHistory;
    }

}