package view;

import entity.Room;

import javax.swing.*;

public class RoomView extends Layout {
    private JPanel container;
    private Room room;

    public RoomView(Room room) {
        this.add(container);
        this.guiInitilaze(500, 700);
        this.room = room;
    }
}
