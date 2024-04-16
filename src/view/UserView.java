package view;

import business.UserManager;
import core.Helper;
import entity.User;

import javax.swing.*;

public class UserView extends Layout {
    private JPanel container;
    private JLabel lbl_title;
    private JTextField fld_username;
    private JLabel lbl_password;
    private JTextField fld_password;
    private JLabel lbl_role;
    private JComboBox<User.Role> cmb_role;
    private JButton btn_save;
    private JLabel lbl_username;
    private User user;
    private UserManager userManager = new UserManager();

    public UserView(User user) {
        this.add(container);
        this.guiInitilaze(400, 500);
        this.user = user;

        this.cmb_role.setModel(new DefaultComboBoxModel<>(User.Role.values()));

        if (user != null) {
            this.fld_username.setText(user.getUsername());
            this.fld_password.setText(user.getPassword());
            this.cmb_role.getModel().setSelectedItem(user.getRole());
        }

        btn_save.addActionListener(e -> {
            if (Helper.isFieldListEmpty(new JTextField[]{this.fld_username, this.fld_password}) || this.cmb_role.getSelectedItem() == null) {
                Helper.showMessage("fill");
            } else {
                boolean result;
                this.user.setRole((User.Role) this.cmb_role.getSelectedItem());
                this.user.setUsername(this.fld_username.getText());
                this.user.setPassword(this.fld_password.getText());

                if (userManager.isUsernameExist(this.user)) {
                    Helper.showMessage("Bu kullanıcı adı zaten kullanılıyor !");
                } else {
                    if (this.user.getId() != 0) {
                        result = this.userManager.update(this.user);
                    } else {
                        result = this.userManager.save(this.user);
                    }

                    if (result) {
                        Helper.showMessage("done");
                        dispose();
                    }
                }
            }

        });

    }

}
