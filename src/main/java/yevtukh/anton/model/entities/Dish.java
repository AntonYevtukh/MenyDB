package yevtukh.anton.model.entities;

import javax.persistence.*;

/**
 * Created by Anton on 14.10.2017.
 */
@Entity
@Table(name = "Dishes")
@NamedQuery(name = "Dish.selectAll", query = "SELECT d FROM Dish d")
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "weight", nullable = false)
    private int weight;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "discount", nullable = false, columnDefinition = "INT default 0")
    private int discount;

    public Dish() {

    }

    public Dish(int id, String name, int weight, double price, int discount) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.price = price;
        this.discount = discount;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public double getPrice() {
        return price;
    }

    public double getActualPrice() {
        return price * (100 - discount) / 100;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
