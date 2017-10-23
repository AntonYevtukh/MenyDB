package yevtukh.anton.database;
import yevtukh.anton.model.entities.Dish;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Anton on 18.10.2017.
 */
public class InitData {

    public final static List<Dish> INITIAL_DISHES = new ArrayList<>();
    static {
        Dish dish1 = new Dish(0, "Hamburger", 150, 20.00, 0);
        Dish dish2 = new Dish(0, "Cheeseburger", 160, 25.00, 0);
        Dish dish3 = new Dish(0, "BigMac", 230, 45.00, 0);
        Dish dish4 = new Dish(0, "BigTasty", 450, 70.00, 5);
        Dish dish5 = new Dish(0, "ChickenRoll", 250, 50.00, 10);
        INITIAL_DISHES.addAll(Arrays.asList(dish1, dish2, dish3, dish4, dish5));
    }
}
