/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diplomdaoandbusiness.dao;

import diplomdaoandbusiness.beans.entities.Product;
import diplomdaoandbusiness.util.DaoConfig;
import diplomdaoandbusiness.util.IConnectionFactory;
import diplomdaoandbusiness.util.MyConnectionFactory;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Olga
 */
public class ProductDao {

    private final IConnectionFactory connectionFactory = DaoConfig.getConnectionFactory();

    public List<Product> list() {
        List<Product> list = new ArrayList<>();
        ResultSet rs = null;
        Statement stmt = null;
        try {
            Connection con = connectionFactory.getDefaultConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("Select p.PROD_ACC, p.MANUF_OKPO, p.CATEG_ACC, p.PROD_NAME, p.PROD_PRICE, p.PROD_DESCR, p.PROD_FLSKL From Product p");
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("PROD_ACC"));
                product.setIdManuf(rs.getInt("MANUF_OKPO"));
                product.setIdCateg(rs.getInt("CATEG_ACC"));
                product.setName(rs.getString("PROD_NAME"));
                product.setPrice(rs.getBigDecimal("PROD_PRICE"));
                product.setDescription(rs.getString("PROD_DESCR"));
                product.setFlSkl(rs.getBoolean("PROD_FLSKL"));
                list.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return list;
    }

    public Product getProductInfo(int productId) throws SQLException {
        Connection con = connectionFactory.getDefaultConnection();
        PreparedStatement ps = con.prepareStatement("Select MANUF_OKPO, CATEG_ACC, PROD_NAME,PROD_PRICE, PROD_DESCR, PROD_FLSKL From Product where PROD_ACC = ?");
        ps.setInt(1, productId);
        ResultSet rs = ps.executeQuery();
        Product ret = null;
        if (rs.next()) {
            ret = new Product();
            ret.setId(productId);
            ret.setIdManuf(rs.getInt("MANUF_OKPO"));
            ret.setIdCateg(rs.getInt("CATEG_ACC"));
            ret.setName(rs.getString("PROD_NAME"));
            ret.setPrice(rs.getBigDecimal("PROD_PRICE"));
            ret.setDescription(rs.getString("PROD_DESCR"));
        }

        rs.close();
        ps.close();
        return ret;
    }

    public int getNextId() throws SQLException {
        Connection con = connectionFactory.getDefaultConnection();
        PreparedStatement ps = con.prepareStatement("select max(PROD_ACC) from Product");
        ResultSet rs = ps.executeQuery();
        int ret = 0;
        if (rs.next()) {
            ret = rs.getInt(1) + 1;
        }
        rs.close();
        return ret;
    }

    public void addProduct(Product product) throws SQLException {
        Connection con = connectionFactory.getDefaultConnection();
        PreparedStatement ps = con.prepareStatement("insert into Product(PROD_ACC , MANUF_OKPO , CATEG_ACC, PROD_NAME, PROD_PRICE, PROD_DESCR, PROD_FLSKL) values(?,?,?,?,?,?,?)");
        int i = 1;
        product.setFlSkl(true);
        product.setId(getNextId());
        ps.setInt(i++, product.getId());
        ps.setInt(i++, product.getIdManuf());
        ps.setInt(i++, product.getIdCateg());
        ps.setString(i++, product.getName());
        System.out.println(product.getPrice());
        ps.setBigDecimal(i++, product.getPrice());
        ps.setString(i++, product.getDescription());
        ps.setBoolean(i++, product.getFlSkl());

        ps.execute();
        ps.close();
    }

    public int checkName(String login, Integer... selfId) throws SQLException {
        String cmd = "select count(*) from Product where PROD_NAME = ?";
        if (selfId.length > 0) {
            cmd += " and PROD_ACC!=?";
        }
        Connection con = connectionFactory.getDefaultConnection();
        PreparedStatement ps = con.prepareStatement(cmd);
        ps.setString(1, login);
        if (selfId.length > 0) {
            ps.setInt(2, selfId[0]);
        }
        ResultSet rs = ps.executeQuery();
        int ret = 0;
        if (rs.next()) {
            ret = rs.getInt(1);
        }
        rs.close();
        ps.close();
        return ret;
    }

    public int modifyProduct(Product product) throws SQLException {
        Connection con = connectionFactory.getDefaultConnection();
        PreparedStatement ps = con.prepareStatement("update Product set PROD_NAME = ?, PROD_PRICE = ?, PROD_DESCR =  ?, PROD_FLSKL = ? where PROD_ACC = ? ");
        int i = 1;
        ps.setString(i++, product.getName());
        ps.setBigDecimal(i++, product.getPrice());
        ps.setString(i++, product.getDescription());
        ps.setBoolean(i++, product.getFlSkl());
        ps.setInt(i++, product.getId());
        int ret = ps.executeUpdate();
        ps.close();
        return ret;
    }

    public int deleteProduct(int productId) throws SQLException {
        Connection con = connectionFactory.getDefaultConnection();
        PreparedStatement ps = con.prepareStatement("delete Product where PROD_ACC = ? ");
        int i = 1;
        ps.setInt(i++, productId);
        int ret = ps.executeUpdate();
        ps.close();
        return ret;
    }

}
