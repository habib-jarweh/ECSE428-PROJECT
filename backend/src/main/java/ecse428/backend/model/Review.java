package ecse428.backend.model;

import ecse428.backend.dto.ReviewDto;
import jakarta.persistence.*;


@Entity
@Table(name = "Review")
public class Review {

    private Long reviewID;
    private Order order;
    private Meal meal;

    private String reviewText;

    private Integer rating;

    protected Review() {}

    public Review(Order order, Meal meal, String reviewText, Integer rating) {
        this.order = order;
        this.meal = meal;
        this.reviewText = reviewText;
        this.rating = rating;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getReviewID() {
        return this.reviewID;
    }

    public void setReviewID(Long reviewID) {
        this.reviewID = reviewID;
    }

    @ManyToOne(optional = false)
    public Order getOrder() {
        return this.order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @ManyToOne(optional = false)
    public Meal getMeal() {
        return this.meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public String getReviewText() {
        return this.reviewText;
    }

    public Integer getRating() {
        return this.rating;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public ReviewDto convertToDto() {
        return new ReviewDto(
                this.getReviewID(),
                this.getOrder().getOrderID(),
                this.getMeal().getMealName(),
                this.getReviewText(),
                this.getRating());
    }
}
