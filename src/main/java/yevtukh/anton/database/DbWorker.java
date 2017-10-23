package yevtukh.anton.database;

import yevtukh.anton.model.dao.implementations.JpaDishesDao;
import yevtukh.anton.model.dao.interfaces.DishesDao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * Created by Anton on 14.10.2017.
 */
public class DbWorker {

    private final EntityManagerFactory ENTITY_MANAGER_FACTORY;
    private static DbWorker instance;

    public static DbWorker getInstance() {
        if (instance == null) {
            try {
                instance = new DbWorker();
            } catch (Exception e) {
                System.out.println("Can't get instance of DbWorker");
                e.printStackTrace();
            }
        }
        return instance;
    }

    private DbWorker()
            throws Exception {

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("db.properties");
        Properties properties = new Properties();
        properties.load(inputStream);

        boolean runtimeConfig = "1".equals(properties.getProperty("runtime_config")) ? true : false;
        boolean fill = "1".equals(properties.getProperty("fill")) ? true : false;

        if (runtimeConfig) {
            properties.put("javax.persistence.jdbc.url", System.getenv("JDBC_DATABASE_URL"));
            properties.put("javax.persistence.jdbc.user", System.getenv("JDBC_DATABASE_USERNAME"));
            properties.put("javax.persistence.jdbc.password", System.getenv("JDBC_DATABASE_PASSWORD"));
        }

        ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory(
                properties.getProperty("dbms_name"), properties);

        if (fill) {
            fillDb();
        }
    }

    public DishesDao createDishesDao()
            throws SQLException {
        return new JpaDishesDao(ENTITY_MANAGER_FACTORY.createEntityManager());
    }

    private void fillDb()
            throws SQLException, ClassNotFoundException {
        if (InitData.INITIAL_DISHES != null) {
            DishesDao dishesDao = createDishesDao();
            dishesDao.insertDishes(InitData.INITIAL_DISHES);
        }
    }

    public void stop() {
        ENTITY_MANAGER_FACTORY.close();
    }
}
