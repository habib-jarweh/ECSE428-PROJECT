package ecse428.backend.dto;

import jakarta.validation.constraints.NotNull;

public class ReviewDto {

    private Long reviewID;

    @NotNull
    private Long orderID;

    @NotNull
    private String mealName;

    private String reviewText;

    private Integer rating;

    public ReviewDto(Long reviewID, Long orderID, String mealName, String reviewText, Integer rating) {
        this.reviewID = reviewID;
        this.orderID = orderID;
        this.mealName = mealName;
        this.reviewText = reviewText;
        this.rating = rating;
    }

    public Long getReviewID() {
        return reviewID;
    }

    public void setReviewID(Long reviewID) {
        this.reviewID = reviewID;
    }

    public Long getOrderID() {
        return orderID;
    }

    public void setOrderID(Long orderID) {
        this.orderID = orderID;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
