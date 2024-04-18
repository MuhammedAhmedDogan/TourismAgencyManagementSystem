package entity;
import java.util.Date;
public class Season {
    private int id;
    private Date startDate;
    private Date endDate;
    private Hotel hotel;

    public Season() {
    }

    public Season(Date startDate, Date endDate, Hotel hotel) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.hotel = hotel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
