package ecse428.backend.model;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Order {
    
    private long orderID;
    private double total;
    private Set<MealItem> mealItems;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getOrderID(){
        return orderID;
    }

    public double getTotal(){
        return total;
    }
  
    @OneToMany(cascade = { CascadeType.ALL })
    public Set<MealItem> getMealItems(){
        return mealItems;
    }
    

    public void setOrderID(long orderID){
        this.orderID = orderID;
    }

    public void setTotal(double total){
        this.total = total;
    }

      public void setMealItems(Set<MealItem> mealItems){
        this.mealItems = mealItems;
    }   


}
