package view;

import entity.Room;

import javax.swing.*;

public class RoomView extends Layout {
    private JPanel container;
    private JPanel pnl_top;
    private JLabel fld_title;
    private JPanel pnl_left;
    private JPanel pnl_right;
    private JPanel pnl_bottom;
    private JLabel lbl_hotel;
    private JComboBox cmb_hotel;
    private JLabel lbl_room_type;
    private JComboBox cmb_room_type;
    private JLabel lbl_beds;
    private JFormattedTextField fld_beds;
    private JLabel lbl_area;
    private JFormattedTextField fld_area;
    private JLabel lbl_stock;
    private JFormattedTextField fld_stock;
    private JCheckBox chck_tv;
    private JCheckBox chck_minibar;
    private JCheckBox chck_game_console;
    private JCheckBox chck_safe;
    private JCheckBox chck_projection;
    private JLabel lbl_season_1;
    private JFormattedTextField fld_ultra_adult_1;
    private JFormattedTextField fld_ultra_child_1;
    private JLabel lbl_ultra_1;
    private JFormattedTextField fld_all_inclusive_adult_1;
    private JFormattedTextField fld_all_inclusive_child_1;
    private JLabel lbl_room_breakfast_1;
    private JLabel lbl_all_inclusive_1;
    private JFormattedTextField fld_room_breakfast_adult_1;
    private JFormattedTextField fld_room_breakfast_child_1;
    private JLabel lbl_full_pension_1;
    private JFormattedTextField fld_full_pension_adult_1;
    private JFormattedTextField fld_full_pension_child_1;
    private JLabel lbl_half_pension_1;
    private JFormattedTextField fld_half_pension_adult_1;
    private JFormattedTextField fld_half_pension_child_1;
    private JLabel lbl_only_bed_1;
    private JFormattedTextField fld_only_bed_adult_1;
    private JFormattedTextField fld_only_bed_child_1;
    private JLabel lbl_full_credit_1;
    private JFormattedTextField fld_full_credit_adult_1;
    private JFormattedTextField fld_full_credit_chid_1;
    private JLabel lbl_season_2;
    private JLabel lbl_ultra_2;
    private JFormattedTextField fld_ultra_adult_2;
    private JFormattedTextField fld_ultra_child_2;
    private JLabel lbl_all_inclusive_2;
    private JFormattedTextField fld_all_inclusive_adult_2;
    private JFormattedTextField fld_all_inclusive_child_2;
    private JLabel lbl_room_breakfast_2;
    private JFormattedTextField fld_room_breakfast_adult_2;
    private JFormattedTextField fld_room_breakfast_child_2;
    private JLabel lbl_full_pension_2;
    private JFormattedTextField fld_full_pension_adult_2;
    private JFormattedTextField fld_full_pension_child_2;
    private JLabel lbl_half_pension_2;
    private JFormattedTextField fld_half_pension_adult_2;
    private JFormattedTextField fld_half_pension_child_2;
    private JLabel lbl_only_bed_2;
    private JFormattedTextField fld_only_bed_adult_2;
    private JFormattedTextField fld_only_bed_child_2;
    private JLabel lbl_full_credit_2;
    private JFormattedTextField fld_full_credit_adult_2;
    private JFormattedTextField fld_full_credit_child_2;
    private JButton btn_save;
    private Room room;

    public RoomView(Room room) {
        this.add(container);
        this.guiInitilaze(600, 950);
        this.room = room;
    }
}
