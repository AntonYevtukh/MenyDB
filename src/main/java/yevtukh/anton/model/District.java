package yevtukh.anton.model;

import java.util.StringJoiner;

/**
 * Created by Anton on 14.10.2017.
 */
public enum District {

    HOLOSEEVSKY, SVYATOSHINSKY, SOLOMYANSKY, OBOLONSKY, PODOLSKY, PECHERSKY, SHEVCHENKIVSKY,
    DARNYTSKY, DNIPROVSKY, DESNYANSKY;

    public static District safeParse(String districtString) {
        if (districtString == null)
            return null;
        try {
            return District.valueOf(districtString.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public static String toSqlEnumString() {
        StringJoiner stringJoiner = new StringJoiner("', '", " ('", "') ");
        for (District district : values())
            stringJoiner.add(district.name());
        return stringJoiner.toString();
    }

    @Override
    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
}
