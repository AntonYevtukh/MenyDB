package yevtukh.anton.controllers;

import org.hibernate.exception.ConstraintViolationException;
import yevtukh.anton.database.DbWorker;
import yevtukh.anton.model.District;
import yevtukh.anton.model.dao.interfaces.DishesDao;
import yevtukh.anton.model.entities.Dish;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.RollbackException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Anton on 23.10.2017.
 */
public class AdminController extends HttpServlet {

    private static final DbWorker DB_WORKER = DbWorker.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("districts", District.values());
        switch (req.getParameter("action")) {
            case "get":
                getDishes(req, resp);
                break;
            case "add":
                addDish(req, resp);
                break;
            case "update":
                updateDish(req, resp);
                break;
            case "delete":
                deleteDish(req, resp);
                break;
            default:
                resp.setStatus(400);
                resp.sendRedirect("");
        }
    }

    private void addDish(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        try (DishesDao dishesDao = DB_WORKER.createDishesDao()){
            dishesDao.insertDish(parseDish(req));
            req.setAttribute("success_message", "Dish successfully added");
        } catch (RollbackException e) {
            req.setAttribute("error_message", "Dish name should be unique");
            System.err.println("Unable to insert the Dish into table");
            e.printStackTrace();
        } catch (SQLException e) {
            req.setAttribute("error_message", "Internal SQL error");
            System.err.println("Unable to insert the Dish into table");
            e.printStackTrace();
        } catch (IllegalArgumentException | NullPointerException e) {
            req.setAttribute("error_message", "Invalid / inconsistent request data");
            System.err.println("Unable to parse search parameters");
            e.printStackTrace();
        } finally {
            getDishes(req, resp);
        }
    }

    private void updateDish(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        try (DishesDao dishesDao = DB_WORKER.createDishesDao()){
            dishesDao.updateDish(parseDish(req));
            req.setAttribute("success_message", "Dish successfully updated");
        } catch (RollbackException e) {
            req.setAttribute("error_message", "Dish name should be unique");
            System.err.println("Unable to insert the Dish into table");
            e.printStackTrace();
        } catch (SQLException e) {
            req.setAttribute("error_message", "Internal SQL error");
            System.err.println("Unable to insert the Dish into table");
            e.printStackTrace();
        } catch (IllegalArgumentException | NullPointerException e) {
            req.setAttribute("error_message", "Invalid / inconsistent dish data");
            System.err.println("Unable to parse search parameters");
            e.printStackTrace();
        } catch (EntityNotFoundException e) {
            req.setAttribute("error_message", e.getMessage());
            System.err.println("Unable to parse search parameters");
            e.printStackTrace();
        }
        finally {
            getDishes(req, resp);
        }
    }

    private void deleteDish(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        try (DishesDao dishesDao = DB_WORKER.createDishesDao()){
            dishesDao.deleteDish(Integer.parseInt(req.getParameter("id")));
            req.setAttribute("success_message", "Dish successfully deleted");
        } catch (SQLException e) {
            req.setAttribute("error_message", "Internal SQL error");
            System.err.println("Unable to insert the Dish into table");
            e.printStackTrace();
        } catch (IllegalArgumentException | NullPointerException e) {
            req.setAttribute("error_message", "Invalid / inconsistent dish data");
            System.err.println("Unable to parse search parameters");
            e.printStackTrace();
        } catch (EntityNotFoundException e) {
            req.setAttribute("error_message", e.getMessage());
            System.err.println("Unable to parse search parameters");
            e.printStackTrace();
        } finally {
            getDishes(req, resp);
        }
    }

    private void getDishes(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        try (DishesDao dishesDao = DB_WORKER.createDishesDao()){
            List<Dish> dishes = dishesDao.selectAllDishes();
            req.setAttribute("dishes", dishes);
        } catch (SQLException e) {
            System.err.println("Unable to get Dishes");
            e.printStackTrace();
        } finally {
            setPreviousRequestParameters(req);
            req.getRequestDispatcher("/WEB-INF/views/admin.jsp").forward(req, resp);
        }
    }

    private Dish parseDish(HttpServletRequest req) {

        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        int weight = Integer.parseInt(req.getParameter("weight"));
        double price = Double.parseDouble(req.getParameter("price"));
        int discount = Integer.parseInt(req.getParameter("discount"));
        return new Dish(id, name, weight, price, discount);
    }

    private void setPreviousRequestParameters(HttpServletRequest req) {

        boolean withDetails = req.getParameter("details") != null;
        Dish dish = null;

        if (withDetails) {
            try (DishesDao dishesDao = DB_WORKER.createDishesDao()) {
                dish = dishesDao.findDish(Integer.parseInt(req.getParameter("id")));
                withDetails = dish == null ? false : true;
            } catch (SQLException | IllegalArgumentException e) {
                withDetails = false;
            }
        }

        req.setAttribute("id", withDetails ? dish.getId() : req.getParameter("id"));
        req.setAttribute("name", withDetails? dish.getName() : req.getParameter("name"));
        req.setAttribute("weight", withDetails ? dish.getWeight() : req.getParameter("weight"));
        req.setAttribute("price", withDetails ? dish.getPrice() : req.getParameter("price"));
        req.setAttribute("discount", withDetails ? dish.getDiscount() : req.getParameter("discount"));
    }
}
