package yevtukh.anton.controllers;

import yevtukh.anton.database.DbWorker;
import yevtukh.anton.model.*;
import yevtukh.anton.model.dao.interfaces.DishesDao;
import yevtukh.anton.model.entities.Dish;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Anton on 14.10.2017.
 */
public class MenuController extends HttpServlet {

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
            default:
                resp.setStatus(400);
                resp.sendRedirect("");
        }
    }

    private void getDishes(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {

        try (DishesDao dishesDao = DB_WORKER.createDishesDao()){

            List<Dish> dishes;
            if (req.getParameter("select_all") != null)
                dishes = dishesDao.selectAllDishes();
            else if (req.getParameter("select_random") != null) {
                dishes = dishesDao.selectRandomDishesBySumWeight(Integer.parseInt(req.getParameter("sum_weight")));
            }
            else {
                dishes = dishesDao.selectDishes(parseSearchParameters(req));
            }

            req.setAttribute("success_message", "Dishes successfully found");
            req.setAttribute("dishes", dishes);
        } catch (SQLException e) {
            req.setAttribute("error_message", "Internal SQL error");
            System.err.println("Unable to get Dishes");
            e.printStackTrace();
        } catch (IllegalArgumentException | NullPointerException e) {
            req.setAttribute("error_message", "Invalid / inconsistent request data");
            System.err.println("Unable to parse search parameters");
            e.printStackTrace();
        } finally {
            setPreviousRequestParameters(req);
            req.getRequestDispatcher("/WEB-INF/views/menu.jsp").forward(req, resp);
        }

    }

    private SearchParameters parseSearchParameters(HttpServletRequest req) {

        double priceFrom = Double.parseDouble(req.getParameter("from_price"));
        double priceTo =  Double.parseDouble(req.getParameter("to_price"));
        boolean hasDiscount = "discount".equals(req.getParameter("discount")) ? true : false;

        return new SearchParameters(priceFrom, priceTo, hasDiscount);
    }

    private void setPreviousRequestParameters(HttpServletRequest req) {

        req.setAttribute("from_price", req.getParameter("from_price"));
        req.setAttribute("to_price", req.getParameter("to_price"));
        req.setAttribute("discount", "discount".equals(req.getParameter("discount")) ? true : false);
        req.setAttribute("sum_weight", req.getParameter("sum_weight"));
    }
}
