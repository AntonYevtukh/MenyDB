package yevtukh.anton.controllers;

import yevtukh.anton.database.DbWorker;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by Anton on 23.10.2017.
 */
@WebListener
public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DbWorker.getInstance();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        DbWorker.getInstance().stop();
    }
}
