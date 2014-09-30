package diplomdaoandbusiness.swingClient.frame;

import diplomdaoandbusiness.beans.entities.Product;
import diplomdaoandbusiness.dao.ProductDao;
import diplomdaoandbusiness.swingClient.AppConfig;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
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
    private JLabel nameLabel1 = new JLabel("id производителя", SwingConstants.LEFT);
    private JTextField nameField1 = new JTextField();
    private JLabel nameLabel2 = new JLabel("id категории", SwingConstants.LEFT);
    private JTextField nameField2 = new JTextField();
    private JLabel nameLabel3 = new JLabel("Цена", SwingConstants.LEFT);
    private JTextField nameField3 = new JTextField();
    private JLabel nameLabel4 = new JLabel("Описание", SwingConstants.LEFT);
    private JTextField nameField4 = new JTextField();
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
        
        AppConfig.doJob(new Runnable() {
            @Override
            public void run() {
                try {
                    product = productDao.getProductInfo(prod.getId());
                } catch (SQLException ex) {
                    Logger.getLogger(ProductEdit.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        String caption = "Редактирование товара [" + prod.getId() + "] " + prod.getName();
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
        c.add(nameLabel1);
        c.add(nameField1);
        c.add(nameLabel2);
        c.add(nameField2);
        c.add(nameLabel3);
        c.add(nameField3);
        c.add(nameLabel4);
        c.add(nameField4);

        if (product != null) {
            nameField.setText(product.getName());
            nameField1.setText(String.valueOf(product.getIdManuf()));
            nameField2.setText(String.valueOf(product.getIdCateg()));
            nameField3.setText(String.valueOf(product.getPrice()));
            nameField4.setText(product.getDescription());
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
                            p.setIdManuf(Integer.parseInt(nameField1.getText()));
                            p.setIdCateg(Integer.parseInt(nameField2.getText()));
                            p.setPrice(new BigDecimal(nameField3.getText()));
                            p.setDescription(nameField4.getText());
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
