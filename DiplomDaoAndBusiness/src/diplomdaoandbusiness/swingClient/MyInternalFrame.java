/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diplomdaoandbusiness.swingClient;

import diplomdaoandbusiness.beans.entities.Manufacturer;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.beans.PropertyVetoException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;

public class MyInternalFrame extends JInternalFrame {

    private final JToolBar toolBar = new JToolBar(JToolBar.HORIZONTAL);
    private final ManufactureTableModels model = new ManufactureTableModels();
    private final JTable table = new JTable(model);

    public MyInternalFrame() {
        setTitle("Производители");
        setMaximizable(true);

        Container c = getContentPane();
        c.setLayout(new BorderLayout());

        c.add(toolBar, BorderLayout.NORTH);
        c.add(new JScrollPane(table));
        prepareToolBar();
        setDefaultData();
    }

    private void prepareToolBar() {
        toolBar.add(new JButton(new AbstractAction("Редактировать") {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                int idx = table.getSelectedRow();
                if(idx==-1){
                    //
                } else {
                    Manufacturer manufacturer = model.getManufacturer(idx);

                    EditDialog dlg = new EditDialog(MainFrame.mainFrame, manufacturer);
                    dlg.setLocationByPlatform(true);
                    dlg.setVisible(true);
                    
                    if(dlg.getState()==EditDialog.State.SAVED){
                        //change data
                    }
                }
                
            }
        }));
    }
    
    private void setDefaultData(){
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setId(1);
        manufacturer.setName("a1");
                
        Manufacturer manufacturer1 = new Manufacturer();
        manufacturer1.setId(1);
        manufacturer1.setName("a1");
        model.setList(Arrays.asList(manufacturer, manufacturer1));
    }

}
