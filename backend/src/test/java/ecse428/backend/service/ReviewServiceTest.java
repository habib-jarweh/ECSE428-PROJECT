package ecse428.backend.service;

import ecse428.backend.dao.MealRepository;
import ecse428.backend.dao.OrderRepository;
import ecse428.backend.dao.ReviewRepository;
import ecse428.backend.dto.ReviewDto;
import ecse428.backend.model.Meal;
import ecse428.backend.model.Order;
import ecse428.backend.model.Review;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ReviewServiceTest {

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private MealRepository mealRepository;

    @Mock
    private OrderRepository orderRepository;

    private ReviewService reviewService;

    private Review testReview;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        reviewService = new ReviewService(reviewRepository, mealRepository, orderRepository);

        String mealName = "Test Meal";
        Long orderId = 1L;
        String reviewText = "Great meal!";
        Integer rating = 5;

        Meal meal = new Meal();
        meal.setMealName(mealName);
        Order order = new Order();
        order.setOrderID(orderId);

        testReview = new Review(order, meal, reviewText, rating);
    }

    @Test
    public void testCreateReview() {
        String mealName = "Test Meal";
        Long orderId = 1L;
        String reviewText = "Great meal!";
        Integer rating = 5;

        Meal meal = new Meal();
        meal.setMealName(mealName);
        Order order = new Order();
        order.setOrderID(orderId);
        order.setMeals(new ArrayList<>(List.of(mealName)));

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));
        when(mealRepository.findMealByMealName(mealName)).thenReturn(meal);
        when(reviewRepository.findReviewByOrderAndMeal(order, meal)).thenReturn(null);

        ReviewDto createdReviewDto = reviewService.createReview(mealName, orderId, reviewText, rating);

        assertNotNull(createdReviewDto);
        assertEquals(reviewText, createdReviewDto.getReviewText());
        assertEquals(rating, createdReviewDto.getRating());

        verify(reviewRepository, times(1)).save(any());
    }

    @Test
    public void testUpdateText() {
        Long reviewId = 1L;
        String updatedText = "Updated review text";

        Review review = testReview;
        review.setReviewID(reviewId);

        when(reviewRepository.findReviewByReviewID(reviewId)).thenReturn(review);

        ReviewDto updatedReviewDto = reviewService.updateText(reviewId, updatedText);

        assertNotNull(updatedReviewDto);
        assertEquals(updatedText, updatedReviewDto.getReviewText());

        verify(reviewRepository, times(1)).save(any());
    }

    @Test
    public void testUpdateRating() {
        Long reviewId = 1L;
        Integer updatedRating = 4;

        Review review = testReview;
        review.setReviewID(reviewId);

        when(reviewRepository.findReviewByReviewID(reviewId)).thenReturn(review);

        ReviewDto updatedReviewDto = reviewService.updateRating(reviewId, updatedRating);

        assertNotNull(updatedReviewDto);
        assertEquals(updatedRating, updatedReviewDto.getRating());

        verify(reviewRepository, times(1)).save(any());
    }

    @Test
    public void testGetReviewById() {
        Long reviewId = 1L;
        Review review = testReview;
        review.setReviewID(reviewId);

        when(reviewRepository.findReviewByReviewID(reviewId)).thenReturn(review);

        ReviewDto retrievedReviewDto = reviewService.getReviewById(reviewId);

        assertNotNull(retrievedReviewDto);
        assertEquals(reviewId, retrievedReviewDto.getReviewID());
    }

    @Test
    public void testGetReviewsByMealName() {
        String mealName = "Test Meal";
        Meal meal = new Meal();
        meal.setMealName(mealName);
        Review review = testReview;
        review.setMeal(meal);
        List<Review> reviews = new ArrayList<>();
        reviews.add(review);

        when(mealRepository.findMealByMealName(mealName)).thenReturn(meal);
        when(reviewRepository.findReviewsByMeal(meal)).thenReturn(reviews);

        List<ReviewDto> reviewDtos = reviewService.getReviewsByMealName(mealName);

        assertNotNull(reviewDtos);
        assertEquals(1, reviewDtos.size());
        assertEquals(mealName, reviewDtos.get(0).getMealName());
    }

    @Test
    public void testDeleteReview() {
        Long reviewId = 1L;
        Review review = testReview;
        review.setReviewID(reviewId);

        when(reviewRepository.findReviewByReviewID(reviewId)).thenReturn(review);

        boolean result = reviewService.deleteReview(reviewId);

        assertTrue(result);
        verify(reviewRepository, times(1)).delete(review);
    }
}
