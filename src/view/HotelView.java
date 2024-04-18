package view;

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

    public HotelView(Hotel hotel) {
        this.add(container);
        this.guiInitilaze(500, 700);
        this.hotel = hotel;

        this.cmb_star.setModel(new DefaultComboBoxModel<>(Hotel.Star.values()));
        this.cmb_star.setSelectedItem(null);

    }

}
