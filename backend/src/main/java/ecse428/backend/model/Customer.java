public class Customer extends User{

    private Double weight;
    private Double weightGoal;
    private List<String> dietaryRestrictions; //might want to make this a dict or something so we can have tags and data

    public Customer(String name, String phoneNumber, String emailAddress, String username, String password) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.username = username;
        this.password = password;
    }

    //overload for case when weight and restrictions aren't known at time of object creation
    //those fields will more than likely be set with setter at some other time
    public Customer(String name, String phoneNumber, String emailAddress, String username, String password, Double weight, Double weightGoal, List<String> dietaryRestrictions) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.username = username;
        this.password = password;
        this.weight = weight;
        this.weightGoal = weightGoal;
        this.dietaryRestriction = dietaryRestriction;
    }

    //SETTERS

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public void setWeightGoal(Double weightGoal) {
        this.weightGoal = weightGoal;
    }

    public void setDietaryRestriction(List<String> dietaryRestrictions) {
        this.dietaryRestrictions = dietaryRestrictions;
    }

    // public void addDietaryRestriction(String dietaryRestriction) {
    //     this.dietaryRestrictions.add(dietaryRestriction);
    // }

    //GETTERS

    public Double getWeight() {
        return this.weight;
    }

    public Double getWeightGoal() {
        return this.weightGoal;
    }

    public List<String> getDietaryRestrictions() {
        return this.dietaryRestrictions;
    }
}