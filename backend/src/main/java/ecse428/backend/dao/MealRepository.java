package ecse428.backend.dao;

import ecse428.backend.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealRepository extends JpaRepository<Meal, String> {

    Meal findMealByMealName(String mealname);

}
