package business;

import core.Helper;
import dao.PensionDao;
import dao.PriceDao;
import entity.Price;

import java.util.ArrayList;

public class PriceManager {
    private final PriceDao priceDao;

    public PriceManager() {
        this.priceDao = new PriceDao();
    }

    public ArrayList<Price> findAll() {
        return this.priceDao.findAll();
    }

    public Price getById(int id) {
        return this.priceDao.getById(id);
    }

    public boolean deleteById(int id) {
        if (this.getById(id) == null) {
            Helper.showMessage("Fiyat bulunamadı !");
            return false;
        }
        return this.priceDao.deleteById(id);
    }

    public boolean deleteByRoomId(int roomId) {
        return this.priceDao.deleteByRoomId(roomId);
    }

    public boolean deleteByHotelId(int hotelId) {
        return this.priceDao.deleteByHotelId(hotelId);
    }

    public boolean deleteByPensionId(int pensionId) {
        return this.priceDao.deleteByPensionId(pensionId);
    }

    public boolean save(Price price) {
        if (this.getById(price.getId()) != null) {
            Helper.showMessage("error");
            return false;
        }
        return this.priceDao.save(price);
    }

    public boolean update(Price price) {
        if (this.getById(price.getId()) == null) {
            Helper.showMessage("Fiyat bulunamadı !");
            return false;
        }
        return this.priceDao.update(price);
    }
}
