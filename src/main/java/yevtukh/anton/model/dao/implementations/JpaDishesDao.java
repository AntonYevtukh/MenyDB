package yevtukh.anton.model.dao.implementations;

import yevtukh.anton.model.SearchParameters;
import yevtukh.anton.model.dao.interfaces.DishesDao;
import yevtukh.anton.model.entities.Dish;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by Anton on 22.10.2017.
 */
public class JpaDishesDao implements DishesDao {

    private final EntityManager entityManager;

    public JpaDishesDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void insertDish(Dish dish) {
        entityManager.getTransaction().begin();
        try {
            entityManager.persist(dish);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void insertDishes(List<Dish> dishes){
        for (Dish dish : dishes)
            insertDish(dish);
    }

    @Override
    public void updateDish(Dish dish) {

        Dish oldDish = entityManager.find(Dish.class, dish.getId());
        if (oldDish == null) {
            throw new EntityNotFoundException("Dish not found");
        }

        entityManager.getTransaction().begin();
        try {
            entityManager.merge(dish);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void deleteDish(int id) {

        Dish dish = entityManager.find(Dish.class, id);
        if (dish == null) {
            throw new EntityNotFoundException("Dish not found");
        }
        entityManager.getTransaction().begin();
        try {
            entityManager.remove(dish);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public Dish findDish(int id) {
        return entityManager.find(Dish.class, id);
    }

    @Override
    public List<Dish> selectDishes(SearchParameters searchParameters) {
        Query query = entityManager.createQuery(
                "SELECT d FROM Dish d " +
                        "WHERE d.price * (100 - d.discount) / 100 BETWEEN :from_price AND :to_price" +
                        (searchParameters.isHasDiscount() ? " AND d.discount > 0" : "")
        );
        query.setParameter("from_price", searchParameters.getPriceFrom());
        query.setParameter("to_price", searchParameters.getPriceTo());
        return query.getResultList();
    }

    @Override
    public List<Dish> selectAllDishes() {
        Query query = entityManager.createNamedQuery("Dish.selectAll", Dish.class);
        return query.getResultList();
    }

    @Override
    public List<Dish> selectRandomDishesBySumWeight(int sumWeight) {
        List<Dish> allDishes = selectAllDishes();
        List<Dish> dishes = new ArrayList<>();
        Random random = new Random();
        int dishesCount = random.nextInt(5) + 1;

        if (!allDishes.isEmpty()) {
            int minWeight = Collections.min(allDishes, Comparator.comparingInt(Dish::getWeight)).getWeight();

            while (sumWeight >= minWeight && dishesCount > 0) {
                Dish dish = allDishes.get(random.nextInt(allDishes.size()));
                if (dish.getWeight() <= sumWeight) {
                    dishes.add(dish);
                    sumWeight -= dishes.get(dishes.size() - 1).getWeight();
                    dishesCount--;
                }
            }
        }

        return dishes;
    }

    @Override
    public void close() throws SQLException {
        entityManager.close();
    }
}
