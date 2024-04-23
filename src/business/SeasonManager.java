package business;

import core.Helper;
import dao.SeasonDao;
import entity.Season;

import java.util.ArrayList;
import java.util.Date;

public class SeasonManager {
    private final SeasonDao seasonDao;

    public SeasonManager() {
        this.seasonDao = new SeasonDao();
    }

    public ArrayList<Season> findAll() {
        return this.seasonDao.findAll();
    }

    public Season getById(int id) {
        return this.seasonDao.getById(id);
    }

    public Season getForReservation(int hotelId, Date startDate, Date endDate) {
        return this.seasonDao.getForReservation(hotelId,startDate,endDate);
    }

    public ArrayList<Season> getByHotelId(int hotel_id) {
        return this.seasonDao.getByHotelId(hotel_id);
    }

    public boolean deleteByHotelId(int id) {
        if (this.getByHotelId(id).isEmpty()) {
            Helper.showMessage("Sezon bulunamadı !");
            return false;
        }
        return this.seasonDao.deleteByHotelId(id);
    }

    public boolean save(Season season) {
        if (this.getById(season.getId()) != null) {
            Helper.showMessage("error");
            return false;
        }
        return this.seasonDao.save(season);
    }

    public boolean update(Season season) {
        if (this.getById(season.getId()) == null) {
            Helper.showMessage("Sezon bulunamadı !");
            return false;
        }
        return this.seasonDao.update(season);
    }

}
