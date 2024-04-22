package business;

import core.Helper;
import dao.ReservationDao;
import entity.Hotel;
import entity.Reservation;

import java.util.ArrayList;

public class ReservationManager {
    private final ReservationDao reservationDao;

    public ReservationManager() {
        this.reservationDao = new ReservationDao();
    }

    public ArrayList<Reservation> findAll() {
        return this.reservationDao.findAll();
    }

    public Reservation getById(int id) {
        return this.reservationDao.getById(id);
    }

    public ArrayList<Reservation> getByHotelId(int hotelId) {
        return this.reservationDao.getByHotelId(hotelId);
    }

    public ArrayList<Reservation> getByRoomId(int roomId) {
        return this.reservationDao.getByRoomId(roomId);
    }

    public ArrayList<Reservation> getByPensionId(int pensionId) {
        return this.reservationDao.getByPensionId(pensionId);
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<Reservation> reservations) {
        ArrayList<Object[]> reservationList = new ArrayList<>();
        for (Reservation reservation : reservations) {
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = reservation.getId();
            rowObject[i++] = reservation.getCustomerName();
            rowObject[i++] = reservation.getCustomerId();
            rowObject[i++] = reservation.getAdults();
            rowObject[i++] = reservation.getChildren();
            rowObject[i++] = reservation.getHotel().getName() + " - " + reservation.getHotel().getCity();
            rowObject[i++] = reservation.getRoom().getRoomType();
            rowObject[i++] = reservation.getPension().getPensionType();
            rowObject[i++] = Helper.formatDate(reservation.getReservationStartDate());
            rowObject[i++] = Helper.formatDate(reservation.getReservationEndDate());
            rowObject[i] = reservation.getCost();

            reservationList.add(rowObject);
        }
        return reservationList;
    }

    public boolean deleteId(int id) {
        if (this.getById(id) == null) {
            Helper.showMessage("Rezervasyon bulunamadı !");
            return false;
        }
        return this.reservationDao.deleteId(id);
    }

    public boolean deleteByHotelId(int hotelId) {
        return this.reservationDao.deleteByHotelId(hotelId);
    }

    public boolean deleteByRoomId(int roomId) {
        return this.reservationDao.deleteByRoomId(roomId);
    }

    public boolean deleteByPensionId(int pensionId) {
        return this.reservationDao.deleteByPensionId(pensionId);
    }

    public boolean save(Reservation reservation) {
        if (this.getById(reservation.getId()) != null) {
            Helper.showMessage("error");
            return false;
        }
        return this.reservationDao.save(reservation);
    }

    public boolean update(Reservation reservation) {
        if (this.getById(reservation.getId()) == null) {
            Helper.showMessage("Rezervasyon bulunamadı !");
            return false;
        }
        return this.reservationDao.update(reservation);
    }
}
