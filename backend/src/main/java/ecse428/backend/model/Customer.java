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
    private Set<MealOrder> mealOrders;

    //SETTERS
    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public void setWeightGoal(Double weightGoal) {
        this.weightGoal = weightGoal;
    }

    public void setDietaryRestrictions(Set<DieteryRestriction> dietaryRestrictions) {
        this.dietaryRestrictions = dietaryRestrictions;
    }

    public void setOrders(Set<MealOrder> orders){
        this.mealOrders = orders;
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
    public Set<MealOrder> getOrders(){
        return mealOrders;
    }
}