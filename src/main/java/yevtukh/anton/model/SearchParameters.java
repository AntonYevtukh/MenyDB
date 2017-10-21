package yevtukh.anton.model;

/**
 * Created by Anton on 14.10.2017.
 */
public class SearchParameters {

    public final static String DEFAULT_ADDRESS = "";
    public final static int DEFAULT_FROM_ROOMS = 1;
    public final static int DEFAULT_TO_ROOMS = 10;
    public final static int DEFAULT_FROM_AREA = 0;
    public final static int DEFAULT_TO_AREA = 100;
    public final static int DEFAULT_FROM_PRICE = 0;
    public final static int DEFAULT_TO_PRICE = 10000;

    private final District district;
    private final String address;
    private final int fromArea;
    private final int toArea;
    private final int fromRooms;
    private final int toRooms;
    private final int fromPrice;
    private final int toPrice;

    public SearchParameters(District district, String address, int fromRooms, int toRooms,
                            int fromArea, int toArea, int fromPrice, int toPrice) {
        this.district = district;
        this.address = address == null ? "" : address;
        this.fromArea = fromArea;
        this.toArea = toArea;
        this.fromRooms = fromRooms;
        this.toRooms = toRooms;
        this.fromPrice = fromPrice;
        this.toPrice = toPrice;
    }

    public SearchParameters() {
        this.district = null;
        this.address = DEFAULT_ADDRESS;
        this.fromRooms = DEFAULT_FROM_ROOMS;
        this.toRooms = DEFAULT_TO_ROOMS;
        this.fromArea = DEFAULT_FROM_AREA;
        this.toArea = DEFAULT_TO_AREA;
        this.fromPrice = DEFAULT_FROM_PRICE;
        this.toPrice = DEFAULT_TO_PRICE;
    }

    public District getDistrict() {
        return district;
    }

    public String getAddress() {
        return address;
    }

    public int getFromArea() {
        return fromArea;
    }

    public int getToArea() {
        return toArea;
    }

    public int getFromRooms() {
        return fromRooms;
    }

    public int getToRooms() {
        return toRooms;
    }

    public int getFromPrice() {
        return fromPrice;
    }

    public int getToPrice() {
        return toPrice;
    }

    @Override
    public String toString() {
        return "SearchParameters{" +
                "district=" + district +
                ", address='" + address + '\'' +
                ", fromArea=" + fromArea +
                ", toArea=" + toArea +
                ", fromRooms=" + fromRooms +
                ", toRooms=" + toRooms +
                ", fromPrice=" + fromPrice +
                ", toPrice=" + toPrice +
                '}';
    }
}
