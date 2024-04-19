package business;

import core.Helper;
import dao.UserDao;
import entity.User;
import java.util.ArrayList;

public class UserManager {
    private final UserDao userDao;

    public UserManager() {
        this.userDao = new UserDao();
    }

    public ArrayList<User> findAll() {
        return this.userDao.findAll();
    }

    public User findByLogin(String username, String password) {
        return this.userDao.findByLogin(username, password);
    }

    public User getById(int id) {
        return this.userDao.getById(id);
    }

    public ArrayList<User> getByRole(User.Role role) {
        if (role == null) {
            return this.findAll();
        } else {
            return userDao.getByRole(role);
        }
    }

    public boolean delete(int id) {
        return this.userDao.delete(id);
    }

    public boolean save(User user) {
        if (this.getById(user.getId()) != null) {
            Helper.showMessage("error");
            return false;
        }
        return this.userDao.save(user);
    }

    public boolean update(User user) {
        if (this.getById(user.getId()) == null) {
            Helper.showMessage(user.getId() + " ID kayıtlı kullanıcı bulunamadı !");
            return false;
        }
        return this.userDao.update(user);
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<User> users) {
        ArrayList<Object[]> userList = new ArrayList<>();
        for (User user : users) {
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = user.getId();
            rowObject[i++] = user.getUsername();
            rowObject[i++] = user.getPassword();
            rowObject[i] = user.getRole();
            userList.add(rowObject);
        }
        return userList;
    }

    public boolean isUsernameExist(User thisuser) {
        for (User user : this.findAll()) {
            if (user.getUsername().equals(thisuser.getUsername()) && user.getId() != thisuser.getId()) {
                return true;
            }
        }
        return false;
    }
}
