package diplomdaoandbusiness.swingClient.frame;

import diplomdaoandbusiness.beans.entities.Manufacturer;
import diplomdaoandbusiness.dao.ManufacturerDao;
import diplomdaoandbusiness.swingClient.AppConfig;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class ManufacturerEdit extends JDialog {

    private static org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(ManufacturerEdit.class);
    private JLabel nameLabel = new JLabel("Наименование", SwingConstants.LEFT);
    private JTextField nameField = new JTextField();
    private Manufacturer manufacturer = null;
    private boolean saved = false;
    ManufacturerDao manufacturerDao = new ManufacturerDao();

    public ManufacturerEdit() {
        super(AppConfig.getMainFrame(), true);
        String caption = "Добавление нового предприятия";
        init(caption);

    }

   public ManufacturerEdit(final Manufacturer manuf) throws SQLException {
        super(AppConfig.getMainFrame(), true);
        manufacturer = manufacturerDao.getManufacturerInfo(manuf.getId());
        //System.out.println(manufacturer);
        //AppConfig.doJob(new Runnable() {
           // @Override
          // public void run() {
          //      try {
           //        manufacturer = manufacturerDao.getManufacturerInfo(manuf.getId()); 
           //     } catch (SQLException ex) {
           //         Logger.getLogger(ProductEdit.class.getName()).log(Level.SEVERE, null, ex);
            //    }
          // }
        //});
        String caption = "Редактирование предприятия [" + manufacturer.getId() + "] " + manufacturer.getName();
        init(caption);
    }

    private void init(String title) {
        setTitle(title);
        setSize(new Dimension(400, 300));
        setContent();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(AppConfig.getMainFrame());
    }

    private void setContent() {
        Container c = getContentPane();
        c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
        c.add(nameLabel);
        c.add(nameField);

        if (manufacturer != null) {
            nameField.setText(manufacturer.getName());
            System.out.println("" + manufacturer);

        }
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton closeBtn = new JButton("Закрыть");
        closeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManufacturerEdit.this.dispose();
            }
        });
        JButton saveBtn = new JButton("Сохранить");
        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AppConfig.doJob(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Manufacturer m = manufacturer;
                            if (m == null) {
                                m = new Manufacturer();
                            }
                            m.setName(nameField.getText());
                            if (manufacturer == null) {
                            manufacturerDao.addManufacturer(m);
                            }else {
                            manufacturerDao.modifyManufacturer(m);   
                            }
                            manufacturer = m;
                            System.out.println(m);
                            saved = true;
                            JOptionPane.showMessageDialog(ManufacturerEdit.this, "Сохранено", "Информация", JOptionPane.INFORMATION_MESSAGE);
                            ManufacturerEdit.this.dispose();
                        } catch (Throwable ex) {
                            JOptionPane.showMessageDialog(ManufacturerEdit.this, ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
                            LOGGER.error(null, ex);
                        }
                    }
                });

            }
        });
        buttonPanel.add(closeBtn);
        buttonPanel.add(saveBtn);
        c.add(buttonPanel);

    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public boolean isSaved() {
        return saved;
    }
}
