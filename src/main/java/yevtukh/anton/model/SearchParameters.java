package yevtukh.anton.model;

/**
 * Created by Anton on 14.10.2017.
 */
public class SearchParameters {

    private double priceFrom;
    private double priceTo;
    private boolean hasDiscount;

    public SearchParameters(double priceFrom, double priceTo, boolean hasDiscount) {
        this.priceFrom = priceFrom;
        this.priceTo = priceTo;
        this.hasDiscount = hasDiscount;
    }

    public double getPriceFrom() {
        return priceFrom;
    }

    public double getPriceTo() {
        return priceTo;
    }

    public boolean isHasDiscount() {
        return hasDiscount;
    }
}
