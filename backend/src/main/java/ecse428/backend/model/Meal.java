package ecse428.backend.model;

import java.util.Set;

import ecse428.backend.model.SmartEats.DieteryRestriction;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Meal {
    

    private String mealName;
    private String description;
    private double rating;
    private double price;
    private Set<DieteryRestriction> dietaryRestrictions; 
    private Set<MealItem> mealItems;

    @Id
    public String getMealName(){
        return mealName;
    }
    public String getDescription(){
        return description;
    }
    public double getRating(){
        return rating;
    }
    public double getPrice(){
        return price;
    } 
    public Set<DieteryRestriction> getDietaryRestrictions() {
        return this.dietaryRestrictions;
    }
    @OneToMany(cascade = { CascadeType.ALL })
    public Set<MealItem> getMealItems(){
        return mealItems;
    }
    public void setMealName(String mealName){
        this.mealName = mealName;
    }
    public void setDescription(String desc){
        this.description = desc;
    }
    public void setRating(double rating){
        this.rating = rating;
    }
    public void setPrice(double price){
        this.price = price;
    }
    public void setDietaryRestrictions(Set<DieteryRestriction> dietaryRestrictions) {
        this.dietaryRestrictions = dietaryRestrictions;
    }
    public void setMealItems(Set<MealItem> mealItems){
        this.mealItems = mealItems;
    }   
}
