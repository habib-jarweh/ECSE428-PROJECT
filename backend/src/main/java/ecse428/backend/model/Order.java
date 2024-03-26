package ecse428.backend.model;

import java.util.List;
import java.util.Set;

import ecse428.backend.dto.OrderDto;
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
    private List<String> meals;

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

    public List<String> getMeals() {
        return this.meals;
    }

    public void setMeals(List<String> meals) {
        this.meals = meals;
    }

    public OrderDto convertToDto() {
        OrderDto order = new OrderDto();
        order.setTotal(this.total);
        order.setCustomerEmail(this.customer.getEmail());
        order.setMealNames(this.meals);
        return order;
    }


}
