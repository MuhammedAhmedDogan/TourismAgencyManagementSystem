package view;

import business.HotelManager;
import business.PensionManager;
import business.RoomManager;
import business.SeasonManager;
import core.Helper;
import entity.City;
import entity.Hotel;
import entity.Room;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class EmployeeView extends Layout {
    private JPanel container;
    private JLabel lbl_title;
    private JLabel lbl_welcome;
    private JButton btn_admin;
    private JButton btn_exit;
    private JTabbedPane tbd_employee;
    private JPanel pnl_room;
    private JPanel pnl_hotel;
    private JPanel pnl_reservation;
    private JScrollPane scrl_room;
    private JScrollPane scrl_hotel;
    private JScrollPane scrl_reservation;
    private JTable tbl_hotel;
    private JButton btn_hotel_add;
    private JPanel pnl_hotel_add;
    private JTable tbl_room;
    private JPanel pnl_room_add;
    private JButton btn_room_add;
    private JComboBox<City> cmb_city_search;
    private JLabel lbl_city_search;
    private JButton btn_city_clear;
    private JComboBox cmb_hotel_search;
    private JButton btn_hotel_fiilter_clear;
    private DefaultTableModel tmdl_hotel = new DefaultTableModel();
    private DefaultTableModel tmdl_room = new DefaultTableModel();
    private User user;
    private HotelManager hotelManager;
    private SeasonManager seasonManager;
    private PensionManager pensionManager;
    private RoomManager roomManager;
    private JPopupMenu hotel_menu = new JPopupMenu();
    private JPopupMenu room_menu = new JPopupMenu();
    private Object[] col_hotel;
    private Object[] col_room;

    public EmployeeView(User user) {
        this.add(container);
        this.guiInitilaze(1000, 700);
        this.user = user;
        this.hotelManager = new HotelManager();
        this.seasonManager = new SeasonManager();
        this.pensionManager = new PensionManager();
        this.roomManager = new RoomManager();

        this.lbl_welcome.setText("Hoşgeldiniz : " + this.user.getUsername() + " (" + this.user.getRole() + ")");

        this.btn_admin.setVisible(this.user.getRole() == User.Role.ADMIN);
        this.btn_admin.addActionListener(e -> {
            AdminView adminView = new AdminView(this.user);
            dispose();
        });

        this.btn_exit.addActionListener(e -> dispose());

        loadHotelTable(null);
        loadHotelComponent();

        loadRoomTable();
        loadRoomComponent();
    }

    public void loadRoomTable() {
        this.col_room = new Object[]{"ID", "Otel Adı", "Oda Tipi", "Yatak Sayısı", "Oda Alanı (Metrekare)", "Boş Oda Sayısı"};
        ArrayList<Object[]> roomList = roomManager.getForTable(col_room.length, this.roomManager.findAll());
        createTable(this.tmdl_room, this.tbl_room, col_room, roomList);
        tbl_room.getColumnModel().getColumn(0).setMaxWidth(75);
    }

    public void loadRoomComponent() {
        tableRowSelect(this.tbl_room);

        this.room_menu = new JPopupMenu();
        this.room_menu.add("Güncelle").addActionListener(e -> {
            int selectRoomId = this.getTableSelectedRow(tbl_room, 0);
            RoomView roomView = new RoomView(this.roomManager.getById(selectRoomId));
            roomView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadRoomTable();
                }
            });
        });
        this.room_menu.add("Sil").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectRoomId = this.getTableSelectedRow(tbl_room, 0);
                if (this.roomManager.deleteById(selectRoomId)) {
                    Helper.showMessage("done");
                    loadRoomTable();
                } else {
                    Helper.showMessage("error");
                }
            }
        });
        this.tbl_room.setComponentPopupMenu(room_menu);

        this.btn_room_add.addActionListener(e -> {
            RoomView roomView = new RoomView(new Room());
            roomView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadRoomTable();
                }
            });

        });
    }

    public void loadHotelTable(ArrayList<Object[]> hotelList) {
        this.col_hotel = new Object[]{"ID", "Otel Adı", "Şehir", "Telefon", "eMail", "Yıldız"};
        if (hotelList == null) {
            hotelList = this.hotelManager.getForTable(col_hotel.length, this.hotelManager.findAll());
        }
        createTable(this.tmdl_hotel, this.tbl_hotel, col_hotel, hotelList);
        tbl_hotel.getColumnModel().getColumn(0).setMaxWidth(75);
        tbl_hotel.getColumnModel().getColumn(5).setMaxWidth(75);
    }

    public void loadHotelComponent() {
        loadHotelFilter();

        tableRowSelect(this.tbl_hotel);

        this.hotel_menu = new JPopupMenu();
        this.hotel_menu.add("Güncelle").addActionListener(e -> {
            int selectHotelId = this.getTableSelectedRow(tbl_hotel, 0);
            HotelView hotelView = new HotelView(this.hotelManager.getById(selectHotelId));
            hotelView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotelTable(hotelRowListBySearch());
                    loadRoomTable();
                }
            });
        });
        this.hotel_menu.add("Sil").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectHotelId = this.getTableSelectedRow(tbl_hotel, 0);
                if (this.pensionManager.deleteByHotelId(selectHotelId) && this.seasonManager.deleteByHotelId(selectHotelId) && this.roomManager.deleteByHotelId(selectHotelId) && this.hotelManager.delete(selectHotelId)) {
                    Helper.showMessage("done");
                    loadHotelTable(hotelRowListBySearch());
                    loadRoomTable();
                } else {
                    Helper.showMessage("error");
                }
            }
        });
        this.tbl_hotel.setComponentPopupMenu(hotel_menu);

        this.btn_hotel_add.addActionListener(e -> {
            HotelView hotelView = new HotelView(new Hotel());
            hotelView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotelTable(null);
                    cmb_city_search.setSelectedItem(null);
                }
            });
        });

        this.cmb_city_search.addActionListener(e -> loadHotelTable(hotelRowListBySearch()));

        this.btn_city_clear.addActionListener(e -> {
            this.cmb_city_search.setSelectedItem(null);
            loadHotelTable(null);
        });
    }

    public void loadHotelFilter() {
        this.cmb_city_search.setModel(new DefaultComboBoxModel<>(City.values()));
        this.cmb_city_search.setSelectedItem(null);

        JTextField searchField = new JTextField();
        searchField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {
                String query = searchField.getText().toLowerCase();
                List<City> filteredItems = new ArrayList<>();
                for (City item : City.values()) {
                    if (item.toString().startsWith(query)) {
                        filteredItems.add(item);
                    }
                }

                cmb_city_search.removeAllItems();
                for (City item : filteredItems){
                    cmb_city_search.addItem(item);
                }
            }
        });
    }

    public ArrayList<Object[]> hotelRowListBySearch() {
        ArrayList<Hotel> userListBySearch = this.hotelManager.getByCity((City) this.cmb_city_search.getSelectedItem());
        return this.hotelManager.getForTable(this.col_hotel.length, userListBySearch);
    }
}
