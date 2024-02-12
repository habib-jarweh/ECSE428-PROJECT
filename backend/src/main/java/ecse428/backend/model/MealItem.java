package ecse428.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class MealItem {
    

    private long mealID;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	public Long getMealID() {
		return mealID;
	}

    public void setMealID(long ID){
        this.mealID = ID;
    }
}
