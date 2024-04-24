package business;

import core.Helper;
import dao.PensionDao;
import entity.Pension;
import java.util.ArrayList;

public class PensionManager {
    private final PensionDao pensionDao;

    public PensionManager() {
        this.pensionDao = new PensionDao();
    }

    public ArrayList<Pension> findAll() {
        return this.pensionDao.findAll();
    }

    public Pension getById(int id) {
        return this.pensionDao.getById(id);
    }

    public Pension getForReservation(int hotelId, String pensionType) {
        return this.pensionDao.geForReservation(hotelId, pensionType);
    }

    public ArrayList<Pension> getByHotelId(int hotel_id) {
        return this.pensionDao.getByHotelId(hotel_id);
    }

    public boolean deleteById(int id) {
        if (this.getById(id) == null) {
            Helper.showMessage("Pansiyon bulunamadı !");
            return false;
        }
        return this.pensionDao.deleteById(id);
    }

    public boolean deleteByHotelId(int id) {
        if (this.getByHotelId(id).isEmpty()) {
            Helper.showMessage("Pansiyon bulunamadı !");
            return false;
        }
        return this.pensionDao.deleteByHotelId(id);
    }

    public boolean save(Pension pension) {
        if (this.getById(pension.getId()) != null) {
            Helper.showMessage("error");
            return false;
        }
        return this.pensionDao.save(pension);
    }

    public boolean update(Pension pension) {
        if (this.getById(pension.getId()) == null) {
            Helper.showMessage("Pansiyon bulunamadı !");
            return false;
        }
        return this.pensionDao.update(pension);
    }
}