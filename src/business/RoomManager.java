package business;

import core.Helper;
import dao.RoomDao;
import entity.Room;
import java.util.ArrayList;

public class RoomManager {
    private final RoomDao roomDao;

    public RoomManager() {
        this.roomDao = new RoomDao();
    }

    public ArrayList<Room> findAll() {
        return this.roomDao.findAll();
    }

    public Room getById(int id) {
        return this.roomDao.getById(id);
    }

    public ArrayList<Room> getByHotelId(int hotel_id) {
        return this.roomDao.getByHotelId(hotel_id);
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<Room> rooms) {
        ArrayList<Object[]> roomList = new ArrayList<>();
        for (Room room : rooms) {
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = room.getId();
            rowObject[i++] = room.getHotel().getName() + " - " + room.getHotel().getCity();
            rowObject[i++] = room.getRoomType().toString();
            rowObject[i++] = room.getBeds();
            rowObject[i++] = room.getArea();
            rowObject[i] = room.getStock();

            roomList.add(rowObject);
        }
        return roomList;
    }

    public ArrayList<Object[]> getForTable2(int size, ArrayList<Room> rooms) {
        ArrayList<Object[]> roomList = new ArrayList<>();
        for (Room room : rooms) {
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = room.getId();
            rowObject[i++] = room.getHotel().getName() + " - " + room.getHotel().getCity();
            rowObject[i++] = room.getHotel().getStar().getStarValue();
            rowObject[i++] = room.getRoomType().toString();
            rowObject[i++] = room.getBeds();
            rowObject[i++] = room.getArea();
            rowObject[i] = room.getStock();

            roomList.add(rowObject);
        }
        return roomList;
    }

    public boolean deleteById(int id) {
        if (this.getById(id) == null) {
            Helper.showMessage("Oda bulunamadı !");
            return false;
        }
        return this.roomDao.deleteById(id);
    }

    public boolean deleteByHotelId(int id) {
        if (this.getByHotelId(id).isEmpty()) {
            Helper.showMessage("Oda bulunamadı !");
            return false;
        }
        return this.roomDao.deleteByHotelId(id);
    }

    public boolean save(Room room) {
        if (this.getById(room.getId()) != null) {
            Helper.showMessage("error");
            return false;
        }
        return this.roomDao.save(room);
    }

    public boolean update(Room room) {
        if (this.getById(room.getId()) == null) {
            Helper.showMessage("Oda bulunamadı !");
            return false;
        }
        return this.roomDao.update(room);
    }

    public int newRoomId() {
        return this.roomDao.newRoomId();
    }
}