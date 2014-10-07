/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab9;

import java.sql.Connection;

/**
 *
 * @author Olga
 */
public interface ConnectionFactory {

    public Connection doConnection(String url, String login, String password);
}
