public class Meal {
    private String mealName;
    private String description;
    private String[] reviews;

    public Meal(String mealName, String description, String[] reviews) {
        this.mealName = mealName;
        this.description = description;
        this.reviews = reviews;
    }

    //SETTERS

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setReviews(String[] reviews) {
        this.reviews = reviews;
    }

    //GETTERS

    public String getMealName() {
        return this.mealName;
    }

    public String getDescription() {
        return this.description;
    }

    public String[] getReviews() {
        return this.reviews;
    }
}