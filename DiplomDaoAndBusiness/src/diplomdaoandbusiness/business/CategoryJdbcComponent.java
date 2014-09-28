package diplomdaoandbusiness.business;

import diplomdaoandbusiness.beans.entities.Category;
import diplomdaoandbusiness.dao.CategoryDao;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Olga
 */
public class CategoryJdbcComponent implements ICategoryServices {

    //private IConnectionFactory connectionFactory = DaoConfig.getConnectionFactory();
    private CategoryDao categoryDao = new CategoryDao();

    @Override
    public List<Category> list() {
        Connection con = null;
        List<Category> ret = null;
        try {
            con = connectionFactory.getDefaultConnection();
            ret = categoryDao.list(con);
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
    public List<Integer> listCategoryChild(int categoryId) {
        Connection con = null;
        List<Integer> list = null;
        try {
            con = connectionFactory.getDefaultConnection();
            list = categoryDao.selectCategoryChild(con, categoryId);
        } catch (SQLException ex) {
            try {
                con.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(CategoryJdbcComponent.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new RuntimeException(ex);
        } finally {
            try {
                con.close();
            } catch (Exception ex) {
            }
        }
        return list;
    }

    @Override
    public void add(Category category) {
        Connection con = null;
        try {
            con = connectionFactory.getDefaultConnection();
            con.setAutoCommit(false);
            if (categoryDao.checkName(con, category.getName()) > 0) {
                throw new LogicException("Категория с именем " + category.getName() + " уже существует");
            }
            category.setId(categoryDao.getNextId(con));
            categoryDao.addCategory(con, category);
            con.commit();
        } catch (SQLException ex) {
            try {
                con.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(CategoryJdbcComponent.class.getName()).log(Level.SEVERE, null, ex1);
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
    public void modify(Category category) {
        Connection con = null;
        try {
            con = connectionFactory.getDefaultConnection();
            con.setAutoCommit(false);
            if (categoryDao.checkName(con, category.getName(), category.getId()) > 0) {
                throw new LogicException("Категория с именем " + category.getName() + " уже существует");
            }
            if (categoryDao.modifyCategory(con, category) == 0) {
                throw new LogicException("Категории с кодом " + category.getId() + " не существует");
            }
            con.commit();
        } catch (SQLException ex) {
            try {
                con.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(CategoryJdbcComponent.class.getName()).log(Level.SEVERE, null, ex1);
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
