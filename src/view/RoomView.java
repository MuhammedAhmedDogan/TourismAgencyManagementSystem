package view;

import business.*;
import core.ComboItem;
import core.Helper;
import entity.*;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import java.text.NumberFormat;
import java.util.ArrayList;

public class RoomView extends Layout {
    private JPanel container;
    private JPanel pnl_top;
    private JLabel fld_title;
    private JPanel pnl_left;
    private JPanel pnl_right;
    private JPanel pnl_bottom;
    private JLabel lbl_hotel;
    private JComboBox<ComboItem> cmb_hotel;
    private JLabel lbl_room_type;
    private JComboBox<Room.RoomType> cmb_room_type;
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
    private JFormattedTextField fld_full_credit_child_1;
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
    private JLabel tl_ultra_1;
    private JLabel tl_ultra_2;
    private JLabel tl_ultra_3;
    private JLabel tl_ultra_4;
    private JLabel tl_all_inclusive_1;
    private JLabel tl_all_inclusive_2;
    private JLabel tl_all_inclusive_3;
    private JLabel tl_all_inclusive_4;
    private JLabel tl_room_breakfast_1;
    private JLabel tl_room_breakfast_2;
    private JLabel tl_room_breakfast_3;
    private JLabel tl_room_breakfast_4;
    private JLabel tl_full_pension_1;
    private JLabel tl_full_pension_2;
    private JLabel tl_full_pension_3;
    private JLabel tl_full_pension_4;
    private JLabel tl_half_pension_1;
    private JLabel tl_half_pension_2;
    private JLabel tl_half_pension_3;
    private JLabel tl_half_pension_4;
    private JLabel tl_only_bed_1;
    private JLabel tl_only_bed_2;
    private JLabel tl_only_bed_3;
    private JLabel tl_only_bed_4;
    private JLabel tl_full_credit_1;
    private JLabel tl_full_credit_2;
    private JLabel tl_full_credit_3;
    private JLabel tl_full_credit_4;
    private Room room;
    private Hotel hotel;
    private RoomManager roomManager;
    private HotelManager hotelManager;
    private SeasonManager seasonManager;
    private PensionManager pensionManager;
    private PriceManager priceManager;
    private ArrayList<Season> seasons;
    private ArrayList<Pension> pensions;
    private ArrayList<Price> prices;

    public RoomView(Room room) {
        this.add(container);
        this.guiInitilaze(600, 950);
        this.room = room;
        this.roomManager = new RoomManager();
        this.hotelManager = new HotelManager();
        this.seasonManager = new SeasonManager();
        this.pensionManager = new PensionManager();
        this.priceManager = new PriceManager();

        this.cmb_room_type.setModel(new DefaultComboBoxModel<>(Room.RoomType.values()));
        for (Hotel hotel : this.hotelManager.findAll()) {
            this.cmb_hotel.addItem(new ComboItem(hotel.getId(), hotel.getName() + " - " + hotel.getCity()));
        }

        this.hotel = this.hotelManager.getById(((ComboItem) this.cmb_hotel.getSelectedItem()).getKey());

        if (this.room.getId() != 0) {
            this.hotel = this.room.getHotel();
            this.cmb_room_type.setSelectedItem(room.getRoomType());
            ComboItem defaultHotel = new ComboItem(this.room.getHotel().getId(), this.room.getHotel().getName() + " - " + this.room.getHotel().getCity());
            this.cmb_hotel.getModel().setSelectedItem(defaultHotel);
            this.fld_beds.setValue(this.room.getBeds());
            this.fld_area.setValue(this.room.getArea());
            this.fld_stock.setValue(this.room.getStock());
            this.chck_tv.setSelected(this.room.isTv());
            this.chck_minibar.setSelected(this.room.isMinibar());
            this.chck_game_console.setSelected(this.room.isGameConsole());
            this.chck_safe.setSelected(this.room.isSafe());
            this.chck_projection.setSelected(this.room.isProjection());
            this.seasons = this.seasonManager.getByHotelId(this.hotel.getId());
            this.pensions = this.pensionManager.getByHotelId(this.hotel.getId());
            this.setCurrentPrices();
        }
        this.seasons = this.seasonManager.getByHotelId(this.hotel.getId());
        this.pensions = this.pensionManager.getByHotelId(this.hotel.getId());
        this.prices = this.priceManager.getByRoomId(this.room.getId());
        this.setFormatFields();

        this.cmb_hotel.addActionListener(e -> {
            this.hotel = this.hotelManager.getById(((ComboItem) this.cmb_hotel.getSelectedItem()).getKey());
            this.seasons = this.seasonManager.getByHotelId(this.hotel.getId());
            this.pensions = this.pensionManager.getByHotelId(this.hotel.getId());
            this.setFormatFields();
        });

        this.btn_save.addActionListener(e -> {
            if (Helper.isFieldListEmpty(new JTextField[]{this.fld_beds, this.fld_area, this.fld_stock}) || this.isFieldEmpty() || this.cmb_hotel.getSelectedItem() == null || this.cmb_room_type.getSelectedItem() == null) {
                Helper.showMessage("fill");
            } else {
                boolean resultRoomSave;
                boolean resultPriceSave = true;
                this.room.setHotel(this.hotelManager.getById(((ComboItem) this.cmb_hotel.getSelectedItem()).getKey()));
                this.room.setRoomType((Room.RoomType) this.cmb_room_type.getSelectedItem());
                this.room.setBeds(Integer.parseInt(this.fld_beds.getValue().toString()));
                this.room.setArea(Integer.parseInt(this.fld_area.getValue().toString()));
                this.room.setStock(Integer.parseInt(this.fld_stock.getValue().toString()));
                this.room.setTv(this.chck_tv.isSelected());
                this.room.setMinibar(this.chck_minibar.isSelected());
                this.room.setGameConsole(this.chck_game_console.isSelected());
                this.room.setSafe(this.chck_safe.isSelected());
                this.room.setProjection(this.chck_projection.isSelected());

                if (this.room.getId() != 0) {
                    resultRoomSave = this.roomManager.update(this.room);
                    this.priceManager.deleteByRoomId(this.room.getId());
                    this.priceSave();

                } else {
                    resultRoomSave = this.roomManager.save(this.room);
                    this.room = this.roomManager.getById(this.roomManager.newRoomId());
                    this.priceSave();
                }

                for (Price price : prices) {
                    resultPriceSave = this.priceManager.save(price);
                }

                if (resultRoomSave && resultPriceSave) {
                    Helper.showMessage("done");
                    dispose();
                } else {
                    Helper.showMessage("error");
                }
            }
        });
    }

    private boolean isFieldEmpty() {
        JTextField[] textFields = new JTextField[]{
                this.fld_ultra_adult_1,
                this.fld_ultra_adult_2,
                this.fld_ultra_child_1,
                this.fld_ultra_child_2,
                this.fld_all_inclusive_adult_1,
                this.fld_all_inclusive_adult_2,
                this.fld_all_inclusive_child_1,
                this.fld_all_inclusive_child_2,
                this.fld_room_breakfast_adult_1,
                this.fld_room_breakfast_adult_2,
                this.fld_room_breakfast_child_1,
                this.fld_room_breakfast_child_2,
                this.fld_full_pension_adult_1,
                this.fld_full_pension_adult_2,
                this.fld_full_pension_child_1,
                this.fld_full_pension_child_2,
                this.fld_half_pension_adult_1,
                this.fld_half_pension_adult_2,
                this.fld_half_pension_child_1,
                this.fld_half_pension_child_2,
                this.fld_only_bed_adult_1,
                this.fld_only_bed_adult_2,
                this.fld_only_bed_child_1,
                this.fld_only_bed_child_2,
                this.fld_full_credit_adult_1,
                this.fld_full_credit_adult_2,
                this.fld_full_credit_child_1,
                this.fld_full_credit_child_2,
        };
        for (JTextField textField : textFields) {
            if (textField.isVisible() && textField.getText().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    private void setCurrentPrices() {
        for (Pension pension : pensions) {
            if (pension.getPensionType().equals(Pension.PensionType.Ultra_Her_Sey_Dahil)) {
                this.fld_ultra_adult_1.setValue(this.priceManager.getPrice(this.room,pension,seasons.getFirst(),"'adult'"));
                this.fld_ultra_child_1.setValue(this.priceManager.getPrice(this.room,pension,seasons.getFirst(),"'child'"));
                this.fld_ultra_adult_2.setValue(this.priceManager.getPrice(this.room,pension,seasons.getLast(),"'adult'"));
                this.fld_ultra_child_2.setValue(this.priceManager.getPrice(this.room,pension,seasons.getLast(),"'child'"));
            }
            if (pension.getPensionType().equals(Pension.PensionType.Her_Sey_Dahil)) {
                this.fld_all_inclusive_adult_1.setValue(this.priceManager.getPrice(this.room,pension,seasons.getFirst(),"'adult'"));
                this.fld_all_inclusive_child_1.setValue(this.priceManager.getPrice(this.room,pension,seasons.getFirst(),"'child'"));
                this.fld_all_inclusive_adult_2.setValue(this.priceManager.getPrice(this.room,pension,seasons.getLast(),"'adult'"));
                this.fld_all_inclusive_child_2.setValue(this.priceManager.getPrice(this.room,pension,seasons.getLast(),"'child'"));
            }
            if (pension.getPensionType().equals(Pension.PensionType.Oda_Kahvalti)) {
                this.fld_room_breakfast_adult_1.setValue(this.priceManager.getPrice(this.room,pension,seasons.getFirst(),"'adult'"));
                this.fld_room_breakfast_child_1.setValue(this.priceManager.getPrice(this.room,pension,seasons.getFirst(),"'child'"));
                this.fld_room_breakfast_adult_2.setValue(this.priceManager.getPrice(this.room,pension,seasons.getLast(),"'adult'"));
                this.fld_room_breakfast_child_2.setValue(this.priceManager.getPrice(this.room,pension,seasons.getLast(),"'child'"));
            }
            if (pension.getPensionType().equals(Pension.PensionType.Tam_Pansiyon)) {
                this.fld_full_pension_adult_1.setValue(this.priceManager.getPrice(this.room,pension,seasons.getFirst(),"'adult'"));
                this.fld_full_pension_child_1.setValue(this.priceManager.getPrice(this.room,pension,seasons.getFirst(),"'child'"));
                this.fld_full_pension_adult_2.setValue(this.priceManager.getPrice(this.room,pension,seasons.getLast(),"'adult'"));
                this.fld_full_pension_child_2.setValue(this.priceManager.getPrice(this.room,pension,seasons.getLast(),"'child'"));
            }
            if (pension.getPensionType().equals(Pension.PensionType.Yarim_Pansiyon)) {
                this.fld_half_pension_adult_1.setValue(this.priceManager.getPrice(this.room,pension,seasons.getFirst(),"'adult'"));
                this.fld_half_pension_child_1.setValue(this.priceManager.getPrice(this.room,pension,seasons.getFirst(),"'child'"));
                this.fld_half_pension_adult_2.setValue(this.priceManager.getPrice(this.room,pension,seasons.getLast(),"'adult'"));
                this.fld_half_pension_child_2.setValue(this.priceManager.getPrice(this.room,pension,seasons.getLast(),"'child'"));
            }
            if (pension.getPensionType().equals(Pension.PensionType.Sadece_Yatak)) {
                this.fld_only_bed_adult_1.setValue(this.priceManager.getPrice(this.room,pension,seasons.getFirst(),"'adult'"));
                this.fld_only_bed_child_1.setValue(this.priceManager.getPrice(this.room,pension,seasons.getFirst(),"'child'"));
                this.fld_only_bed_adult_2.setValue(this.priceManager.getPrice(this.room,pension,seasons.getLast(),"'adult'"));
                this.fld_only_bed_child_2.setValue(this.priceManager.getPrice(this.room,pension,seasons.getLast(),"'child'"));
            }
            if (pension.getPensionType().equals(Pension.PensionType.Alkol_Haric_Full_Credit)) {
                this.fld_full_credit_adult_1.setValue(this.priceManager.getPrice(this.room,pension,seasons.getFirst(),"'adult'"));
                this.fld_full_credit_child_1.setValue(this.priceManager.getPrice(this.room,pension,seasons.getFirst(),"'child'"));
                this.fld_full_credit_adult_2.setValue(this.priceManager.getPrice(this.room,pension,seasons.getLast(),"'adult'"));
                this.fld_full_credit_child_2.setValue(this.priceManager.getPrice(this.room,pension,seasons.getLast(),"'child'"));
            }
        }
    }

    private void priceSave() {
        this.prices.clear();

        for (Pension pension : this.pensions) {
            if (pension.getPensionType().equals(Pension.PensionType.Ultra_Her_Sey_Dahil)) {
                this.prices.add(new Price(this.room, this.hotel, pension, seasons.getFirst(), "adult", Integer.parseInt(this.fld_ultra_adult_1.getValue().toString())));
                this.prices.add(new Price(this.room, this.hotel, pension, seasons.getFirst(), "child", Integer.parseInt(this.fld_ultra_child_1.getValue().toString())));
                this.prices.add(new Price(this.room, this.hotel, pension, seasons.getLast(), "adult", Integer.parseInt(this.fld_ultra_adult_2.getValue().toString())));
                this.prices.add(new Price(this.room, this.hotel, pension, seasons.getLast(), "child", Integer.parseInt(this.fld_ultra_child_2.getValue().toString())));
            }
            if (pension.getPensionType().equals(Pension.PensionType.Her_Sey_Dahil)) {
                this.prices.add(new Price(this.room, this.hotel, pension, seasons.getFirst(), "adult", Integer.parseInt(this.fld_all_inclusive_adult_1.getValue().toString())));
                this.prices.add(new Price(this.room, this.hotel, pension, seasons.getFirst(), "child", Integer.parseInt(this.fld_all_inclusive_child_1.getValue().toString())));
                this.prices.add(new Price(this.room, this.hotel, pension, seasons.getLast(), "adult", Integer.parseInt(this.fld_all_inclusive_adult_2.getValue().toString())));
                this.prices.add(new Price(this.room, this.hotel, pension, seasons.getLast(), "child", Integer.parseInt(this.fld_all_inclusive_child_2.getValue().toString())));
            }
            if (pension.getPensionType().equals(Pension.PensionType.Oda_Kahvalti)) {
                this.prices.add(new Price(this.room, this.hotel, pension, seasons.getFirst(), "adult", Integer.parseInt(this.fld_room_breakfast_adult_1.getValue().toString())));
                this.prices.add(new Price(this.room, this.hotel, pension, seasons.getFirst(), "child", Integer.parseInt(this.fld_room_breakfast_child_1.getValue().toString())));
                this.prices.add(new Price(this.room, this.hotel, pension, seasons.getLast(), "adult", Integer.parseInt(this.fld_room_breakfast_adult_2.getValue().toString())));
                this.prices.add(new Price(this.room, this.hotel, pension, seasons.getLast(), "child", Integer.parseInt(this.fld_room_breakfast_child_2.getValue().toString())));
            }
            if (pension.getPensionType().equals(Pension.PensionType.Tam_Pansiyon)) {
                this.prices.add(new Price(this.room, this.hotel, pension, seasons.getFirst(), "adult", Integer.parseInt(this.fld_full_pension_adult_1.getValue().toString())));
                this.prices.add(new Price(this.room, this.hotel, pension, seasons.getFirst(), "child", Integer.parseInt(this.fld_full_pension_child_1.getValue().toString())));
                this.prices.add(new Price(this.room, this.hotel, pension, seasons.getLast(), "adult", Integer.parseInt(this.fld_full_pension_adult_2.getValue().toString())));
                this.prices.add(new Price(this.room, this.hotel, pension, seasons.getLast(), "child", Integer.parseInt(this.fld_full_pension_child_2.getValue().toString())));
            }
            if (pension.getPensionType().equals(Pension.PensionType.Yarim_Pansiyon)) {
                this.prices.add(new Price(this.room, this.hotel, pension, seasons.getFirst(), "adult", Integer.parseInt(this.fld_half_pension_adult_1.getValue().toString())));
                this.prices.add(new Price(this.room, this.hotel, pension, seasons.getFirst(), "child", Integer.parseInt(this.fld_half_pension_child_1.getValue().toString())));
                this.prices.add(new Price(this.room, this.hotel, pension, seasons.getLast(), "adult", Integer.parseInt(this.fld_half_pension_adult_2.getValue().toString())));
                this.prices.add(new Price(this.room, this.hotel, pension, seasons.getLast(), "child", Integer.parseInt(this.fld_half_pension_child_2.getValue().toString())));
            }
            if (pension.getPensionType().equals(Pension.PensionType.Sadece_Yatak)) {
                this.prices.add(new Price(this.room, this.hotel, pension, seasons.getFirst(), "adult", Integer.parseInt(this.fld_only_bed_adult_1.getValue().toString())));
                this.prices.add(new Price(this.room, this.hotel, pension, seasons.getFirst(), "child", Integer.parseInt(this.fld_only_bed_child_1.getValue().toString())));
                this.prices.add(new Price(this.room, this.hotel, pension, seasons.getLast(), "adult", Integer.parseInt(this.fld_only_bed_adult_2.getValue().toString())));
                this.prices.add(new Price(this.room, this.hotel, pension, seasons.getLast(), "child", Integer.parseInt(this.fld_only_bed_child_2.getValue().toString())));
            }
            if (pension.getPensionType().equals(Pension.PensionType.Alkol_Haric_Full_Credit)) {
                this.prices.add(new Price(this.room, this.hotel, pension, seasons.getFirst(), "adult", Integer.parseInt(this.fld_full_credit_adult_1.getValue().toString())));
                this.prices.add(new Price(this.room, this.hotel, pension, seasons.getFirst(), "child", Integer.parseInt(this.fld_full_credit_child_1.getValue().toString())));
                this.prices.add(new Price(this.room, this.hotel, pension, seasons.getLast(), "adult", Integer.parseInt(this.fld_full_credit_adult_2.getValue().toString())));
                this.prices.add(new Price(this.room, this.hotel, pension, seasons.getLast(), "child", Integer.parseInt(this.fld_full_credit_child_2.getValue().toString())));
            }
        }
    }

    private void setFormatFields() {
        this.lbl_season_1.setText(this.seasons.getFirst().getStartDate().toString() + " / " + this.seasons.getFirst().getEndDate().toString() + "   SEZONU");
        this.lbl_season_2.setText(this.seasons.getLast().getStartDate().toString() + " / " + this.seasons.getLast().getEndDate().toString() + "   SEZONU");

        this.fld_beds.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(NumberFormat.getIntegerInstance())));
        this.fld_area.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(NumberFormat.getIntegerInstance())));
        this.fld_stock.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(NumberFormat.getIntegerInstance())));
        this.fld_ultra_adult_1.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(NumberFormat.getIntegerInstance())));
        this.fld_ultra_adult_2.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(NumberFormat.getIntegerInstance())));
        this.fld_ultra_child_1.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(NumberFormat.getIntegerInstance())));
        this.fld_ultra_child_2.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(NumberFormat.getIntegerInstance())));
        this.fld_all_inclusive_adult_1.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(NumberFormat.getIntegerInstance())));
        this.fld_all_inclusive_adult_2.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(NumberFormat.getIntegerInstance())));
        this.fld_all_inclusive_child_1.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(NumberFormat.getIntegerInstance())));
        this.fld_all_inclusive_child_2.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(NumberFormat.getIntegerInstance())));
        this.fld_room_breakfast_adult_1.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(NumberFormat.getIntegerInstance())));
        this.fld_room_breakfast_adult_2.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(NumberFormat.getIntegerInstance())));
        this.fld_room_breakfast_child_1.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(NumberFormat.getIntegerInstance())));
        this.fld_room_breakfast_child_2.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(NumberFormat.getIntegerInstance())));
        this.fld_full_pension_adult_1.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(NumberFormat.getIntegerInstance())));
        this.fld_full_pension_adult_2.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(NumberFormat.getIntegerInstance())));
        this.fld_full_pension_child_1.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(NumberFormat.getIntegerInstance())));
        this.fld_full_pension_child_2.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(NumberFormat.getIntegerInstance())));
        this.fld_half_pension_adult_1.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(NumberFormat.getIntegerInstance())));
        this.fld_half_pension_adult_2.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(NumberFormat.getIntegerInstance())));
        this.fld_half_pension_child_1.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(NumberFormat.getIntegerInstance())));
        this.fld_half_pension_child_2.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(NumberFormat.getIntegerInstance())));
        this.fld_only_bed_adult_1.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(NumberFormat.getIntegerInstance())));
        this.fld_only_bed_adult_2.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(NumberFormat.getIntegerInstance())));
        this.fld_only_bed_child_1.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(NumberFormat.getIntegerInstance())));
        this.fld_only_bed_child_2.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(NumberFormat.getIntegerInstance())));
        this.fld_full_credit_adult_1.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(NumberFormat.getIntegerInstance())));
        this.fld_full_credit_adult_2.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(NumberFormat.getIntegerInstance())));
        this.fld_full_credit_child_1.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(NumberFormat.getIntegerInstance())));
        this.fld_full_credit_child_2.setFormatterFactory(new DefaultFormatterFactory(new NumberFormatter(NumberFormat.getIntegerInstance())));

        this.lbl_ultra_1.setVisible(this.hotel.isUltraPension());
        this.lbl_ultra_2.setVisible(this.hotel.isUltraPension());
        this.fld_ultra_adult_1.setVisible(this.hotel.isUltraPension());
        this.fld_ultra_adult_2.setVisible(this.hotel.isUltraPension());
        this.fld_ultra_child_1.setVisible(this.hotel.isUltraPension());
        this.fld_ultra_child_2.setVisible(this.hotel.isUltraPension());
        this.tl_ultra_1.setVisible(this.hotel.isUltraPension());
        this.tl_ultra_2.setVisible(this.hotel.isUltraPension());
        this.tl_ultra_3.setVisible(this.hotel.isUltraPension());
        this.tl_ultra_4.setVisible(this.hotel.isUltraPension());

        this.lbl_all_inclusive_1.setVisible(this.hotel.isAllInclusivePension());
        this.lbl_all_inclusive_2.setVisible(this.hotel.isAllInclusivePension());
        this.fld_all_inclusive_adult_1.setVisible(this.hotel.isAllInclusivePension());
        this.fld_all_inclusive_adult_2.setVisible(this.hotel.isAllInclusivePension());
        this.fld_all_inclusive_child_1.setVisible(this.hotel.isAllInclusivePension());
        this.fld_all_inclusive_child_2.setVisible(this.hotel.isAllInclusivePension());
        this.tl_all_inclusive_1.setVisible(this.hotel.isAllInclusivePension());
        this.tl_all_inclusive_2.setVisible(this.hotel.isAllInclusivePension());
        this.tl_all_inclusive_3.setVisible(this.hotel.isAllInclusivePension());
        this.tl_all_inclusive_4.setVisible(this.hotel.isAllInclusivePension());

        this.lbl_room_breakfast_1.setVisible(this.hotel.isRoomBreakfastPension());
        this.lbl_room_breakfast_2.setVisible(this.hotel.isRoomBreakfastPension());
        this.fld_room_breakfast_adult_1.setVisible(this.hotel.isRoomBreakfastPension());
        this.fld_room_breakfast_adult_2.setVisible(this.hotel.isRoomBreakfastPension());
        this.fld_room_breakfast_child_1.setVisible(this.hotel.isRoomBreakfastPension());
        this.fld_room_breakfast_child_2.setVisible(this.hotel.isRoomBreakfastPension());
        this.tl_room_breakfast_1.setVisible(this.hotel.isRoomBreakfastPension());
        this.tl_room_breakfast_2.setVisible(this.hotel.isRoomBreakfastPension());
        this.tl_room_breakfast_3.setVisible(this.hotel.isRoomBreakfastPension());
        this.tl_room_breakfast_4.setVisible(this.hotel.isRoomBreakfastPension());

        this.lbl_full_pension_1.setVisible(this.hotel.isFullPension());
        this.lbl_full_pension_2.setVisible(this.hotel.isFullPension());
        this.fld_full_pension_adult_1.setVisible(this.hotel.isFullPension());
        this.fld_full_pension_adult_2.setVisible(this.hotel.isFullPension());
        this.fld_full_pension_child_1.setVisible(this.hotel.isFullPension());
        this.fld_full_pension_child_2.setVisible(this.hotel.isFullPension());
        this.tl_full_pension_1.setVisible(this.hotel.isFullPension());
        this.tl_full_pension_2.setVisible(this.hotel.isFullPension());
        this.tl_full_pension_3.setVisible(this.hotel.isFullPension());
        this.tl_full_pension_4.setVisible(this.hotel.isFullPension());

        this.lbl_half_pension_1.setVisible(this.hotel.isHalfPension());
        this.lbl_half_pension_2.setVisible(this.hotel.isHalfPension());
        this.fld_half_pension_adult_1.setVisible(this.hotel.isHalfPension());
        this.fld_half_pension_adult_2.setVisible(this.hotel.isHalfPension());
        this.fld_half_pension_child_1.setVisible(this.hotel.isHalfPension());
        this.fld_half_pension_child_2.setVisible(this.hotel.isHalfPension());
        this.tl_half_pension_1.setVisible(this.hotel.isHalfPension());
        this.tl_half_pension_2.setVisible(this.hotel.isHalfPension());
        this.tl_half_pension_3.setVisible(this.hotel.isHalfPension());
        this.tl_half_pension_4.setVisible(this.hotel.isHalfPension());

        this.lbl_only_bed_1.setVisible(this.hotel.isOnlyBedPension());
        this.lbl_only_bed_2.setVisible(this.hotel.isOnlyBedPension());
        this.fld_only_bed_adult_1.setVisible(this.hotel.isOnlyBedPension());
        this.fld_only_bed_adult_2.setVisible(this.hotel.isOnlyBedPension());
        this.fld_only_bed_child_1.setVisible(this.hotel.isOnlyBedPension());
        this.fld_only_bed_child_2.setVisible(this.hotel.isOnlyBedPension());
        this.tl_only_bed_1.setVisible(this.hotel.isOnlyBedPension());
        this.tl_only_bed_2.setVisible(this.hotel.isOnlyBedPension());
        this.tl_only_bed_3.setVisible(this.hotel.isOnlyBedPension());
        this.tl_only_bed_4.setVisible(this.hotel.isOnlyBedPension());

        this.lbl_full_credit_1.setVisible(this.hotel.isFullCreditPension());
        this.lbl_full_credit_2.setVisible(this.hotel.isFullCreditPension());
        this.fld_full_credit_adult_1.setVisible(this.hotel.isFullCreditPension());
        this.fld_full_credit_adult_2.setVisible(this.hotel.isFullCreditPension());
        this.fld_full_credit_child_1.setVisible(this.hotel.isFullCreditPension());
        this.fld_full_credit_child_2.setVisible(this.hotel.isFullCreditPension());
        this.tl_full_credit_1.setVisible(this.hotel.isFullCreditPension());
        this.tl_full_credit_2.setVisible(this.hotel.isFullCreditPension());
        this.tl_full_credit_3.setVisible(this.hotel.isFullCreditPension());
        this.tl_full_credit_4.setVisible(this.hotel.isFullCreditPension());
    }
}

