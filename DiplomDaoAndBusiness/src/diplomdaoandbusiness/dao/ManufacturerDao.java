package diplomdaoandbusiness.dao;

import diplomdaoandbusiness.beans.entities.Manufacturer;
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
public class ManufacturerDao {

    private final IConnectionFactory connectionFactory = DaoConfig.getConnectionFactory();

    public List<Manufacturer> list() throws SQLException {
        Connection con = connectionFactory.getDefaultConnection();
        PreparedStatement ps = con.prepareStatement("select MANUF_OKPO, MANUF_NAME from Manufacturer");
        ResultSet rs = ps.executeQuery();
        List<Manufacturer> list = new ArrayList<>();
        while (rs.next()) {
            Manufacturer r = new Manufacturer();
            r.setId(rs.getInt("MANUF_OKPO"));
            r.setName(rs.getString("MANUF_NAME"));
            list.add(r);
        }
        rs.close();
        ps.close();
        return list;
    }

    public int getNextId() throws SQLException {
        Connection con = connectionFactory.getDefaultConnection();
        PreparedStatement ps = con.prepareStatement("select max(MANUF_OKPO) from Manufacturer");
        ResultSet rs = ps.executeQuery();
        int ret = 0;
        if (rs.next()) {
            ret = rs.getInt(1) + 1;
        }
        rs.close();
        return ret;
    }

    public void addManufacturer(Manufacturer manufacturer) throws SQLException {
        Connection con = connectionFactory.getDefaultConnection();
        PreparedStatement ps = con.prepareStatement("insert into Manufacturer(MANUF_OKPO , MANUF_NAME ) values(?,?)");
        int i = 1;
        manufacturer.setId(getNextId());
        ps.setInt(i++, manufacturer.getId());
        ps.setString(i++, manufacturer.getName());
        ps.execute();
        ps.close();
    }

    public Manufacturer getManufacturerInfo(int manufacturerId) throws SQLException {
        Connection con = connectionFactory.getDefaultConnection();
        PreparedStatement ps = con.prepareStatement("Select MANUF_NAME From Manufacturer where MANUF_OKPO = ?");
        ps.setInt(1, manufacturerId);
        ResultSet rs = ps.executeQuery();
        Manufacturer ret = null;
        if (rs.next()) {
            ret = new Manufacturer();
            ret.setId(manufacturerId);
            ret.setName(rs.getString("MANUF_NAME"));

        }

        rs.close();
        ps.close();
        return ret;
    }

    public int deleteManufacturer(int manufacturerId) throws SQLException {
        Connection con = connectionFactory.getDefaultConnection();
        PreparedStatement ps = con.prepareStatement("delete Manufacturer where MANUF_OKPO = ? ");
        int i = 1;
        ps.setInt(i++, manufacturerId);
        int ret = ps.executeUpdate();
        ps.close();
        return ret;
    }

    public int modifyManufacturer(Manufacturer manufacturer) throws SQLException {
        Connection con = connectionFactory.getDefaultConnection();
        PreparedStatement ps = con.prepareStatement("update Manufacturer set MANUF_NAME = ? where MANUF_OKPO = ? ");
        int i = 1;
        ps.setString(i++, manufacturer.getName());
        ps.setInt(i++, manufacturer.getId());
        int ret = ps.executeUpdate();
        ps.close();
        return ret;
    }

    public int checkName(String name, Integer... selfId) throws SQLException {
        String cmd = "select count(*) from Manufacture where MANUF_NAME = ?";
        if (selfId.length > 0) {
            cmd += " and MANUF_OKPO!=?";
        }
        Connection con = connectionFactory.getDefaultConnection();
        PreparedStatement ps = con.prepareStatement(cmd);
        ps.setString(1, name);
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
}
