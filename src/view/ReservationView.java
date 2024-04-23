package view;

import business.RoomManager;
import entity.City;
import entity.Pension;
import entity.Reservation;
import entity.Room;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ReservationView extends Layout {
    private JPanel container;
    private JPanel pnl_top;
    private JLabel lbl_title;
    private JPanel pnl_right;
    private JCheckBox check_carpark;
    private JCheckBox check_wifi;
    private JCheckBox check_pool;
    private JCheckBox check_fitness;
    private JCheckBox check_spa;
    private JCheckBox check_room_service;
    private JCheckBox chck_tv;
    private JCheckBox chck_minibar;
    private JCheckBox chck_game_console;
    private JCheckBox chck_safe;
    private JCheckBox chck_projection;
    private JPanel pnl_left;
    private JLabel lbl_start_date;
    private JLabel lbl_end_date;
    private JLabel lbl_city_filter;
    private JFormattedTextField fld_start_date;
    private JFormattedTextField fld_end_date;
    private JComboBox<City> cmb_city;
    private JTable tbl_room;
    private JPanel pnl_room_list;
    private JScrollPane scrl_room_list;
    private JLabel lbl_room_title;
    private JButton btn_search;
    private JTextField fld_customer_name;
    private JTextField fld_customer_id;
    private JFormattedTextField fld_adults;
    private JFormattedTextField fld_children;
    private JLabel lbl_hotel_name;
    private JLabel lbl_city_name;
    private JLabel lbl_star;
    private JLabel lbl_address;
    private JLabel lbl_phone;
    private JLabel lbl_email;
    private JButton btn_save;
    private JComboBox<Pension.PensionType> cmb_pension_type;
    private JLabel lbl_cost;
    private Reservation reservation;
    private Object[] col_room;
    private final DefaultTableModel tmdl_room = new DefaultTableModel();
    private final RoomManager roomManager;
    private Room room;

    public ReservationView(Reservation reservation) {
        this.add(container);
        this.guiInitilaze(700, 950);
        this.reservation = reservation;

        this.roomManager = new RoomManager();
        loadRoomTable(null);
        loadRoomComponent();
    }

    public void loadRoomTable(ArrayList<Object[]> roomList) {
        this.col_room = new Object[]{"ID", "Otel Adı", "Yıldız", "Oda Tipi", "Yatak Sayısı", "Metrekare", "Boş Oda Sayısı"};
        if (roomList == null) {
            roomList = this.roomManager.getForTable2(this.col_room.length, this.roomManager.findAll());
        }
        createTable(this.tmdl_room, this.tbl_room, this.col_room, roomList);
        tbl_room.getColumnModel().getColumn(0).setMaxWidth(75);
    }

    public void loadRoomComponent() {
        tbl_room.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selected_row = tbl_room.rowAtPoint(e.getPoint());
                tbl_room.setRowSelectionInterval(selected_row, selected_row);
                int selectRoomId = getTableSelectedRow(tbl_room, 0);
                loadInfo(selectRoomId);
            }
        });
    }

    public void loadInfo(int roomId) {
        this.room = roomManager.getById(roomId);
        this.lbl_hotel_name.setText("Otel Adı : " + this.room.getHotel().getName());
        this.lbl_star.setText("Yıldız : " + this.room.getHotel().getStar().getStarValue());
        this.lbl_city_name.setText("Şehir : " + this.room.getHotel().getCity().getName());
        this.lbl_address.setText("Adres : " + this.room.getHotel().getAddress());
        this.lbl_phone.setText("Telefon : " + this.room.getHotel().getPhone());
        this.lbl_email.setText("email : " + this.room.getHotel().getEmail());
        this.check_carpark.setSelected(this.room.getHotel().isCarPark());
        this.check_wifi.setSelected(this.room.getHotel().isWifi());
        this.check_pool.setSelected(this.room.getHotel().isPool());
        this.check_fitness.setSelected(this.room.getHotel().isFitness());
        this.check_spa.setSelected(this.room.getHotel().isSpa());
        this.check_room_service.setSelected(this.room.getHotel().isRoomService());
        this.chck_tv.setSelected(this.room.isTv());
        this.chck_minibar.setSelected(this.room.isMinibar());
        this.chck_game_console.setSelected(this.room.isGameConsole());
        this.chck_safe.setSelected(this.room.isSafe());
        this.chck_projection.setSelected(this.room.isProjection());

    }
}
