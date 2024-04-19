package view;

import business.HotelManager;
import business.PensionManager;
import business.RoomManager;
import business.SeasonManager;
import core.Helper;
import entity.Hotel;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

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
    private DefaultTableModel tmdl_hotel = new DefaultTableModel();
    private DefaultTableModel tmdl_room = new DefaultTableModel();
    private User user;
    private HotelManager hotelManager;
    private SeasonManager seasonManager;
    private PensionManager pensionManager;
    private RoomManager roomManager;
    private JPopupMenu hotel_menu = new JPopupMenu();

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

        this.btn_exit.addActionListener(e -> {
            dispose();
        });

        loadHotelTable();
        loadHotelComponent();

        loadRoomTable();
    }

    public void loadRoomTable() {
        Object[] col_room = {"ID", "Otel Adı", "Oda Tipi", "Yatak Sayısı", "Oda Alanı (Metrekare)", "Boş Oda Sayısı"};
        ArrayList<Object[]> roomList = roomManager.getForTable(col_room.length, this.roomManager.findAll());
        createTable(this.tmdl_room, this.tbl_room, col_room, roomList);
        tbl_room.getColumnModel().getColumn(0).setMaxWidth(75);
    }

    public void loadHotelTable() {
        Object[] col_hotel = {"ID", "Otel Adı", "Şehir", "Telefon", "eMail", "Yıldız"};
        ArrayList<Object[]> hotelList = this.hotelManager.getForTable(col_hotel.length, this.hotelManager.findAll());
        createTable(this.tmdl_hotel, this.tbl_hotel, col_hotel, hotelList);
        tbl_hotel.getColumnModel().getColumn(0).setMaxWidth(75);
        tbl_hotel.getColumnModel().getColumn(5).setMaxWidth(75);
    }

    public void loadHotelComponent() {
        tableRowSelect(this.tbl_hotel);

        this.hotel_menu = new JPopupMenu();
        this.hotel_menu.add("Güncelle").addActionListener(e -> {
            int selectHotelId = this.getTableSelectedRow(tbl_hotel, 0);
            HotelView hotelView = new HotelView(this.hotelManager.getById(selectHotelId));
            hotelView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotelTable();
                }
            });
        });
        this.hotel_menu.add("Sil").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectHotelId = this.getTableSelectedRow(tbl_hotel, 0);
                if (this.pensionManager.deleteByHotelId(selectHotelId) && this.seasonManager.deleteByHotelId(selectHotelId) && this.hotelManager.delete(selectHotelId)) {
                    Helper.showMessage("done");
                    loadHotelTable();
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
                    loadHotelTable();
                }
            });

        });
    }
}
