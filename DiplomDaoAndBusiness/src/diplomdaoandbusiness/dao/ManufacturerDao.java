/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diplomdaoandbusiness.dao;

import diplomdaoandbusiness.beans.entities.Manufacturer;
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
public class ManufacturerDao {
    
    public List<Manufacturer> list(Connection con) throws SQLException{
        PreparedStatement ps = con.prepareStatement("select MANUF_OKPO, MANUF_NAME from Manufacturer");
        ResultSet rs = ps.executeQuery();
        List<Manufacturer> list = new ArrayList<>();
        while(rs.next()){
            Manufacturer r = new Manufacturer();
            r.setId(rs.getInt("MANUF_OKPO"));
            r.setName(rs.getString("MANUF_NAME"));
            list.add(r);
        }
        rs.close();
        ps.close();
        return list;
    }
}
