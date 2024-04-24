package dao;

import core.Db;
import entity.Season;

import java.sql.*;
import java.util.ArrayList;

public class SeasonDao {
    private final Connection con;
    private final HotelDao hotelDao;

    public SeasonDao() {
        this.con = Db.getInstance();
        this.hotelDao = new HotelDao();
    }

    public ArrayList<Season> selectByQuery(String query) {
        ArrayList<Season> seasonList = new ArrayList<>();
        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while (rs.next()) {
                seasonList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seasonList;
    }

    public ArrayList<Season> findAll() {
        return selectByQuery("SELECT * FROM public.season ORDER BY season_id ASC");
    }

    public Season getById(int id) {
        Season obj = null;
        String query = "SELECT * FROM public.season WHERE season_id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = this.match(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public Season getForReservation(int hotelId, java.util.Date startDate, java.util.Date endDate) {
        Season obj = null;
        String query = "SELECT * FROM public.season WHERE season_hotel_id = ? AND season_start_date <= ? AND season_end_date >= ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, hotelId);
            java.sql.Date sqlDateStart = new java.sql.Date(startDate.getTime());
            java.sql.Date sqlDateEnd = new java.sql.Date(endDate.getTime());
            pr.setDate(2,sqlDateStart);
            pr.setDate(3,sqlDateEnd);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = this.match(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public ArrayList<Season> getByHotelId(int hotelId) {
        return selectByQuery("SELECT * FROM public.season WHERE season_hotel_id = " + hotelId + " ORDER BY season_id");
    }

    public Season match(ResultSet rs) throws SQLException {
        Season obj = new Season();
        obj.setId(rs.getInt("season_id"));
        obj.setHotel(this.hotelDao.getById(rs.getInt("season_hotel_id")));
        obj.setStartDate(rs.getDate("season_start_date"));
        obj.setEndDate(rs.getDate("season_end_date"));
        return obj;
    }

    public boolean deleteByHotelId(int id) {
        String query = "DELETE FROM public.season WHERE season_hotel_id = ?";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean save(Season season) {
        String query = "INSERT INTO public.season " +
                "(" +
                "season_hotel_id," +
                "season_start_date," +
                "season_end_date" +
                ")" +
                " VALUES (?,?,?)";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, season.getHotel().getId());
            java.sql.Date sqlDateStart = new java.sql.Date(season.getStartDate().getTime());
            java.sql.Date sqlDateEnd = new java.sql.Date(season.getEndDate().getTime());
            pr.setDate(2, sqlDateStart);
            pr.setDate(3, sqlDateEnd);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean update(Season season) {
        String query = "UPDATE public.season SET " +
                "season_hotel_id = ?, " +
                "season_start_date = ?, " +
                "season_end_date = ? " +
                "WHERE season_id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, season.getHotel().getId());
            java.sql.Date sqlDateStart = new java.sql.Date(season.getStartDate().getTime());
            java.sql.Date sqlDateEnd = new java.sql.Date(season.getEndDate().getTime());
            pr.setDate(2, sqlDateStart);
            pr.setDate(3, sqlDateEnd);
            pr.setInt(4, season.getId());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

}
