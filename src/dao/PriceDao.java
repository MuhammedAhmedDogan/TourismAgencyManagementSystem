package dao;

import core.Db;
import entity.Price;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PriceDao {
    private final Connection con;
    private final HotelDao hotelDao;
    private final RoomDao roomDao;
    private final SeasonDao seasonDao;
    private final PensionDao pensionDao;

    public PriceDao() {
        this.con = Db.getInstance();
        this.hotelDao = new HotelDao();
        this.roomDao = new RoomDao();
        this.seasonDao = new SeasonDao();
        this.pensionDao = new PensionDao();
    }

    public ArrayList<Price> selectByQuery(String query) {
        ArrayList<Price> priceList = new ArrayList<>();
        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while (rs.next()) {
                priceList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return priceList;
    }

    public ArrayList<Price> findAll() {
        return selectByQuery("SELECT * FROM public.price ORDER BY price_id ASC");
    }

    public Price getById(int id) {
        Price obj = null;
        String query = "SELECT * FROM public.price WHERE price_id = ?";
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

    public ArrayList<Price> getByRoomId(int roomId){
        return selectByQuery("SELECT * FROM public.price WHERE price_room_id = " + roomId + " ORDER BY price_id ASC");
    }

    public Price match(ResultSet rs) throws SQLException {
        Price obj = new Price();
        obj.setId(rs.getInt("price_id"));
        obj.setRoom(this.roomDao.getById(rs.getInt("price_room_id")));
        obj.setHotel(this.hotelDao.getById(rs.getInt("price_hotel_id")));
        obj.setPension(this.pensionDao.getById(rs.getInt("price_pension_id")));
        obj.setSeason(this.seasonDao.getById(rs.getInt("price_season_id")));
        obj.setGuestType(rs.getString("price_guest_type"));
        obj.setPrice(rs.getFloat("price"));
        return obj;
    }

    public boolean deleteById(int id) {
        String query = "DELETE FROM public.price WHERE price_id = ?";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean deleteByRoomId(int roomId) {
        String query = "DELETE FROM public.price WHERE price_room_id = ?";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, roomId);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean deleteByHotelId(int hotelId) {
        String query = "DELETE FROM public.price WHERE price_hotel_id = ?";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, hotelId);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean deleteByPensionId(int pensionId) {
        String query = "DELETE FROM public.price WHERE price_pension_id = ?";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, pensionId);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean save(Price price) {
        String query = "INSERT INTO public.price " +
                "(" +
                "price_room_id, " +
                "price_hotel_id, " +
                "price_pension_id, " +
                "price_season_id, " +
                "price_guest_type, " +
                "price" +
                ")" +
                " VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, price.getRoom().getId());
            pr.setInt(2, price.getHotel().getId());
            pr.setInt(3, price.getPension().getId());
            pr.setInt(4, price.getSeason().getId());
            pr.setString(5, price.getGuestType());
            pr.setFloat(6, price.getPrice());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean update(Price price) {
        String query = "UPDATE public.price SET " +
                "price_room_id = ?, " +
                "price_hotel_id = ?, " +
                "price_pension_id = ?, " +
                "price_season_id = ?, " +
                "price_guest_type = ?, " +
                "price = ? " +
                "WHERE price_id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, price.getRoom().getId());
            pr.setInt(2, price.getHotel().getId());
            pr.setInt(3, price.getPension().getId());
            pr.setInt(4, price.getSeason().getId());
            pr.setString(5, price.getGuestType());
            pr.setFloat(6, price.getPrice());
            pr.setInt(7, price.getId());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
