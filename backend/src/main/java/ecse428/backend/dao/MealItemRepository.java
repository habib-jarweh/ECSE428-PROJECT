package ecse428.backend.dao;

import ecse428.backend.model.MealItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealItemRepository extends JpaRepository<MealItem, Long> {}
