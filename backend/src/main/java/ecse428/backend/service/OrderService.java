package ecse428.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ecse428.backend.dao.MealRepository;
import ecse428.backend.dao.OrderRepository;
import ecse428.backend.model.Meal;
import ecse428.backend.model.Order;
import ecse428.backend.dto.OrderDto;

import java.util.HashSet;
import java.util.Set;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final MealRepository mealRepository; // Ensure you have this

    @Autowired
    public OrderService(OrderRepository orderRepository, MealRepository mealRepository) {
        this.orderRepository = orderRepository;
        this.mealRepository = mealRepository;
    }

    public Order createOrder(OrderDto orderDto) {
        Order order = orderDto.convertToEntity();

        // Decrease stock quantity for each meal in the order
        Set<Meal> mealsToUpdate = new HashSet<>();
        for (String mealName : orderDto.getMealNames()) {
            Meal meal = mealRepository.findMealByMealName(mealName);
            if (meal != null) {
                int newQuantity = meal.getStockQuantity() - 1;
                if (newQuantity >= 0) {
                    meal.setStockQuantity(newQuantity);
                    mealsToUpdate.add(meal);
                    mealRepository.save(meal);
                } else {
                    throw new IllegalArgumentException("Insufficient stock for meal: " + mealName);
                }
            } else {
                throw new IllegalArgumentException("Meal with name " + mealName + " does not exist.");
            }
        }

        order.setMeals(orderDto.getMealNames());

        try {
            // Attempt to save the new order
            return orderRepository.save(order);
        } catch (DataIntegrityViolationException ex) {
            // Handle possible data integrity issues, for example, unique constraints
            System.out.println(ex.toString());
            throw new IllegalArgumentException("Cannot create the order due to constraints violation.", ex);        
        }
    }
    
    // Assume other parts of the class remain unchanged
}
