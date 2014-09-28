/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diplomdaoandbusiness.business;

import diplomdaoandbusiness.beans.entities.Manufacturer;
import diplomdaoandbusiness.dao.ManufacturerDao;
import diplomdaoandbusiness.services.IManufacturerServices;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Olga
 */
public class ManufacturerJdbcComponent implements IManufacturerServices{
    
    //private IConnectionFactory connectionFactory = DaoConfig.getConnectionFactory();
    
    private ManufacturerDao manufacturerDao = new ManufacturerDao();
    

    @Override
    public List<Manufacturer> list() {
        Connection con = null;
        List<Manufacturer> ret = null;
        try {
            //con = connectionFactory.getDefaultConnection();
            ret = manufacturerDao.list(con);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally{
            try{
                con.close();
            } catch(Exception ex){}
        }
        return ret;
    }

    @Override
    public void add(Manufacturer user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int manufacturerId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
