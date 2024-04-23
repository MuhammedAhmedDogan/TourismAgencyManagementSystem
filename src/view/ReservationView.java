package view;

import business.RoomManager;
import core.ComboItem;
import core.Helper;
import entity.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DateFormatter;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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
    private JLabel lbl_room_type;
    private Reservation reservation;
    private Object[] col_room;
    private final DefaultTableModel tmdl_room = new DefaultTableModel();
    private final RoomManager roomManager;
    private Room room;

    public ReservationView(Reservation reservation) {
        this.add(container);
        this.guiInitilaze(700, 950);
        this.roomManager = new RoomManager();
        this.reservation = reservation;
        this.room = this.reservation.getRoom();

        setFormatFields();
        loadRoomTable(null);
        loadRoomComponent();

        this.btn_search.addActionListener(e -> {
            if (Helper.isFieldListEmpty(new JTextField[]{this.fld_start_date, this.fld_end_date}) || this.cmb_city.getSelectedItem() == null) {
                Helper.showMessage("fill");
            } else {

            }
        });

        this.btn_save.addActionListener(e -> {
            if (Helper.isFieldListEmpty(new JTextField[]{this.fld_customer_name, this.fld_customer_id, this.fld_adults, this.fld_children, this.fld_start_date, this.fld_end_date}) || this.cmb_pension_type.getSelectedItem() == null) {
                Helper.showMessage("fill");
            } else {

            }
        });

    }

    public void loadRoomTable(ArrayList<Object[]> roomList) {
        this.col_room = new Object[]{"ID", "Otel Adı", "Yıldız", "Oda Tipi", "Yatak Sayısı", "Metrekare", "Boş Oda Sayısı"};
        if (roomList == null) {
            roomList = this.roomManager.getForTable2(this.col_room.length, this.roomManager.findAll());
        }
        createTable(this.tmdl_room, this.tbl_room, this.col_room, roomList);
        tbl_room.getColumnModel().getColumn(0).setMaxWidth(75);
        if (this.room != null) {
            this.loadInfo(this.room.getId());
        }

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
        this.lbl_room_type.setText("Oda Özellikleri (" + this.room.getRoomType() + ")");
        this.cmb_pension_type.removeAllItems();
        if (this.room.getHotel().isUltraPension())
            this.cmb_pension_type.addItem(Pension.PensionType.Ultra_Her_Sey_Dahil);
        if (this.room.getHotel().isAllInclusivePension())
            this.cmb_pension_type.addItem(Pension.PensionType.Her_Sey_Dahil);
        if (this.room.getHotel().isRoomBreakfastPension())
            this.cmb_pension_type.addItem(Pension.PensionType.Oda_Kahvalti);
        if (this.room.getHotel().isFullPension())
            this.cmb_pension_type.addItem(Pension.PensionType.Tam_Pansiyon);
        if (this.room.getHotel().isHalfPension())
            this.cmb_pension_type.addItem(Pension.PensionType.Yarim_Pansiyon);
        if (this.room.getHotel().isOnlyBedPension())
            this.cmb_pension_type.addItem(Pension.PensionType.Sadece_Yatak);
        if (this.room.getHotel().isFullCreditPension())
            this.cmb_pension_type.addItem(Pension.PensionType.Alkol_Haric_Full_Credit);
    }

    public void setFormatFields() {
        this.cmb_city.setModel(new DefaultComboBoxModel<>(City.values()));
        this.cmb_city.setSelectedItem(null);

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

                cmb_city.removeAllItems();
                for (City item : filteredItems) {
                    cmb_city.addItem(item);
                }
            }
        });

        this.fld_adults.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(NumberFormat.getIntegerInstance())));
        this.fld_children.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(NumberFormat.getIntegerInstance())));

        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        DateFormatter dateFormatter = new DateFormatter(dateFormat);
        DefaultFormatterFactory formatterFactory = new DefaultFormatterFactory(dateFormatter);
        this.fld_start_date.setFormatterFactory(formatterFactory);
        this.fld_end_date.setFormatterFactory(formatterFactory);

        if (this.reservation.getId() != 0) {
            this.fld_customer_name.setText(this.reservation.getCustomerName());
            this.fld_customer_id.setText(this.reservation.getCustomerId());
            this.fld_adults.setValue(this.reservation.getAdults());
            this.fld_children.setValue(this.reservation.getChildren());
            this.fld_start_date.setText(Helper.formatDate(this.reservation.getReservationStartDate()));
            this.fld_end_date.setText(Helper.formatDate(this.reservation.getReservationEndDate()));
            this.cmb_city.setSelectedItem(this.room.getHotel().getCity());
        }
    }
}
