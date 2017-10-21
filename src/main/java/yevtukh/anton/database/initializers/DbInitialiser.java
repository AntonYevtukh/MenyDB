package yevtukh.anton.database.initializers;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Anton on 17.10.2017.
 */
public interface DbInitialiser {

    void initDB(boolean drop) throws SQLException, ClassNotFoundException;
}
