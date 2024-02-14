package ecse428.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;


@Entity
public class MealItem {
    

    private Long mealID;
    private Meal meal;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getMealID() {
        return this.mealID;
    }

    public void setMealID(Long mealID) {
        this.mealID = mealID;
    }

    @ManyToOne(optional = false)
    public Meal getMeal() {
        return this.meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

}
