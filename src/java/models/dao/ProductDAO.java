package models.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import models.product.Product;
import org.apache.log4j.Logger;
import utils.DBUtils;

public class ProductDAO {
    private static final Logger LOGGER = Logger.getLogger(ProductDAO.class);
    private static final String CREATE = "INSERT INTO tblProducts (name, description, price, quantity, categoryID, image) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String GET = "SELECT * FROM tblProducts WHERE productID = ?";
    private static final String UPDATE = "UPDATE tblProducts SET name = ?, description = ?, price = ?, quantity = ?, categoryID = ?, image = ? WHERE productID = ?";
    private static final String UPDATE_QUANTITY = "UPDATE tblProducts SET quantity = ? WHERE productID = ?";
    private static final String DELETE = "DELETE FROM tblProducts WHERE productID = ?";
    private static final String SEARCH = "SELECT * FROM tblProducts WHERE name LIKE ?";
    
    public ProductDAO() {
        
    }

    // Create a new product
    public void createProduct(Product product) throws NamingException, SQLException {
        Connection connection = DBUtils.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setInt(4, product.getQuantity());
            preparedStatement.setInt(5, product.getCategoryID());
            preparedStatement.setString(6, product.getImage());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        connection.close();
    }

    // Read a product by ID
    public Product getProductById(Long productID) throws NamingException, SQLException {
        Connection connection = DBUtils.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET)) {
            preparedStatement.setLong(1, productID);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Product product = new Product();
                    product.setProductID(resultSet.getLong("productID"));
                    product.setName(resultSet.getString("name"));
                    product.setDescription(resultSet.getString("description"));
                    product.setPrice(resultSet.getDouble("price"));
                    product.setQuantity(resultSet.getInt("quantity"));
                    product.setCategoryID(resultSet.getInt("categoryID"));
                    product.setImage(resultSet.getString("image"));
                    return product;
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        finally {
            connection.close();
        }
        return null;
    }

    // Update a product
    public void updateProduct(Product product) throws NamingException, SQLException {
        Connection connection = DBUtils.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setInt(4, product.getQuantity());
            preparedStatement.setInt(5, product.getCategoryID());
            preparedStatement.setString(6, product.getImage());
            preparedStatement.setLong(7, product.getProductID());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        finally {
            connection.close();
        }
    }

    // Delete a product by ID
    public void deleteProduct(Long productID) throws NamingException, SQLException {
        Connection connection = DBUtils.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setLong(1, productID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        finally {
            connection.close();
        }
    }

    // Retrieve all products
    public List<Product> getListProduct(String search) throws NamingException, SQLException {
        Connection connection = DBUtils.getConnection();
        List<Product> products = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SEARCH)) {
            preparedStatement.setString(1, "%" + search + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setProductID(resultSet.getLong("productID"));
                product.setName(resultSet.getString("name"));
                product.setDescription(resultSet.getString("description"));
                product.setPrice(resultSet.getDouble("price"));
                product.setQuantity(resultSet.getInt("quantity"));
                product.setCategoryID(resultSet.getInt("categoryID"));
                product.setImage(resultSet.getString("image"));
                products.add(product);
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        finally {
            connection.close();
        }
        return products;
    }
    public boolean updateProductQuantity(Product product, int quantity) throws SQLException, NamingException {
        Connection connection = DBUtils.getConnection();
        boolean result = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUANTITY)) {
            preparedStatement.setLong(2, product.getProductID());
            preparedStatement.setInt(1, product.getQuantity() - quantity);
            result = preparedStatement.executeUpdate() > 0 ? true : false;
        }
        catch (Exception e) {
            LOGGER.error(e);
        }
        finally {
            connection.close();
        }
        return result;
    }
}