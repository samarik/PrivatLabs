/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package diplomdaoandbusiness.swingClient.frame;

import diplomdaoandbusiness.beans.entities.Category;
import diplomdaoandbusiness.services.ICategoryServices;
import diplomdaoandbusiness.swingClient.AppConfig;
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
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class CategoryEdit extends JDialog {

    private static org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(CategoryEdit.class);
    private JLabel nameLabel = new JLabel("Наименование", SwingConstants.LEFT);
    private JTextField nameField = new JTextField();
    private Category category = null;
    private boolean saved = false;

    public CategoryEdit() {
        super(AppConfig.getMainFrame(), true);
        String caption = "Добавление новой категории";
        init(caption);

    }

    public CategoryEdit(Category category) {
        super(AppConfig.getMainFrame(), true);
        AppConfig.doJob(new Runnable() {
            @Override
            public void run() {
                //manufacturer = manufacturerService.getInfo(manufacturer.getId());
            }
        });
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

        if (category != null) {
            nameField.setText(category.getName());
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
                            if (category == null) {
//                                categoryService.add(c);
                            } else {
//                                categoryService.modify(c);
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
