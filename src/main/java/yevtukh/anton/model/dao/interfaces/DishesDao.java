package yevtukh.anton.model.dao.interfaces;

import yevtukh.anton.model.entities.Dish;
import yevtukh.anton.model.SearchParameters;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Anton on 21.10.2017.
 */
public interface DishesDao extends AutoCloseable {
    void insertDish(Dish dish);
    void insertDishes(List<Dish> dishes);
    void updateDish(Dish dish);
    void deleteDish(int id);
    Dish findDish(int id);
    List<Dish> selectDishes(SearchParameters searchParameters);
    List<Dish> selectAllDishes();
    List<Dish> selectRandomDishesBySumWeight(int sumWeight);
    void close() throws SQLException;
}
