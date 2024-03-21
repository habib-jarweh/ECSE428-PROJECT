package ecse428.backend.dao;

import ecse428.backend.model.Meal;
import ecse428.backend.model.Order;
import ecse428.backend.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long>{
    Review findReviewByReviewID(Long reviewID);

    List<Review> findReviewsByMeal(Meal meal);

    Review findReviewByOrderAndMeal(Order order, Meal meal);
}
