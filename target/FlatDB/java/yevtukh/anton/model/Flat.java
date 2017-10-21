package yevtukh.anton.model;

/**
 * Created by Anton on 14.10.2017.
 */
public class Flat {

    private District district;
    private String address;
    private int rooms;
    private int area;
    private int price;

    public Flat() {
        this.district = District.values()[0];
        this.address = "";
        this.rooms = 1;
        this.area = 0;
        this.price = 0;
    }

    public Flat(District district, String address, int rooms, int area, int price) {
        this.district = district;
        this.address = address;
        this.rooms = rooms;
        this.area = area;
        this.price = price;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Flat{" +
                "district=" + district +
                ", address='" + address + '\'' +
                ", rooms=" + rooms +
                ", area=" + area +
                ", price=" + price +
                '}';
    }
}
