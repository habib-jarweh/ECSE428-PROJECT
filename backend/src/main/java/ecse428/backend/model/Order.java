package ecse428.backend.model;

import java.util.Set;

import org.hibernate.engine.internal.Cascade;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "`Order`")
public class Order {

    private Long orderID;
    private Double total;
    private Customer customer;
    private Set<MealItem> mealItems;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getOrderID() {
        return this.orderID;
    }

    public void setOrderID(Long orderID) {
        this.orderID = orderID;
    }

    public Double getTotal() {
        return this.total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @ManyToOne(optional = false)
    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @OneToMany(cascade = {CascadeType.ALL})
    public Set<MealItem> getMealItems() {
        return this.mealItems;
    }

    public void setMealItems(Set<MealItem> mealItems) {
        this.mealItems = mealItems;
    }


}