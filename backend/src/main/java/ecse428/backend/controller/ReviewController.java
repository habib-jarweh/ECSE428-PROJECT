package ecse428.backend.controller;

import ecse428.backend.dto.ReviewDto;
import ecse428.backend.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createReview(@Valid @RequestBody ReviewDto reviewDto, BindingResult result) {
        if (result.hasErrors()) {
            String errors = result.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            return ResponseEntity.badRequest().body(errors);
        }
        try {
            return ResponseEntity.ok(
                reviewService.createReview(
                    reviewDto.getMealName(),
                    reviewDto.getOrderID(),
                    reviewDto.getReviewText(),
                    reviewDto.getRating()
                ));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/getById")
    public ResponseEntity<?> getReviewById(@RequestParam Long reviewID) {
        try {
            return ResponseEntity.ok(reviewService.getReviewById(reviewID));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/getByMeal")
    public ResponseEntity<?> getReviewByMeal(@RequestParam String mealName) {
        try {
            return ResponseEntity.ok(reviewService.getReviewsByMealName(mealName));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping("/updateText")
    public ResponseEntity<?> updateReviewText(@RequestParam Long reviewID, @RequestParam String reviewText) {
        try {
            return ResponseEntity.ok(reviewService.updateText(reviewID, reviewText));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping("/updateRating")
    public ResponseEntity<?> updateReviewRating(@RequestParam Long reviewID, @RequestParam Integer rating) {
        try {
            return ResponseEntity.ok(reviewService.updateRating(reviewID, rating));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteReview(@RequestParam Long reviewID) {
        try {
            return ResponseEntity.ok(reviewService.deleteReview(reviewID));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

}
