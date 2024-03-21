package ecse428.backend.dto;

import ecse428.backend.model.Order;
import ecse428.backend.model.Customer;
import ecse428.backend.model.Meal;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class OrderDto {

    private Double total;
    private String customerEmail;
    private List<String> mealNames;

    // Getters and setters
    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public List<String> getMealNames() {
        return mealNames;
    }

    public void setMealNames(List<String> mealNames) {
        this.mealNames = mealNames;
    }

    // Convert to Order entity
    public Order convertToEntity() { 
        Order order = new Order();
        order.setTotal(this.total);
        
        Customer customer = new Customer(this.customerEmail); 
        order.setCustomer(customer);

        // Set<Meal> meals = this.mealNames.stream()
        //         .map(mealName -> {
        //             Meal meal= new Meal(); // Fetch the mealItem using mealItemId
        //             meal.setMealName(mealName); // Placeholder for setting the meal item ID
        //             return meal;
        //         })
        //         .collect(Collectors.toSet());
        // order.setMeals(meals);

        return order;
    }
}
