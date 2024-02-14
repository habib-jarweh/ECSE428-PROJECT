package ecse428.backend.model;

import ecse428.backend.model.SmartEats.DietaryRestriction;

import java.util.Set;

import jakarta.persistence.Entity;

@Entity
public class Customer extends Account{

    private String billingAddress;
    private Integer points;
    private Set<DietaryRestriction> dietaryRestrictions;
    private String pfpImageLink;

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

    public String getPfpImageLink() {
        return this.pfpImageLink;
    }

    public void setPfpImageLink(String pfpImageLink) {
        this.pfpImageLink = pfpImageLink;
    }
    
}