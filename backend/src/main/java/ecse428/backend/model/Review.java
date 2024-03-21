package ecse428.backend.model;

import jakarta.persistence.*;

import java.util.Set;


@Entity
@Table(name = "Review")
public class Review {

    private Long reviewID;
    private Order order;
    private Customer customer;
    private Meal meal;

    private String reviewText;

    private Integer rating;

    protected Review() {}

    public Review(Order order, Customer customer, Meal meal, String reviewText, Integer rating) {
        this.order = order;
        this.customer = customer;
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
    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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
}
