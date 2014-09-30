package diplomdaoandbusiness.swingClient.frame;

import diplomdaoandbusiness.beans.entities.Category;
import diplomdaoandbusiness.dao.CategoryDao;
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
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class CategoryEdit extends JDialog {

    private static org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(CategoryEdit.class);
    private JLabel nameLabel = new JLabel("Наименование", SwingConstants.LEFT);
    private JTextField nameField = new JTextField();
    private JLabel nameLabel1 = new JLabel("Описание", SwingConstants.LEFT);
    private JTextField nameField1 = new JTextField();
    private Category category = null;
    CategoryDao categoryDao = new CategoryDao();
    private boolean saved = false;

    public CategoryEdit() {
        super(AppConfig.getMainFrame(), true);
        String caption = "Добавление новой категории";
        init(caption);

    }

    public CategoryEdit(Category cat) throws SQLException {
        super(AppConfig.getMainFrame(), true);
        category = categoryDao.getCategoryInfo(cat.getId());
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
        String caption = "Редактирование категории [" + category.getId() + "] " + category.getName();
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
        if (category != null) {
            nameField.setText(category.getName());
            nameField1.setText(category.getDescription());
            System.out.println("" + category);

        }
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton closeBtn = new JButton("Закрыть");
        closeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CategoryEdit.this.dispose();
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
                            Category c = category;
                            if (c == null) {
                                c = new Category();
                            }
                            c.setName(nameField.getText());
                            c.setDescription(nameField1.getText());
                            if (category == null) {
                                categoryDao.addCategory(c);
                            } else {
                                categoryDao.modifyCategory(c);
                            }
                            category = c;
                            saved = true;
                            JOptionPane.showMessageDialog(CategoryEdit.this, "Сохранено", "Информация", JOptionPane.INFORMATION_MESSAGE);
                            CategoryEdit.this.dispose();
                        } catch (Throwable ex) {
                            JOptionPane.showMessageDialog(CategoryEdit.this, ex.getMessage(), "Ошибка", JOptionPane.ERROR_MESSAGE);
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

    public Category getCategory() {
        return category;
    }

    public boolean isSaved() {
        return saved;
    }
}
