package dao;

import core.Db;
import entity.Reservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class ReservationDao {
    private final Connection con;
    private final RoomDao roomDao;
    private final HotelDao hotelDao;
    private final PensionDao pensionDao;
    private final SeasonDao seasonDao;
    private final PriceDao priceDao;

    public ReservationDao() {
        this.con = Db.getInstance();
        this.roomDao = new RoomDao();
        this.hotelDao = new HotelDao();
        this.pensionDao = new PensionDao();
        this.seasonDao = new SeasonDao();
        this.priceDao = new PriceDao();
    }

    public ArrayList<Reservation> selectByQuery(String query) {
        ArrayList<Reservation> reservationList = new ArrayList<>();
        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while (rs.next()) {
                reservationList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservationList;
    }

    public ArrayList<Reservation> findAll() {
        return selectByQuery("SELECT * FROM public.reservation ORDER BY reservation_id ASC");
    }

    public ArrayList<Reservation> getForStockControl(int roomId, Date startDate,Date endDate ) {
        ArrayList<Reservation> reservations = new ArrayList<>();
        String query = "SELECT * FROM public.reservation WHERE reservation_room_id = ? AND reservation_start_date < ? AND reservation_end_date > ? ";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, roomId);
            java.sql.Date sqlDateStart = new java.sql.Date(startDate.getTime());
            java.sql.Date sqlDateEnd = new java.sql.Date(endDate.getTime());
            pr.setDate(2, sqlDateEnd);
            pr.setDate(3, sqlDateStart);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                reservations.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    public Reservation getById(int id) {
        Reservation obj = null;
        String query = "SELECT * FROM public.reservation WHERE reservation_id = ?";
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

    public ArrayList<Reservation> getByHotelId(int hotelId) {
        return selectByQuery("SELECT * FROM public.reservation WHERE reservation_hotel_id = " + hotelId + " ORDER BY reservation_id");
    }

    public ArrayList<Reservation> getByRoomId(int roomId) {
        return selectByQuery("SELECT * FROM public.reservation WHERE reservation_room_id = " + roomId + " ORDER BY reservation_id");
    }

    public ArrayList<Reservation> getByPensionId(int pensionId) {
        return selectByQuery("SELECT * FROM public.reservation WHERE reservation_pension_id = " + pensionId + " ORDER BY reservation_id");
    }

    public Reservation match(ResultSet rs) throws SQLException {
        Reservation obj = new Reservation();
        obj.setId(rs.getInt("reservation_id"));
        obj.setRoom(this.roomDao.getById(rs.getInt("reservation_room_id")));
        obj.setHotel(this.hotelDao.getById(rs.getInt("reservation_hotel_id")));
        obj.setPension(this.pensionDao.getById(rs.getInt("reservation_pension_id")));
        obj.setSeason(this.seasonDao.getById(rs.getInt("reservation_season_id")));
        obj.setCustomerName(rs.getString("customer_name"));
        obj.setCustomerId(rs.getString("customer_id"));
        obj.setAdults(rs.getInt("adults"));
        obj.setChildren(rs.getInt("children"));
        obj.setReservationStartDate(rs.getDate("reservation_start_date"));
        obj.setReservationEndDate(rs.getDate("reservation_end_date"));
        obj.setCost(rs.getFloat("cost"));
        return obj;
    }

    public boolean deleteId(int id) {
        String query = "DELETE FROM public.reservation WHERE reservation_id = ?";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean deleteByHotelId(int hotelId) {
        String query = "DELETE FROM public.reservation WHERE reservation_hotel_id = ?";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, hotelId);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean deleteByRoomId(int roomId) {
        String query = "DELETE FROM public.reservation WHERE reservation_room_id = ?";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, roomId);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean deleteByPensionId(int pensionId) {
        String query = "DELETE FROM public.reservation WHERE reservation_pension_id = ?";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, pensionId);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean save(Reservation reservation) {
        String query = "INSERT INTO public.reservation " +
                "(" +
                "reservation_room_id, " +
                "reservation_hotel_id, " +
                "reservation_pension_id, " +
                "reservation_season_id, " +
                "customer_name, " +
                "customer_id, " +
                "adults, " +
                "children, " +
                "reservation_start_date, " +
                "reservation_end_date, " +
                "cost" +
                ")" +
                " VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, reservation.getRoom().getId());
            pr.setInt(2, reservation.getHotel().getId());
            pr.setInt(3, reservation.getPension().getId());
            pr.setInt(4, reservation.getSeason().getId());
            pr.setString(5, reservation.getCustomerName());
            pr.setString(6, reservation.getCustomerId());
            pr.setInt(7, reservation.getAdults());
            pr.setInt(8, reservation.getChildren());

            java.sql.Date sqlDateStart = new java.sql.Date(reservation.getReservationStartDate().getTime());
            java.sql.Date sqlDateEnd = new java.sql.Date(reservation.getReservationEndDate().getTime());
            pr.setDate(9, sqlDateStart);
            pr.setDate(10, sqlDateEnd);
            pr.setFloat(11, reservation.getCost());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean update(Reservation reservation) {
        String query = "UPDATE public.reservation SET " +
                "reservation_room_id = ?, " +
                "reservation_hotel_id = ?, " +
                "reservation_pension_id = ?, " +
                "reservation_season_id = ?, " +
                "customer_name = ?, " +
                "customer_id = ?, " +
                "adults = ?, " +
                "children = ?, " +
                "reservation_start_date = ?, " +
                "reservation_end_date = ?, " +
                "cost = ? " +
                "WHERE reservation_id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, reservation.getRoom().getId());
            pr.setInt(2, reservation.getHotel().getId());
            pr.setInt(3, reservation.getPension().getId());
            pr.setInt(4, reservation.getSeason().getId());
            pr.setString(5, reservation.getCustomerName());
            pr.setString(6, reservation.getCustomerId());
            pr.setInt(7, reservation.getAdults());
            pr.setInt(8, reservation.getChildren());

            java.sql.Date sqlDateStart = new java.sql.Date(reservation.getReservationStartDate().getTime());
            java.sql.Date sqlDateEnd = new java.sql.Date(reservation.getReservationEndDate().getTime());
            pr.setDate(9, sqlDateStart);
            pr.setDate(10, sqlDateEnd);
            pr.setFloat(11, reservation.getCost());
            pr.setInt(12, reservation.getId());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
