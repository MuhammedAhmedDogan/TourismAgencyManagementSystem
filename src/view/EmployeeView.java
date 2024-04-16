package view;

import entity.User;

import javax.swing.*;

public class EmployeeView extends Layout {
    private JPanel container;
    private JLabel lbl_title;
    private JLabel lbl_welcome;
    private JButton btn_admin;
    private JButton btn_exit;
    private JTabbedPane tbd_employee;
    private JPanel pnl_room;
    private JPanel pnl_hotel;
    private JPanel pnl_reservation;
    private User user;

    public EmployeeView(User user) {
        this.add(container);
        this.guiInitilaze(800, 500);
        this.user = user;

        this.lbl_welcome.setText("Hoşgeldiniz : " + this.user.getUsername() + " (" + this.user.getRole() + ")");

        this.btn_admin.setVisible(this.user.getRole() == User.Role.ADMIN);
        this.btn_admin.addActionListener(e -> {
            AdminView adminView = new AdminView(this.user);
            dispose();
        });


    }
}
