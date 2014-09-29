package diplomdaoandbusiness.swingClient.frame;

import diplomdaoandbusiness.beans.entities.Product;
import diplomdaoandbusiness.dao.ProductDao;
import diplomdaoandbusiness.swingClient.AppConfig;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.event.InternalFrameEvent;
import org.apache.log4j.Logger;

/**
 *
 * @author Olga
 */
public class ProductFrame extends JInternalFrame {

    private static Logger LOGGER = Logger.getLogger(CategoryEdit.class);
    private ProductTableModels model = new ProductTableModels();
    private JTable table = new JTable(model);
    private JToolBar toolBar = new JToolBar();
    private ProductDao productDao = new ProductDao();

    public ProductFrame() {
        super("Управление справочником товаров", true, true, true, true);
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
                            model.setList(productDao.list());
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

                ProductEdit productEdit = new ProductEdit();
                productEdit.setVisible(true);
                if (productEdit.isSaved()) {
                    try {
                        model.addProduct(productEdit.getProduct());
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
                            Product product = model.getProduct(table.getSelectedRow());
                            ProductEdit productEdit = new ProductEdit(product);
                            productEdit.setVisible(true);
                            if (productEdit.isSaved()) {
                                model.setProduct(table.getSelectedRow(), productEdit.getProduct());
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
                                    Product product = model.getProduct(table.getSelectedRow());
                                    try {
                                        productDao.deleteProduct(product.getId());
                                        model.deleteProduct(table.getSelectedRow());
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
                        ProductFrame.this.fireInternalFrameEvent(InternalFrameEvent.INTERNAL_FRAME_CLOSING);
                        ProductFrame.this.dispose();
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
