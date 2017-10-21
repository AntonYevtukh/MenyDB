package yevtukh.anton.database;

import yevtukh.anton.model.District;
import yevtukh.anton.model.Flat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Anton on 18.10.2017.
 */
public class InitData {

    public final static List<Flat> INITIAL_FLATS = new ArrayList<>();
    static {
        Flat flat1 = new Flat(District.OBOLONSKY, "Pryrichna, 17", 2, 47, 7000);
        Flat flat2 = new Flat(District.SHEVCHENKIVSKY, "Kudryavskya, 2", 2, 65, 8000);
        Flat flat3 = new Flat(District.SOLOMYANSKY, "Narodnogo opovchennya, 2", 2, 55, 6000);
        Flat flat4 = new Flat(District.OBOLONSKY, "Pryrichna, 17", 2, 47, 5000);
        Flat flat5 = new Flat(District.SHEVCHENKIVSKY, "Khrestyatyk, 3", 4, 100, 25000);
        INITIAL_FLATS.addAll(Arrays.asList(flat1, flat2, flat3, flat4, flat5));
    }
}
