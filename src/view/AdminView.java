package view;

import business.UserManager;
import core.Helper;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class AdminView extends Layout{
    private JPanel container;
    private JPanel pnl_top;
    private JLabel lbl_welcome;
    private JButton btn_employee;
    private JLabel lbl_title;
    private JButton btn_exit;
    private JTabbedPane tbd_users;
    private JPanel pnl_users;
    private JScrollPane scrl_users;
    private JTable tbl_users;
    private User user;
    private UserManager userManager;
    private DefaultTableModel tmdl_user = new DefaultTableModel();
    private JPopupMenu user_menu = new JPopupMenu();

    public AdminView(User user) {
        this.add(container);
        this.guiInitilaze(800, 500);
        this.user = user;
        this.userManager = new UserManager();

        this.lbl_welcome.setText("Hoşgeldiniz : " + this.user.getUsername());
        loadUserTable();
        loadUserComponent();
    }

    public void loadUserTable() {
        Object[] col_user = {"ID", "Kullanıcı Adı", "Şifre", "Pozisyon"};
        ArrayList<Object[]> carList = this.userManager.getFortable(col_user.length, this.userManager.findAll());
        createTable(this.tmdl_user, this.tbl_users, col_user, carList);
    }

    public void loadUserComponent() {
        tableRowSelect(this.tbl_users);
        this.user_menu = new JPopupMenu();
        this.user_menu.add("Yeni").addActionListener(e -> {
            UserView userView = new UserView(new User());
            userView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadUserTable();
                }
            });
        });
        this.user_menu.add("Güncelle").addActionListener(e -> {
            int selectUserId = this.getTableSelectedRow(tbl_users, 0);
            UserView userView = new UserView(this.userManager.getById(selectUserId));
            userView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadUserTable();
                }
            });
        });
        this.user_menu.add("Sil").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectUserId = this.getTableSelectedRow(tbl_users, 0);
                if (this.userManager.delete(selectUserId)) {
                    Helper.showMessage("done");
                    loadUserTable();
                } else {
                    Helper.showMessage("error");
                }
            }
        });
        this.tbl_users.setComponentPopupMenu(user_menu);
    }
}
