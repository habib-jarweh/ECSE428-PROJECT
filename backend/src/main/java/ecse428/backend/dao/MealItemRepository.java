package ecse428.backend.dao;

import ecse428.backend.model.MealItem;
import org.springframework.data.repository.CrudRepository;

public interface MealItemRepository extends CrudRepository<MealItem, Long> {}
