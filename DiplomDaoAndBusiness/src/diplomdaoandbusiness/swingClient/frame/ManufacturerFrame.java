package diplomdaoandbusiness.swingClient.frame;

import diplomdaoandbusiness.beans.entities.Manufacturer;
import diplomdaoandbusiness.dao.ManufacturerDao;
import diplomdaoandbusiness.services.IManufacturerServices;
import diplomdaoandbusiness.swingClient.AppConfig;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.event.InternalFrameEvent;
import org.apache.log4j.Logger;

public class ManufacturerFrame extends JInternalFrame {

    private static Logger LOGGER = Logger.getLogger(ManufacturerEdit.class);
    private ManufactureTableModels model = new ManufactureTableModels();
    private JTable table = new JTable(model);
    private JToolBar toolBar = new JToolBar();
    private ManufacturerDao manufacturerDao = new ManufacturerDao();
    
    public ManufacturerFrame() {
        super("Управление справочником производители", true, true, true, true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(new Dimension(400, 300));
        setLayout(new BorderLayout());
        JButton refreshButton = new JButton("Обновить");
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AppConfig.doJob(new Runnable() {
                    @Override
                    public void run() {
                        try {
//                            model.setManufacturerList(manufacturerDao.list(null));
                        } catch (Throwable t) {
                            JOptionPane.showMessageDialog(AppConfig.getMainFrame(), t.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
                            LOGGER.error(null, t);
                        }
                    }
                });
            }
        });
        JButton addButton = new JButton("Добавить");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ManufacturerEdit manufacturerEdit = new ManufacturerEdit();
                manufacturerEdit.setVisible(true);
                if (manufacturerEdit.isSaved()) {
                    try {
                        model.addManufacturer(manufacturerEdit.getManufacturer());
                    } catch (Throwable t) {
                        JOptionPane.showMessageDialog(AppConfig.getMainFrame(), t.getMessage(), "Ошибка", JOptionPane.ERROR);
                        LOGGER.error(null, t);
                    }
                }
            }
        });
        JButton editButton = new JButton("Редактировать");

        editButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (table.getSelectedRow() < 0) {
                            JOptionPane.showMessageDialog(AppConfig.getMainFrame(), "Не выбрана запись", "Внимание!!!", JOptionPane.WARNING_MESSAGE);
                        }
                        try {
                            Manufacturer manufacturer = model.getManufacturer(table.getSelectedRow());

                            ManufacturerEdit userEdit = new ManufacturerEdit(manufacturer);
                            userEdit.setVisible(true);
                            if (userEdit.isSaved()) {
                                model.setManufacturer(table.getSelectedRow(), userEdit.getManufacturer());
                            }
                        } catch (Throwable t) {
                            JOptionPane.showMessageDialog(AppConfig.getMainFrame(), t.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
                            LOGGER.error(null, t);
                        }
                    }
                });

        JButton delButton = new JButton("Удалить");

        delButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (table.getSelectedRow() < 0) {
                            JOptionPane.showMessageDialog(AppConfig.getMainFrame(), "Не выбрана запись", "Внимание!!!", JOptionPane.WARNING_MESSAGE);
                        } else if (JOptionPane.showConfirmDialog(AppConfig.getMainFrame(), "Вы действительно хотите удалить запись?", "Внимание!!!",
                                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

                            AppConfig.doJob(new Runnable() {
                                @Override
                                public void run() {
                                    Manufacturer manufacturer = model.getManufacturer(table.getSelectedRow());
                                    try {
//                                        manufacturerService.delete(manufacturer.getId());
                                        //model.deleteManufacturer(table.getSelectedRow());
                                    } catch (Throwable t) {
                                        JOptionPane.showMessageDialog(AppConfig.getMainFrame(), t.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
                                        LOGGER.error(null, t);
                                    }
                                }
                            });
                        }
                    }
                });
        JButton closeButton = new JButton("Закрыть");

        closeButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ManufacturerFrame.this.fireInternalFrameEvent(InternalFrameEvent.INTERNAL_FRAME_CLOSING);
                        ManufacturerFrame.this.dispose();
                    }
                });
        toolBar.add(refreshButton);

        toolBar.addSeparator();

        toolBar.add(addButton);

        toolBar.add(editButton);

        toolBar.addSeparator();

        toolBar.add(delButton);

        toolBar.addSeparator();

        toolBar.add(closeButton);

        getContentPane()
                .add(toolBar, BorderLayout.NORTH);
        getContentPane()
                .add(new JScrollPane(table));

    }
}
