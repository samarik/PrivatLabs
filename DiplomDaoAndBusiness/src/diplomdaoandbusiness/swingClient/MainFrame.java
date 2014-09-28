/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diplomdaoandbusiness.swingClient;

//import com.sun.glass.events.KeyEvent;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyVetoException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class MainFrame extends JFrame {

    private JDesktopPane desktop = new JDesktopPane();
    private JMenuBar menuBar = new JMenuBar();
    private Map<String, JInternalFrame> frameMap = new HashMap<>();

    public MainFrame() {
        super("Витрина(администратор)");
        JMenu mainMenu = new JMenu("Главное");
        JMenuItem usersMenu = new JMenuItem("Производители");
        usersMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ref = "ManufacturerFrame";
                JInternalFrame f = frameMap.get(ref);
                if (f == null) {
                    f = new ManufacturerFrame();
                    desktop.add(f);
                    frameMap.put(ref, f);
                    f.addInternalFrameListener(new CommonInternalFrameListener(frameMap, ref));
                    try {
                        f.setMaximum(true);
                    } catch (PropertyVetoException ex) {
                        Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                f.setVisible(true);
            }
        });

        mainMenu.add(usersMenu);
        JMenuItem closeMenu = new JMenuItem("Закрыть");
        closeMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.this.dispose();
            }
        });

        mainMenu.add(closeMenu);
        menuBar.add(mainMenu);
        setJMenuBar(menuBar);
        setSize(new Dimension(800, 600));
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(desktop, BorderLayout.CENTER);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

}
