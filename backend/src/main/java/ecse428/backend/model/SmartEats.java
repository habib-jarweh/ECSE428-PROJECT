package ecse428.backend.model;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

public class SmartEats {
    
   
    public enum DieteryRestriction {
        Peanut, Dairy, Gluten 
    }

    private long systemID;
    private Set<Customer> customers;
    private Set<Admin> admins;
    private Set<Meal> meals;
    private Set<MealItem> mealItems;
    private Set<Order> orders;
     

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getSystemID() {
		return systemID;
	}


    @OneToMany(cascade = { CascadeType.ALL })
    public Set<Customer> getCustomers(){
        return customers;
    }

    @OneToMany(cascade = { CascadeType.ALL })
    public Set<Admin> getAdmins(){
        return admins;
    }

    @OneToMany(cascade = { CascadeType.ALL })
    public Set<Meal> getMeals(){
        return meals;
    }

    
    @OneToMany(cascade = { CascadeType.ALL })
    public Set<Order> getOrders(){
        return orders;
    }

     
    @OneToMany(cascade = { CascadeType.ALL })
    public Set<MealItem> getMealItems(){
        return mealItems;
    }

    public void setCustomers(Set<Customer> customers){
        this.customers = customers;
    }

    public void setAdmins(Set<Admin> admins){
        this.admins = admins;
    }

    public void setSystemID(long ID){
        this.systemID = ID;
    }

    public void setMeals(Set<Meal> meals){
        this.meals = meals;
    }

    public void setOrders(Set<Order> orders){
        this.orders = orders;
    }

    public void setMealItems(Set<MealItem> mealItems){
        this.mealItems = mealItems;
    }   

}
