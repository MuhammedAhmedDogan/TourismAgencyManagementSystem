package entity;

public class Price {
    private int id;
    private Room room;
    private Hotel hotel;
    private Pension pension;
    private Season season;
    private String guestType;
    private float price;

    public Price() {
    }

    public Price(Room room, Hotel hotel, Pension pension, Season season, String guestType, float price) {
        this.room = room;
        this.hotel = hotel;
        this.pension = pension;
        this.season = season;
        this.guestType = guestType;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Pension getPension() {
        return pension;
    }

    public void setPension(Pension pension) {
        this.pension = pension;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public String getGuestType() {
        return guestType;
    }

    public void setGuestType(String guestType) {
        this.guestType = guestType;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
