package ecse428.backend.model;

import ecse428.backend.model.SmartEats.DieteryRestriction;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Customer extends Account{

    private Double weight;
    private Double weightGoal;
    private Set<DieteryRestriction> dietaryRestrictions; 
    private Set<Order> orders;

    //SETTERS
    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public void setWeightGoal(Double weightGoal) {
        this.weightGoal = weightGoal;
    }

    public void setDietaryRestriction(Set<DieteryRestriction> dietaryRestrictions) {
        this.dietaryRestrictions = dietaryRestrictions;
    }

    public void setOrders(Set<Order> orders){
        this.orders = orders;
    }

    //GETTERS
    public Double getWeight() {
        return this.weight;
    }

    public Double getWeightGoal() {
        return this.weightGoal;
    }

    public Set<DieteryRestriction> getDietaryRestrictions() {
        return this.dietaryRestrictions;
    }

    @OneToMany(cascade = { CascadeType.ALL })
    public Set<Order> getOrders(){
        return orders;
    }
}