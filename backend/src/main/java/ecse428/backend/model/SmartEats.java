package ecse428.backend.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.Set;

public class SmartEats {

  public enum DietaryRestriction {
    Peanut,
    Dairy,
    Gluten,
    Vegan,
    Halal,
  }

  public enum Ingredient {
    Peanut,
    Milk,
    Flour,
    Egg,
    Meat,
    Butter,
    Beef,
    Chicken,
    Fish,
    Rice,
  }

  private Long systemID;
  private Set<Account> accounts;
  private Set<Customer> customers;
  private Set<Admin> admins;
  private Set<Order> orders;
  private Set<Meal> meals;
  private Set<MealItem> mealItems;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public Long getSystemID() {
    return this.systemID;
  }

  public void setSystemID(Long systemID) {
    this.systemID = systemID;
  }

  @OneToMany(cascade = { CascadeType.ALL })
  public Set<Account> getAccounts() {
    return this.accounts;
  }

  public void setAccounts(Set<Account> accounts) {
    this.accounts = accounts;
  }

  @OneToMany(cascade = { CascadeType.ALL })
  public Set<Customer> getCustomers() {
    return this.customers;
  }

  public void setCustomers(Set<Customer> customers) {
    this.customers = customers;
  }

  @OneToMany(cascade = { CascadeType.ALL })
  public Set<Admin> getAdmins() {
    return this.admins;
  }

  public void setAdmins(Set<Admin> admins) {
    this.admins = admins;
  }

  @OneToMany(cascade = { CascadeType.ALL })
  public Set<Order> getOrders() {
    return this.orders;
  }

  public void setOrders(Set<Order> orders) {
    this.orders = orders;
  }

  @OneToMany(cascade = { CascadeType.ALL })
  public Set<Meal> getMeals() {
    return this.meals;
  }

  public void setMeals(Set<Meal> meals) {
    this.meals = meals;
  }

  @OneToMany(cascade = { CascadeType.ALL })
  public Set<MealItem> getMealItems() {
    return this.mealItems;
  }

  public void setMealItems(Set<MealItem> mealItems) {
    this.mealItems = mealItems;
  }

  public static class Pair<F, S> {

    private F first;
    private S second;

    public Pair(F first, S second) {
      this.first = first;
      this.second = second;
    }

    public F getFirst() {
      return first;
    }

    public S getSecond() {
      return second;
    }

    public void setFirst(F first) {
      this.first = first;
    }

    public void setSecond(S second) {
        this.second = second;
    }

    @Override
    public int hashCode() {
      return first.hashCode() ^ second.hashCode();
    }
    
    @Override
    public boolean equals(Object o) {
      if (!(o instanceof Pair)) return false;
      Pair pairo = (Pair) o;
      return (
        this.first.equals(pairo.getFirst()) &&
        this.second.equals(pairo.getSecond())
      );
    }
  }
}
