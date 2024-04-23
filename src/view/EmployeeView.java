package view;

import business.*;
import core.ComboItem;
import core.Helper;
import entity.*;

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
    private JComboBox<ComboItem> cmb_room_search_by_hotel;
    private JButton btn_hotel_filter_clear;
    private JTable tbl_reservation;
    private JPanel lbl_reservation_add;
    private JButton btn_reservation_add;
    private final DefaultTableModel tmdl_hotel = new DefaultTableModel();
    private final DefaultTableModel tmdl_room = new DefaultTableModel();
    private final DefaultTableModel tmdl_reservation = new DefaultTableModel();
    private User user;
    private HotelManager hotelManager;
    private SeasonManager seasonManager;
    private PensionManager pensionManager;
    private PriceManager priceManager;
    private RoomManager roomManager;
    private ReservationManager reservationManager;
    private JPopupMenu hotel_menu = new JPopupMenu();
    private JPopupMenu room_menu = new JPopupMenu();
    private JPopupMenu reservation_menu = new JPopupMenu();
    private Object[] col_hotel;
    private Object[] col_room;
    private Object[] col_reservation;

    public EmployeeView(User user) {
        this.add(container);
        this.guiInitilaze(1000, 700);
        this.user = user;
        this.hotelManager = new HotelManager();
        this.seasonManager = new SeasonManager();
        this.pensionManager = new PensionManager();
        this.roomManager = new RoomManager();
        this.priceManager = new PriceManager();
        this.reservationManager = new ReservationManager();

        this.lbl_welcome.setText("Hoşgeldiniz : " + this.user.getUsername() + " (" + this.user.getRole() + ")");

        this.btn_admin.setVisible(this.user.getRole() == User.Role.ADMIN);
        this.btn_admin.addActionListener(e -> {
            AdminView adminView = new AdminView(this.user);
            dispose();
        });

        this.btn_exit.addActionListener(e -> dispose());

        loadHotelTable(null);
        loadHotelComponent();

        loadRoomTable(null);
        loadRoomComponent();

        loadReservationTable(null);
        loadReservationComponent();
    }

    public void loadReservationTable(ArrayList<Object[]> reservationList) {
        this.col_reservation = new Object[]{"ID", "Müşteri İsmi", "Müşteri TC no", "Yetişkin", "Çocuk", "Otel", "Oda Tipi", "Pansiyon Tipi", "Giriş Tarihi", "Çıkış Tarihi", "Fiyat"};
        if (reservationList == null) {
            reservationList = this.reservationManager.getForTable(this.col_reservation.length, this.reservationManager.findAll());
        }
        createTable(this.tmdl_reservation, this.tbl_reservation, this.col_reservation, reservationList);
        tbl_reservation.getColumnModel().getColumn(0).setMaxWidth(50);
        tbl_reservation.getColumnModel().getColumn(3).setMaxWidth(60);
        tbl_reservation.getColumnModel().getColumn(4).setMaxWidth(50);
        tbl_reservation.getColumnModel().getColumn(8).setMaxWidth(100);
        tbl_reservation.getColumnModel().getColumn(9).setMaxWidth(100);
        tbl_reservation.getColumnModel().getColumn(10).setMaxWidth(200);
    }

    public void loadReservationComponent() {
        tableRowSelect(this.tbl_reservation);

        this.reservation_menu = new JPopupMenu();
        this.reservation_menu.add("Görüntüle - Güncelle").addActionListener(e -> {
            int selectReservationId = this.getTableSelectedRow(tbl_reservation, 0);
            ReservationView reservationView = new ReservationView(this.reservationManager.getById(selectReservationId));
            reservationView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadReservationTable(null);
                }
            });
        });
        this.reservation_menu.add("Rezervasyonu Sil").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectReservationId = this.getTableSelectedRow(tbl_reservation, 0);
                if (this.reservationManager.deleteById(selectReservationId)) {
                    Helper.showMessage("done");
                    loadReservationTable(null);
                } else {
                    Helper.showMessage("error");
                }
            }
        });
        this.tbl_reservation.setComponentPopupMenu(reservation_menu);

        this.btn_reservation_add.addActionListener(e -> {
            if (this.roomManager.findAll().isEmpty()) {
                Helper.showMessage("Rezervasyon eklemek için sistemde kayıtlı oda bulunmalıdır ! Lütfen önce oda ekleyiniz.");
            } else {
                ReservationView reservationView = new ReservationView(new Reservation());
                reservationView.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        loadReservationTable(null);
                    }
                });
            }
        });
    }

    public void loadRoomTable(ArrayList<Object[]> roomList) {
        this.col_room = new Object[]{"ID", "Otel Adı", "Oda Tipi", "Yatak Sayısı", "Oda Alanı (Metrekare)", "Boş Oda Sayısı"};
        if (roomList == null) {
            roomList = this.roomManager.getForTable(this.col_room.length, this.roomManager.findAll());
        }
        createTable(this.tmdl_room, this.tbl_room, this.col_room, roomList);
        tbl_room.getColumnModel().getColumn(0).setMaxWidth(75);
    }

    public void loadRoomComponent() {
        tableRowSelect(this.tbl_room);
        this.loadRoomFilterComboBox();

        this.room_menu = new JPopupMenu();
        this.room_menu.add("GÖrüntüle - Güncelle").addActionListener(e -> {
            int selectRoomId = this.getTableSelectedRow(tbl_room, 0);
            RoomView roomView = new RoomView(this.roomManager.getById(selectRoomId));
            roomView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadRoomTable(null);
                }
            });
        });
        this.room_menu.add("Odayı Sil").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectRoomId = this.getTableSelectedRow(tbl_room, 0);
                if (this.priceManager.deleteByRoomId(selectRoomId) && this.roomManager.deleteById(selectRoomId)) {
                    Helper.showMessage("done");
                    loadRoomTable(roomRowListBySearch());
                } else {
                    Helper.showMessage("error");
                }
            }
        });
        this.tbl_room.setComponentPopupMenu(room_menu);

        this.btn_room_add.addActionListener(e -> {
            if (this.hotelManager.findAll().isEmpty()) {
                Helper.showMessage("Oda eklemek için sistemde kayıtlı otel bulunmalıdır ! Lütfen önce otel ekleyiniz.");
            } else {
                RoomView roomView = new RoomView(new Room());
                roomView.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        loadRoomTable(null);
                    }
                });
            }

        });

        this.cmb_room_search_by_hotel.addActionListener(e -> loadRoomTable(roomRowListBySearch()));

        this.btn_hotel_filter_clear.addActionListener(e -> {
            this.cmb_room_search_by_hotel.setSelectedItem(null);
            loadRoomTable(null);
        });
    }

    public void loadHotelTable(ArrayList<Object[]> hotelList) {
        this.col_hotel = new Object[]{"ID", "Otel Adı", "Şehir", "Telefon", "eMail", "Yıldız"};
        if (hotelList == null) {
            hotelList = this.hotelManager.getForTable(col_hotel.length, this.hotelManager.findAll());
        }
        createTable(this.tmdl_hotel, this.tbl_hotel, this.col_hotel, hotelList);
        tbl_hotel.getColumnModel().getColumn(0).setMaxWidth(75);
        tbl_hotel.getColumnModel().getColumn(5).setMaxWidth(75);
    }

    public void loadHotelComponent() {
        loadHotelFilterComboBox();
        tableRowSelect(this.tbl_hotel);

        this.hotel_menu = new JPopupMenu();
        this.hotel_menu.add("Görüntüle - Güncelle").addActionListener(e -> {
            int selectHotelId = this.getTableSelectedRow(tbl_hotel, 0);
            HotelView hotelView = new HotelView(this.hotelManager.getById(selectHotelId));
            hotelView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotelTable(null);
                    loadRoomTable(null);
                    loadRoomFilterComboBox();
                }
            });
        });
        this.hotel_menu.add("Oteli Sil").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectHotelId = this.getTableSelectedRow(tbl_hotel, 0);
                if (!this.roomManager.getByHotelId(selectHotelId).isEmpty()) {
                    this.priceManager.deleteByHotelId(selectHotelId);
                    this.roomManager.deleteByHotelId(selectHotelId);
                }
                if (this.pensionManager.deleteByHotelId(selectHotelId) && this.seasonManager.deleteByHotelId(selectHotelId) && this.hotelManager.delete(selectHotelId)) {
                    Helper.showMessage("done");
                    loadHotelTable(hotelRowListBySearch());
                    loadRoomTable(null);
                    loadRoomFilterComboBox();
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
                    loadRoomTable(null);
                    loadRoomFilterComboBox();
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

    public void loadRoomFilterComboBox() {
        this.cmb_room_search_by_hotel.removeAllItems();
        for (Hotel hotel : this.hotelManager.findAll()) {
            this.cmb_room_search_by_hotel.addItem(new ComboItem(hotel.getId(), hotel.getName() + " - " + hotel.getCity()));
        }
        this.cmb_room_search_by_hotel.getModel().setSelectedItem(null);
    }


    public void loadHotelFilterComboBox() {
        this.cmb_city_search.setModel(new DefaultComboBoxModel<>(City.values()));
        this.cmb_city_search.setSelectedItem(null);

        JTextField searchField = new JTextField();
        searchField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

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
                for (City item : filteredItems) {
                    cmb_city_search.addItem(item);
                }
            }
        });
    }

    public ArrayList<Object[]> roomRowListBySearch() {
        if ((ComboItem) (this.cmb_room_search_by_hotel.getSelectedItem()) == null) {
            return this.roomManager.getForTable(this.col_room.length, this.roomManager.findAll());
        }
        ArrayList<Room> roomListBySearch = this.roomManager.getByHotelId(((ComboItem) (this.cmb_room_search_by_hotel.getSelectedItem())).getKey());
        return this.roomManager.getForTable(this.col_room.length, roomListBySearch);
    }

    public ArrayList<Object[]> hotelRowListBySearch() {
        ArrayList<Hotel> hotelListBySearch = this.hotelManager.getByCity((City) this.cmb_city_search.getSelectedItem());
        return this.hotelManager.getForTable(this.col_hotel.length, hotelListBySearch);
    }
}