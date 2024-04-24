package view;

import business.*;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    private JButton btn_cost;
    private Reservation reservation;
    private Object[] col_room;
    private final DefaultTableModel tmdl_room = new DefaultTableModel();
    private final RoomManager roomManager;
    private final SeasonManager seasonManager;
    private final PensionManager pensionManager;
    private final PriceManager priceManager;
    private final ReservationManager reservationManager;
    private Room room;
    private Room beforeUpdateRoom;

    public ReservationView(Reservation reservation) {
        this.add(container);
        this.guiInitilaze(800, 950);
        this.roomManager = new RoomManager();
        this.seasonManager = new SeasonManager();
        this.pensionManager = new PensionManager();
        this.priceManager = new PriceManager();
        this.reservationManager = new ReservationManager();
        this.reservation = reservation;
        this.room = this.reservation.getRoom();

        setFormatFields();
        loadRoomTable(null);
        loadRoomComponent();

        // Değerlendirme formu 16.
        if (this.reservation.getId() != 0) {
            this.beforeUpdateRoom = this.reservation.getRoom();
            loadInfo(this.reservation.getRoom().getId());
            this.lbl_cost.setText("Toplam Tutar : " + this.reservation.getCost() + " TL"); // Değerlendirme formu 17.
        }
        if (this.room != null)
            this.cmb_pension_type.setSelectedItem(this.reservation.getPension().getPensionType());

        // Değerlendirme formu 15. 19.
        this.btn_search.addActionListener(e -> {
            DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            if (Helper.isFieldListEmpty(new JTextField[]{this.fld_start_date, this.fld_end_date}) || this.cmb_city.getSelectedItem() == null) {
                Helper.showMessage("fill");
            } else {
                // Değerlendirme formu 19. 23. (Stok kontrol edilip arama sonucu ona göre getiriliyor)
                try {
                    if (dateFormat.parse(this.fld_end_date.getText()).getTime() - dateFormat.parse(this.fld_start_date.getText()).getTime() <= 0) {
                        Helper.showMessage("Hatalı tarih girdiniz !");
                    } else {
                        ArrayList<Room> roomSearchList = new ArrayList<>();
                        for (Room room1 : this.roomManager.findAll()) {
                            if (this.seasonManager.getForReservation(room1.getHotel().getId(), dateFormat.parse(this.fld_start_date.getText()), dateFormat.parse(this.fld_end_date.getText())) != null && room1.getHotel().getCity().equals(this.cmb_city.getSelectedItem())) {
                                if (room1.getStock() > this.reservationManager.getForStockControl(room1.getId(), dateFormat.parse(this.fld_start_date.getText()), dateFormat.parse(this.fld_end_date.getText())).size()) {
                                    roomSearchList.add(room1);
                                }
                            }
                        }
                        ArrayList<Object[]> roomList = this.roomManager.getForTable2(this.col_room.length, roomSearchList);
                        loadRoomTable(roomList);
                    }
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        // Değerlendirme formu 17.
        this.btn_cost.addActionListener(e -> {
            if (Helper.isFieldListEmpty(new JTextField[]{this.fld_customer_name, this.fld_customer_id, this.fld_adults, this.fld_children, this.fld_start_date, this.fld_end_date}) || this.cmb_pension_type.getSelectedItem() == null) {
                Helper.showMessage("fill");
            } else {
                this.setReservation();
                if (this.reservation.getSeason() == null) {
                    this.lbl_cost.setText("Toplam Tutar : -----");
                    Helper.showMessage("Hatalı bir tarih girdiniz !");

                } else {
                    this.lbl_cost.setText("Toplam Tutar : " + this.reservation.getCost() + " TL");
                }
            }
        });

        // Değerlendirme formu 18.
        this.btn_save.addActionListener(e -> {
            if (Helper.isFieldListEmpty(new JTextField[]{this.fld_customer_name, this.fld_customer_id, this.fld_adults, this.fld_children, this.fld_start_date, this.fld_end_date}) || this.cmb_pension_type.getSelectedItem() == null) {
                Helper.showMessage("fill");
            } else {
                boolean isRoomAvailable = false;
                ArrayList<Room> roomSearchList = new ArrayList<>();
                DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                try {
                    if (dateFormat.parse(this.fld_end_date.getText()).getTime() - dateFormat.parse(this.fld_start_date.getText()).getTime() <= 0) {
                        Helper.showMessage("Hatalı tarih girdiniz !");
                    } else {
                        for (Room room1 : this.roomManager.findAll()) {
                            if (this.seasonManager.getForReservation(room1.getHotel().getId(), dateFormat.parse(this.fld_start_date.getText()), dateFormat.parse(this.fld_end_date.getText())) != null) {
                                if (room1.getStock() > this.reservationManager.getForStockControl(room1.getId(), dateFormat.parse(this.fld_start_date.getText()), dateFormat.parse(this.fld_end_date.getText())).size()) {
                                    roomSearchList.add(room1);
                                }
                            }
                        }
                        // Değerlendirme formu 15. 19.
                        for (Room room2 : roomSearchList) {
                            if (room2.getId() == this.room.getId()) {
                                isRoomAvailable = true;
                            }
                        }
                        if (!isRoomAvailable) {
                            Helper.showMessage("Otele ait seçili oda tipinde girilen tarih için boş oda bulunmamaktadır !\nBoş oda ara butonunu kullanarak rezervasyona uygun odaları listeleyebilirsiniz.");
                        }
                    }
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }

                boolean result;
                this.setReservation();

                if (this.reservation.getSeason() == null) {
                    this.lbl_cost.setText("Toplam Tutar : -----");
                    Helper.showMessage("Hatalı bir tarih girdiniz !");
                    result = false;
                } else if (!isRoomAvailable) {
                    result = false;
                } else if (this.reservation.getId() != 0) {
                    result = this.reservationManager.update(this.reservation);
                } else {
                    result = this.reservationManager.save(this.reservation);
                }

                if (result) {
                    Helper.showMessage("done");
                    dispose();
                }
            }
        });

    }

    public void loadRoomTable(ArrayList<Object[]> roomList) {
        this.col_room = new Object[]{"ID", "Otel Adı", "Yıldız", "Oda Tipi", "Yatak Sayısı", "Metrekare", "Oda Sayısı"};
        if (roomList == null) {
            roomList = this.roomManager.getForTable2(this.col_room.length, this.roomManager.findAll());
        }
        createTable(this.tmdl_room, this.tbl_room, this.col_room, roomList);
        tbl_room.getColumnModel().getColumn(0).setMaxWidth(50);
        tbl_room.getColumnModel().getColumn(2).setMaxWidth(200);
        tbl_room.getColumnModel().getColumn(5).setMaxWidth(400);
        tbl_room.getColumnModel().getColumn(6).setMaxWidth(150);
    }

    // Değerlendirme formu 16.
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

    public void setReservation() {
        this.reservation.setRoom(this.room);
        this.reservation.setHotel(this.room.getHotel());
        this.reservation.setPension(this.pensionManager.getForReservation(this.room.getHotel().getId(), Objects.requireNonNull(this.cmb_pension_type.getSelectedItem()).toString()));
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        try {
            this.reservation.setSeason(this.seasonManager.getForReservation(this.room.getHotel().getId(), dateFormat.parse(this.fld_start_date.getText()), dateFormat.parse(this.fld_end_date.getText())));
            this.reservation.setReservationStartDate(dateFormat.parse(this.fld_start_date.getText()));
            this.reservation.setReservationEndDate(dateFormat.parse(this.fld_end_date.getText()));
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }

        this.reservation.setCustomerName(this.fld_customer_name.getText());
        this.reservation.setCustomerId(this.fld_customer_id.getText());
        this.reservation.setAdults(Integer.parseInt(this.fld_adults.getValue().toString()));
        this.reservation.setChildren(Integer.parseInt(this.fld_children.getValue().toString()));

        // Değerlendirme formu 17. (Fiyat Hesaplama)
        if (this.reservation.getSeason() != null) {
            int cost = 0;
            for (int i = 0; i < this.reservation.getAdults(); i++) {
                cost += this.priceManager.getPrice(this.room, this.reservation.getPension(), this.reservation.getSeason(), "'adult'");
            }
            for (int i = 0; i < this.reservation.getChildren(); i++) {
                cost += this.priceManager.getPrice(this.room, this.reservation.getPension(), this.reservation.getSeason(), "'child'");
            }

            long difference = this.reservation.getReservationEndDate().getTime() - this.reservation.getReservationStartDate().getTime();
            long daysBetween = difference / (1000 * 60 * 60 * 24);
            cost *= (int) daysBetween;
            this.reservation.setCost(cost);
        }
    }

    // Değerlendirme formu 16.
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