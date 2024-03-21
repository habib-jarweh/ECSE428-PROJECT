package ecse428.backend.service;

import ecse428.backend.dao.MealRepository;
import ecse428.backend.dao.OrderRepository;
import ecse428.backend.dao.ReviewRepository;
import ecse428.backend.dto.ReviewDto;
import ecse428.backend.model.Meal;
import ecse428.backend.model.Order;
import ecse428.backend.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MealRepository mealRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, MealRepository mealRepository, OrderRepository orderRepository) {
        this.reviewRepository = reviewRepository;
        this.mealRepository = mealRepository;
        this.orderRepository = orderRepository;
    }

    public ReviewDto createReview(String mealName, Long OrderID, String reviewText, Integer rating) {
        // Check if the order exists
        Optional<Order> optionalOrder = orderRepository.findById(OrderID);
        if (optionalOrder.isEmpty()) {
            throw new IllegalArgumentException("Order with id " + OrderID + " does not exist.");
        }
        Order order = optionalOrder.get();


        // Check if the meal exists
        Meal meal = mealRepository.findMealByMealName(mealName);
        if (meal == null) {
            throw new IllegalArgumentException("Meal with name " + mealName + " does not exist.");
        }

        // Check if the meal in each mealItem is in the order
        if(order.getMealItems().stream().noneMatch(mealItem -> mealItem.getMeal().equals(meal))){
            throw new IllegalArgumentException("Meal with name " + mealName + " is not in the order.");
        }

        // Check if the review already exists
        if(reviewRepository.findReviewByOrderAndMeal(order, meal) != null){
            throw new IllegalArgumentException("Review for meal " + mealName + " in order " + OrderID + " already exists.");
        }


        // Check if the rating is valid
        if(rating == null || rating < 1 || rating > 5){
            throw new IllegalArgumentException("Rating must be between 1 and 5.");
        }

        Review review = new Review(order, meal, reviewText, rating);
        reviewRepository.save(review);
        return review.convertToDto();
    }

    public ReviewDto updateText(Long reviewID, String reviewText) {
        Review review = reviewRepository.findReviewByReviewID(reviewID);
        if (review == null) {
            throw new IllegalArgumentException("Review with id " + reviewID + " does not exist.");
        }
        review.setReviewText(reviewText);
        reviewRepository.save(review);
        return review.convertToDto();
    }

    public ReviewDto updateRating(Long reviewID, Integer rating) {
        Review review = reviewRepository.findReviewByReviewID(reviewID);
        if (review == null) {
            throw new IllegalArgumentException("Review with id " + reviewID + " does not exist.");
        }
        if(rating == null || rating < 1 || rating > 5){
            throw new IllegalArgumentException("Rating must be between 1 and 5.");
        }
        review.setRating(rating);
        reviewRepository.save(review);
        return review.convertToDto();
    }

    public ReviewDto getReviewById(Long reviewID){
        Review review = reviewRepository.findReviewByReviewID(reviewID);
        if (review == null) {
            throw new IllegalArgumentException("Review with id " + reviewID + " does not exist.");
        }
        return review.convertToDto();
    }

    public List<ReviewDto> getReviewsByMealName(String mealName){

        Meal meal = mealRepository.findMealByMealName(mealName);
        if (meal == null) {
            throw new IllegalArgumentException("Meal with name " + mealName + " does not exist.");
        }

        List<Review> reviews = reviewRepository.findReviewsByMeal(meal);

        return reviews.stream().map(Review::convertToDto).toList();
    }

    public boolean deleteReview(Long reviewID) {
        Review review = reviewRepository.findReviewByReviewID(reviewID);

        if (review == null) {
            throw new IllegalArgumentException("Review with id " + reviewID + " does not exist.");
        }

        reviewRepository.delete(review);
        return true;
    }
}
