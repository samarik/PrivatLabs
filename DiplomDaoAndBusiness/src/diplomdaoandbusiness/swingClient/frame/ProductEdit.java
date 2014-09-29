/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package diplomdaoandbusiness.swingClient.frame;


import diplomdaoandbusiness.beans.entities.Product;
import diplomdaoandbusiness.dao.ProductDao;
import diplomdaoandbusiness.services.IProductServices;
import diplomdaoandbusiness.swingClient.AppConfig;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author Olga
 */
public class ProductEdit extends JDialog {

    private static org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(ProductEdit.class);
    private JLabel nameLabel = new JLabel("Наименование", SwingConstants.LEFT);
    private JTextField nameField = new JTextField();
    private Product product = null;
    private boolean saved = false;
    ProductDao productDao = new ProductDao();

    public ProductEdit() {
        super(AppConfig.getMainFrame(), true);
        String caption = "Добавление нового товара";
        init(caption);

    }

    public ProductEdit(final Product prod) throws SQLException {
        super(AppConfig.getMainFrame(), true);
        product = productDao.getProductInfo(prod.getId());
//        AppConfig.doJob(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    
//                } catch (SQLException ex) {
//                    Logger.getLogger(ProductEdit.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        });
        String caption = "Редактирование товара [" + product.getId() + "] " + product.getName();
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

        if (product != null) {
            nameField.setText(product.getName());
            System.out.println("" + product);

        }
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton closeBtn = new JButton("Закрыть");
        closeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProductEdit.this.dispose();
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
                            Product p = product;
                            if (p == null) {
                                p = new Product();
                            }
                            p.setName(nameField.getText());
                            if (product == null) {
                                productDao.addProduct(p);
                            } else {
                                productDao.modifyProduct(p);
                            }
                            product = p;
                            saved = true;
                            JOptionPane.showMessageDialog(ProductEdit.this, "Сохранено", "Информация", JOptionPane.INFORMATION_MESSAGE);
                            ProductEdit.this.dispose();
                        } catch (Throwable ex) {
                            JOptionPane.showMessageDialog(ProductEdit.this, ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
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

    public Product getProduct() {
        return product;
    }

    public boolean isSaved() {
        return saved;
    }
}


