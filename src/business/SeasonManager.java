package business;

import core.Helper;
import dao.SeasonDao;
import entity.Season;

import java.util.ArrayList;

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

    public ArrayList<Season> getByHotelId(int hotel_id) {
        return this.seasonDao.getByHotelId(hotel_id);
    }

    public boolean deleteById(int id) {
        return this.seasonDao.deleteById(id);
    }

    public boolean deleteByHotelId(int id) {
        return this.seasonDao.deleteByHotelId(id);
    }

    public boolean save(Season season) {
        if (this.getById(season.getId()) != null) {
            Helper.showMessage("error");
            return false;
        }
        return this.seasonDao.save(season);
    }

}
