package ecse428.backend.controller;

import ecse428.backend.dto.ReviewDto;
import ecse428.backend.service.ReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ReviewControllerTest {

    @Mock
    private ReviewService reviewService;

    @Mock
    private BindingResult bindingResult;

    private ReviewController reviewController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        reviewController = new ReviewController(reviewService);
    }

    @Test
    public void testCreateReview_ValidInput() {
        ReviewDto reviewDto = new ReviewDto(1L, 1L, "Test Meal", "Great meal!", 5);

        when(bindingResult.hasErrors()).thenReturn(false);
        when(reviewService.createReview(reviewDto.getMealName(), reviewDto.getOrderID(), reviewDto.getReviewText(), reviewDto.getRating())).thenReturn(reviewDto);

        ResponseEntity<?> responseEntity = reviewController.createReview(reviewDto, bindingResult);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(reviewDto, responseEntity.getBody());
    }

    @Test
    public void testCreateReview_InvalidInput() {
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getAllErrors()).thenReturn(new ArrayList<>());

        ReviewDto nullReview = new ReviewDto(null, null, null, null, null);

        ResponseEntity<?> responseEntity = reviewController.createReview(nullReview, bindingResult);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    public void testGetReviewById_ValidInput() {
        Long reviewId = 1L;
        ReviewDto reviewDto = new ReviewDto(1L, 1L, "Test Meal", "Great meal!", 5);
        reviewDto.setReviewID(reviewId);

        when(reviewService.getReviewById(reviewId)).thenReturn(reviewDto);

        ResponseEntity<?> responseEntity = reviewController.getReviewById(reviewId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(reviewDto, responseEntity.getBody());
    }

    @Test
    public void testGetReviewsByMeal_ValidInput() {
        String mealName = "Test Meal";
        ReviewDto reviewDto = new ReviewDto(1L, 1L, "Test Meal", "Great meal!", 5);
        ArrayList<ReviewDto> reviewDtos = new ArrayList<>();
        reviewDtos.add(reviewDto);

        when(reviewService.getReviewsByMealName(mealName)).thenReturn(reviewDtos);

        ResponseEntity<?> responseEntity = reviewController.getReviewsByMeal(mealName);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(reviewDtos, responseEntity.getBody());
    }

    @Test
    public void testUpdateReviewText_ValidInput() {
        Long reviewId = 1L;
        String reviewText = "Updated review text";
        ReviewDto reviewDto = new ReviewDto(1L, 1L, "Test Meal", "Great meal!", 5);
        reviewDto.setReviewID(reviewId);
        reviewDto.setReviewText(reviewText);

        when(reviewService.updateText(reviewId, reviewText)).thenReturn(reviewDto);

        ResponseEntity<?> responseEntity = reviewController.updateReviewText(reviewId, reviewText);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(reviewDto, responseEntity.getBody());
    }

    @Test
    public void testUpdateReviewRating_ValidInput() {
        Long reviewId = 1L;
        Integer rating = 4;
        ReviewDto reviewDto = new ReviewDto(1L, 1L, "Test Meal", "Great meal!", 5);
        reviewDto.setReviewID(reviewId);
        reviewDto.setRating(rating);

        when(reviewService.updateRating(reviewId, rating)).thenReturn(reviewDto);

        ResponseEntity<?> responseEntity = reviewController.updateReviewRating(reviewId, rating);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(reviewDto, responseEntity.getBody());
    }

    @Test
    public void testDeleteReview_ValidInput() {
        Long reviewId = 1L;

        when(reviewService.deleteReview(reviewId)).thenReturn(true);

        ResponseEntity<?> responseEntity = reviewController.deleteReview(reviewId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(true, responseEntity.getBody());
    }

}
