/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab7;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Olga
 */
public class sybaseConnectionFactory implements ConnectionFactory {

    @Override
    public Connection doConnection(String url, String login, String password) {
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Драйвер не найден");
            System.exit(0);
        }
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(
                    url,
                    login, password);
        } catch (Exception e) {
            System.out.println("Нет соеденения с базой");
            System.exit(0);
        }
        return conn;
    }

}
