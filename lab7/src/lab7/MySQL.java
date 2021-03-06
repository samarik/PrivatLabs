/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab7;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author Olga
 */
public class MySQL {

    private Connection conn;

    public MySQL(Connection con) {
        conn = con;

    }

    public String getSQL() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите SQL: ");
        String sql = "";  // замечание нужно было использовать StringBuilder
        String read;
        int j = 1;
        do {
            System.out.print(j + "> ");
            read = scanner.nextLine();
            if (read.equals("go")) {
                break;
            }
            sql = sql + " " + read;
            j++;
        } while (!read.equals("exit"));
        return sql;
    }

    public void executeSQL(String sql) throws SQLException {
        Statement st = conn.createStatement();
        st.execute(sql);
        while (true) {
            int rowCount = st.getUpdateCount();
            if (rowCount > 0) {
                System.out.println("Кол-во измененных строк = " + rowCount);
                st.getMoreResults();
                continue;
            }
            if (rowCount == 0) {
                System.out.println("Нет измененных строк");
                st.getMoreResults();
                continue;
            }
            ResultSet rs = st.getResultSet();
            if (rs != null) {
                ResultSetMetaData m = rs.getMetaData();
                for (int i = 1; i <= m.getColumnCount(); i++) {
                    printFormatedString(m.getColumnLabel(i), m.getColumnDisplaySize(i));
                }
                System.out.println();
                int countStr = 0;
                while (rs.next()) {
                    for (int i = 1; i <= m.getColumnCount(); i++) {
                        printFormatedString(rs.getString(i), m.getColumnDisplaySize(i));
                    }
                    System.out.println();
                    countStr = ++countStr;
                }
                System.out.println("Количество обработанных строк " + countStr);
                st.getMoreResults();
                continue;
            }

            break;
        }

    }

    public void printFormatedString(String s, int size) {
        System.out.print(s);
        while (s.length() < size) {
            System.out.print(" ");
            size--;
        }
        System.out.print("\t" + "|");

    }
}
