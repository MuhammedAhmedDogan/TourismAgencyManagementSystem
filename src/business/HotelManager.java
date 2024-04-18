package business;

import dao.HotelDao;
import entity.Hotel;

import java.util.ArrayList;

public class HotelManager {
    private final HotelDao hotelDao;

    public HotelManager() {
        this.hotelDao = new HotelDao();
    }

    public ArrayList<Hotel> findAll() {
        return this.hotelDao.findAll();
    }

    public Hotel getById(int id) {
        return this.hotelDao.getById(id);
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<Hotel> hotels) {
        ArrayList<Object[]> hotelList = new ArrayList<>();
        for (Hotel hotel : hotels) {
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = hotel.getId();
            rowObject[i++] = hotel.getName();
            rowObject[i++] = hotel.getCity();
            rowObject[i++] = hotel.getPhone();
            rowObject[i++] = hotel.getEmail();
            rowObject[i] = hotel.getStar().getStarValue();

            hotelList.add(rowObject);
        }
        return hotelList;
    }

    public boolean delete(int id) {
        return this.hotelDao.delete(id);
    }

}