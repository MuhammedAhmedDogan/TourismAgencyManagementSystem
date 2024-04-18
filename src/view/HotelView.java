package view;

import entity.Hotel;

import javax.swing.*;

public class HotelView extends Layout{
    private JPanel container;
    private Hotel hotel;

    public HotelView(Hotel hotel) {
        this.add(container);
        this.guiInitilaze(400, 500);
        this.hotel = hotel;
    }
}
