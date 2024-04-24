package dao;

import core.Db;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDao {
    private final Connection con;

    public UserDao() {
        this.con = Db.getInstance();
    }

    public ArrayList<User> findAll() {
        ArrayList<User> userList = new ArrayList<>();
        String query = "SELECT * FROM public.user ORDER BY user_name ASC";
        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while (rs.next()) {
                userList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public User findByLogin(String username, String password) {
        User obj = null;
        String query = "SELECT * FROM public.user WHERE user_name = ? AND user_password = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setString(1, username);
            pr.setString(2, password);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = this.match(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public User getById(int id) {
        User obj = null;
        String query = "SELECT * FROM public.user WHERE user_id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = this.match(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public ArrayList<User> getByRole(User.Role role) {
        ArrayList<User> userList = new ArrayList<>();
        String query = "SELECT * FROM public.user WHERE user_role = ? ORDER BY user_name ASC";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setString(1, role.toString());
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                userList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public boolean delete(int id) {
        String query = "DELETE FROM public.user WHERE user_id = ?";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean save(User user) {
        String query = "INSERT INTO public.user " +
                "(" +
                "user_role," +
                "user_name," +
                "user_password" +
                ")" +
                " VALUES (?,?,?)";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setString(1, user.getRole().toString());
            pr.setString(2, user.getUsername());
            pr.setString(3, user.getPassword());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean update(User user) {
        String query = "UPDATE public.user SET " +
                "user_role = ?, " +
                "user_name = ?, " +
                "user_password = ? " +
                "WHERE user_id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setString(1, user.getRole().toString());
            pr.setString(2, user.getUsername());
            pr.setString(3, user.getPassword());
            pr.setInt(4, user.getId());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public User match(ResultSet rs) throws SQLException {
        User obj = new User();
        obj.setId(rs.getInt("user_id"));
        obj.setUsername(rs.getString("user_name"));
        obj.setPassword(rs.getString("user_password"));
        obj.setRole(User.Role.valueOf(rs.getString("user_role")));
        return obj;
    }
}