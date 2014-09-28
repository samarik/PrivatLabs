/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diplomdaoandbusiness.swingClient;

//import com.sun.glass.events.KeyEvent;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyVetoException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;


public class MainFrame extends JFrame {
    
    public static final MainFrame mainFrame = new MainFrame();

    //private final ImageIcon icon = new ImageIcon(this.getClass().getResource("/resources/Frog-32.png"));
    private JInternalFrame internalFrame = null;
    private final JDesktopPane desktopPane = new JDesktopPane();

    public MainFrame() throws HeadlessException {
        super("Витрина_админимтратор");
        setResizable(false);
        //setIconImage(icon.getImage());
        setSize(new Dimension(800, 600));
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        
        Container c = getContentPane();
        c.setLayout(new BorderLayout());
        c.add(desktopPane);
        
        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(MainFrame.this, "Вы уверены?", "Внимание!!!", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE)) {
                    dispose();
                }
            }

        });
       
        creatMenu();
        
        //addWindow();
    }

    private void creatMenu() {

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menu = new JMenu("Файл");
        menuBar.add(menu);
        AbstractAction newWindowAction = new AbstractAction("Производители") {

            @Override
            public void actionPerformed(ActionEvent e) {
addWindow();
            }
        };
        //newWindowAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.MODIFIER_ALT));

        JMenuItem newWindow = new JMenuItem(newWindowAction);
        menu.add(newWindow);

        AbstractAction exitAction = new AbstractAction("Выход") {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        };
        //exitAction.putValue(Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.MODIFIER_ALT));
        JMenuItem exit = new JMenuItem(exitAction);
        menu.add(exitAction);

    }

    private void addWindow() {
        if (internalFrame == null) {
            internalFrame = new MyInternalFrame();
            internalFrame.setSize(new Dimension(400, 300));
            desktopPane.add(internalFrame);
        }
        internalFrame.setVisible(true);

        try {
            internalFrame.setSelected(true);
            internalFrame.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
