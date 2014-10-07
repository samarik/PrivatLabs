/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab9;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author Olga
 */
public class MySQL {

    private Connection conn;
    private JTextArea result;
    private JTextArea requests;

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
                result.append("Кол-во измененных строк = " + rowCount+"\n");
                st.getMoreResults();
                continue;
            }
            if (rowCount == 0) {
               result.append("Нет измененных строк\n");
                st.getMoreResults();
                continue;
            }
            ResultSet rs = st.getResultSet();
            if (rs != null) {
                ResultSetMetaData m = rs.getMetaData();
                for (int i = 1; i <= m.getColumnCount(); i++) {
                    printFormatedString(m.getColumnLabel(i), m.getColumnDisplaySize(i));
                }
                result.append("\n");
                int countStr = 0;
                while (rs.next()) {
                    for (int i = 1; i <= m.getColumnCount(); i++) {
                        printFormatedString(rs.getString(i), m.getColumnDisplaySize(i));
                    }
                    result.append("\n");
                    countStr = ++countStr;
                }
                result.append("Количество обработанных строк " + countStr+"\n");
                st.getMoreResults();
                continue;
            }

            break;
        }

    }

    public void printFormatedString(String s, int size) {
        result.append(s);
        while (s.length() < size) {
            result.append(" ");
            size--;
        }
        result.append("\t" + "|");

    }
    public void myFrame(){
        JFrame frame = new JFrame("iSQL ");
        JPanel mainPanel = new JPanel();
        JPanel resultPanel = new JPanel();
        requests = new JTextArea(20, 50);
        result = new JTextArea(20, 50);
        JLabel labelRequest= new JLabel("SQL Statements");
        JLabel labelResult= new JLabel("Results");
        requests.addKeyListener(new MySQL.ExecuteKeyListener());
        //requests.setLineWrap(true);
        //requests.setWrapStyleWord(true);
        //requests.setEditable(true);
        JScrollPane scroller = new JScrollPane(requests);
        JScrollPane scrollerResult = new JScrollPane(result);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        //JButton executeButton = new JButton("Выполнить");
        //executeButton.addActionListener(new MainIsqlSwing.ExecuteButtonListener());
        mainPanel.add(labelRequest,BorderLayout.NORTH);
        mainPanel.add(scroller,BorderLayout.WEST);
        resultPanel.add(labelResult);
        resultPanel.add(scrollerResult);
        JSplitPane splitPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, mainPanel, resultPanel);
        splitPanel.setOneTouchExpandable(true);
        splitPanel.setDividerLocation(350);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(splitPanel,BorderLayout.CENTER);
        frame.setSize(600, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    public class ExecuteKeyListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_F5 ) {
                try {
                executeSQL(requests.getSelectedText());
            } catch (SQLException ex) {
                Logger.getLogger(MySQL.class.getName()).log(Level.SEVERE, null, ex);
            }
                
            
        } 
        }

        @Override
        public void keyReleased(KeyEvent e) {
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

}
}
