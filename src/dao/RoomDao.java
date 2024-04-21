package dao;

import core.Db;
import entity.Room;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoomDao {
    private final Connection con;
    private final HotelDao hotelDao;

    public RoomDao() {
        this.con = Db.getInstance();
        this.hotelDao = new HotelDao();
    }

    public ArrayList<Room> selectByQuery(String query) {
        ArrayList<Room> roomList = new ArrayList<>();
        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while (rs.next()) {
                roomList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomList;
    }

    public ArrayList<Room> findAll() {
        return selectByQuery("SELECT * FROM public.room ORDER BY room_hotel_id ASC");
    }

    public Room getById(int id) {
        Room obj = null;
        String query = "SELECT * FROM public.room WHERE room_id = ?";
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

    public ArrayList<Room> getByHotelId(int hotelId) {
        return selectByQuery("SELECT * FROM public.room WHERE room_hotel_id = " + hotelId + " ORDER BY room_id");
    }

    public Room match(ResultSet rs) throws SQLException {
        Room obj = new Room();
        obj.setId(rs.getInt("room_id"));
        obj.setHotel(this.hotelDao.getById(rs.getInt("room_hotel_id")));
        obj.setRoomType(Room.RoomType.valueOf(rs.getString("room_type")));
        obj.setBeds(rs.getInt("room_beds"));
        obj.setArea(rs.getInt("room_area"));
        obj.setTv(rs.getBoolean("tv"));
        obj.setMinibar(rs.getBoolean("minibar"));
        obj.setGameConsole(rs.getBoolean("game_console"));
        obj.setSafe(rs.getBoolean("safe"));
        obj.setProjection(rs.getBoolean("projection"));
        obj.setStock(rs.getInt("stock"));
        return obj;
    }

    public boolean deleteById(int id) {
        String query = "DELETE FROM public.room WHERE room_id = ?";
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
        String query = "DELETE FROM public.room WHERE room_hotel_id = ?";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean save(Room room) {
        String query = "INSERT INTO public.room " +
                "(" +
                "room_hotel_id, " +
                "room_type, " +
                "room_beds, " +
                "room_area, " +
                "tv, " +
                "minibar, " +
                "game_console, " +
                "safe, " +
                "projection, " +
                "stock" +
                ")" +
                " VALUES (?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, room.getHotel().getId());
            pr.setString(2, room.getRoomType().toString());
            pr.setInt(3, room.getBeds());
            pr.setInt(4, room.getArea());
            pr.setBoolean(5, room.isTv());
            pr.setBoolean(6, room.isMinibar());
            pr.setBoolean(7, room.isGameConsole());
            pr.setBoolean(8, room.isSafe());
            pr.setBoolean(9, room.isProjection());
            pr.setInt(10, room.getStock());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean update(Room room) {
        String query = "UPDATE public.room SET " +
                "room_hotel_id = ?, " +
                "room_type = ?, " +
                "room_beds = ?, " +
                "room_area = ?, " +
                "tv = ?, " +
                "minibar = ?, " +
                "game_console = ?, " +
                "safe = ?, " +
                "projection = ?, " +
                "stock = ? " +
                "WHERE room_id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, room.getHotel().getId());
            pr.setString(2, room.getRoomType().toString());
            pr.setInt(3, room.getBeds());
            pr.setInt(4, room.getArea());
            pr.setBoolean(5, room.isTv());
            pr.setBoolean(6, room.isMinibar());
            pr.setBoolean(7, room.isGameConsole());
            pr.setBoolean(8, room.isSafe());
            pr.setBoolean(9, room.isProjection());
            pr.setInt(10, room.getStock());
            pr.setInt(11, room.getId());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
