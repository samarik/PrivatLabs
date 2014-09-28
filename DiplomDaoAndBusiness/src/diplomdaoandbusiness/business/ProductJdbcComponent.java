/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diplomdaoandbusiness.business;

import diplomdaoandbusiness.beans.entities.Category;
import diplomdaoandbusiness.beans.entities.Manufacturer;
import diplomdaoandbusiness.beans.entities.Product;
import diplomdaoandbusiness.services.IProductServices;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Olga
 */
public class ProductJdbcComponent implements IProductServices {

    //private IConnectionFactory connectionFactory = DaoConfig.getConnectionFactory();
    private ProductDao productDao = new ProductDao();

    @Override
    public List<Product> list() {
        Connection con = null;
        List<Product> ret = null;
        try {
            //con = connectionFactory.getDefaultConnection();
            ret = productDao.list(con);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            try {
                con.close();
            } catch (Exception ex) {
            }
        }
        return ret;
    }

    @Override
    public Product getInfo(int idProduct) {
        Connection con = null;
        Product ret = null;
        try {
            con = connectionFactory.getDefaultConnection();
            ret = productDao.getProductInfo(con, idProduct);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            try {
                con.close();
            } catch (Exception ex) {
            }
        }
        return ret;
    }

    @Override
    public void add(Product product) {
        Connection con = null;
        try {
            con = connectionFactory.getDefaultConnection();
            con.setAutoCommit(false);
            if (productDao.checkName(con, product.getName()) > 0) {
                throw new LogicException("Товар с именем " + product.getName() + " уже существует");
            }
            product.setId(productDao.getNextId(con));
            productDao.addCategory(con, product);
            con.commit();
        } catch (SQLException ex) {
            try {
                con.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(ProductJdbcComponent.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new RuntimeException(ex);
        } finally {
            try {
                con.close();
            } catch (Exception ex) {
            }
        }
    }

    @Override
    public void delete(int productId) {
        Connection con = null;
        try {
            con = connectionFactory.getDefaultConnection();
            con.setAutoCommit(false);
            if (productDao.deleteUser(con, productId) == 0) {
                throw new LogicException("Товара с кодом " + productId + " не существует");
            }
            con.commit();
        } catch (SQLException ex) {
            try {
                con.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(UserJdbcComponent.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new RuntimeException(ex);
        } finally {
            try {
                con.close();
            } catch (Exception ex) {
            }
        }
    }

    @Override
    public void modify(Product product) {
        Connection con = null;
        try {
            con = connectionFactory.getDefaultConnection();
            con.setAutoCommit(false);
            if (productDao.checkName(con, product.getName(), product.getId()) > 0) {
                throw new LogicException("Товар с именем " + product.getName() + " уже существует");
            }
            if (productDao.modifyCategory(con, product) == 0) {
                throw new LogicException("Товара с кодом " + product.getId() + " не существует");
            }
            con.commit();
        } catch (SQLException ex) {
            try {
                con.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(ProductJdbcComponent.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new RuntimeException(ex);
        } finally {
            try {
                con.close();
            } catch (Exception ex) {
            }
        }
    }

}
