package entity;

public class Room {
    private int id;
    private Hotel hotel;
    private RoomType roomType;
    private int beds;
    private int area;
    private boolean tv;
    private boolean minibar;
    private boolean gameConsole;
    private boolean safe;
    private boolean projection;
    private int stock;

    public enum RoomType {
        Single_Room,
        Double_Room,
        Junior_Suite,
        Suite
    }

    public Room() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public int getBeds() {
        return beds;
    }

    public void setBeds(int beds) {
        this.beds = beds;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public boolean isTv() {
        return tv;
    }

    public void setTv(boolean tv) {
        this.tv = tv;
    }

    public boolean isMinibar() {
        return minibar;
    }

    public void setMinibar(boolean minibar) {
        this.minibar = minibar;
    }

    public boolean isGameConsole() {
        return gameConsole;
    }

    public void setGameConsole(boolean gameConsole) {
        this.gameConsole = gameConsole;
    }

    public boolean isSafe() {
        return safe;
    }

    public void setSafe(boolean safe) {
        this.safe = safe;
    }

    public boolean isProjection() {
        return projection;
    }

    public void setProjection(boolean projection) {
        this.projection = projection;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}