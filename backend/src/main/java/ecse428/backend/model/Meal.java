package ecse428.backend.model;

import java.util.Set;

import ecse428.backend.model.SmartEats.DietaryRestriction;
import ecse428.backend.model.SmartEats.Ingredient;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Meal {
    
    private String mealName;
    private String description;
    private Double rating;
    private Double price;
    private Set<Ingredient> ingredients;
    private Set<DietaryRestriction> dietaryRestrictions;
    private String imageLink;

    @Id
    public String getMealName() {
        return this.mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getRating() {
        return this.rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Set<Ingredient> getIngredients() {
        return this.ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Set<DietaryRestriction> getDietaryRestrictions() {
        return this.dietaryRestrictions;
    }

    public void setDietaryRestrictions(Set<DietaryRestriction> dietaryRestrictions) {
        this.dietaryRestrictions = dietaryRestrictions;
    }
 
    public String getImageLink() {
        return this.imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }
}
