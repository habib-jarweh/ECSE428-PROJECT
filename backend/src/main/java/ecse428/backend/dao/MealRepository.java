package ecse428.backend.dao;

import ecse428.backend.model.Meal;
import org.springframework.data.repository.CrudRepository;

public interface MealRepository extends CrudRepository<Meal, String> {}
