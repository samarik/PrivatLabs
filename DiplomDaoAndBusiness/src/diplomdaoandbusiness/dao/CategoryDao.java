package diplomdaoandbusiness.dao;

import diplomdaoandbusiness.beans.entities.Category;
import diplomdaoandbusiness.util.DaoConfig;
import diplomdaoandbusiness.util.IConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Olga
 */
public class CategoryDao {

    private IConnectionFactory connectionFactory = DaoConfig.getConnectionFactory();

    public List<Category> list() throws SQLException {
        Connection con = connectionFactory.getDefaultConnection();
        List<Category> list = new ArrayList<>();
        PreparedStatement ps = con.prepareStatement("select CATEG_ACC, Cat_CATEG_ACC, CATEG_NAME, CATEG_DESCR from Category order by CATEG_ACC");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Category category = new Category();
            category.setId(rs.getInt("CATEG_ACC"));
            category.setIdParent(rs.getInt("Cat_CATEG_ACC"));
            category.setName(rs.getString("CATEG_NAME"));
            category.setDescription(rs.getString("CATEG_DESCR"));
            list.add(category);
        }
        rs.close();
        ps.close();
        return list;
    }

    public int getNextId() throws SQLException {
        Connection con = connectionFactory.getDefaultConnection();

        PreparedStatement ps = con.prepareStatement("select max(CATEG_ACC) from Category");
        ResultSet rs = ps.executeQuery();
        int ret = 0;
        if (rs.next()) {
            ret = rs.getInt(1) + 1;
        }
        rs.close();
        //CallableStatement call = con.prepareCall("{call GenId (?,?,?)}");
        //call.setString(1, "C");
        //call.setInt(2, 1);
        //call.setInt(3, 0);
        //ResultSet r = call.executeQuery();
        //while (r.next()) {
        //  System.out.println("---------------------------------------");
        // System.out.println("Идентификатор " + r.getString(ret));
        //r.close();
        //call.close();

        return ret;
    }

    public void addCategory(Category category) throws SQLException {
        Connection con = connectionFactory.getDefaultConnection();
        PreparedStatement ps = con.prepareStatement("insert into Category(CATEG_ACC, Cat_CATEG_ACC , CATEG_NAME, CATEG_DESCR) values(?,?,?,?)");
        int i = 1;
        category.setId(getNextId());
        ps.setInt(i++, category.getId());
        ps.setInt(i++, 10);
        ps.setString(i++, category.getName());
        ps.setString(i++, category.getDescription());
        ps.execute();
        ps.close();
    }

    public int checkName(Connection con, String login, Integer... selfId) throws SQLException {
        String cmd = "select count(*) from Category where CATEG_NAME = ?";
        if (selfId.length > 0) {
            cmd += " and CATEG_ACC!=?";
        }

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

    public int modifyCategory(Connection con, Category category) throws SQLException {

        PreparedStatement ps = con.prepareStatement("update Category set Cat_CATEG_ACC = ?, CATEG_NAME  = ?, CATEG_DESCR = ? where CATEG_ACC = ? ");
        int i = 1;
        ps.setInt(i++, category.getIdParent());
        ps.setString(i++, category.getName());
        ps.setString(i++, category.getDescription());
        ps.setInt(i++, category.getId());
        int ret = ps.executeUpdate();
        ps.close();
        return ret;
    }

    public List<Integer> selectCategoryChild(Connection con, int categoryId) throws SQLException {
        PreparedStatement ps = con.prepareStatement("select Cat_CATEG_ACC from Category where CATEG_ACC = ?");
        ps.setInt(1, categoryId);
        ResultSet rs = ps.executeQuery();
        List<Integer> ret = new ArrayList<>();
        while (rs.next()) {
            ret.add(rs.getInt(1));
        }
        rs.close();
        ps.close();
        return ret;
    }

    public int deleteCategory(int categoryId) throws SQLException {
        Connection con = connectionFactory.getDefaultConnection();
        PreparedStatement ps = con.prepareStatement("delete Category where CATEG_ACC  = ? ");
        int i = 1;
        ps.setInt(i++, categoryId);
        int ret = ps.executeUpdate();
        ps.close();
        return ret;
    }

    public Category getCategoryInfo(int categoryId) throws SQLException {
        Connection con = connectionFactory.getDefaultConnection();
        PreparedStatement ps = con.prepareStatement("Select CATEG_NAME, CATEG_DESCR From Category where CATEG_ACC = ?");
        ps.setInt(1, categoryId);
        ResultSet rs = ps.executeQuery();
        Category ret = null;
        if (rs.next()) {
            ret = new Category();
            ret.setId(categoryId);
            ret.setName(rs.getString("CATEG_NAME"));
            ret.setDescription(rs.getString("CATEG_DESCR"));

        }

        rs.close();
        ps.close();
        return ret;
    }

    public int modifyCategory(Category category) throws SQLException {
        Connection con = connectionFactory.getDefaultConnection();
        PreparedStatement ps = con.prepareStatement("update Category set CATEG_NAME = ?, CATEG_DESCR = ? where CATEG_ACC = ? ");
        int i = 1;
        ps.setString(i++, category.getName());
        ps.setString(i++, category.getDescription());
        ps.setInt(i++, category.getId());
        int ret = ps.executeUpdate();
        ps.close();
        return ret;
    }

}
