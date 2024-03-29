package ecse428.backend.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class WeightDate {

    Long id;
    LocalDate date;
    Double Weight;

    //Default constructor for JPA
    protected WeightDate() {}

    public WeightDate(LocalDate date, Double Weight) {
        this.date = date;
        this.Weight = Weight;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getWeight() {
        return this.Weight;
    }

    public void setWeight(Double Weight) {
        this.Weight = Weight;
    }

}
