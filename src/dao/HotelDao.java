package dao;

import core.Db;
import entity.Hotel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HotelDao {
    private final Connection con;

    public HotelDao() {
        this.con = Db.getInstance();
    }

    public ArrayList<Hotel> selectByQuery(String query) {
        ArrayList<Hotel> hotelList = new ArrayList<>();
        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while (rs.next()) {
                hotelList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotelList;
    }

    public ArrayList<Hotel> findAll() {
        return selectByQuery("SELECT * FROM public.hotel ORDER BY hotel_name ASC");
    }

    public Hotel getById(int id) {
        Hotel obj = null;
        String query = "SELECT * FROM public.hotel WHERE hotel_id = ?";
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

    public Hotel match(ResultSet rs) throws SQLException {
        Hotel obj = new Hotel();
        obj.setId(rs.getInt("hotel_id"));
        obj.setName(rs.getString("hotel_name"));
        obj.setCity(rs.getString("hotel_city"));
        obj.setAddress(rs.getString("hotel_address"));
        obj.setEmail(rs.getString("hotel_email"));
        obj.setPhone(rs.getString("hotel_phone"));
        obj.setStar(Hotel.Star.valueOf(rs.getString("hotel_star")));
        obj.setCarPark(rs.getBoolean("car_park"));
        obj.setWifi(rs.getBoolean("wifi"));
        obj.setPool(rs.getBoolean("pool"));
        obj.setFitness(rs.getBoolean("fitness"));
        obj.setSpa(rs.getBoolean("spa"));
        obj.setRoomService(rs.getBoolean("room_service"));
        obj.setUltraPension(rs.getBoolean("ultra_pension"));
        obj.setAllInclusivePension(rs.getBoolean("all_inclusive_pension"));
        obj.setRoomBreakfastPension(rs.getBoolean("room_breakfast_pension"));
        obj.setFullPension(rs.getBoolean("full_pension"));
        obj.setHalfPension(rs.getBoolean("half_pension"));
        obj.setOnlyBedPension(rs.getBoolean("only_bed_pension"));
        obj.setFullCreditPension(rs.getBoolean("full_credit_pension"));
        return obj;
    }

    public boolean delete(int id) {
        String query = "DELETE FROM public.hotel WHERE hotel_id = ?";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

}
