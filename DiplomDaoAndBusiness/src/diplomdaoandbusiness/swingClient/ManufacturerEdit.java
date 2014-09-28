/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diplomdaoandbusiness.swingClient;

import diplomdaoandbusiness.beans.entities.Manufacturer;
import diplomdaoandbusiness.services.IManufacturerServices;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author Novomlinov Aleksandr
 */
public class ManufacturerEdit extends JDialog {

    private static org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(ManufacturerEdit.class);
    private JLabel nameLabel = new JLabel("Наименование", SwingConstants.LEFT);
    private JTextField nameField = new JTextField();
    private IManufacturerServices manufacturerService = AppConfig.getServiceFactory().getManufacturerService();
    private Manufacturer manufacturer = null;
    private boolean saved = false;

    public ManufacturerEdit() {
        super(AppConfig.getMainFrame(), true);
        String caption = "Добавление нового предприятия";
        init(caption);

    }

    public ManufacturerEdit(Manufacturer manufacturer) {
        super(AppConfig.getMainFrame(), true);
        AppConfig.doJob(new Runnable() {
            @Override
            public void run() {
                //manufacturer = manufacturerService.getInfo(manufacturer.getId());
            }
        });
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
                                manufacturerService.add(m);
                            } else {
                                manufacturerService.modify(m);
                            }
                            manufacturer = m;
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

    public Manufacturer getUser() {
        return manufacturer;
    }

    public boolean isSaved() {
        return saved;
    }
}
