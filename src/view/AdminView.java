package view;

import business.UserManager;
import core.Helper;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class AdminView extends Layout {
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
    private JPanel pnl_filter;
    private JComboBox<User.Role> cmb_filter_role;
    private JLabel lbl_filter;
    private JButton btn_search;
    private JButton btn_clear;
    private User user;
    private UserManager userManager;
    private DefaultTableModel tmdl_user = new DefaultTableModel();
    private JPopupMenu user_menu = new JPopupMenu();
    private Object[] col_user;

    public AdminView(User user) {
        this.add(container);
        this.guiInitilaze(800, 500);
        this.user = user;
        this.userManager = new UserManager();

        this.lbl_welcome.setText("Hoşgeldiniz : " + this.user.getUsername());
        loadUserTable(null);
        loadUserComponent();
        loadUserFilter();
    }

    public void loadUserTable(ArrayList<Object[]> userList) {
        this.col_user = new Object[]{"ID", "Kullanıcı Adı", "Şifre", "Pozisyon"};
        if (userList == null) {
            userList = this.userManager.getForTable(col_user.length, this.userManager.findAll());
        }
        createTable(this.tmdl_user, this.tbl_users, col_user, userList);
    }

    public void loadUserComponent() {
        tableRowSelect(this.tbl_users);
        this.user_menu = new JPopupMenu();
        this.user_menu.add("Yeni").addActionListener(e -> {
            UserView userView = new UserView(new User());
            userView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadUserTable(null);
                }
            });
        });
        this.user_menu.add("Güncelle").addActionListener(e -> {
            int selectUserId = this.getTableSelectedRow(tbl_users, 0);
            UserView userView = new UserView(this.userManager.getById(selectUserId));
            userView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadUserTable(null);
                }
            });
        });
        this.user_menu.add("Sil").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectUserId = this.getTableSelectedRow(tbl_users, 0);
                if (this.userManager.delete(selectUserId)) {
                    Helper.showMessage("done");
                    loadUserTable(null);
                } else {
                    Helper.showMessage("error");
                }
            }
        });
        this.tbl_users.setComponentPopupMenu(user_menu);

        this.btn_search.addActionListener(e -> {
            ArrayList<User> userListBySearch = this.userManager.getByRole((User.Role) cmb_filter_role.getSelectedItem());
            ArrayList<Object[]> userRowListBySearch = this.userManager.getForTable(this.col_user.length, userListBySearch);
            loadUserTable(userRowListBySearch);
        });

        this.btn_clear.addActionListener(e -> {
            this.cmb_filter_role.setSelectedItem(null);
            loadUserTable(null);
        });
    }

    public void loadUserFilter() {
        this.cmb_filter_role.setModel(new DefaultComboBoxModel<>(User.Role.values()));
        this.cmb_filter_role.setSelectedItem(null);
    }
}
