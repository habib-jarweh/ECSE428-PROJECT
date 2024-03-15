package ecse428.backend.dto;

import ecse428.backend.model.Meal;
import ecse428.backend.model.SmartEats.Ingredient;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Set;
import java.util.stream.Collectors;

public class MealDto {

    @NotNull
    @Size(min = 1, message = "Meal name must not be empty.")
    private String mealName;
    
    private String description;
    
    private Double rating;
    
    @NotNull(message = "Price must not be null.")
    private Double price;
    
    private Set<Ingredient> ingredients;
    
    private String imageLink;

    // public MealDto(Meal meal) {
    //     this.mealName = meal.getMealName();
    //     this.description = meal.getDescription();
    //     this.rating = meal.getRating();
    //     this.price = meal.getPrice();
    //     this.ingredients = meal.getIngredients();
    //     this.imageLink = meal.getImageLink();
    // }

    // Getters and Setters
    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    // Convert to Entity
    public Meal convertToEntity() {
        Meal meal = new Meal();
        meal.setMealName(this.mealName);
        meal.setDescription(this.description);
        meal.setRating(this.rating);
        meal.setPrice(this.price);
        meal.setIngredients(this.ingredients);
        meal.setImageLink(this.imageLink);
        return meal;
    }
}

