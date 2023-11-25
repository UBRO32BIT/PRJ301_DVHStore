/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import models.auth.User;
import org.apache.log4j.Logger;
import utils.DBUtils;

/**
 *
 * @author ubro3
 */
public class UserDAO {
    static final Logger LOGGER = Logger.getLogger(UserDAO.class);
    private static final String LOGIN = "SELECT userID, userName, fullName, address, phone, email, roleID, profileImage FROM tblUsers WHERE userName=? AND password=? AND isDeleted=0";
    private static final String GOOGLE_LOGIN = "SELECT userID, userName, fullName, address, phone, email, roleID, profileImage FROM tblUsers WHERE googleID = ?";
    private static final String SEARCH = "SELECT userID, userName, fullName, address, phone, email, roleID, profileImage FROM tblUsers WHERE fullName LIKE ? AND isDeleted=0";
    private static final String GET_BY_ID = "SELECT userID, userName, fullName, address, phone, email, roleID, profileImage FROM tblUsers WHERE userID=?";
    private static final String DELETE = "UPDATE tblUsers SET isDeleted=1 WHERE userID=?";
    private static final String UPDATE = "UPDATE tblUsers SET fullName=?, address=?, phone=?, roleID=? WHERE userID=?";
    private static final String REGISTER ="INSERT INTO tblUsers(userName, fullName, password, address, phone, email, roleID) VALUES (?,?,?,?,?,?,?)";
    private static final String GOOGLE_REGISTER = "INSERT INTO tblUsers(userName, fullName, email, roleID, googleID, profileImage) VALUES (?,?,?,?,?,?)";
    private static final String CHECK_DUPLICATE = "SELECT userName FROM tblUsers WHERE userName = ?";
    private static final String NUM = "SELECT count(*) AS num FROM tblUsers WHERE roleID = 'US'";

    public User checkLogin(String userName, String pwd) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        User user = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(LOGIN);
                ptm.setString(1, userName);
                ptm.setString(2, pwd);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    int userID = rs.getInt("userID");
                    String fullName = rs.getNString("fullName");
                    String address = rs.getNString("address");
                    String phone = rs.getString("phone");
                    String email = rs.getString("email");
                    String roleID = rs.getString("roleID");
                    String profileImage = rs.getString("profileImage");
                    user = new User(userID, userName, fullName, null, address, phone, email, roleID, null, profileImage);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return user;
    }
    public User checkGoogleLogin(String googleID) throws SQLException {
                Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        User user = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GOOGLE_LOGIN);
                ptm.setString(1, googleID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    int userID = rs.getInt("userID");
                    String userName = rs.getString("userName");
                    String fullName = rs.getNString("fullName");
                    String address = rs.getNString("address");
                    String phone = rs.getString("phone");
                    String email = rs.getString("email");
                    String roleID = rs.getString("roleID");
                    String profileImage = rs.getString("profileImage");
                    user = new User(userID, userName, fullName, null, address, phone, email, roleID, googleID, profileImage);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return user;
    }

    public List<User> getListUser(String search) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        List<User> result = new ArrayList<>();
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH);
                ptm.setString(1, "%" + search + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int userID = rs.getInt("userID");
                    String userName = rs.getString("userName");
                    String fullName = rs.getNString("fullName");
                    String address = rs.getNString("address");
                    String phone = rs.getString("phone");
                    String email = rs.getString("email");
                    String roleID = rs.getString("roleID");
                    String profileImage = rs.getString("profileImage");
                    User user = new User(userID, userName, fullName, null, address, phone, email, roleID, null, profileImage);
                    result.add(user);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }

    public boolean delete(int userID) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        boolean result = false;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE);
                ptm.setInt(1, userID);
                result = ptm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }

    public boolean update(int userID, String fullName, String address, String phone, String roleID) throws Exception {
        Connection conn = null;
        PreparedStatement ptm = null;
        boolean result = false;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE);
                ptm.setString(1, fullName);
                ptm.setString(2, address);
                ptm.setString(3, phone);
                ptm.setString(4, roleID);
                ptm.setInt(5, userID);
                result = ptm.executeUpdate() > 0 ? true : false;
            }

        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }
    public boolean insert(User user) throws SQLException, ClassNotFoundException, NamingException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(REGISTER);
                ptm.setString(1, user.getUserName());
                ptm.setString(2, user.getFullName());
                ptm.setString(3, user.getPassword());
                ptm.setString(4, user.getAddress());
                ptm.setString(5, user.getPhone());
                ptm.setString(6, user.getEmail());
                ptm.setString(7, user.getRoleID());
                check = ptm.executeUpdate()>0 ? true:false;
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }
    public boolean insertGoogle(User user) throws SQLException, ClassNotFoundException, NamingException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GOOGLE_REGISTER);
                ptm.setString(1, user.getUserName());
                ptm.setString(2, user.getFullName());
                ptm.setString(3, user.getEmail());
                ptm.setString(4, user.getRoleID());
                ptm.setString(5, user.getGoogleID());
                ptm.setString(6, user.getProfileImage());
                check = ptm.executeUpdate()>0 ? true:false;
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }
    public boolean checkDuplicateUserName(String userName) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        boolean result = false;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_DUPLICATE);
                ptm.setString(1, userName);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    result = true;
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }
    public User getUserByID(int userID) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        User result = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_BY_ID);
                ptm.setInt(1, userID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String userName = rs.getString("userName");
                    String fullName = rs.getNString("fullName");
                    String address = rs.getNString("address");
                    String phone = rs.getString("phone");
                    String email = rs.getString("email");
                    String roleID = rs.getString("roleID");
                    String profileImage = rs.getString("profileImage");
                    User user = new User(userID, userName, fullName, null, address, phone, email, roleID, null, profileImage);
                    result = user;
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }
    public int getNumber() throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        int result = 0;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(NUM);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    result = Integer.parseInt(rs.getString("num"));
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }
}
