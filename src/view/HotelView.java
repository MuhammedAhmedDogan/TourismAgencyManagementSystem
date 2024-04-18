package view;

import business.HotelManager;
import core.Helper;
import entity.Hotel;

import javax.swing.*;

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
    private JTextField fld_first_season_start;
    private JTextField fld_first_season_end;
    private JTextField fld_second_season_start;
    private JTextField fld_second_season_end;
    private JCheckBox check_ultra_pension;
    private JCheckBox check_all_inclusive_pension;
    private JCheckBox check_room_breakfast_pension;
    private JCheckBox check_full_pension;
    private JCheckBox check_half_pension;
    private JCheckBox check_only_bed_pension;
    private JCheckBox check_full_credit_pension;
    private JButton btn_save;
    private Hotel hotel;
    private HotelManager hotelManager;

    public HotelView(Hotel hotel) {
        this.add(container);
        this.guiInitilaze(500, 700);
        this.hotel = hotel;
        this.hotelManager = new HotelManager();

        this.cmb_star.setModel(new DefaultComboBoxModel<>(Hotel.Star.values()));
        this.cmb_star.setSelectedItem(null);

        this.btn_save.addActionListener(e -> {
            if (Helper.isFieldListEmpty(new JTextField[]{this.fld_name, this.fld_city, this.fld_address, this.fld_email, this.fld_phone, this.fld_first_season_start, this.fld_first_season_end, this.fld_second_season_start, this.fld_second_season_end}) || this.cmb_star.getSelectedItem() == null) {
                Helper.showMessage("fill");
            } else {
                boolean result;
                this.hotel.setName(this.fld_name.getText());
                this.hotel.setCity(this.fld_city.getText());
                this.hotel.setAddress(this.fld_address.getText());
                this.hotel.setEmail(this.fld_email.getText());
                this.hotel.setPhone(this.fld_phone.getText());
                this.hotel.setStar((Hotel.Star) this.cmb_star.getSelectedItem());
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
                    result = true;
                    //result = this.hotelManager.update(this.hotel);
                } else {
                    result = this.hotelManager.save(this.hotel);
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
