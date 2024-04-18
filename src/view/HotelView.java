package view;

import business.HotelManager;
import business.SeasonManager;
import core.Helper;
import entity.Hotel;
import entity.Season;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import javax.swing.text.DefaultFormatterFactory;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class HotelView extends Layout {
    private JPanel container;
    private JPanel pnl_top;
    private JPanel pnl_left;
    private JPanel pnl_right;
    private JLabel lbl_title;
    private JTextField fld_name;
    private JTextField fld_city;
    private JTextField fld_address;
    private JTextField fld_email;
    private JTextField fld_phone;
    private JComboBox<Hotel.Star> cmb_star;
    private JCheckBox check_carpark;
    private JCheckBox check_wifi;
    private JCheckBox check_pool;
    private JCheckBox check_fitness;
    private JCheckBox check_spa;
    private JCheckBox check_room_service;
    private JPanel pnl_bottom;
    private JCheckBox check_ultra_pension;
    private JCheckBox check_all_inclusive_pension;
    private JCheckBox check_room_breakfast_pension;
    private JCheckBox check_full_pension;
    private JCheckBox check_half_pension;
    private JCheckBox check_only_bed_pension;
    private JCheckBox check_full_credit_pension;
    private JButton btn_save;
    private JFormattedTextField fld_season1_start;
    private JFormattedTextField fld_season1_end;
    private JFormattedTextField fld_season2_start;
    private JFormattedTextField fld_season2_end;
    private Hotel hotel;
    private HotelManager hotelManager;
    private SeasonManager seasonManager;
    private ArrayList<Season> seasons;

    public HotelView(Hotel hotel) {
        this.add(container);
        this.guiInitilaze(500, 700);
        this.hotel = hotel;
        this.hotelManager = new HotelManager();
        this.seasonManager = new SeasonManager();
        this.seasons = seasonManager.getByHotelId(hotel.getId());

        this.cmb_star.setModel(new DefaultComboBoxModel<>(Hotel.Star.values()));
        this.cmb_star.setSelectedItem(null);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormatter dateFormatter = new DateFormatter(dateFormat);
        DefaultFormatterFactory formatterFactory = new DefaultFormatterFactory(dateFormatter);
        this.fld_season1_start.setFormatterFactory(formatterFactory);
        this.fld_season1_end.setFormatterFactory(formatterFactory);
        this.fld_season2_start.setFormatterFactory(formatterFactory);
        this.fld_season2_end.setFormatterFactory(formatterFactory);

        if (!seasons.isEmpty()) {
            this.fld_name.setText(hotel.getName());
            this.fld_city.setText(hotel.getCity());
            this.fld_address.setText(hotel.getAddress());
            this.fld_email.setText(hotel.getAddress());
            this.fld_phone.setText(hotel.getPhone());
            this.cmb_star.setSelectedItem(hotel.getStar());
            this.check_carpark.setSelected(hotel.isCarPark());
            this.check_wifi.setSelected(hotel.isWifi());
            this.check_pool.setSelected(hotel.isPool());
            this.check_fitness.setSelected(hotel.isFitness());
            this.check_spa.setSelected(hotel.isSpa());
            this.check_room_service.setSelected(hotel.isRoomService());
            this.check_ultra_pension.setSelected(hotel.isUltraPension());
            this.check_all_inclusive_pension.setSelected(hotel.isAllInclusivePension());
            this.check_room_breakfast_pension.setSelected(hotel.isRoomBreakfastPension());
            this.check_full_pension.setSelected(hotel.isFullPension());
            this.check_half_pension.setSelected(hotel.isHalfPension());
            this.check_only_bed_pension.setSelected(hotel.isOnlyBedPension());
            this.check_full_credit_pension.setSelected(hotel.isFullCreditPension());
            this.fld_season1_start.setText(seasons.getFirst().getStartDate().toString());
            this.fld_season1_end.setText(seasons.getFirst().getEndDate().toString());
            this.fld_season2_start.setText(seasons.getLast().getStartDate().toString());
            this.fld_season2_end.setText(seasons.getLast().getEndDate().toString());

        }

        this.btn_save.addActionListener(e -> {
            if (Helper.isFieldListEmpty(new JTextField[]{this.fld_name, this.fld_city, this.fld_address, this.fld_email, this.fld_phone, this.fld_season1_start, this.fld_season1_end, this.fld_season2_start, this.fld_season2_end}) || this.cmb_star.getSelectedItem() == null) {
                Helper.showMessage("fill");
            } else {
                boolean result;
                this.hotel.setName(this.fld_name.getText());
                this.hotel.setCity(this.fld_city.getText());
                this.hotel.setAddress(this.fld_address.getText());
                this.hotel.setEmail(this.fld_email.getText());
                this.hotel.setPhone(this.fld_phone.getText());
                this.hotel.setStar((Hotel.Star) cmb_star.getSelectedItem());
                this.hotel.setCarPark(this.check_carpark.isSelected());
                this.hotel.setWifi(this.check_wifi.isSelected());
                this.hotel.setPool(this.check_pool.isSelected());
                this.hotel.setFitness(this.check_fitness.isSelected());
                this.hotel.setSpa(this.check_spa.isSelected());
                this.hotel.setRoomService(this.check_room_service.isSelected());
                this.hotel.setUltraPension(this.check_ultra_pension.isSelected());
                this.hotel.setAllInclusivePension(this.check_all_inclusive_pension.isSelected());
                this.hotel.setRoomBreakfastPension(this.check_room_breakfast_pension.isSelected());
                this.hotel.setFullPension(this.check_full_pension.isSelected());
                this.hotel.setHalfPension(this.check_half_pension.isSelected());
                this.hotel.setOnlyBedPension(this.check_only_bed_pension.isSelected());
                this.hotel.setFullCreditPension(this.check_full_credit_pension.isSelected());

                if (this.hotel.getId() != 0) {
                    result = this.hotelManager.update(this.hotel);
                    try {
                        seasons.getFirst().setStartDate(dateFormat.parse(this.fld_season1_start.getText()));
                        seasons.getFirst().setEndDate(dateFormat.parse(this.fld_season1_end.getText()));
                        seasons.getLast().setStartDate(dateFormat.parse(this.fld_season2_start.getText()));
                        seasons.getLast().setEndDate(dateFormat.parse(this.fld_season2_end.getText()));
                    } catch (ParseException ex) {
                        throw new RuntimeException(ex);
                    }
                    result = this.seasonManager.update(this.seasons.getFirst()) && this.seasonManager.update(this.seasons.getLast());

                } else {
                    result = this.hotelManager.save(hotel);
                    this.hotel = this.hotelManager.getById(this.hotelManager.newHotelId());
                    this.seasons.clear();
                    try {
                        this.seasons.add(new Season(dateFormat.parse(this.fld_season1_start.getText()), dateFormat.parse(this.fld_season1_end.getText()), this.hotel));
                        this.seasons.add(new Season(dateFormat.parse(this.fld_season2_start.getText()), dateFormat.parse(this.fld_season2_end.getText()), this.hotel));
                    } catch (ParseException ex) {
                        throw new RuntimeException(ex);
                    }
                    result = this.seasonManager.save(seasons.getFirst()) && this.seasonManager.save(seasons.getLast());
                }

                if (result) {
                    Helper.showMessage("done");
                    dispose();
                } else {
                    Helper.showMessage("error");
                }
            }
        });

    }

}
