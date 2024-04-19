package dao;

import core.Db;
import entity.Pension;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PensionDao {
    private final Connection con;
    private HotelDao hotelDao;

    public PensionDao() {
        this.con = Db.getInstance();
        this.hotelDao = new HotelDao();
    }

    public ArrayList<Pension> selectByQuery(String query) {
        ArrayList<Pension> pensionList = new ArrayList<>();
        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while (rs.next()) {
                pensionList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pensionList;
    }

    public ArrayList<Pension> findAll() {
        return selectByQuery("SELECT * FROM public.pension ORDER BY pension_id ASC");
    }

    public Pension getById(int id) {
        Pension obj = null;
        String query = "SELECT * FROM public.pension WHERE pension_id = ?";
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

    public ArrayList<Pension> getByHotelId(int hotelId) {
        return selectByQuery("SELECT * FROM public.pension WHERE pension_hotel_id = " + hotelId + " ORDER BY pension_id");
    }

    public Pension match(ResultSet rs) throws SQLException {
        Pension obj = new Pension();
        obj.setId(rs.getInt("pension_id"));
        obj.setHotel(this.hotelDao.getById(rs.getInt("pension_hotel_id")));
        obj.setPensionType(Pension.PensionType.valueOf(rs.getString("pension_type")));
        return obj;
    }

    public boolean deleteById(int id) {
        String query = "DELETE FROM public.pension WHERE pension_id = ?";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean deleteByHotelId(int id) {
        String query = "DELETE FROM public.pension WHERE pension_hotel_id = ?";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean save(Pension pension) {
        String query = "INSERT INTO public.pension " +
                "(" +
                "pension_hotel_id, " +
                "pension_type" +
                ")" +
                " VALUES (?,?)";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, pension.getHotel().getId());
            pr.setString(2, pension.getPensionType().toString());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean update(Pension pension) {
        String query = "UPDATE public.season SET " +
                "pension_hotel_id = ?, " +
                "pension_type = ? " +
                "WHERE pension_id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, pension.getHotel().getId());
            pr.setString(2, pension.getPensionType().toString());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
